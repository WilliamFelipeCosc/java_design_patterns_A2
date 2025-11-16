package questao4;

import java.util.HashSet;
import java.util.Set;

/**
 * Validador 4: Valida duplicidade no banco de dados.
 * 
 * Design Pattern: Chain of Responsibility (ConcreteHandler)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por validar duplicidade
 * 
 * Justificativa: Verifica se documento ja foi processado.
 * Faz rollback se validacoes subsequentes falharem.
 */
public class ValidadorBancoDados extends ValidadorAbstrato {
    
    // Simula banco de dados de documentos processados
    private static Set<String> documentosProcessados = new HashSet<>();
    
    public ValidadorBancoDados() {
        super("Validador Banco de Dados", 4000);
    }
    
    @Override
    protected ResultadoValidacao executarValidacao(DocumentoFiscal documento) throws InterruptedException {
        System.out.println("[Banco de Dados] Verificando duplicidade...");
        
        // Simula consulta ao banco
        Thread.sleep(600);
        
        String numeroNFe = documento.getNumeroNFe();
        
        if (documentosProcessados.contains(numeroNFe)) {
            return ResultadoValidacao.falha(nome, 
                "Documento duplicado: NF-e " + numeroNFe + " ja foi processada");
        }
        
        System.out.println("[Banco de Dados] Nenhuma duplicidade encontrada");
        System.out.println("[Banco de Dados] Inserindo documento no banco...");
        
        // Simula insercao
        Thread.sleep(400);
        
        documentosProcessados.add(numeroNFe);
        documento.setInseridoNoBanco(true);
        
        System.out.println("[Banco de Dados] Documento inserido com sucesso");
        System.out.println("[Banco de Dados] Total de documentos: " + documentosProcessados.size());
        
        return ResultadoValidacao.sucesso(nome);
    }
    
    @Override
    public void rollback(DocumentoFiscal documento) {
        if (documento.isInseridoNoBanco()) {
            System.out.println("[Banco de Dados] Executando ROLLBACK...");
            documentosProcessados.remove(documento.getNumeroNFe());
            documento.setInseridoNoBanco(false);
            System.out.println("[Banco de Dados] Insercao revertida com sucesso");
        }
    }
    
    /**
     * Metodo utilitario para limpar o banco simulado (para testes).
     */
    public static void limparBanco() {
        documentosProcessados.clear();
    }
}
