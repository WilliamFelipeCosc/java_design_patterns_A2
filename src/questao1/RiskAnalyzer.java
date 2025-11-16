package questao1;

/**
 * Classe Context do padrao Strategy.
 * 
 * Design Pattern: Strategy (Context)
 * Principio SOLID aplicado: OCP e DIP
 * - OCP: Aberto para extensao (novos algoritmos) sem modificacao
 * - DIP: Depende da abstracao RiskAlgorithm, nao de implementacoes concretas
 * 
 * Justificativa: Permite ao cliente trocar algoritmos em runtime
 * sem conhecer detalhes de implementacao. O cliente interage apenas
 * com esta classe, mantendo baixo acoplamento.
 */
public class RiskAnalyzer {
    private RiskAlgorithm algorithm;
    private RiskContext context;
    
    /**
     * Construtor que recebe o contexto complexo com parametros financeiros.
     * 
     * @param context Contexto com dados financeiros
     */
    public RiskAnalyzer(RiskContext context) {
        this.context = context;
    }
    
    /**
     * Permite trocar o algoritmo em tempo de execucao.
     * Este e o metodo chave do padrao Strategy.
     * 
     * @param algorithm Novo algoritmo a ser utilizado
     */
    public void setAlgorithm(RiskAlgorithm algorithm) {
        this.algorithm = algorithm;
        System.out.println("\n>>> Algoritmo alterado para: " + algorithm.getName() + " <<<\n");
    }
    
    /**
     * Executa a analise de risco usando o algoritmo atual.
     * 
     * @return Resultado do calculo
     * @throws IllegalStateException se nenhum algoritmo foi definido
     */
    public double analyze() {
        if (algorithm == null) {
            throw new IllegalStateException("Nenhum algoritmo de risco foi definido");
        }
        
        System.out.println("=".repeat(60));
        System.out.println("Executando: " + algorithm.getName());
        System.out.println("=".repeat(60));
        
        double result = algorithm.calculate(context);
        
        System.out.println("=".repeat(60));
        System.out.println();
        
        return result;
    }
    
    /**
     * Atualiza o contexto de analise.
     * 
     * @param context Novo contexto
     */
    public void setContext(RiskContext context) {
        this.context = context;
    }
    
    /**
     * Retorna o algoritmo atual.
     * 
     * @return Algoritmo atual ou null se nenhum foi definido
     */
    public RiskAlgorithm getCurrentAlgorithm() {
        return algorithm;
    }
}
