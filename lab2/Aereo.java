class Aereo extends Robo{

    int altitude;
    int altitudeMax;
    int metros;

    public Aereo(String nome, int posicaoX, int posicaoY, int altitude, int altitudeMax){
    super(nome, posicaoX,posicaoY);

    this.altitude = altitude;
    this.altitudeMax = altitudeMax;

    public void subir(int metros){
        if (altitude + metros <= altitudeMax) {
            altitude += metros;
        } else {
            System.out.println("voce excedeu os limites");
        }
    }

    public void descer(int metros) {
        if (altitude - metros >= 0) {
            altitude -= metros;
        } else {
            System.out.println("voce excedeu os limites");
            altitude = 0;
        }
    }
}

}