package questao1;

/**
 * Implementacao concreta do algoritmo Value at Risk (VaR).
 * 
 * Design Pattern: Strategy (ConcreteStrategy)
 * Principio SOLID aplicado: SRP e OCP
 * - SRP: Responsavel apenas pelo calculo de VaR
 * - OCP: Pode ser estendido sem modificar outras classes
 * 
 * Justificativa: Encapsula o algoritmo de VaR, permitindo que seja
 * trocado por outros algoritmos sem afetar o cliente.
 */
public class ValueAtRiskAlgorithm implements RiskAlgorithm {
    
    @Override
    public double calculate(RiskContext context) {
        System.out.println("[VaR] Iniciando calculo de Value at Risk");
        System.out.println("[VaR] Nivel de confianca: " + (context.getConfidenceLevel() * 100) + "%");
        System.out.println("[VaR] Horizonte temporal: " + context.getTimeHorizon() + " dia(s)");
        System.out.println("[VaR] Volatilidade: " + context.getVolatility());
        
        // Calculo dummy: VaR = Valor do Portfolio * Volatilidade * Z-score
        double portfolioValue = context.getPortfolioValues().stream()
            .mapToDouble(Double::doubleValue)
            .sum();
        
        // Z-score aproximado para 95% de confianca
        double zScore = 1.645;
        if (context.getConfidenceLevel() >= 0.99) {
            zScore = 2.326;
        }
        
        double var = portfolioValue * context.getVolatility() * zScore * Math.sqrt(context.getTimeHorizon());
        
        System.out.println("[VaR] Resultado: R$ " + String.format("%.2f", var));
        System.out.println("[VaR] Interpretacao: Perda maxima esperada em " + 
                         (context.getConfidenceLevel() * 100) + "% dos casos");
        
        return var;
    }
    
    @Override
    public String getName() {
        return "Value at Risk (VaR)";
    }
}
