package questao1;

/**
 * Implementacao concreta do algoritmo Expected Shortfall (ES).
 * 
 * Design Pattern: Strategy (ConcreteStrategy)
 * Principio SOLID aplicado: SRP e OCP
 * - SRP: Responsavel apenas pelo calculo de ES
 * - OCP: Pode ser estendido sem modificar outras classes
 * 
 * Justificativa: Encapsula o algoritmo de Expected Shortfall (CVaR),
 * que calcula a perda esperada quando o VaR e ultrapassado.
 */
public class ExpectedShortfallAlgorithm implements RiskAlgorithm {
    
    @Override
    public double calculate(RiskContext context) {
        System.out.println("[ES] Iniciando calculo de Expected Shortfall");
        System.out.println("[ES] Nivel de confianca: " + (context.getConfidenceLevel() * 100) + "%");
        System.out.println("[ES] Analisando cenarios de cauda da distribuicao");
        
        // Calculo dummy: ES = VaR * fator de ajuste
        double portfolioValue = context.getPortfolioValues().stream()
            .mapToDouble(Double::doubleValue)
            .sum();
        
        double zScore = 1.645;
        if (context.getConfidenceLevel() >= 0.99) {
            zScore = 2.326;
        }
        
        double var = portfolioValue * context.getVolatility() * zScore * Math.sqrt(context.getTimeHorizon());
        
        // ES e tipicamente maior que VaR
        double es = var * 1.3;
        
        System.out.println("[ES] Resultado: R$ " + String.format("%.2f", es));
        System.out.println("[ES] Interpretacao: Perda media esperada nos piores " + 
                         ((1 - context.getConfidenceLevel()) * 100) + "% dos casos");
        System.out.println("[ES] ES e mais conservador que VaR, considerando perdas extremas");
        
        return es;
    }
    
    @Override
    public String getName() {
        return "Expected Shortfall (ES/CVaR)";
    }
}
