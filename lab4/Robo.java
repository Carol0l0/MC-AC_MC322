//Classe que representa um robô genérico

import java.util.ArrayList;

public class Robo implements Entidade, Comunicavel, Sensoreavel{
    
    private String id;
    protected String direcao;
    protected Ambiente ambiente;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ;
    private ArrayList<Sensor> sensores;

    protected EstadoRobo estado;
    protected TipoEntidade tipoEntidade = TipoEntidade.ROBO;

    public String getId(){
        return id;
    }
    
    @Override
    public int getX() {
        return this.posicaoX;
    }

    @Override
    public int getY() {
        return this.posicaoY;
    }
    
    @Override
    public int getZ() {
        return this.posicaoZ;
    }
    
    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.ROBO;
    }
    
    @Override
    public String getDescricao() {
        return "Robô " + this.id;
    }
    
    @Override
    public char getRepresentacao() {
        return 'R';
    }

    public int getTotalColisoes() {
        return totalColisoes;
    }
    
    public void setAmbiente(Ambiente a){
        this.ambiente=a;
    }

    public void ligar() {
        this.estado = EstadoRobo.LIGADO;
        System.out.println(id + " foi ligado.");
    }

    public void desligar() {
        this.estado = EstadoRobo.DESLIGADO;
        System.out.println(id + " foi desligado.");
    }

    public Robo(String id, int posicaoX, int posicaoY, int posicaoZ) {
        this.id = id;
        this.direcao = "Norte";
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
        this.sensores = new ArrayList<>();
        this.estado = EstadoRobo.DESLIGADO; 
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
        if(this.ambiente.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, 0) && !identificarObstaculo(this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ) && !identificarRoboNoCaminho(deltaX, deltaY, 0)){
            this.posicaoX+=deltaX;
            this.posicaoY+=deltaY;
            return true;
        }
        if(!this.ambiente.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, 0))
        System.out.println("Limite excedido!");
        return false;

    }

    public int totalColisoes = 0;

    public boolean identificarObstaculo(int x, int y, int z) {
        for (Obstaculo o : ambiente.listadeObstaculos) {
    
            int altura = o.getTipo().getAlturaPadrao();
    
            if (x >= o.getPosicaoX1() && x <= o.getPosicaoX2() &&
                y >= o.getPosicaoY1() && y <= o.getPosicaoY2() &&
                z <= altura) {
    
                if (o instanceof SabioMagico) {
                    SabioMagico sabio = (SabioMagico) o;
                    if (!sabio.desafiar()) {
                        totalColisoes++;
                        return true; // bloqueado pelo sábio
                    }
                    // Se o desafio for vencido, continua procurando outros obstáculos
                    continue;
                } else {
                    if (!o.getTipo().podePassar(this)) {
                        System.out.println("Obstáculo detectado! Obstáculo: " + o.getTipo() + " impede a passagem.");
                        totalColisoes++;
                        return true; // bloqueado por obstáculo comum
                    }
                }
            }
        }
    
        return false; // nenhum obstáculo impede a passagem
    }


    public Boolean identificarRoboNoCaminho(int x, int y, int z){
        for (Robo robo : this.ambiente.listadeRobos) {
            if (this != robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
                totalColisoes++; 
                System.out.println("Obstáculo detectado! Robô: " + robo.getId());
                return true;
            }
        }
    
        return false;
    }

    public Boolean identificarObstaculoSemSabio(int x, int y, int z) {
        for (Obstaculo o : ambiente.listadeObstaculos) {
    
            int altura = o.getTipo().getAlturaPadrao();
    
            if (x >= o.getPosicaoX1() && x <= o.getPosicaoX2() &&
                y >= o.getPosicaoY1() && y <= o.getPosicaoY2() &&
                z <= altura) {
    
                if (o instanceof SabioMagico) {
                    totalColisoes++;  
                    return true;
                } else {
                    if (!o.getTipo().podePassar(this)) {
                        System.out.println("Obstáculo detectado! Obstáculo: " + o.getTipo() + " impede a passagem.");
                        totalColisoes++;  
                        return true;
                    }
                }
            }
        }
    
        for (Robo robo : this.ambiente.listadeRobos) {
            if (this != robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
                totalColisoes++; 
                System.out.println("Obstáculo detectado! Robô: " + robo.getId());
                return true;
            }
        }
    
        return false;
    }
    
    
    //Método para exibir a posição atual do robô
    public void exibirPosicao(){
        System.out.println(this.id+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+", "+this.posicaoZ+"). Direção "+this.direcao);
    }

    public void setX(int x) {
        this.posicaoX = x;
    }
    
    public void setY(int y) {
        this.posicaoY = y;
    }
    
    public void setZ(int z) {
        this.posicaoZ = z;
    }

    //proximos 3 metodos foram herdados da interface comunicavel e usam a classe centralComunicacao, preferi implementar as interfaces no robo geral pq acredito que todos robos irão usar
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode enviar mensagens.");
        }
        CentralComunicacao.enviar(this, destinatario, mensagem);
    }
    
    @Override
    public void receberMensagem(String mensagem) throws RoboDesligadoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode receber mensagens.");
        }
        System.out.println("[" + this.id + "] recebeu mensagem: " + mensagem);
    }

    @Override
    public void acionarSensores() throws RoboDesligadoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode usar sensores.");
        }
        usarSensores();
    }
    
}