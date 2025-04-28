//Classe que representa um robô genérico

import java.util.ArrayList;

public class Robo{
    
    private String nome;
    protected String direcao;
    protected Ambiente ambiente;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ;
    private ArrayList<Sensor> sensores;

    //método para obter o nome do robô
    public String getNome(){
        return nome;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public int getPosicaoZ() {
        return posicaoZ;
    }

    //define ambiente
    public void setAmbiente(Ambiente a){
        this.ambiente=a;
    }

    //Construtor para inicializar um robô em uma posição específica
    public Robo(String nome, int posicaoX, int posicaoY, int posicaoZ){
        this.nome = nome;
        this.direcao="Norte";
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
        this.sensores = new ArrayList<>();
    }

    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    public void usarSensores() {
        for (Sensor s : sensores) {
            s.monitorar();
        }
    }

    //Método para mover o robô dentro do ambiente, verificando obstáculos e limites de borda
    public boolean mover(int deltaX, int deltaY){
        if(this.ambiente.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, 0) && !identificarObstaculo(this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
            this.posicaoX+=deltaX;
            this.posicaoY+=deltaY;
            return true;
        }
        System.out.println("Limite excedido!");
        return false;

    }

    public int totalColisoes = 0;

    public Boolean identificarObstaculo(int x, int y, int z) {
        for (Obstaculo o : ambiente.listadeObstaculos) {
    
            int altura = o.getTipo().getAlturaPadrao();
    
            if (x >= o.getPosicaoX1() && x <= o.getPosicaoX2() &&
                y >= o.getPosicaoY1() && y <= o.getPosicaoY2() &&
                z <= altura) {
    
                if (o instanceof SabioMagico) {
                    SabioMagico sabio = (SabioMagico) o;
                    if (!sabio.desafiar()) {
                        totalColisoes++;  //colisao com obs
                        return true;

                    }
                } else {
                    if (!o.getTipo().podePassar(this)) {
                        System.out.println("Obstáculo detectado! Obstáculo: " + o.getTipo() + " impede a passagem.");
                        totalColisoes++;  //colisao com obs
                        return true;

                    }
                }
            }
        }
    
        for (Robo robo : this.ambiente.listadeRobos) {
            if (this != robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
    
                totalColisoes++; //colisao com robo
    
                System.out.println("Obstáculo detectado! Robô: " + robo.getNome());
                return true;
            }
        }
    
        return false;
    }

    public int getTotalColisoes() {
        return totalColisoes;
    }
    
    //Método para exibir a posição atual do robô
    public void exibirPosicao(){
        System.out.println(this.nome+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+", "+this.posicaoZ+"). Direção "+this.direcao);
    }
    
}