package questao4;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerenciador da cadeia de validacao com Circuit Breaker.
 * 
 * Design Pattern: Chain of Responsibility + Circuit Breaker
 * Principio SOLID aplicado: SRP, OCP
 * - SRP: Responsavel por coordenar a cadeia de validacao
 * - OCP: Pode adicionar novos validadores sem modificacao
 * 
 * Justificativa: Coordena a execucao da cadeia, implementa circuit breaker
 * que interrompe apos 3 falhas, e gerencia rollback de validadores.
 * 
 * Circuit Breaker: Interrompe a cadeia apos 3 validacoes falharem,
 * evitando processamento desnecessario e desperdicio de recursos.
 */
public class CadeiaValidacao {
    
    private Validador primeiroValidador;
    private List<Validador> validadores;
    private int maxFalhas;
    private int falhasAtual;
    
    public CadeiaValidacao() {
        this.validadores = new ArrayList<>();
        this.maxFalhas = 3;
        this.falhasAtual = 0;
    }
    
    /**
     * Adiciona um validador ao final da cadeia.
     * 
     * @param validador Validador a adicionar
     */
    public void adicionarValidador(Validador validador) {
        validadores.add(validador);
        
        if (primeiroValidador == null) {
            primeiroValidador = validador;
        } else {
            // Conecta o ultimo validador ao novo
            Validador ultimo = validadores.get(validadores.size() - 2);
            ultimo.setProximo(validador);
        }
    }
    
    /**
     * Executa a cadeia de validacao com circuit breaker.
     * 
     * @param documento Documento a validar
     * @return ResultadoValidacao final
     */
    public ResultadoValidacao executar(DocumentoFiscal documento) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("INICIANDO CADEIA DE VALIDACAO DE NF-e");
        System.out.println("=".repeat(70));
        System.out.println("Numero NF-e: " + documento.getNumeroNFe());
        System.out.println("Total de validadores: " + validadores.size());
        System.out.println("Circuit Breaker: Maximo de " + maxFalhas + " falhas permitidas");
        System.out.println("=".repeat(70) + "\n");
        
        falhasAtual = 0;
        List<Validador> validadoresExecutados = new ArrayList<>();
        
        try {
            // Executa a cadeia
            Validador validadorAtual = primeiroValidador;
            
            while (validadorAtual != null) {
                validadoresExecutados.add(validadorAtual);
                
                try {
                    ResultadoValidacao resultado = validadorAtual.validar(documento);
                    
                    if (!resultado.isValido()) {
                        falhasAtual++;
                        System.out.println("\n[CIRCUIT BREAKER] Falha " + falhasAtual + " de " + maxFalhas);
                        
                        if (falhasAtual >= maxFalhas) {
                            System.out.println("[CIRCUIT BREAKER] LIMITE DE FALHAS ATINGIDO!");
                            System.out.println("[CIRCUIT BREAKER] Interrompendo cadeia de validacao");
                            
                            // Executa rollback dos validadores executados
                            executarRollback(validadoresExecutados, documento);
                            
                            return ResultadoValidacao.falha("Circuit Breaker", 
                                "Cadeia interrompida apos " + maxFalhas + " falhas");
                        }
                    }
                    
                    // Avanca para o proximo validador
                    validadorAtual = getProximoValidador(validadorAtual);
                    
                } catch (ValidacaoException e) {
                    falhasAtual++;
                    System.out.println("\n[CIRCUIT BREAKER] Falha " + falhasAtual + " de " + maxFalhas);
                    System.out.println("[CIRCUIT BREAKER] Motivo: " + e.getMessage());
                    
                    if (falhasAtual >= maxFalhas) {
                        System.out.println("[CIRCUIT BREAKER] LIMITE DE FALHAS ATINGIDO!");
                        System.out.println("[CIRCUIT BREAKER] Interrompendo cadeia de validacao");
                        
                        // Executa rollback
                        executarRollback(validadoresExecutados, documento);
                        
                        return ResultadoValidacao.falha("Circuit Breaker", 
                            "Cadeia interrompida: " + e.getMessage());
                    }
                    
                    // Continua para proximo validador se nao atingiu limite
                    validadorAtual = getProximoValidador(validadorAtual);
                    
                } catch (TimeoutException e) {
                    System.out.println("\n[TIMEOUT] " + e.getMessage());
                    
                    // Timeout tambem conta como falha
                    falhasAtual++;
                    
                    if (falhasAtual >= maxFalhas) {
                        executarRollback(validadoresExecutados, documento);
                        return ResultadoValidacao.falha("Circuit Breaker", "Timeout: " + e.getMessage());
                    }
                    
                    validadorAtual = getProximoValidador(validadorAtual);
                }
            }
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("VALIDACAO CONCLUIDA COM SUCESSO");
            System.out.println("=".repeat(70));
            System.out.println("Total de validadores executados: " + validadoresExecutados.size());
            System.out.println("Total de falhas: " + falhasAtual);
            System.out.println("=".repeat(70) + "\n");
            
            return ResultadoValidacao.sucesso("Cadeia de Validacao");
            
        } catch (Exception e) {
            System.out.println("\n[ERRO] Erro inesperado na cadeia: " + e.getMessage());
            executarRollback(validadoresExecutados, documento);
            return ResultadoValidacao.falha("Cadeia de Validacao", "Erro inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Executa rollback em todos os validadores que foram executados.
     * 
     * @param validadoresExecutados Lista de validadores que foram executados
     * @param documento Documento para rollback
     */
    private void executarRollback(List<Validador> validadoresExecutados, DocumentoFiscal documento) {
        if (validadoresExecutados.isEmpty()) {
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EXECUTANDO ROLLBACK");
        System.out.println("=".repeat(70));
        
        // Executa rollback na ordem reversa
        for (int i = validadoresExecutados.size() - 1; i >= 0; i--) {
            Validador validador = validadoresExecutados.get(i);
            try {
                validador.rollback(documento);
            } catch (Exception e) {
                System.out.println("[ERRO ROLLBACK] Falha ao fazer rollback de " + 
                                 validador.getNome() + ": " + e.getMessage());
            }
        }
        
        System.out.println("=".repeat(70) + "\n");
    }
    
    /**
     * Retorna o proximo validador na cadeia.
     * 
     * @param atual Validador atual
     * @return Proximo validador ou null
     */
    private Validador getProximoValidador(Validador atual) {
        int index = validadores.indexOf(atual);
        if (index >= 0 && index < validadores.size() - 1) {
            return validadores.get(index + 1);
        }
        return null;
    }
    
    /**
     * Define o numero maximo de falhas permitidas antes do circuit breaker.
     * 
     * @param maxFalhas Numero maximo de falhas
     */
    public void setMaxFalhas(int maxFalhas) {
        this.maxFalhas = maxFalhas;
    }
    
    /**
     * Retorna o numero de validadores na cadeia.
     * 
     * @return Total de validadores
     */
    public int getTotalValidadores() {
        return validadores.size();
    }
}
