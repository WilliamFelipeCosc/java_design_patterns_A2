package questao1;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que encapsula o contexto complexo com multiplos parametros financeiros.
 * 
 * Principio SOLID aplicado: SRP (Single Responsibility Principle)
 * - Responsavel apenas por armazenar e fornecer dados de contexto
 * 
 * Justificativa: Centraliza todos os parametros necessarios para os algoritmos,
 * facilitando o compartilhamento de dados complexos entre diferentes estrategias.
 */
public class RiskContext {
    private List<Double> portfolioValues;
    private double confidenceLevel;
    private int timeHorizon;
    private double volatility;
    private double expectedReturn;
    
    public RiskContext() {
        this.portfolioValues = new ArrayList<>();
        this.confidenceLevel = 0.95;
        this.timeHorizon = 1;
        this.volatility = 0.0;
        this.expectedReturn = 0.0;
    }
    
    public void addPortfolioValue(double value) {
        this.portfolioValues.add(value);
    }
    
    public List<Double> getPortfolioValues() {
        return new ArrayList<>(portfolioValues);
    }
    
    public double getConfidenceLevel() {
        return confidenceLevel;
    }
    
    public void setConfidenceLevel(double confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }
    
    public int getTimeHorizon() {
        return timeHorizon;
    }
    
    public void setTimeHorizon(int timeHorizon) {
        this.timeHorizon = timeHorizon;
    }
    
    public double getVolatility() {
        return volatility;
    }
    
    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }
    
    public double getExpectedReturn() {
        return expectedReturn;
    }
    
    public void setExpectedReturn(double expectedReturn) {
        this.expectedReturn = expectedReturn;
    }
}
