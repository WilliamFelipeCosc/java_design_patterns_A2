package questao4;

import java.util.Date;

/**
 * Validador 2: Valida Certificado Digital (expiracao e revogacao).
 * 
 * Design Pattern: Chain of Responsibility (ConcreteHandler)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por validar certificado digital
 * 
 * Justificativa: Valida autenticidade e validade do certificado.
 * Deve executar apenas se Schema XML passar.
 */
public class ValidadorCertificadoDigital extends ValidadorAbstrato {
    
    public ValidadorCertificadoDigital() {
        super("Validador Certificado Digital", 3000);
    }
    
    @Override
    protected ResultadoValidacao executarValidacao(DocumentoFiscal documento) throws InterruptedException {
        System.out.println("[Certificado] Validando certificado digital...");
        
        // Simula consulta de certificado
        Thread.sleep(800);
        
        String certificado = documento.getCertificadoDigital();
        
        if (certificado == null || certificado.isEmpty()) {
            return ResultadoValidacao.falha(nome, "Certificado digital ausente");
        }
        
        // Validacao dummy de expiracao
        System.out.println("[Certificado] Verificando data de expiracao...");
        Thread.sleep(300);
        
        if (certificado.contains("EXPIRADO")) {
            return ResultadoValidacao.falha(nome, "Certificado digital expirado");
        }
        
        // Validacao dummy de revogacao
        System.out.println("[Certificado] Consultando lista de revogacao...");
        Thread.sleep(400);
        
        if (certificado.contains("REVOGADO")) {
            return ResultadoValidacao.falha(nome, "Certificado digital foi revogado");
        }
        
        System.out.println("[Certificado] Certificado valido e nao revogado");
        System.out.println("[Certificado] Emitido em: " + new Date());
        
        return ResultadoValidacao.sucesso(nome);
    }
}
