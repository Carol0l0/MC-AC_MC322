public interface Entidade { // utilizada em Rôbo e Obstáculo

    int getX1();
    int getX2();
    int getY1();
    int getY2();
    int getZ();
    int setX1();
    int setX2();
    int setY1();
    int setY2();
    int setZ();

    String getNome();

    Boolean podeAdicionar(Ambiente a);

    TipoEntidade getTipoEntidade();

    String getDescricao();
    
    char getRepresentacao();       
    
}
