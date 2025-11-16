package questao2;

import java.util.HashMap;

/**
 * Adaptador bidirecional entre a interface moderna e o sistema legado.
 * 
 * Design Pattern: Adapter (Adapter Class)
 * Principio SOLID aplicado: SRP, OCP, DIP
 * - SRP: Responsavel apenas pela adaptacao entre as duas interfaces
 * - OCP: Fechado para modificacao, aberto para extensao
 * - DIP: Depende de abstrações (ProcessadorTransacoes)
 * 
 * Justificativa: Permite que o sistema moderno use o legado sem conhecer
 * seus detalhes. Implementa conversao bidirecional mantendo compatibilidade.
 * 
 * Caracteristicas:
 * - Converte interface moderna para legada (autorizar -> processarTransacao)
 * - Converte resposta legada para moderna (HashMap -> RespostaTransacao)
 * - Trata campos obrigatorios do legado que nao existem na interface moderna
 * - Codifica moedas conforme especificacao legada
 */
public class AdaptadorSistemaBancario implements ProcessadorTransacoes {
    private SistemaBancarioLegado sistemaLegado;
    
    // Codigo do estabelecimento padrao (campo obrigatorio no legado)
    private static final String CODIGO_ESTABELECIMENTO_PADRAO = "EST001";
    
    // Tipo de transacao padrao (campo obrigatorio no legado)
    private static final String TIPO_TRANSACAO_PADRAO = "COMPRA";
    
    public AdaptadorSistemaBancario(SistemaBancarioLegado sistemaLegado) {
        this.sistemaLegado = sistemaLegado;
    }
    
    /**
     * Implementa a interface moderna, adaptando para o sistema legado.
     * 
     * Conversao moderna -> legada:
     * - cartao -> "numero_cartao"
     * - valor -> "valor_transacao"
     * - moeda (String) -> "codigo_moeda" (Integer)
     * - Adiciona campos obrigatorios do legado com valores padrao
     */
    @Override
    public RespostaTransacao autorizar(String cartao, double valor, String moeda) {
        System.out.println("\n[ADAPTER] Convertendo chamada moderna para formato legado");
        System.out.println("[ADAPTER] Entrada: cartao=" + cartao + ", valor=" + valor + ", moeda=" + moeda);
        
        // Converte parametros modernos para formato legado
        HashMap<String, Object> parametrosLegado = converterParaFormatoLegado(cartao, valor, moeda);
        
        // Chama o sistema legado
        HashMap<String, Object> resultadoLegado = sistemaLegado.processarTransacao(parametrosLegado);
        
        // Converte resposta legada para formato moderno
        RespostaTransacao resposta = converterParaFormatoModerno(resultadoLegado);
        
        System.out.println("[ADAPTER] Conversao concluida");
        System.out.println("[ADAPTER] Saida: " + resposta);
        
        return resposta;
    }
    
    /**
     * Converte parametros da interface moderna para o formato legado.
     * Adiciona campos obrigatorios que nao existem na interface moderna.
     * 
     * @param cartao Numero do cartao
     * @param valor Valor da transacao
     * @param moeda Codigo da moeda (USD, EUR, BRL)
     * @return HashMap no formato esperado pelo sistema legado
     */
    private HashMap<String, Object> converterParaFormatoLegado(String cartao, double valor, String moeda) {
        HashMap<String, Object> parametros = new HashMap<>();
        
        // Conversao direta de campos
        parametros.put("numero_cartao", cartao);
        parametros.put("valor_transacao", valor);
        
        // Codificacao de moedas conforme especificacao legada
        parametros.put("codigo_moeda", codificarMoeda(moeda));
        
        // Adiciona campos obrigatorios do legado que nao existem na interface moderna
        parametros.put("codigo_estabelecimento", CODIGO_ESTABELECIMENTO_PADRAO);
        parametros.put("tipo_transacao", TIPO_TRANSACAO_PADRAO);
        
        System.out.println("[ADAPTER] Campos adicionados pelo adapter:");
        System.out.println("[ADAPTER]   - codigo_estabelecimento: " + CODIGO_ESTABELECIMENTO_PADRAO);
        System.out.println("[ADAPTER]   - tipo_transacao: " + TIPO_TRANSACAO_PADRAO);
        
        return parametros;
    }
    
    /**
     * Codifica moedas conforme especificacao do sistema legado.
     * USD=1, EUR=2, BRL=3
     * 
     * @param moeda Codigo da moeda em String
     * @return Codigo numerico da moeda
     */
    private int codificarMoeda(String moeda) {
        switch (moeda.toUpperCase()) {
            case "USD":
                return 1;
            case "EUR":
                return 2;
            case "BRL":
                return 3;
            default:
                System.out.println("[ADAPTER] AVISO: Moeda desconhecida '" + moeda + "', usando BRL como padrao");
                return 3;
        }
    }
    
    /**
     * Converte resposta do formato legado para o formato moderno.
     * 
     * Conversao legada -> moderna:
     * - "status" (0/1) -> boolean aprovada
     * - "codigo_autorizacao" -> String codigoAutorizacao
     * - "mensagem_sistema" -> String mensagem
     * 
     * @param resultadoLegado HashMap retornado pelo sistema legado
     * @return RespostaTransacao no formato moderno
     */
    private RespostaTransacao converterParaFormatoModerno(HashMap<String, Object> resultadoLegado) {
        // Extrai status (0 = negado, 1 = aprovado)
        int status = (Integer) resultadoLegado.get("status");
        boolean aprovada = (status == 1);
        
        // Extrai codigo de autorizacao
        String codigoAutorizacao = (String) resultadoLegado.getOrDefault("codigo_autorizacao", "");
        
        // Extrai mensagem (pode ser mensagem_sistema ou mensagem_erro)
        String mensagem;
        if (resultadoLegado.containsKey("mensagem_sistema")) {
            mensagem = (String) resultadoLegado.get("mensagem_sistema");
        } else {
            mensagem = (String) resultadoLegado.getOrDefault("mensagem_erro", "Erro desconhecido");
        }
        
        return new RespostaTransacao(aprovada, codigoAutorizacao, mensagem);
    }
    
    /**
     * Metodo utilitario para decodificar moedas (conversao reversa).
     * Permite operacao bidirecional.
     * 
     * @param codigoMoeda Codigo numerico da moeda
     * @return String com codigo da moeda
     */
    public String decodificarMoeda(int codigoMoeda) {
        switch (codigoMoeda) {
            case 1:
                return "USD";
            case 2:
                return "EUR";
            case 3:
                return "BRL";
            default:
                return "UNKNOWN";
        }
    }
}
