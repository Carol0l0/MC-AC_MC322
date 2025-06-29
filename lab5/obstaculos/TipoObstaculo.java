package obstaculos;
import robos.Robo;
import robos.RoboAereo;
import robos.RoboTerrestre;

/**
 * Define os tipos específicos de obstáculos e suas propriedades de bloqueio.
 */

public enum TipoObstaculo {

    CAIXADESOM("Caixa de Som", 3, true, true),            // Bloqueia todos e emite som
    ARVOREMISTICA("Árvore Mística", 5,true, true),        // Bloqueia todos
    LAGODEACIDO("Lago de Ácido",0, true, false),          // Bloqueia robôs terrestres
    FORTEVENTANIA("Forte Ventania",49,false, true),       // Bloqueia robôs aéreos
    SABIOMAGICO("Sábio Mágico",49,true, true);            // Concede passagem após charada

    // Altura padrão que o obstáculo ocupa no ambiente.
    private final int alturaPadrao;
    // Indica se o obstáculo bloqueia a passagem de robôs terrestres.
    private final boolean bloqueiaPassagemTerrestres;
    // Indica se o obstáculo bloqueia a passagem de robôs aéreos.
    private final boolean bloqueiaPassagemAereos;
    // Nome descritivo do tipo de obstáculo.
    private final String nome;

    /**
     * Construtor para definir as propriedades de um tipo de obstáculo.
     * @param nome Nome do obstáculo.
     * @param alturaPadrao Altura padrão do obstáculo.
     * @param bloqueiaPassagemTerrestres Verdadeiro se bloquear terrestres.
     * @param bloqueiaPassagemAereos Verdadeiro se bloquear aéreos.
     */
    TipoObstaculo(String nome, int alturaPadrao, boolean bloqueiaPassagemTerrestres, boolean bloqueiaPassagemAereos) {
        this.nome = nome;
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagemTerrestres = bloqueiaPassagemTerrestres;
        this.bloqueiaPassagemAereos = bloqueiaPassagemAereos;
    }

    /** Retorna a altura padrão do obstáculo. */
    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    /** Retorna o nome descritivo do obstáculo. */
    public String getNome(){
        return nome;
    }

    /**
     * Verifica se um robô específico pode passar por este tipo de obstáculo.
     * @param r O robô tentando passar.
     * @return Verdadeiro se o robô puder passar, falso caso contrário.
     */
    public boolean podePassar(Robo r) {

        if (r instanceof RoboTerrestre && bloqueiaPassagemTerrestres) {
            return false; // Robô terrestre bloqueado por obstáculo terrestre.
        }
        if (r instanceof RoboAereo && bloqueiaPassagemAereos) {
            return false; // Robô aéreo bloqueado por obstáculo aéreo.
        }

        // Se um robô aéreo tenta passar por um obstáculo que bloqueia terrestres, mas não aéreos, ele passa.
        if (r instanceof RoboAereo && bloqueiaPassagemTerrestres && !bloqueiaPassagemAereos) {
             return true; // Ex: Lago de ácido (bloqueia terrestres, não aéreos)
        }
        // Se um robô terrestre tenta passar por um obstáculo que bloqueia aéreos, mas não terrestres, ele passa.
        if (r instanceof RoboTerrestre && bloqueiaPassagemAereos && !bloqueiaPassagemTerrestres) {
            return true; // Ex: Forte ventania (bloqueia aéreos, não terrestres)
        }

        return true; // Assume que pode passar se não for explicitamente bloqueado pelos tipos específicos.
    }
}