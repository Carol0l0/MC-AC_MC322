import java.util.ArrayList;

public class Ambiente{
    
    private int aX;
    private int aY;
    private int aZ;
    public ArrayList<Robo> listadeRobos;

    public Ambiente(int aX, int aY, int aZ){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listadeRobos=new ArrayList<Robo>();
    }

    public boolean dentroDosLimites (int x, int y, int z){
        return x>=0 && x<this.aX && y>=0 && y<this.aY && z>=0 && z<this.aZ;
    
    }

    public void adicionarRobo(Robo r){
        this.listadeRobos.add(r);
        System.out.print("Robo "+r.getNome()+" adicionado!");
    }

}