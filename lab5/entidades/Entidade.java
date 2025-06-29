package entidades;
import ambientes.Ambiente;

/**
 * Interface comum para todos os elementos presentes no ambiente (robôs, obstáculos, etc.).
 */

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

    /** Retorna o identificador único da entidade. */
    String getId();

    /**
     * Verifica se a entidade pode ser adicionada a um ambiente específico.
     * @param a O ambiente a ser verificado.
     * @return Verdadeiro se puder ser adicionada, falso caso contrário.
     */
    Boolean podeAdicionar(Ambiente a);

    /** Retorna o tipo de entidade (ex: ROBO, OBSTACULO). */
    TipoEntidade getTipoEntidade();

    /** Retorna uma descrição textual da entidade. */
    String getDescricao();
    
    /** Retorna o caractere que representa a entidade no mapa. */
    char getRepresentacao();       
    
}