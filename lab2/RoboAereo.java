class RoboAereo extends Robo{

    int altitude;
    int altitudeMax;
    int metros;

    public RoboAereo(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ);
        this.altitudeMax = altitudeMax;
    }

    public void subir(int metros, Ambiente a){
        if(!identificarObstaculo(a, this.posicaoX, this.posicaoY, this.posicaoZ+metros)){
            if (posicaoZ + metros <= altitudeMax) {
                posicaoZ += metros;
            } else {
                System.out.println("Você excedeu os limites de altitude");
            }
        }
        else{
            System.out.println("Não foi possível subir. Obstáculo detectado em ("+this.posicaoX+", "+this.posicaoY+", "+(this.posicaoZ+metros)+")");
        }
    }

    public void descer(int metros, Ambiente a) {
        if(!identificarObstaculo(a, this.posicaoX, this.posicaoY, this.posicaoZ-metros)){
            if (posicaoZ - metros <= altitudeMax) {
                posicaoZ -= metros;
            } else {
                System.out.println("Você excedeu os limites de altitude");
            }
        }
    }
}