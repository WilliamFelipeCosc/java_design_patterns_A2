package questao4;

/**
 * Excecao lancada quando uma validacao falha.
 */
public class ValidacaoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
