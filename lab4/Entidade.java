public interface Entidade { // utilizada em Rôbo e Obstáculo

    int getX1();
    int getX2();
    int getY1();
    int getY2();
    int getZ();
    void setX1(int x1);
    void setX2(int x2);
    void setY1(int y1);
    void setY2(int y2);

    String getId();

    Boolean podeAdicionar(Ambiente a);

    TipoEntidade getTipoEntidade();

    String getDescricao();
    
    char getRepresentacao();       
    
}
