import java.util.ArrayList;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int aX;
    private int aY;
    private int aZ;
    public ArrayList<Entidade> listaEntidades;
    public ArrayList<Robo> listadeRobos;
    public ArrayList<Obstaculo> listadeObstaculos;
    public int som[][][]; //registra a intensidade se som no ambiente
    

    //Construtor para inicializar o ambiente com as dimensões específicas
    public Ambiente(int aX, int aY, int aZ){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listaEntidades=new ArrayList<Entidade>();
        //this.listadeRobos=new ArrayList<Robo>();
        //this.listadeObstaculos = new ArrayList<Obstaculo>();
        this.som=new int[aX][aY][aZ];
    }

    //Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites (int x, int y, int z){
        return x>=0 && x<this.aX && y>=0 && y<this.aY && z>=0 && z<this.aZ;
    }

    public boolean BloqueioAoAdicionar(int x, int y, int z) {

        for(Entidade e : this.listaEntidades){
            if(e.getTipoEntidade()==TipoEntidade.OBSTACULO){
                int altura = e.getZ();
                if (x >= e.getX1() && x <= e.getX2() &&
                    y >= e.getY1() && y <= e.getY2() && //CONCERTAR PARA A LISTA DE ENTIDADES FUNCIONAR
                    z <= altura) {
                    return true;
                }
            }
            else{
                if (e.getX1() == x && e.getY1() == y && e.getZ() == z) {
                    return true;
                }
            }
        }

        /*
        for (Obstaculo o : this.listadeObstaculos) {
            int altura = o.getTipoObstaculo().getAlturaPadrao();
            if (x >= o.getX1() && x <= o.getX2() &&
                y >= o.getY1() && y <= o.getY2() &&
                z <= altura) {
                return true; //
            }
        }
    
        for (Robo r : this.listadeRobos) {
            if (r.getX1() == x && r.getY1() == y && r.getZ() == z) {
                return true;
            }
        }
        */
    
        return false;
    }
    
    public void adicionarRobo(Robo r) {
        int x = r.getX1();
        int y = r.getY1();
        int z = r.getZ();
    
        r.setAmbiente(this);
    
        // Verifica se o robô está dentro dos limites
        if (!dentroDosLimites(x, y, z)) {
            System.out.println("Erro: Posição do robô " + r.getNome() + " está fora dos limites do ambiente.");
            return;
        }
    
        // Verifica se existe um obstáculo ou robô já ocupando o espaço
        if (this.BloqueioAoAdicionar(x, y, z)) {
            System.out.println("Erro: Não pode adicionar robô, obstáculo ou outro robô detectado em (" + x + ", " + y + ", " + z + ")!");
            return;
        }

        //verifica se existe outro robô com o mesmo nome
        for(Entidade existente : this.listaEntidades){
            if(r.getNome()==existente.getNome()){ //LISTA DE ENTIDADES
                return; //AAAAAAAAAAAAAAAAAAAAAAAAAAAAH
            }
        }
    
        // Adiciona o robô à lista
        this.listadeRobos.add(r);
        System.out.println("\nRobô " + r.getNome() + " adicionado com sucesso!");
    }
    

    //Método para remover um robô do ambiente
    public void removerRobo(Robo r){
        this.listadeRobos.remove(r);
        //ADICIONAR FEATURE DA MATRIZ
        System.out.println("\nRobo "+r.getNome()+" removido com sucessso!");
    }

    // Adicionar obstáculos no ambiente
    public boolean adicionarObstaculo(Obstaculo o) {
        if (o.podeAdicionar(this)) {
            this.listadeObstaculos.add(o); //LISTA DE ENTIDADES
            System.out.println("Obstáculo do tipo " + o.getTipoObstaculo() + " adicionado.");
            return true;
        } else {
            System.out.println("Erro: Obstáculo não pôde ser adicionado, pois há sobreposição!");
            return false;
        }
    }

    //Remove obstáculos do ambiente
    public void removerObstaculo(Obstaculo o){ //LISTA DE ENTIDADES
        this.listadeObstaculos.remove(o);
        System.out.println("Obstáculo do tipo " + o.getTipoObstaculo() + " removido.");
    }

    public Robo buscarRoboPorNome(String nome) { //LISTA DE ENTIDADES
        for (Robo r : listadeRobos) {
            if (r.getNome().equalsIgnoreCase(nome)) {
                return r;
            }
        }
        return null;
    }  
    
    public ArrayList<Obstaculo> getListadeObstaculos() {//LISTA DE ENTIDADES
        return listadeObstaculos;
    }
 
    //Adiciona caixa de som
    public boolean adicionaCaixaDeSom(CaixaDeSom c){ //LISTA DE ENTIDADES
        if(adicionarObstaculo(c)){
            int x=c.getX1(), y=c.getY1(), z=0, intensidade=c.getIntensidade();
            for (int i=intensidade; i>0; i--) {
                for (int j=x-i; j<=x+i; j++) {
                    for (int k=y-i; k<=y+i; k++) {
                        for (int l=z-i; l<=z+i; l++) {
                            if (j>=0 && j<10 && k>=0 && k<10 && l>=0 && l<10) {
                                this.som[j][k][l]++;
                            }
                        }
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
    
    }

    //remove caixa de som do ambiente
    public void removeCaixaDeSom(CaixaDeSom c){ //LISTA DE ENTIDADES
        removerObstaculo(c);
        int x=c.getX1(), y=c.getY1(), z=0, intensidade=c.getIntensidade();
        for (int i=intensidade; i>0; i--) {
            for (int j=x-i; j<=x+i; j++) {
                for (int k=y-i; k<=y+i; k++) {
                    for (int l=z-i; l<=z+i; l++) {
                        if (j>=0 && j<10 && k>=0 && k<10 && l>=0 && l<10) {
                            this.som[j][k][l]--;
                        }
                    }
                }
            }
        }
    }

    public void detectarColisoes() {
        int totalColisoes = 0;
    
        for (Robo robo : listadeRobos) {
            totalColisoes += robo.getTotalColisoes();
        }
    
        System.out.println("Já houveram " + totalColisoes/2 + " colisões no total!");

    }

}

