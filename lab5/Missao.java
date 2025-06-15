public interface Missao {

    String getNome();

    String getDescricao();

    boolean executar(AgenteInteligente agente);
    
}