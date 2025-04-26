public enum TipoObstaculo {

    CAIXADESOM(3, true, true),              //
    LAGODEACIDO(0, true, false),            //Bloqueia qualquer tipo de robô terrestre
    FORTEVENTANIA(50,false, true),          //Bloqueia qualquer tipo de robô aéreo
    FIREWALLMALICIOSO(50,false, false),     //Bloqueia o uso de qualquer sensor
    SABIOMAGICO(50,true, true);             //Só da passagem caso acerte a pergunta

    private final int alturaPadrao;
    private final boolean bloqueiaPassagemTerrestres;
    private final boolean bloqueiaPassagemAereos;
    private static SabioMagico sabioMagico;

    TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagemTerrestres, boolean bloqueiaPassagemAereos) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagemTerrestres = bloqueiaPassagemTerrestres;
        this.bloqueiaPassagemAereos = bloqueiaPassagemAereos;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    public boolean bloqueiaPassagem() {
        return bloqueiaPassagemTerrestres;
    }

    public static void configurarSabioMagico(SabioMagico sm) {
        sabioMagico = sm;
    }

public boolean podePassar(Robo r) {
    if (this == SABIOMAGICO) {
        SabioMagico sabio = new SabioMagico();
        return sabio.desafiar();
    }

    if (r instanceof RoboTerrestre && bloqueiaPassagemTerrestres) {
        return false;
    }
    if (r instanceof RoboAereo && bloqueiaPassagemAereos) {
        return false;
    }

    return true;
}



}    
