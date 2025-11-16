package questao2;

/**
 * Classe que representa a resposta de uma transacao no formato moderno.
 * 
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por encapsular dados de resposta
 * 
 * Justificativa: Encapsula a resposta de forma clara e type-safe.
 */
public class RespostaTransacao {
    private boolean aprovada;
    private String codigoAutorizacao;
    private String mensagem;
    private long timestamp;
    
    public RespostaTransacao(boolean aprovada, String codigoAutorizacao, String mensagem) {
        this.aprovada = aprovada;
        this.codigoAutorizacao = codigoAutorizacao;
        this.mensagem = mensagem;
        this.timestamp = System.currentTimeMillis();
    }
    
    public boolean isAprovada() {
        return aprovada;
    }
    
    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return String.format("RespostaTransacao{aprovada=%s, codigo=%s, mensagem='%s', timestamp=%d}",
            aprovada, codigoAutorizacao, mensagem, timestamp);
    }
}
