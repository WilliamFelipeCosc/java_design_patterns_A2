package questao1;

/**
 * Interface Strategy para algoritmos de calculo de risco.
 * 
 * Design Pattern: Strategy
 * Principio SOLID aplicado: DIP (Dependency Inversion Principle)
 * - Classes de alto nivel dependem de abstracoes, nao de implementacoes concretas
 * 
 * Justificativa: Permite que o contexto dependa de uma abstracao,
 * facilitando a troca de algoritmos em tempo de execucao sem conhecer
 * detalhes de implementacao.
 */
public interface RiskAlgorithm {
    /**
     * Calcula metricas de risco baseado no contexto fornecido.
     * 
     * @param context Contexto com parametros financeiros
     * @return Resultado do calculo de risco
     */
    double calculate(RiskContext context);
    
    /**
     * Retorna o nome do algoritmo para identificacao.
     * 
     * @return Nome do algoritmo
     */
    String getName();
}
