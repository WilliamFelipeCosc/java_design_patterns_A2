package questao4;

/**
 * Classe que representa um documento fiscal eletronico (NF-e).
 * 
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por armazenar dados do documento
 * 
 * Justificativa: Encapsula todos os dados necessarios para validacao.
 */
public class DocumentoFiscal {
    private String numeroNFe;
    private String xmlContent;
    private String certificadoDigital;
    private double valorTotal;
    private double valorImpostos;
    private boolean inseridoNoBanco;
    
    public DocumentoFiscal(String numeroNFe, String xmlContent) {
        this.numeroNFe = numeroNFe;
        this.xmlContent = xmlContent;
        this.inseridoNoBanco = false;
    }
    
    public String getNumeroNFe() {
        return numeroNFe;
    }
    
    public void setNumeroNFe(String numeroNFe) {
        this.numeroNFe = numeroNFe;
    }
    
    public String getXmlContent() {
        return xmlContent;
    }
    
    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }
    
    public String getCertificadoDigital() {
        return certificadoDigital;
    }
    
    public void setCertificadoDigital(String certificadoDigital) {
        this.certificadoDigital = certificadoDigital;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public double getValorImpostos() {
        return valorImpostos;
    }
    
    public void setValorImpostos(double valorImpostos) {
        this.valorImpostos = valorImpostos;
    }
    
    public boolean isInseridoNoBanco() {
        return inseridoNoBanco;
    }
    
    public void setInseridoNoBanco(boolean inseridoNoBanco) {
        this.inseridoNoBanco = inseridoNoBanco;
    }
}
