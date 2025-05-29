public enum TipoEntidade {
    ROBO('r', "rôbos andam pelo ambiente de diferentes maneiras, mas os obstáculos podem bloquear a sua"),
    OBSTACULO('o', "bloqueia a passagem dos rôbos"),
    CAIXADESOM('c', "obstáculo que emite som"),
    SABIOMAGICO('m', "deixa voce passar se responder corretamente a charada");

    public final char representacao;
    public final String descricao;

    TipoEntidade(char representacao, String descricao){
        this.representacao=representacao;
        this.descricao=descricao;
    }
}
