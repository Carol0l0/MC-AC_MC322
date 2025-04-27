//Classe que representa um robô genérico

import java.util.ArrayList;

public class Robo{
    
    private String nome;
    protected String direcao;
    public int posicaoX;
    public int posicaoY;
    public int posicaoZ;
    private ArrayList<Sensor> sensores;

    //método para obter o nome do robô
    public String getNome(){
        return nome;
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
    public boolean mover(int deltaX, int deltaY, Ambiente a){
        if(a.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, 0) && !identificarObstaculo(a, this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
            this.posicaoX+=deltaX;
            this.posicaoY+=deltaY;
            return true;
        }
        System.out.println("Limite excedido!");
        return false;

    }

    //Método para verificar se há um obstáculo na nova posição final
    public Boolean identificarObstaculo(Ambiente a, int x, int y, int z) {
        for (Robo robo : a.listadeRobos) {
            if (this!=robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
                System.out.println("Obstáculo detectado! Robô: " + robo.getNome());
                return true;
            }
        }
    
        return false;
    }

    //Método para exibir a posição atual do robô
    public String exibirPosicao(){
        System.out.println(this.nome+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+", "+this.posicaoZ+"). Direção "+this.direcao);
        return this.posicaoX+" "+this.posicaoY;
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

    
    
}



