package comunicacao;
import java.util.ArrayList;

import exception.RoboDesligadoException;
import robos.Robo;

public class CentralComunicacao {
    private ArrayList<String> mensagens = new ArrayList<>();

    public void registrarMensagem(String remetente, String msg) {
        mensagens.add("[" + remetente + "]: " + msg);
    }

    public void exibirMensagens() {
        for (String mensagem : mensagens) {
            System.out.println(mensagem);
        }
    }

    //verifica se tanto o remetente quanto o destinatário são do tipo entidade correta, e aí chama o enviar
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
    

