package questao3;

/**
 * Estado concreto: Alerta Vermelho.
 * 
 * Design Pattern: State (ConcreteState)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas pelo comportamento do estado ALERTA_VERMELHO
 * 
 * Justificativa: Encapsula comportamento durante alerta vermelho.
 * Valida tempo em alerta vermelho e falha do sistema de resfriamento
 * antes de permitir transicao para EMERGENCIA.
 */
public class EstadoAlertaVermelho extends EstadoUsinaAbstrato {
    
    @Override
    public String getNome() {
        return "ALERTA_VERMELHO";
    }
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        super.entrar(contexto);
        System.out.println("[ALERTA_VERMELHO] SITUACAO CRITICA!");
        System.out.println("[ALERTA_VERMELHO] Temperatura extremamente elevada");
        System.out.println("[ALERTA_VERMELHO] Acionando protocolo de emergencia");
        System.out.println("[ALERTA_VERMELHO] Evacuacao da area nao essencial iniciada");
        
        ParametrosUsina parametros = contexto.getParametros();
        parametros.setInicioAlertaVermelho(System.currentTimeMillis());
    }
    
    @Override
    public void emergencia(UsinaNuclear contexto) {
        ParametrosUsina parametros = contexto.getParametros();
        
        System.out.println("[ALERTA_VERMELHO] Verificando condicoes para EMERGENCIA...");
        System.out.println("[ALERTA_VERMELHO] Sistema de resfriamento: " + 
                         (parametros.isSistemaResfriamentoAtivo() ? "ATIVO" : "FALHOU"));
        System.out.println("[ALERTA_VERMELHO] Tempo em alerta vermelho: " + 
                         parametros.getTempoEmAlertaVermelho() + " segundos");
        
        // Regra: ALERTA_VERMELHO -> EMERGENCIA se sistema de resfriamento falhar
        // OU se permanecer em alerta vermelho por mais de 30 segundos
        boolean sistemaFalhou = !parametros.isSistemaResfriamentoAtivo();
        boolean tempoExcedido = parametros.getTempoEmAlertaVermelho() > 30;
        
        if (!sistemaFalhou && !tempoExcedido) {
            throw new IllegalStateException(
                "EMERGENCIA so pode ser ativada se sistema de resfriamento falhar " +
                "ou se permanecer em ALERTA_VERMELHO por mais de 30 segundos");
        }
        
        System.out.println("[ALERTA_VERMELHO] CONDICAO DE EMERGENCIA CONFIRMADA!");
        contexto.setEstado(new EstadoEmergencia());
    }
    
    @Override
    public void alertaAmarelo(UsinaNuclear contexto) {
        ParametrosUsina parametros = contexto.getParametros();
        
        System.out.println("[ALERTA_VERMELHO] Verificando possibilidade de reducao de alerta...");
        System.out.println("[ALERTA_VERMELHO] Temperatura atual: " + parametros.getTemperatura() + "C");
        
        // Pode voltar para amarelo se temperatura diminuir
        validarCondicao(contexto, "Temperatura deve estar entre 300C e 400C", 
                       parametros.getTemperatura() > 300 && parametros.getTemperatura() <= 400);
        
        System.out.println("[ALERTA_VERMELHO] Temperatura reducao detectada. Retornando a alerta amarelo...");
        contexto.setEstado(new EstadoAlertaAmarelo());
    }
    
    @Override
    public void alertaVermelho(UsinaNuclear contexto) {
        System.out.println("[ALERTA_VERMELHO] Usina ja esta em alerta vermelho");
    }
}
