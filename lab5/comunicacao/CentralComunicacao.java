package comunicacao;
import java.util.ArrayList;

import exception.RoboDesligadoException;
import robos.Robo;

/**
 * Gerencia a troca e o registro de mensagens entre robôs.
 */
public class CentralComunicacao {
    // Armazena todas as mensagens registradas.
    private ArrayList<String> mensagens = new ArrayList<>();

    /**
     * Adiciona uma mensagem ao histórico.
     * @param remetente Quem enviou.
     * @param msg Conteúdo da mensagem.
     */
    public void registrarMensagem(String remetente, String msg) {
        mensagens.add("[" + remetente + "]: " + msg);
    }

    /**
     * Exibe todas as mensagens registradas no console.
     */
    public void exibirMensagens() {
        for (String mensagem : mensagens) {
            System.out.println(mensagem);
        }
    }

    /**
     * Envia uma mensagem entre comunicáveis, verificando se são robôs e lidando com robôs desligados.
     * @param remetente O remetente da mensagem.
     * @param destinatario O destinatário da mensagem.
     * @param mensagem O texto da mensagem.
     */
    public static void enviarMensagem(Comunicavel remetente, Comunicavel destinatario, String mensagem) {
        if (!(remetente instanceof Robo) || !(destinatario instanceof Robo)) {
            System.out.println("Um dos envolvidos não é uma entidade válida.");
            return;
        }
    
        try {
            remetente.enviarMensagem(destinatario, mensagem);
        } catch (RoboDesligadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}