package questao3;

/**
 * Estado concreto: Emergencia.
 * 
 * Design Pattern: State (ConcreteState)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas pelo comportamento do estado EMERGENCIA
 * 
 * Justificativa: Encapsula comportamento durante emergencia.
 * Previne transicoes circulares perigosas - so pode ir para DESLIGADA ou MANUTENCAO.
 * EMERGENCIA so pode ser ativado apos passar por ALERTA_VERMELHO.
 */
public class EstadoEmergencia extends EstadoUsinaAbstrato {
    
    @Override
    public String getNome() {
        return "EMERGENCIA";
    }
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        super.entrar(contexto);
        System.out.println("[EMERGENCIA] *** ESTADO DE EMERGENCIA DECLARADO ***");
        System.out.println("[EMERGENCIA] Desligamento de emergencia em andamento");
        System.out.println("[EMERGENCIA] Barras de controle sendo inseridas");
        System.out.println("[EMERGENCIA] Evacuacao total da instalacao");
        System.out.println("[EMERGENCIA] Autoridades nucleares notificadas");
    }
    
    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("[EMERGENCIA] Executando desligamento de emergencia...");
        System.out.println("[EMERGENCIA] Reator sendo desativado completamente");
        contexto.setEstado(new EstadoDesligada());
    }
    
    @Override
    public void emergencia(UsinaNuclear contexto) {
        System.out.println("[EMERGENCIA] Usina ja esta em estado de emergencia");
    }
    
    // Previne transicoes circulares perigosas
    // De EMERGENCIA so pode ir para DESLIGADA ou MANUTENCAO
    // Nao pode voltar diretamente para OPERACAO_NORMAL, ALERTA_AMARELO ou ALERTA_VERMELHO
    
    @Override
    public void operacaoNormal(UsinaNuclear contexto) {
        throw new IllegalStateException(
            "Transicao direta de EMERGENCIA para OPERACAO_NORMAL nao permitida. " +
            "Usina deve ser desligada primeiro para avaliacao completa.");
    }
    
    @Override
    public void alertaAmarelo(UsinaNuclear contexto) {
        throw new IllegalStateException(
            "Transicao de EMERGENCIA para ALERTA_AMARELO nao permitida. " +
            "Esta e uma transicao circular perigosa.");
    }
    
    @Override
    public void alertaVermelho(UsinaNuclear contexto) {
        throw new IllegalStateException(
            "Transicao de EMERGENCIA para ALERTA_VERMELHO nao permitida. " +
            "Esta e uma transicao circular perigosa.");
    }
}
