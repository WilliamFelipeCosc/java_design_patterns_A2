package questao4;

/**
 * Excecao lancada quando uma validacao excede o tempo limite.
 */
public class TimeoutException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public TimeoutException(String mensagem) {
        super(mensagem);
    }
}
