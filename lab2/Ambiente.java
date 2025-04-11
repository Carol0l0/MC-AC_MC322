import java.util.ArrayList;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int aX;
    private int aY;
    private int aZ;
    public ArrayList<Robo> listadeRobos;
    public ArrayList<Obstaculo> listadeObstaculos;
    public int som[aX][aY][aZ];
    

    //Construtor para inicializar o ambiente com as dimensões específicas
    public Ambiente(int aX, int aY, int aZ){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listadeRobos=new ArrayList<Robo>();
        this.listadeObstaculos = new ArrayList<Obstaculo>();
        this.som=0000;
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

}


