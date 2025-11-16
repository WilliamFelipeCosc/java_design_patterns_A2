package questao4;

/**
 * Interface para validadores na cadeia de responsabilidade.
 * 
 * Design Pattern: Chain of Responsibility
 * Principio SOLID aplicado: SRP, OCP, ISP
 * - SRP: Cada validador tem uma unica responsabilidade
 * - OCP: Aberto para extensao (novos validadores) fechado para modificacao
 * - ISP: Interface focada e coesa
 * 
 * Justificativa: Define o contrato para validadores que serao
 * encadeados. Permite adicionar novos validadores sem modificar existentes.
 */
public interface Validador {
    /**
     * Valida o documento e passa para o proximo validador se necessario.
     * 
     * @param documento Documento a ser validado
     * @return Resultado da validacao
     * @throws ValidacaoException se a validacao falhar
     * @throws TimeoutException se exceder o tempo limite
     */
    ResultadoValidacao validar(DocumentoFiscal documento) throws ValidacaoException, TimeoutException;
    
    /**
     * Define o proximo validador na cadeia.
     * 
     * @param proximo Proximo validador
     */
    void setProximo(Validador proximo);
    
    /**
     * Retorna o nome do validador.
     * 
     * @return Nome do validador
     */
    String getNome();
    
    /**
     * Retorna o tempo limite em milissegundos.
     * 
     * @return Timeout em ms
     */
    long getTimeout();
    
    /**
     * Executa rollback de alteracoes se suportado.
     * 
     * @param documento Documento para fazer rollback
     */
    void rollback(DocumentoFiscal documento);
}
