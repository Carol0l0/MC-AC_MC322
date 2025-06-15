package exception;
public class ObstaculoException extends Exception {

    public ObstaculoException() {
        super("O caminho está bloqueado por um obstáculo.");
    }

    public ObstaculoException(String mensagem) {
        super(mensagem);
    }
}
