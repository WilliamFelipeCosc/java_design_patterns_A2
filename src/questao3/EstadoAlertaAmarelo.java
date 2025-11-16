package questao3;

/**
 * Estado concreto: Alerta Amarelo.
 * 
 * Design Pattern: State (ConcreteState)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas pelo comportamento do estado ALERTA_AMARELO
 * 
 * Justificativa: Encapsula comportamento durante alerta amarelo.
 * Permite transicao bidirecional com OPERACAO_NORMAL e unidirecional para ALERTA_VERMELHO.
 */
public class EstadoAlertaAmarelo extends EstadoUsinaAbstrato {
    
    @Override
    public String getNome() {
        return "ALERTA_AMARELO";
    }
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        super.entrar(contexto);
        System.out.println("[ALERTA_AMARELO] Temperatura elevada detectada!");
        System.out.println("[ALERTA_AMARELO] Ativando sistemas de monitoramento intensivo");
        System.out.println("[ALERTA_AMARELO] Equipe de seguranca notificada");
        
        ParametrosUsina parametros = contexto.getParametros();
        parametros.setTempoAlertaAmarelo(System.currentTimeMillis());
    }
    
    @Override
    public void alertaVermelho(UsinaNuclear contexto) {
        ParametrosUsina parametros = contexto.getParametros();
        
        System.out.println("[ALERTA_AMARELO] Verificando condicoes para ALERTA_VERMELHO...");
        System.out.println("[ALERTA_AMARELO] Temperatura atual: " + parametros.getTemperatura() + "C");
        
        // Regra: ALERTA_AMARELO -> ALERTA_VERMELHO se temperatura > 400C
        validarCondicao(contexto, "Temperatura deve estar acima de 400C", 
                       parametros.getTemperatura() > 400);
        
        System.out.println("[ALERTA_AMARELO] Condicao critica detectada!");
        contexto.setEstado(new EstadoAlertaVermelho());
    }
    
    @Override
    public void operacaoNormal(UsinaNuclear contexto) {
        ParametrosUsina parametros = contexto.getParametros();
        
        System.out.println("[ALERTA_AMARELO] Verificando condicoes para retorno a operacao normal...");
        System.out.println("[ALERTA_AMARELO] Temperatura atual: " + parametros.getTemperatura() + "C");
        
        // Transicao bidirecional: pode voltar se temperatura normalizar
        validarCondicao(contexto, "Temperatura deve estar abaixo de 300C", 
                       parametros.getTemperatura() <= 300);
        
        System.out.println("[ALERTA_AMARELO] Temperatura normalizada. Retornando a operacao normal...");
        contexto.setEstado(new EstadoOperacaoNormal());
    }
    
    @Override
    public void alertaAmarelo(UsinaNuclear contexto) {
        System.out.println("[ALERTA_AMARELO] Usina ja esta em alerta amarelo");
    }
}
