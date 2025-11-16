package questao4;

/**
 * Validador 3: Valida Regras Fiscais (calculo de impostos).
 * 
 * Design Pattern: Chain of Responsibility (ConcreteHandler)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por validar calculos fiscais
 * 
 * Justificativa: Valida se impostos foram calculados corretamente.
 * Executa apenas se validadores anteriores passarem (validacao condicional).
 */
public class ValidadorRegrasFiscais extends ValidadorAbstrato {
    
    public ValidadorRegrasFiscais() {
        super("Validador Regras Fiscais", 5000);
    }
    
    @Override
    protected ResultadoValidacao executarValidacao(DocumentoFiscal documento) throws InterruptedException {
        System.out.println("[Regras Fiscais] Validando calculos de impostos...");
        
        // Simula calculo complexo
        Thread.sleep(1200);
        
        double valorTotal = documento.getValorTotal();
        double valorImpostos = documento.getValorImpostos();
        
        if (valorTotal <= 0) {
            return ResultadoValidacao.falha(nome, "Valor total deve ser maior que zero");
        }
        
        // Validacao dummy: impostos devem ser entre 10% e 40% do total
        double percentualImpostos = (valorImpostos / valorTotal) * 100;
        
        System.out.println("[Regras Fiscais] Valor total: R$ " + String.format("%.2f", valorTotal));
        System.out.println("[Regras Fiscais] Impostos: R$ " + String.format("%.2f", valorImpostos));
        System.out.println("[Regras Fiscais] Percentual: " + String.format("%.2f", percentualImpostos) + "%");
        
        if (percentualImpostos < 10 || percentualImpostos > 40) {
            return ResultadoValidacao.falha(nome, 
                "Percentual de impostos invalido: " + String.format("%.2f", percentualImpostos) + "% (deve ser entre 10% e 40%)");
        }
        
        System.out.println("[Regras Fiscais] Calculos fiscais corretos");
        
        return ResultadoValidacao.sucesso(nome);
    }
}
