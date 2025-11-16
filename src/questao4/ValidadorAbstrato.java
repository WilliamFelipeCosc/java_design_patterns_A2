package questao4;

/**
 * Classe abstrata base para validadores.
 * 
 * Design Pattern: Chain of Responsibility (Abstract Handler)
 * Principio SOLID aplicado: Template Method, DRY
 * 
 * Justificativa: Evita duplicacao de codigo fornecendo implementacao
 * comum para gerenciamento da cadeia e controle de timeout.
 */
public abstract class ValidadorAbstrato implements Validador {
    protected Validador proximo;
    protected String nome;
    protected long timeout;
    
    public ValidadorAbstrato(String nome, long timeoutMs) {
        this.nome = nome;
        this.timeout = timeoutMs;
    }
    
    @Override
    public void setProximo(Validador proximo) {
        this.proximo = proximo;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public long getTimeout() {
        return timeout;
    }
    
    @Override
    public ResultadoValidacao validar(DocumentoFiscal documento) throws ValidacaoException, TimeoutException {
        long inicio = System.currentTimeMillis();
        
        System.out.println("[" + nome + "] Iniciando validacao...");
        
        try {
            // Executa validacao especifica
            ResultadoValidacao resultado = executarValidacao(documento);
            
            // Verifica timeout
            long tempoDecorrido = System.currentTimeMillis() - inicio;
            if (tempoDecorrido > timeout) {
                throw new TimeoutException(nome + " excedeu o tempo limite de " + timeout + "ms");
            }
            
            System.out.println("[" + nome + "] Concluida em " + tempoDecorrido + "ms");
            
            if (!resultado.isValido()) {
                System.out.println("[" + nome + "] FALHOU: " + resultado.getMensagem());
                throw new ValidacaoException(resultado.getMensagem());
            }
            
            System.out.println("[" + nome + "] APROVADA");
            
            // Passa para o proximo validador se existir
            if (proximo != null && deveContinuar(documento)) {
                return proximo.validar(documento);
            }
            
            return resultado;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ValidacaoException("Validacao interrompida: " + e.getMessage());
        }
    }
    
    /**
     * Executa a validacao especifica do validador concreto.
     * 
     * @param documento Documento a validar
     * @return Resultado da validacao
     * @throws InterruptedException se a validacao for interrompida
     */
    protected abstract ResultadoValidacao executarValidacao(DocumentoFiscal documento) 
        throws InterruptedException;
    
    /**
     * Determina se deve continuar para o proximo validador.
     * Pode ser sobrescrito para implementar logica condicional.
     * 
     * @param documento Documento validado
     * @return true se deve continuar
     */
    protected boolean deveContinuar(DocumentoFiscal documento) {
        return true;
    }
    
    @Override
    public void rollback(DocumentoFiscal documento) {
        // Implementacao padrao: sem rollback
        System.out.println("[" + nome + "] Nenhum rollback necessario");
    }
}
