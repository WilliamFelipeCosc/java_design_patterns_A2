package questao4;

/**
 * Validador 1: Valida Schema XML contra XSD.
 * 
 * Design Pattern: Chain of Responsibility (ConcreteHandler)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por validar schema XML
 * 
 * Justificativa: Primeiro validador da cadeia, verifica estrutura basica do XML.
 */
public class ValidadorSchemaXML extends ValidadorAbstrato {
    
    public ValidadorSchemaXML() {
        super("Validador Schema XML", 2000);
    }
    
    @Override
    protected ResultadoValidacao executarValidacao(DocumentoFiscal documento) throws InterruptedException {
        System.out.println("[Schema XML] Validando estrutura XML contra XSD...");
        
        // Simula validacao com delay
        Thread.sleep(500);
        
        String xml = documento.getXmlContent();
        
        // Validacao dummy
        if (xml == null || xml.isEmpty()) {
            return ResultadoValidacao.falha(nome, "XML vazio ou nulo");
        }
        
        if (!xml.contains("<?xml")) {
            return ResultadoValidacao.falha(nome, "Documento nao e um XML valido");
        }
        
        if (!xml.contains("<NFe>")) {
            return ResultadoValidacao.falha(nome, "Schema NF-e nao encontrado no XML");
        }
        
        System.out.println("[Schema XML] Estrutura XML valida");
        return ResultadoValidacao.sucesso(nome);
    }
}
