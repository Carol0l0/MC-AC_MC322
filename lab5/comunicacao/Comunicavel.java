package comunicacao;
import exception.RoboDesligadoException;

/**
 * Define o comportamento de objetos que podem se comunicar.
 */
public interface Comunicavel {
    
    /**
     * Envia uma mensagem para um destinatário.
     * @param destinatario O receptor da mensagem.
     * @param mensagem O conteúdo da mensagem.
     * @throws RoboDesligadoException Se o remetente estiver desligado.
     */
    void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;

    /**
     * Recebe uma mensagem.
     * @param mensagem O conteúdo da mensagem recebida.
     * @throws RoboDesligadoException Se o receptor estiver desligado.
     */
    void receberMensagem(String mensagem) throws RoboDesligadoException;

}