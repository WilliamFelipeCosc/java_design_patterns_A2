package questao3;

/**
 * Estado abstrato que implementa comportamento padrao.
 * 
 * Design Pattern: State (Abstract State)
 * Principio SOLID aplicado: DRY e Template Method
 * 
 * Justificativa: Evita duplicacao de codigo fornecendo implementacao
 * padrao para metodos comuns. Estados concretos sobrescrevem apenas
 * o que for necessario.
 */
public abstract class EstadoUsinaAbstrato implements EstadoUsina {
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        System.out.println("[ESTADO] Entrando em: " + getNome());
    }
    
    @Override
    public void sair(UsinaNuclear contexto) {
        System.out.println("[ESTADO] Saindo de: " + getNome());
    }
    
    @Override
    public void alertaAmarelo(UsinaNuclear contexto) {
        throw new IllegalStateException("Transicao para ALERTA_AMARELO nao permitida de " + getNome());
    }
    
    @Override
    public void alertaVermelho(UsinaNuclear contexto) {
        throw new IllegalStateException("Transicao para ALERTA_VERMELHO nao permitida de " + getNome());
    }
    
    @Override
    public void emergencia(UsinaNuclear contexto) {
        throw new IllegalStateException("Transicao para EMERGENCIA nao permitida de " + getNome());
    }
    
    @Override
    public void operacaoNormal(UsinaNuclear contexto) {
        throw new IllegalStateException("Transicao para OPERACAO_NORMAL nao permitida de " + getNome());
    }
    
    @Override
    public void desligar(UsinaNuclear contexto) {
        throw new IllegalStateException("Transicao para DESLIGADA nao permitida de " + getNome());
    }
    
    @Override
    public void ativarManutencao(UsinaNuclear contexto) {
        // Manutencao pode ser ativada de qualquer estado
        contexto.setEstado(new EstadoManutencao());
    }
    
    /**
     * Valida condicoes complexas antes de permitir transicoes.
     * 
     * @param contexto Contexto da usina
     * @param condicao Descricao da condicao a validar
     * @param valida Se a condicao e valida
     * @throws IllegalStateException se condicao nao for satisfeita
     */
    protected void validarCondicao(UsinaNuclear contexto, String condicao, boolean valida) {
        if (!valida) {
            throw new IllegalStateException("Condicao nao satisfeita: " + condicao);
        }
    }
}
