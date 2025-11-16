package questao3;

/**
 * Estado concreto: Manutencao.
 * 
 * Design Pattern: State (ConcreteState)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas pelo comportamento do estado MANUTENCAO
 * 
 * Justificativa: Implementa modo de manutencao que sobreescreve
 * temporariamente os estados normais. Pode ser ativado de qualquer estado
 * e permite retorno ao estado anterior ou para DESLIGADA.
 */
public class EstadoManutencao extends EstadoUsinaAbstrato {
    private EstadoUsina estadoAnterior;
    
    @Override
    public String getNome() {
        return "MANUTENCAO";
    }
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        super.entrar(contexto);
        System.out.println("[MANUTENCAO] Modo de manutencao ativado");
        System.out.println("[MANUTENCAO] Operacoes normais suspensas");
        System.out.println("[MANUTENCAO] Equipe de manutencao pode acessar instalacoes");
        System.out.println("[MANUTENCAO] Alarmes automaticos desabilitados");
    }
    
    @Override
    public void sair(UsinaNuclear contexto) {
        super.sair(contexto);
        System.out.println("[MANUTENCAO] Modo de manutencao desativado");
        System.out.println("[MANUTENCAO] Sistemas automaticos reativados");
    }
    
    public void setEstadoAnterior(EstadoUsina estado) {
        this.estadoAnterior = estado;
    }
    
    public void retornarEstadoAnterior(UsinaNuclear contexto) {
        if (estadoAnterior != null && !(estadoAnterior instanceof EstadoManutencao)) {
            System.out.println("[MANUTENCAO] Retornando ao estado anterior: " + estadoAnterior.getNome());
            contexto.setEstado(estadoAnterior);
        } else {
            System.out.println("[MANUTENCAO] Nenhum estado anterior valido. Indo para DESLIGADA.");
            contexto.setEstado(new EstadoDesligada());
        }
    }
    
    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("[MANUTENCAO] Finalizando manutencao e desligando usina...");
        contexto.setEstado(new EstadoDesligada());
    }
    
    @Override
    public void ativarManutencao(UsinaNuclear contexto) {
        System.out.println("[MANUTENCAO] Usina ja esta em modo de manutencao");
    }
    
    // Em modo manutencao, transicoes diretas para outros estados sao bloqueadas
    // Deve-se primeiro sair da manutencao
    
    @Override
    public void operacaoNormal(UsinaNuclear contexto) {
        throw new IllegalStateException(
            "Nao e possivel ir diretamente para OPERACAO_NORMAL durante manutencao. " +
            "Finalize a manutencao primeiro.");
    }
    
    @Override
    public void alertaAmarelo(UsinaNuclear contexto) {
        throw new IllegalStateException(
            "Alarmes automaticos desabilitados durante manutencao.");
    }
    
    @Override
    public void alertaVermelho(UsinaNuclear contexto) {
        throw new IllegalStateException(
            "Alarmes automaticos desabilitados durante manutencao.");
    }
    
    @Override
    public void emergencia(UsinaNuclear contexto) {
        // Emergencia manual ainda pode ser acionada durante manutencao
        System.out.println("[MANUTENCAO] EMERGENCIA ACIONADA MANUALMENTE!");
        contexto.setEstado(new EstadoEmergencia());
    }
}
