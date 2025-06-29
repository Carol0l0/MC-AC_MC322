package entidades;

/**
 * Define os diferentes tipos de entidades presentes no ambiente do simulador.
 */

public enum TipoEntidade {
    ROBO('r', "rôbos andam pelo ambiente por diversos caminhos, mas os obstáculos podem bloquear o seu"),
    OBSTACULO('o', "bloqueia a passagem dos robos"),
    CAIXADESOM('c', "obstáculo que emite som"),
    SABIOMAGICO('m', "deixa voce passar se responder corretamente a charada"),
    VAZIO('.', "não há nada aqui");
    
    /** Caractere que representa o tipo de entidade no mapa. */
    public final char representacao;
    /** Descrição breve do tipo de entidade. */
    public final String descricao;

    /**
     * Construtor para TipoEntidade.
     * @param representacao O caractere de representação.
     * @param descricao A descrição.
     */
    TipoEntidade(char representacao, String descricao){
        this.representacao=representacao;
        this.descricao=descricao;
    }
}