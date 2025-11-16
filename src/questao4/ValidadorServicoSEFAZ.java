package questao4;

import java.util.Random;

/**
 * Validador 5: Valida com servico SEFAZ (consulta online).
 * 
 * Design Pattern: Chain of Responsibility (ConcreteHandler)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por validar com SEFAZ
 * 
 * Justificativa: Validacao final com servico externo.
 * Executa apenas se todas as validacoes anteriores passarem (validacao condicional).
 */
public class ValidadorServicoSEFAZ extends ValidadorAbstrato {
    
    private Random random;
    
    public ValidadorServicoSEFAZ() {
        super("Validador Servico SEFAZ", 6000);
        this.random = new Random();
    }
    
    @Override
    protected ResultadoValidacao executarValidacao(DocumentoFiscal documento) throws InterruptedException {
        System.out.println("[SEFAZ] Conectando ao servico da SEFAZ...");
        
        // Simula latencia de rede
        Thread.sleep(800);
        
        System.out.println("[SEFAZ] Enviando NF-e para autorizacao...");
        System.out.println("[SEFAZ] Numero NF-e: " + documento.getNumeroNFe());
        
        // Simula processamento no servidor
        Thread.sleep(1500);
        
        // Simula resposta (80% de chance de aprovacao)
        boolean autorizada = random.nextInt(100) < 80;
        
        if (!autorizada) {
            return ResultadoValidacao.falha(nome, 
                "SEFAZ rejeitou o documento - Codigo: 999 - Erro no processamento");
        }
        
        String protocolo = "PROT" + System.currentTimeMillis();
        
        System.out.println("[SEFAZ] NF-e AUTORIZADA pela SEFAZ");
        System.out.println("[SEFAZ] Protocolo de autorizacao: " + protocolo);
        System.out.println("[SEFAZ] Situacao: Autorizada para uso");
        
        return ResultadoValidacao.sucesso(nome);
    }
}
