package questao3;

/**
 * Estado concreto: Usina Desligada.
 * 
 * Design Pattern: State (ConcreteState)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas pelo comportamento do estado DESLIGADA
 * 
 * Justificativa: Encapsula o comportamento quando a usina esta desligada.
 * Permite transicao apenas para OPERACAO_NORMAL ou MANUTENCAO.
 */
public class EstadoDesligada extends EstadoUsinaAbstrato {
    
    @Override
    public String getNome() {
        return "DESLIGADA";
    }
    
    @Override
    public void entrar(UsinaNuclear contexto) {
        super.entrar(contexto);
        System.out.println("[DESLIGADA] Todos os sistemas desativados");
        System.out.println("[DESLIGADA] Temperatura voltando ao normal");
    }
    
    @Override
    public void operacaoNormal(UsinaNuclear contexto) {
        System.out.println("[DESLIGADA] Iniciando procedimento de partida...");
        
        ParametrosUsina parametros = contexto.getParametros();
        validarCondicao(contexto, "Sistema de resfriamento deve estar ativo", 
                       parametros.isSistemaResfriamentoAtivo());
        validarCondicao(contexto, "Temperatura deve estar abaixo de 100C", 
                       parametros.getTemperatura() < 100);
        
        System.out.println("[DESLIGADA] Condicoes satisfeitas. Iniciando reator...");
        contexto.setEstado(new EstadoOperacaoNormal());
    }
    
    @Override
    public void desligar(UsinaNuclear contexto) {
        System.out.println("[DESLIGADA] Usina ja esta desligada");
    }
}
