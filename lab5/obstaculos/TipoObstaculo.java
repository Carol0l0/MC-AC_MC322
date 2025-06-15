package obstaculos;
import robos.Robo;
import robos.RoboAereo;
import robos.RoboTerrestre;

public enum TipoObstaculo {

    CAIXADESOM("Caixa de Som", 3, true, true),            //Bloqueia todos e emite som
    ARVOREMISTICA("Árvore Mística", 5,true, true),        //Bloqueia todos
    LAGODEACIDO("Lago de Ácido",0, true, false),          //Bloqueia qualquer tipo de robô terrestre
    FORTEVENTANIA("Forte Ventania",49,false, true),       //Bloqueia qualquer tipo de robô aéreo
    SABIOMAGICO("Sábio Mágico",49,true, true);            //Só da passagem caso acerte a pergunta

    private final int alturaPadrao;
    private final boolean bloqueiaPassagemTerrestres;
    private final boolean bloqueiaPassagemAereos;
    private final String nome;

    TipoObstaculo(String nome, int alturaPadrao, boolean bloqueiaPassagemTerrestres, boolean bloqueiaPassagemAereos) {
        this.nome = nome;
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagemTerrestres = bloqueiaPassagemTerrestres;
        this.bloqueiaPassagemAereos = bloqueiaPassagemAereos;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    public String getNome(){
        return nome;
    }

    public boolean podePassar(Robo r) {

        if (r instanceof RoboTerrestre && bloqueiaPassagemTerrestres) {
            return false;
        }
        if (r instanceof RoboAereo && bloqueiaPassagemAereos) {
            return false;
        }
        if (r instanceof RoboAereo && bloqueiaPassagemTerrestres) {
            return true;
        }
        if (r instanceof RoboTerrestre && bloqueiaPassagemAereos) {
            return true;
        }
        if (r instanceof RoboAereo && bloqueiaPassagemAereos) {
            return false;
        }
        //se for um robô genérico
        return false;
    }



}    
