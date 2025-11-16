package questao3;

/**
 * Interface State para estados da usina nuclear.
 * 
 * Design Pattern: State
 * Principio SOLID aplicado: OCP e LSP
 * - OCP: Novos estados podem ser adicionados sem modificar existentes
 * - LSP: Estados sao substituiveis uns pelos outros
 * 
 * Justificativa: Define o contrato que todos os estados devem seguir,
 * permitindo que cada estado encapsule seu proprio comportamento.
 */
public interface EstadoUsina {
    /**
     * Retorna o nome do estado.
     * 
     * @return Nome do estado
     */
    String getNome();
    
    /**
     * Executa acoes ao entrar no estado.
     * 
     * @param contexto Contexto da usina
     */
    void entrar(UsinaNuclear contexto);
    
    /**
     * Executa acoes ao sair do estado.
     * 
     * @param contexto Contexto da usina
     */
    void sair(UsinaNuclear contexto);
    
    /**
     * Tenta transitar para alerta amarelo.
     * 
     * @param contexto Contexto da usina
     * @throws IllegalStateException se a transicao nao for permitida
     */
    void alertaAmarelo(UsinaNuclear contexto);
    
    /**
     * Tenta transitar para alerta vermelho.
     * 
     * @param contexto Contexto da usina
     * @throws IllegalStateException se a transicao nao for permitida
     */
    void alertaVermelho(UsinaNuclear contexto);
    
    /**
     * Tenta transitar para emergencia.
     * 
     * @param contexto Contexto da usina
     * @throws IllegalStateException se a transicao nao for permitida
     */
    void emergencia(UsinaNuclear contexto);
    
    /**
     * Tenta transitar para operacao normal.
     * 
     * @param contexto Contexto da usina
     * @throws IllegalStateException se a transicao nao for permitida
     */
    void operacaoNormal(UsinaNuclear contexto);
    
    /**
     * Tenta desligar a usina.
     * 
     * @param contexto Contexto da usina
     * @throws IllegalStateException se a transicao nao for permitida
     */
    void desligar(UsinaNuclear contexto);
    
    /**
     * Ativa modo manutencao.
     * 
     * @param contexto Contexto da usina
     */
    void ativarManutencao(UsinaNuclear contexto);
}
