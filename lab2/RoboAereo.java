class RoboAereo extends Robo{

    int altitude;
    int altitudeMax;
    int metros;

    public RoboAereo(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ);
        this.altitudeMax = altitudeMax;
    }

    public void subir(int metros){
        if (posicaoZ + metros <= altitudeMax) {
            posicaoZ += metros;
        } else {
            System.out.println("Você excedeu os limites");
        }
    }

    public void descer(int metros) {
        if (posicaoZ - metros >= 0) {
            posicaoZ -= metros;
        } else {
            System.out.println("Você excedeu os limites");
            posicaoZ = 0;
        }
    }
}