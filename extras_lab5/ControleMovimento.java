package robos;
// ControleMovimento.java

import exception.ForaDosLimitesException;
import exception.ObstaculoException;
import exception.RoboDesligadoException;

public class ControleMovimento {
    private Robo robo; // Referência ao robô que este controle pertence

    public ControleMovimento(Robo robo) {
        this.robo = robo;
    }

    public boolean moverRoboPara(int x, int y, int z) throws RoboDesligadoException, ForaDosLimitesException, ObstaculoException {
        if (this.robo.getEstado() == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.robo.getId() + " está desligado e não pode se mover.");
        }

        // Importante: A instância 'ambiente' do robô DEVE ser inicializada para que isso funcione.
        // Se 'robo.ambiente' for null, ocorrerá um NullPointerException aqui.
        if (this.robo.getAmbiente() == null) {
            throw new IllegalStateException("O ambiente do robô não foi inicializado para o ControleMovimento.");
        }

        if (!this.robo.getAmbiente().dentroDosLimites(x, y, z)) {
            throw new ForaDosLimitesException("Destino (" + x + ", " + y + ", " + z + ") está fora dos limites do ambiente.");
        }

        // O método identificarObstaculo ainda está em Robo, e ele também precisa de robo.ambiente
        if (this.robo.identificarObstaculo(x, y, z)) {
            throw new ObstaculoException("Existe um obstáculo bloqueando o destino (" + x + ", " + y + ", " + z + ").");
        }

        this.robo.setX1(x); // Usando setters para atualizar a posição no objeto Robo
        this.robo.setY1(y);
        this.robo.setZ(z);
        return true;
    }
}