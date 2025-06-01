class RoboAereo extends Robo{

    private int altitudeMax; //altura máxima

    //Método construtor robô aéreo
    public RoboAereo(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ);
        this.altitudeMax = altitudeMax;
    }

    //aumenta a altitude
    public void subir(int metros, Ambiente a){
        metros=Math.abs(metros);
        if(!identificarObstaculo(this.posicaoX, this.posicaoY, this.posicaoZ+metros)){
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

    //diminui a altitude
    public void descer(int metros, Ambiente a) {
        metros=Math.abs(metros);
        if(!identificarObstaculo(this.posicaoX, this.posicaoY, this.posicaoZ-metros)){
            if (posicaoZ - metros <= altitudeMax) {
                posicaoZ -= metros;
            } else {
                System.out.println("Você excedeu os limites de altitude");
            }
        }
        else{
            System.out.println("Não foi possível subir. Obstáculo detectado em ("+this.posicaoX+", "+this.posicaoY+", "+(this.posicaoZ-metros)+")");
        }
    }

    //método limpo para ser herdado pelas classes filhas
    @Override
    public void executarTarefa() {

    }
}