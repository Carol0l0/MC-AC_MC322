import java.util.ArrayList;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int aX;
    private int aY;
    private int aZ;
    public ArrayList<Robo> listadeRobos;
    public ArrayList<Obstaculo> listadeObstaculos;
    public int som[][][]; //registra a intensidade se som no ambiente
    

    //Construtor para inicializar o ambiente com as dimensões específicas
    public Ambiente(int aX, int aY, int aZ){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listadeRobos=new ArrayList<Robo>();
        this.listadeObstaculos = new ArrayList<Obstaculo>();
        this.som=new int[aX][aY][aZ];
    }

    //Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites (int x, int y, int z){
        return x>=0 && x<this.aX && y>=0 && y<this.aY && z>=0 && z<this.aZ;
    
    }

    // Método para adicionar um robô ao ambiente
    public void adicionarRobo(Robo r) {
        int x = r.getPosicaoX();
        int y = r.getPosicaoY();
        int z = r.getPosicaoZ();

        r.setAmbiente(this);

        // Verifica se o robô está dentro dos limites
        if (!dentroDosLimites(x, y, z)) {
            System.out.println("Erro: Posição do robô " + r.getNome() + " está fora dos limites do ambiente.");
            return;
        }

        //Verifica se existe um obstáculo
        if(r.identificarObstaculo(x, y, z)){
            System.out.println("Erro: não pode adicionar robô, obstáculo detectado em ("+x+", "+y+", "+z+")!");
            return;
        }

        // Adiciona o robô à lista
        this.listadeRobos.add(r);
        System.out.println("\nRobo " + r.getNome() + " adicionado com sucesso!");
    }

    public ArrayList<Obstaculo> getListadeObstaculos() {
        return listadeObstaculos;
    }
    
    //Método para remover um robô do ambiente
    public void removerRobo(Robo r){
        this.listadeRobos.remove(r);
        System.out.println("\nRobo "+r.getNome()+" removido com sucessso!");
    }

    //Adicionar obstáculos no ambiente
    public void adicionarObstaculo(Obstaculo o) {
        if(o.podeAdicionar(this)){
            this.listadeObstaculos.add(o);
            System.out.println("Obstáculo do tipo " + o.getTipo() + " adicionado.");
        }
    }

    //Remove obstáculos do ambiente
    public void removerObstaculo(Obstaculo o){
        this.listadeObstaculos.remove(o);
        System.out.println("Obstáculo do tipo " + o.getTipo() + " removido.");
    }

    public Robo buscarRoboPorNome(String nome) {
        for (Robo r : listadeRobos) {
            if (r.getNome().equalsIgnoreCase(nome)) {
                return r;
            }
        }
        return null;
    }    
 
    //Adiciona caixa de som
    public void adicionaCaixaDeSom(CaixaDeSom c){
        adicionarObstaculo(c);
        int x=c.getPosicaoX1(), y=c.getPosicaoY1(), z=0, intensidade=c.getIntensidade();
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
    }

    public void removeCaixaDeSom(CaixaDeSom c){
        removerObstaculo(c);
        int x=c.getPosicaoX1(), y=c.getPosicaoY1(), z=0, intensidade=c.getIntensidade();
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

}