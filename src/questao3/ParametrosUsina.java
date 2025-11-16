package questao3;

/**
 * Classe que encapsula os parametros de operacao da usina nuclear.
 * 
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por armazenar e fornecer dados de monitoramento
 * 
 * Justificativa: Centraliza todos os parametros necessarios para
 * validacao de transicoes de estado.
 */
public class ParametrosUsina {
    private double temperatura;
    private double pressao;
    private double nivelRadiacao;
    private boolean sistemaResfriamentoAtivo;
    private long tempoAlertaAmarelo;
    private long inicioAlertaVermelho;
    
    public ParametrosUsina() {
        this.temperatura = 25.0;
        this.pressao = 1.0;
        this.nivelRadiacao = 0.1;
        this.sistemaResfriamentoAtivo = true;
        this.tempoAlertaAmarelo = 0;
        this.inicioAlertaVermelho = 0;
    }
    
    public double getTemperatura() {
        return temperatura;
    }
    
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    
    public double getPressao() {
        return pressao;
    }
    
    public void setPressao(double pressao) {
        this.pressao = pressao;
    }
    
    public double getNivelRadiacao() {
        return nivelRadiacao;
    }
    
    public void setNivelRadiacao(double nivelRadiacao) {
        this.nivelRadiacao = nivelRadiacao;
    }
    
    public boolean isSistemaResfriamentoAtivo() {
        return sistemaResfriamentoAtivo;
    }
    
    public void setSistemaResfriamentoAtivo(boolean ativo) {
        this.sistemaResfriamentoAtivo = ativo;
    }
    
    public long getTempoAlertaAmarelo() {
        return tempoAlertaAmarelo;
    }
    
    public void setTempoAlertaAmarelo(long tempo) {
        this.tempoAlertaAmarelo = tempo;
    }
    
    public long getInicioAlertaVermelho() {
        return inicioAlertaVermelho;
    }
    
    public void setInicioAlertaVermelho(long tempo) {
        this.inicioAlertaVermelho = tempo;
    }
    
    public long getTempoEmAlertaVermelho() {
        if (inicioAlertaVermelho == 0) {
            return 0;
        }
        return (System.currentTimeMillis() - inicioAlertaVermelho) / 1000;
    }
}
