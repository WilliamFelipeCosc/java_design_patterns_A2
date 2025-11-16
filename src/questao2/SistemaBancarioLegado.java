package questao2;

import java.util.HashMap;
import java.util.Random;

/**
 * Sistema bancario legado com interface obsoleta.
 * 
 * Design Pattern: Adapter (Adaptee)
 * Principio SOLID aplicado: SRP
 * - Responsavel apenas por processar transacoes no formato legado
 * 
 * Justificativa: Representa o sistema legado que nao pode ser modificado.
 * Usa HashMap com tipos Object, que e menos type-safe e mais complexo.
 */
public class SistemaBancarioLegado {
    private Random random;
    
    public SistemaBancarioLegado() {
        this.random = new Random();
    }
    
    /**
     * Processa transacao usando formato legado.
     * 
     * Parametros esperados no HashMap:
     * - "numero_cartao" (String): Numero do cartao
     * - "valor_transacao" (Double): Valor
     * - "codigo_moeda" (Integer): 1=USD, 2=EUR, 3=BRL
     * - "codigo_estabelecimento" (String): OBRIGATORIO no legado
     * - "tipo_transacao" (String): OBRIGATORIO no legado
     * 
     * @param parametros HashMap com parametros da transacao
     * @return HashMap com resultado do processamento
     */
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        System.out.println("[LEGADO] Processando transacao no sistema legado...");
        System.out.println("[LEGADO] Parametros recebidos: " + parametros);
        
        HashMap<String, Object> resultado = new HashMap<>();
        
        // Validacao de campos obrigatorios do legado
        if (!parametros.containsKey("codigo_estabelecimento")) {
            resultado.put("status", 0);
            resultado.put("mensagem_erro", "Campo obrigatorio ausente: codigo_estabelecimento");
            resultado.put("codigo_retorno", "ERR_001");
            return resultado;
        }
        
        if (!parametros.containsKey("tipo_transacao")) {
            resultado.put("status", 0);
            resultado.put("mensagem_erro", "Campo obrigatorio ausente: tipo_transacao");
            resultado.put("codigo_retorno", "ERR_002");
            return resultado;
        }
        
        // Simula processamento
        boolean aprovado = random.nextBoolean();
        
        if (aprovado) {
            resultado.put("status", 1);
            resultado.put("codigo_autorizacao", "AUT" + System.currentTimeMillis());
            resultado.put("mensagem_sistema", "TRANSACAO APROVADA");
            resultado.put("codigo_retorno", "000");
        } else {
            resultado.put("status", 0);
            resultado.put("codigo_autorizacao", "");
            resultado.put("mensagem_sistema", "TRANSACAO NEGADA - SALDO INSUFICIENTE");
            resultado.put("codigo_retorno", "051");
        }
        
        System.out.println("[LEGADO] Resultado: " + resultado);
        
        return resultado;
    }
}
