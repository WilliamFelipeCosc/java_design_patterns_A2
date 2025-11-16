package questao3;

/**
 * Estado concreto: Operacao Normal.
 * 
 * Design Pattern: State (ConcreteState)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas pelo comportamento do estado OPERACAO_NORMAL
 * 
 * Justificativa: Encapsula comportamento durante operacao normal.
 * Valida temperatura antes de permitir transicao para ALERTA_AMARELO.
 */
public class EstadoOperacaoNormal extends EstadoUsinaAbstrato {
    
    @Override
    public String getNome() {
        return "OPERACAO_NORMAL";
    }
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        super.entrar(contexto);
        System.out.println("[OPERACAO_NORMAL] Reator em operacao estavel");
        System.out.println("[OPERACAO_NORMAL] Gerando energia eletrica");
        
        // Reseta contadores de alerta
        ParametrosUsina parametros = contexto.getParametros();
        parametros.setTempoAlertaAmarelo(0);
        parametros.setInicioAlertaVermelho(0);
    }
    
    @Override
    public void alertaAmarelo(UsinaNuclear contexto) {
        ParametrosUsina parametros = contexto.getParametros();
        
        System.out.println("[OPERACAO_NORMAL] Verificando condicoes para ALERTA_AMARELO...");
        System.out.println("[OPERACAO_NORMAL] Temperatura atual: " + parametros.getTemperatura() + "C");
        
        // Regra: OPERACAO_NORMAL -> ALERTA_AMARELO se temperatura > 300C
        validarCondicao(contexto, "Temperatura deve estar acima de 300C", 
                       parametros.getTemperatura() > 300);
        
        System.out.println("[OPERACAO_NORMAL] Condicao satisfeita. Acionando alerta amarelo...");
        contexto.setEstado(new EstadoAlertaAmarelo());
    }
    
    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("[OPERACAO_NORMAL] Iniciando procedimento de desligamento seguro...");
        contexto.setEstado(new EstadoDesligada());
    }
    
    @Override
    public void operacaoNormal(UsinaNuclear contexto) {
        System.out.println("[OPERACAO_NORMAL] Usina ja esta em operacao normal");
    }
}
