import java.util.ArrayList;

public class Ambiente{
    
    private int alturaY;
    private int larguraX;
    public ArrayList<Robo> listadeRobos;

    public Ambiente(int alturaY, int larguraX){
        this.alturaY = alturaY;
        this.larguraX = larguraX;
        this.listadeRobos=new ArrayList<Robo>();
    }

    public boolean dentroDosLimites (int y, int x){
        return x>=0 && x<this.larguraX && y>=0 && y<this.alturaY;
    
    }
    ArrayList<Robo> listaderobos = new ArrayList<>();

    public void adicionarRobo(Robo r){
        listadeRobos.add(r);
        System.out.print("Robo "+r.getNome()+" adicionado!");
    }

}