package questao2;

/**
 * Interface moderna para processamento de transacoes.
 * 
 * Design Pattern: Adapter (Target Interface)
 * Principio SOLID aplicado: ISP (Interface Segregation Principle)
 * - Interface focada e coesa, com metodos especificos
 * 
 * Justificativa: Define o contrato que o sistema moderno espera,
 * com assinaturas claras e tipos atualizados.
 */
public interface ProcessadorTransacoes {
    /**
     * Autoriza uma transacao com cartao.
     * 
     * @param cartao Numero do cartao
     * @param valor Valor da transacao
     * @param moeda Codigo da moeda (USD, EUR, BRL)
     * @return Resposta da autorizacao
     */
    RespostaTransacao autorizar(String cartao, double valor, String moeda);
}
