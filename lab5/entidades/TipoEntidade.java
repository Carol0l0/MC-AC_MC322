package entidades;
public enum TipoEntidade {
    ROBO('r', "rôbos andam pelo ambiente por diversos caminhos, mas os obstáculos podem bloquear o seu"),
    OBSTACULO('o', "bloqueia a passagem dos robos"),
    CAIXADESOM('c', "obstáculo que emite som"),
    SABIOMAGICO('m', "deixa voce passar se responder corretamente a charada"),
    VAZIO('x', "não há nada aqui");
    
    public final char representacao;
    public final String descricao;

    TipoEntidade(char representacao, String descricao){
        this.representacao=representacao;
        this.descricao=descricao;
    }
}
