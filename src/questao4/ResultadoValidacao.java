package questao4;

/**
 * Classe que encapsula o resultado de uma validacao.
 * 
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por armazenar resultado de validacao
 * 
 * Justificativa: Permite retornar informacoes detalhadas sobre
 * o resultado de cada validacao.
 */
public class ResultadoValidacao {
    private boolean valido;
    private String mensagem;
    private String nomeValidador;
    
    public ResultadoValidacao(boolean valido, String mensagem, String nomeValidador) {
        this.valido = valido;
        this.mensagem = mensagem;
        this.nomeValidador = nomeValidador;
    }
    
    public static ResultadoValidacao sucesso(String nomeValidador) {
        return new ResultadoValidacao(true, "Validacao aprovada", nomeValidador);
    }
    
    public static ResultadoValidacao falha(String nomeValidador, String mensagem) {
        return new ResultadoValidacao(false, mensagem, nomeValidador);
    }
    
    public boolean isValido() {
        return valido;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public String getNomeValidador() {
        return nomeValidador;
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s: %s", 
            nomeValidador, 
            valido ? "APROVADO" : "REPROVADO", 
            mensagem);
    }
}
