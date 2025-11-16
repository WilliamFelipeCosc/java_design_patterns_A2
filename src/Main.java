/**
 * Classe principal para demonstracao das 4 questoes.
 * 
 * Executa exemplos de uso de cada questao com os Design Patterns implementados.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("AVALIACAO A2 - DESIGN PATTERNS E PRINCIPIOS SOLID");
        System.out.println("=".repeat(80));
        System.out.println();
        
        // Executa cada questao
        executarQuestao1();
        executarQuestao2();
        executarQuestao3();
        executarQuestao4();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("EXECUCAO CONCLUIDA");
        System.out.println("=".repeat(80));
    }
    
    /**
     * QUESTAO 1: Strategy Pattern - Sistema de Analise de Risco.
     */
    private static void executarQuestao1() {
        System.out.println("\n" + "#".repeat(80));
        System.out.println("QUESTAO 1 - STRATEGY PATTERN: ANALISE DE RISCO FINANCEIRO");
        System.out.println("#".repeat(80));
        System.out.println();
        System.out.println("Pattern: Strategy");
        System.out.println("Principios SOLID: SRP, OCP, DIP");
        System.out.println("Justificativa: Algoritmos intercambiaveis em runtime sem acoplamento");
        System.out.println();
        
        // Cria contexto com parametros financeiros
        questao1.RiskContext context = new questao1.RiskContext();
        context.addPortfolioValue(1000000.0);
        context.addPortfolioValue(500000.0);
        context.addPortfolioValue(750000.0);
        context.setConfidenceLevel(0.95);
        context.setTimeHorizon(1);
        context.setVolatility(0.15);
        context.setExpectedReturn(0.08);
        
        // Cria o analisador
        questao1.RiskAnalyzer analyzer = new questao1.RiskAnalyzer(context);
        
        // Testa Value at Risk
        analyzer.setAlgorithm(new questao1.ValueAtRiskAlgorithm());
        analyzer.analyze();
        
        // Troca para Expected Shortfall em runtime
        analyzer.setAlgorithm(new questao1.ExpectedShortfallAlgorithm());
        analyzer.analyze();
        
        // Troca para Stress Testing em runtime
        analyzer.setAlgorithm(new questao1.StressTestingAlgorithm());
        analyzer.analyze();
    }
    
    /**
     * QUESTAO 2: Adapter Pattern - Integracao com Sistema Legado.
     */
    private static void executarQuestao2() {
        System.out.println("\n" + "#".repeat(80));
        System.out.println("QUESTAO 2 - ADAPTER PATTERN: INTEGRACAO BANCARIA LEGADA");
        System.out.println("#".repeat(80));
        System.out.println();
        System.out.println("Pattern: Adapter (Bidirecional)");
        System.out.println("Principios SOLID: SRP, ISP, DIP");
        System.out.println("Justificativa: Converte interface moderna para legada sem modificar legado");
        System.out.println();
        
        // Cria sistema legado
        questao2.SistemaBancarioLegado sistemaLegado = new questao2.SistemaBancarioLegado();
        
        // Cria adaptador
        questao2.AdaptadorSistemaBancario adaptador = new questao2.AdaptadorSistemaBancario(sistemaLegado);
        
        // Cliente usa interface moderna
        System.out.println("=== Teste 1: Transacao em USD ===");
        questao2.RespostaTransacao resposta1 = adaptador.autorizar("4111111111111111", 150.00, "USD");
        System.out.println("Resultado: " + resposta1);
        
        System.out.println("\n=== Teste 2: Transacao em BRL ===");
        questao2.RespostaTransacao resposta2 = adaptador.autorizar("5555555555554444", 250.50, "BRL");
        System.out.println("Resultado: " + resposta2);
        
        System.out.println("\n=== Teste 3: Transacao em EUR ===");
        questao2.RespostaTransacao resposta3 = adaptador.autorizar("378282246310005", 500.00, "EUR");
        System.out.println("Resultado: " + resposta3);
    }
    
    /**
     * QUESTAO 3: State Pattern - Controle de Usina Nuclear.
     */
    private static void executarQuestao3() {
        System.out.println("\n" + "#".repeat(80));
        System.out.println("QUESTAO 3 - STATE PATTERN: CONTROLE DE USINA NUCLEAR");
        System.out.println("#".repeat(80));
        System.out.println();
        System.out.println("Pattern: State");
        System.out.println("Principios SOLID: SRP, OCP, LSP");
        System.out.println("Justificativa: Encapsula transicoes complexas e valida condicoes");
        System.out.println();
        
        questao3.UsinaNuclear usina = new questao3.UsinaNuclear();
        questao3.ParametrosUsina parametros = usina.getParametros();
        
        usina.exibirStatus();
        
        // Cenario 1: Ligar usina
        System.out.println("=== Cenario 1: Iniciando operacao ===");
        parametros.setTemperatura(80.0);
        usina.transicaoParaOperacaoNormal();
        usina.exibirStatus();
        
        // Cenario 2: Temperatura elevada - Alerta Amarelo
        System.out.println("\n=== Cenario 2: Temperatura elevada ===");
        parametros.setTemperatura(320.0);
        usina.transicaoParaAlertaAmarelo();
        usina.exibirStatus();
        
        // Cenario 3: Temperatura critica - Alerta Vermelho
        System.out.println("\n=== Cenario 3: Temperatura critica ===");
        parametros.setTemperatura(420.0);
        usina.transicaoParaAlertaVermelho();
        usina.exibirStatus();
        
        // Cenario 4: Falha do sistema de resfriamento - Emergencia
        System.out.println("\n=== Cenario 4: Falha no resfriamento ===");
        parametros.setSistemaResfriamentoAtivo(false);
        usina.transicaoParaEmergencia();
        usina.exibirStatus();
        
        // Cenario 5: Desligamento de emergencia
        System.out.println("\n=== Cenario 5: Desligamento de emergencia ===");
        usina.desligar();
        usina.exibirStatus();
        
        // Cenario 6: Modo manutencao
        System.out.println("\n=== Cenario 6: Ativando modo manutencao ===");
        usina.ativarManutencao();
        usina.exibirStatus();
    }
    
    /**
     * QUESTAO 4: Chain of Responsibility + Circuit Breaker - Validacao de NF-e.
     */
    private static void executarQuestao4() {
        System.out.println("\n" + "#".repeat(80));
        System.out.println("QUESTAO 4 - CHAIN OF RESPONSIBILITY: VALIDACAO DE NF-e");
        System.out.println("#".repeat(80));
        System.out.println();
        System.out.println("Pattern: Chain of Responsibility + Circuit Breaker");
        System.out.println("Principios SOLID: SRP, OCP, DIP");
        System.out.println("Justificativa: Validacoes sequenciais com logica condicional e rollback");
        System.out.println();
        
        // Limpa banco de dados simulado
        questao4.ValidadorBancoDados.limparBanco();
        
        // Monta a cadeia de validacao
        questao4.CadeiaValidacao cadeia = new questao4.CadeiaValidacao();
        cadeia.adicionarValidador(new questao4.ValidadorSchemaXML());
        cadeia.adicionarValidador(new questao4.ValidadorCertificadoDigital());
        cadeia.adicionarValidador(new questao4.ValidadorRegrasFiscais());
        cadeia.adicionarValidador(new questao4.ValidadorBancoDados());
        cadeia.adicionarValidador(new questao4.ValidadorServicoSEFAZ());
        
        // Teste 1: Documento valido
        System.out.println("=== Teste 1: Documento Valido ===");
        questao4.DocumentoFiscal doc1 = new questao4.DocumentoFiscal(
            "NFe12345678901234567890123456789012345678901234",
            "<?xml version=\"1.0\"?><NFe><infNFe>...</infNFe></NFe>"
        );
        doc1.setCertificadoDigital("CERT-12345-VALIDO");
        doc1.setValorTotal(1000.00);
        doc1.setValorImpostos(250.00);
        
        questao4.ResultadoValidacao resultado1 = cadeia.executar(doc1);
        System.out.println("Resultado Final: " + resultado1);
        
        // Teste 2: Documento com certificado expirado
        System.out.println("\n\n=== Teste 2: Certificado Expirado ===");
        questao4.DocumentoFiscal doc2 = new questao4.DocumentoFiscal(
            "NFe98765432109876543210987654321098765432109876",
            "<?xml version=\"1.0\"?><NFe><infNFe>...</infNFe></NFe>"
        );
        doc2.setCertificadoDigital("CERT-EXPIRADO-99999");
        doc2.setValorTotal(500.00);
        doc2.setValorImpostos(100.00);
        
        questao4.ResultadoValidacao resultado2 = cadeia.executar(doc2);
        System.out.println("Resultado Final: " + resultado2);
        
        // Teste 3: Documento duplicado (testa rollback)
        System.out.println("\n\n=== Teste 3: Documento Duplicado (Rollback) ===");
        questao4.ResultadoValidacao resultado3 = cadeia.executar(doc1);
        System.out.println("Resultado Final: " + resultado3);
    }
}
