//Classe que representa um robô genérico

import java.util.ArrayList;

public class Robo implements Entidade{
    
    private String nome;
    private TipoEntidade tipo;
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

    public int getX1() {
        return posicaoX;
    }

    public int getY1() {
        return posicaoY;
    }

    public int getZ() {
        return posicaoZ;
    }

    public TipoEntidade getTipoEntidade(){
        return tipo;
    }

    public String getDescricao(){
        return this.tipo.descricao;
    }

    public char getRepresentacao(){
        return this.tipo.representacao;
    }

    public int getTotalColisoes() {
        return totalColisoes;
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
        this.tipo=TipoEntidade.ROBO;
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
        if(!this.ambiente.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, 0))
        System.out.println("Limite excedido!");
        return false;

    }

    public int totalColisoes = 0;

    public Boolean identificarObstaculo(int x, int y, int z) {
        for (Obstaculo o : ambiente.listadeObstaculos) {
    
            int altura = o.getTipoObstaculo().getAlturaPadrao();
    
            if (x >= o.getX1() && x <= o.getX2() &&
                y >= o.getY1() && y <= o.getY2() &&
                z <= altura) {
    
                if (o instanceof SabioMagico) {
                    SabioMagico sabio = (SabioMagico) o;
                    if (!sabio.desafiar()) {
                        totalColisoes++;  //colisao com obs
                        return true;
                    } else { 
                        return false; }
                } else {
                    if (!o.getTipoObstaculo().podePassar(this)) {
                        System.out.println("Obstáculo detectado! Obstáculo: " + o.getTipoObstaculo() + " impede a passagem.");
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

    public Boolean identificarObstaculoSemSabio(int x, int y, int z) {
        for (Obstaculo o : ambiente.listadeObstaculos) {
    
            int altura = o.getTipoObstaculo().getAlturaPadrao();
    
            if (x >= o.getX1() && x <= o.getX2() &&
                y >= o.getY1() && y <= o.getY2() &&
                z <= altura) {
    
                if (o instanceof SabioMagico) {
                    totalColisoes++;  
                    return true;
                } else {
                    if (!o.getTipoObstaculo().podePassar(this)) {
                        System.out.println("Obstáculo detectado! Obstáculo: " + o.getTipoObstaculo() + " impede a passagem.");
                        totalColisoes++;  
                        return true;
                    }
                }
            }
        }
    
        for (Robo robo : this.ambiente.listadeRobos) {
            if (this != robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
                totalColisoes++; 
                System.out.println("Obstáculo detectado! Robô: " + robo.getNome());
                return true;
            }
        }
    
        return false;
    }
    
    
    //Método para exibir a posição atual do robô
    public void exibirPosicao(){
        System.out.println(this.nome+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+", "+this.posicaoZ+"). Direção "+this.direcao);
    }
    
}