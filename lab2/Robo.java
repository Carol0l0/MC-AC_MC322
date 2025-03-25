public class Robo{
    
    private String nome;
    protected String direcao;
    public int posicaoX;
    public int posicaoY;
    public int posicaoZ;

    public String getNome(){
        return nome;
    }

    public Robo(String nome, int posicaoX, int posicaoY, int posicaoZ){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
    }

    public boolean mover(int deltaX, int deltaY, Ambiente a){
        if(a.dentroDosLimites(this.posicaoY+deltaY, this.posicaoX+deltaX) && identificarObstaculo(a, this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
            this.posicaoX+=deltaX;
            this.posicaoY+=deltaY;
            return true;
        }
        System.out.println("Limite excedido!");
        return false;

    }

    public Boolean identificarObstaculo(Ambiente a, int x, int y, int z) {
        for (Robo robo : a.listadeRobos) {
            if (this!=robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
                System.out.println("Obstáculo detectado! Robô: " + robo.getNome());
                return true;
            }
        }
    
        return false;
    }
    
    public String exibirPosicao(){
        System.out.println(this.nome+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+").");
        return this.posicaoX+" "+this.posicaoY;
    }

}