import java.util.ArrayList;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int aX;
    private int aY;
    private int aZ;
    public ArrayList<Entidade> listaEntidades;
    public int som[][][]; //registra a intensidade se som no ambiente
    

    //Construtor para inicializar o ambiente com as dimensões específicas
    public Ambiente(int aX, int aY, int aZ){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listaEntidades=new ArrayList<Entidade>();
        this.som=new int[aX][aY][aZ];
    }

    //Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites (int x, int y, int z){
        return x>=0 && x<this.aX && y>=0 && y<this.aY && z>=0 && z<this.aZ;
    }

    //Para conferir se a entidade pode ser adicionada ao ambiente
    public boolean BloqueioAoAdicionar(int x, int y, int z) {

        for(Entidade e : this.listaEntidades){
            if(e.getTipoEntidade()==TipoEntidade.ROBO){//Se for Rôbo
                if (e.getX1() == x && e.getY1() == y && e.getZ() == z) {
                    return true;
                }
            }
            else{//Se for objeto
                int altura = e.getZ();
                if (x >= e.getX1() && x <= e.getX2() &&
                    y >= e.getY1() && y <= e.getY2() && 
                    z <= altura) {
                    return true;
                }
            }
        }
    
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
            if(existente.getTipoEntidade()==TipoEntidade.ROBO && r.getNome()==existente.getNome()){ 
                return;
            }
        }
    
        // Adiciona o robô à lista
        this.listaEntidades.add(r);
        //ADICIONAR FEATURE DA MATRIZ
        System.out.println("\nRobô " + r.getNome() + " adicionado com sucesso!");
    }
    

    //Método para remover um robô do ambiente
    public void removerRobo(Robo r){
        this.listaEntidades.remove(r);
        //ADICIONAR FEATURE DA MATRIZ
        System.out.println("\nRobo "+r.getNome()+" removido com sucessso!");
    }

    // Adicionar obstáculos no ambiente
    public boolean adicionarObstaculo(Obstaculo o) {
        if (o.podeAdicionar(this)) {
            this.listaEntidades.add(o);
            //ADICIONAR FEATURE DA MATRIZ
            System.out.println("Obstáculo do tipo " + o.getTipoObstaculo() + " adicionado.");
            return true;
        } else {
            System.out.println("Erro: Obstáculo não pôde ser adicionado, pois há sobreposição!");
            return false;
        }
    }

    //Remove obstáculos do ambiente
    public void removerObstaculo(Obstaculo o){
        this.listaEntidades.remove(o);
        //ADICIONAR FEATURE DA MATRIZ
        System.out.println("Obstáculo do tipo " + o.getTipoObstaculo() + " removido.");
    }

    //Encontra Rôbo pela lista
    public Robo buscarRoboPorNome(String nome) { //CONSERTAR PARA RETORNAR TIPO ROBO
        for (Entidade e : listaEntidades) {
            if (e.getNome().equalsIgnoreCase(nome) && e instanceof Robo) {
                return (Robo) e; //CONFERIR SE PEGA
            }
        }
        return null;
    }  
 
    //Adiciona caixa de som
    public boolean adicionaCaixaDeSom(CaixaDeSom c){
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
    public void removeCaixaDeSom(CaixaDeSom c){
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

    //public void exibirSom(int altura){}

    public void detectarColisoes() { //CONCERTAR
        int totalColisoes = 0;
    
        for (Robo robo : listadeRobos) {
            totalColisoes += robo.getTotalColisoes();
        }
    
        System.out.println("Já houveram " + totalColisoes/2 + " colisões no total!");

    }

}

