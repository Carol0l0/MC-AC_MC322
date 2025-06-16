package robos;
// ControleMovimento.java

public class ControleMovimento {
    private Robo robo; // Referência ao robô que este controle pertence

    public ControleMovimento(Robo robo) {
        this.robo = robo;
    }

    public boolean moverRobo(int deltaX, int deltaY){
        return this.robo.mover(deltaX, deltaY);
    }
}