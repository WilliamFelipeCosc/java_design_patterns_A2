package questao1;

/**
 * Implementacao concreta do algoritmo Stress Testing.
 * 
 * Design Pattern: Strategy (ConcreteStrategy)
 * Principio SOLID aplicado: SRP e OCP
 * - SRP: Responsavel apenas pelo calculo de Stress Testing
 * - OCP: Pode ser estendido sem modificar outras classes
 * 
 * Justificativa: Encapsula o algoritmo de Stress Testing,
 * que simula cenarios extremos de mercado.
 */
public class StressTestingAlgorithm implements RiskAlgorithm {
    
    @Override
    public double calculate(RiskContext context) {
        System.out.println("[Stress] Iniciando Stress Testing");
        System.out.println("[Stress] Simulando cenarios extremos de mercado");
        System.out.println("[Stress] Cenario 1: Crise financeira global");
        System.out.println("[Stress] Cenario 2: Choque de volatilidade");
        System.out.println("[Stress] Cenario 3: Colapso de liquidez");
        
        // Calculo dummy: Simula queda drastica no portfolio
        double portfolioValue = context.getPortfolioValues().stream()
            .mapToDouble(Double::doubleValue)
            .sum();
        
        // Cenario de stress: queda de 40% do portfolio
        double stressScenarioLoss = portfolioValue * 0.40;
        
        // Adiciona impacto da volatilidade aumentada
        double volatilityMultiplier = 1 + (context.getVolatility() * 2);
        double totalStressLoss = stressScenarioLoss * volatilityMultiplier;
        
        System.out.println("[Stress] Valor atual do portfolio: R$ " + String.format("%.2f", portfolioValue));
        System.out.println("[Stress] Perda no cenario de stress: R$ " + String.format("%.2f", totalStressLoss));
        System.out.println("[Stress] Percentual de perda: " + String.format("%.1f", (totalStressLoss/portfolioValue)*100) + "%");
        System.out.println("[Stress] Interpretacao: Perda maxima em cenario de crise extrema");
        
        return totalStressLoss;
    }
    
    @Override
    public String getName() {
        return "Stress Testing";
    }
}
