public enum TipoObstaculo {

    CAIXADESOM(3, true, true),              //Bloqueia todos e emite som
    ARVOREMISTICA(5,true, true),            //Bloqueia todos
    LAGODEACIDO(0, true, false),            //Bloqueia qualquer tipo de robô terrestre
    FORTEVENTANIA(50,false, true),          //Bloqueia qualquer tipo de robô aéreo
    SABIOMAGICO(50,true, true);             //Só da passagem caso acerte a pergunta

    private final int alturaPadrao;
    private final boolean bloqueiaPassagemTerrestres;
    private final boolean bloqueiaPassagemAereos;

    TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagemTerrestres, boolean bloqueiaPassagemAereos) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagemTerrestres = bloqueiaPassagemTerrestres;
        this.bloqueiaPassagemAereos = bloqueiaPassagemAereos;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
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
