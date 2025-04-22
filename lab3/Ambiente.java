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

    //Método para adicionar um robô ao ambiente
    public void adicionarRobo(Robo r){
        this.listadeRobos.add(r);
        System.out.println("\nRobo "+r.getNome()+" adicionado com sucesso!");
    }

    //Método para remover um robô do ambiente
    public void removerRobo(Robo r){
        this.listadeRobos.remove(r);
        System.out.println("\nRobo "+r.getNome()+" removido com sucessso!");

    }

    //Adicionar obstáculos no ambiente
    public void adicionarObstaculo(Obstaculo o) {
        this.listadeObstaculos.add(o);
        System.out.println("Obstáculo do tipo " + o.getTipo() + " adicionado.");
    }

    //Adiciona caixa de som
    public void adicionaCaixaDeSom(CaixaDeSom c){
        adicionarObstaculo(c);
        int x=c.getPosicaoX1(), y=c.getPosicaoY1(), intensidade=c.getIntensidade();
        for(int i=intensidade; i>0; i--){
            for(int j=x-i; j<=x+i; j++){
                for(int k=y-i; k<=y+i; k++){
                    som[j][k][0]++;
                }
            }
        }
    }

}


