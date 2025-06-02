//Classe que representa um robô genérico

import java.util.ArrayList;

public abstract class Robo implements Entidade, Comunicavel, Sensoreavel{
    
    private String id;
    protected String direcao;
    protected Ambiente ambiente;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ;
    private ArrayList<Sensor> sensores;
    protected EstadoRobo estado;
    protected TipoEntidade tipoEntidade = TipoEntidade.ROBO;

    public EstadoRobo getEstado(){
        return this.estado;
    }
    
    public String getId() {
        return this.id;
    }

    public int getX1() {
        return this.posicaoX;
    }

    public int getX2(){
        return this.posicaoX;
    }

    public int getY1() {
        return this.posicaoY;
    }

    public int getY2() {
        return this.posicaoY;
    }
    
    public int getZ() {
        return this.posicaoZ;
    }

    public void setX1(int x){
        this.posicaoX=x;
    }

    public void setX2(int x){
        this.posicaoX=x;
    }

    public void setY1(int y){
        this.posicaoY=y;
    }

    public void setY2(int y){
        this.posicaoY=y;
    }
    
    public TipoEntidade getTipoEntidade(){
        return tipoEntidade;
    }

    public String getDescricao(){
        return this.tipoEntidade.descricao;
    }

    public char getRepresentacao(){
        return this.tipoEntidade.representacao;
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

    public Boolean identificarObstaculo(int x, int y, int z) { //Checa se existe uma entidade ocupando a posição dada

        for(Entidade e : ambiente.listaEntidades){
            if(e instanceof Robo){ //Identifica robôs
                if (this != e && e.getX1() == x && e.getY1() == y && e.getZ() == z) {
    
                    totalColisoes++; //colisao com rôbo
        
                    System.out.println("Obstáculo detectado! Robô: " + e.getId());
                    return true;
                }    
            }
            else if(e instanceof Obstaculo){
                int altura = e.getZ();

                if (x >= e.getX1() && x <= e.getX2() &&
                    y >= e.getY1() && y <= e.getY2() &&
                    z <= altura) { //Se o obstáculo ocupa o mesmo espaço do rôbo 

                    if (e instanceof SabioMagico) {
                        SabioMagico sabio = (SabioMagico) e;
                        if (!sabio.desafiar()) {
                            totalColisoes++;  //colisao com o sábio mágico
                            return true;
                        } 
                        else{ //Deixou passar
                            return false; 
                        }
                    } 
                    else{
                        Obstaculo o = (Obstaculo) e;
                        if (!o.getTipoObstaculo().podePassar(this)) { //se o Rôbo não passa pelo tipo de obstáculo
                            System.out.println("Obstáculo detectado! Obstáculo: " + o.getTipoObstaculo() + " impede a passagem.");
                            totalColisoes++;  //colisao com obstáculo
                            return true;
                        }
                    }
                }
            }
        }
    
        return false;
    }

    public Boolean identificarObstaculoSemSabio(int x, int y, int z){ //Método criado para não ocorrerem erros na hora de rodar a função contarObstaculos()

        for(Entidade e : ambiente.listaEntidades){
            if(e.getTipoEntidade()==TipoEntidade.ROBO){//Se for um Rôbo
                if(e instanceof Robo){
                    Robo robo = (Robo) e;
                    if (this != robo && robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z) {
                        totalColisoes++; //Colisao com rôbo
                        System.out.println("Obstáculo detectado! Robô: " + robo.getId());
                        return true;
                    }
                }
                else{//Se for um obstáculo
                    int altura = e.getZ();
            
                    if (x >= e.getX1() && x <= e.getX2() &&
                        y >= e.getY1() && y <= e.getY2() &&
                        z <= altura) {
            
                        if (e instanceof SabioMagico) {
                            totalColisoes++;//Colisão com sábio mágico  
                            return true;
                        } 
                        else {
                            Obstaculo o = (Obstaculo) e;
                            if (!o.getTipoObstaculo().podePassar(this)) {//Não pode passar
                                System.out.println("Obstáculo detectado! Obstáculo: " + o.getId() + " impede a passagem.");
                                totalColisoes++; //Colisão com obstáculo
                                return true;
                            }
                        }
                    }
                }

            }
        }
    
        return false;
    }

    //Método para ver se o rôbo pode ser adicionado em um Ambiente a
    public Boolean podeAdicionar(Ambiente a){
        for(Entidade e : a.listaEntidades){
            if(e.getTipoEntidade()==TipoEntidade.ROBO){ //conferindo se o rôbo impede a adição
                int x=e.getX1();
                int y=e.getY1();
                int z=e.getZ();
                if (this.posicaoX==x && this.posicaoY==y && this.posicaoZ==z) {
                    System.out.println("Obstáculo detectado! Robô: " + e.getId());
                    return false;
                }
            }
            else{ //conferindo se os obstáculos impedem a adição
                if(this.posicaoX >= e.getX1() && this.posicaoX <= e.getX2() &&
                this.posicaoY >= e.getY1() && this.posicaoY <= e.getY2()) {
                    System.out.println("Obstáculo detectado! " + e.getId());
                    return false;
                }
            }
        }
   
       return true;
    }
    
    //Método para exibir a posição atual do robô
    public void exibirPosicao(){
        System.out.println(this.id +" esta na posicao ("+this.posicaoX+", "+this.posicaoY+", "+this.posicaoZ+"). Direção "+this.direcao);
    }

    //proximos 3 metodos foram herdados da interface comunicavel e usam a classe centralComunicacao, preferi implementar as interfaces no robo geral pq acredito que todos robos irão usar
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode enviar mensagens.");
        }
        CentralComunicacao.enviarMensagem(this, destinatario, mensagem);
    }
    
    public void receberMensagem(String mensagem) throws RoboDesligadoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode receber mensagens.");
        }
        System.out.println("[" + this.id + "] recebeu mensagem: " + mensagem);
    }

    public void acionarSensores() throws RoboDesligadoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode usar sensores.");
        }
        usarSensores();
    }

    public void moverPara(int x, int y, int z) throws RoboDesligadoException, ForaDosLimitesException, ObstaculoException {
        if (this.estado == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.id + " está desligado e não pode se mover.");
        }
    
        if (!ambiente.dentroDosLimites(x, y, z)) {
            throw new ForaDosLimitesException("Destino (" + x + ", " + y + ", " + z + ") está fora dos limites do ambiente.");
        }
    
        if (identificarObstaculo(x, y, z)) {
            throw new ObstaculoException("Existe um obstáculo bloqueando o destino (" + x + ", " + y + ", " + z + ").");
        }
    
        this.posicaoX = x;
        this.posicaoY = y;
        this.posicaoZ = z;

        //adicionar atualização mapa e lista de entidade

        System.out.println("Robô " + this.id + " se moveu com sucesso para (" + x + ", " + y + ", " + z + ").");
    }
    
    public abstract void executarTarefa();
}