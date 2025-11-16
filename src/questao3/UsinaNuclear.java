package questao3;

/**
 * Classe Context do padrao State.
 * 
 * Design Pattern: State (Context)
 * Principio SOLID aplicado: OCP e SRP
 * - OCP: Aberto para novos estados sem modificacao
 * - SRP: Responsavel por delegar operacoes ao estado atual
 * 
 * Justificativa: Permite que o comportamento da usina mude conforme
 * seu estado interno. Encapsula transicoes complexas com validacoes.
 * Previne transicoes circulares perigosas atraves dos estados.
 */
public class UsinaNuclear {
    private EstadoUsina estadoAtual;
    private ParametrosUsina parametros;
    
    public UsinaNuclear() {
        this.parametros = new ParametrosUsina();
        this.estadoAtual = new EstadoDesligada();
        this.estadoAtual.entrar(this);
    }
    
    /**
     * Altera o estado da usina.
     * Preserva o estado anterior para modo de manutencao.
     * 
     * @param novoEstado Novo estado
     */
    public void setEstado(EstadoUsina novoEstado) {
        if (estadoAtual != null) {
            estadoAtual.sair(this);
        }
        
        // Se estiver entrando em manutencao, salva estado anterior
        if (novoEstado instanceof EstadoManutencao) {
            ((EstadoManutencao) novoEstado).setEstadoAnterior(estadoAtual);
        }
        
        this.estadoAtual = novoEstado;
        this.estadoAtual.entrar(this);
    }
    
    public EstadoUsina getEstadoAtual() {
        return estadoAtual;
    }
    
    public ParametrosUsina getParametros() {
        return parametros;
    }
    
    // Metodos que delegam para o estado atual
    
    public void transicaoParaAlertaAmarelo() {
        estadoAtual.alertaAmarelo(this);
    }
    
    public void transicaoParaAlertaVermelho() {
        estadoAtual.alertaVermelho(this);
    }
    
    public void transicaoParaEmergencia() {
        estadoAtual.emergencia(this);
    }
    
    public void transicaoParaOperacaoNormal() {
        estadoAtual.operacaoNormal(this);
    }
    
    public void desligar() {
        estadoAtual.desligar(this);
    }
    
    public void ativarManutencao() {
        estadoAtual.ativarManutencao(this);
    }
    
    public void retornarDeManutencao() {
        if (estadoAtual instanceof EstadoManutencao) {
            ((EstadoManutencao) estadoAtual).retornarEstadoAnterior(this);
        } else {
            System.out.println("Usina nao esta em modo de manutencao");
        }
    }
    
    /**
     * Exibe o status atual da usina.
     */
    public void exibirStatus() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STATUS DA USINA NUCLEAR");
        System.out.println("=".repeat(60));
        System.out.println("Estado atual: " + estadoAtual.getNome());
        System.out.println("Temperatura: " + parametros.getTemperatura() + "C");
        System.out.println("Pressao: " + parametros.getPressao() + " atm");
        System.out.println("Nivel de radiacao: " + parametros.getNivelRadiacao() + " mSv/h");
        System.out.println("Sistema de resfriamento: " + 
                         (parametros.isSistemaResfriamentoAtivo() ? "ATIVO" : "INATIVO"));
        System.out.println("=".repeat(60) + "\n");
    }
}
