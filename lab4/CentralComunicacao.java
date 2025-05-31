import java.util.ArrayList;

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

    //verificar se a entidade e do tipo comunicavel e fazer cast pra garantir que funcionara
    public void enviarMsg(Entidade remetente, Entidade destinatario, String mensagem) {
        if (!(remetente instanceof Comunicavel)) {
            System.out.println("Remetente não pode enviar mensagens.");
            return;
        }
    
        if (!(destinatario instanceof Comunicavel)) {
            System.out.println("Destinatário não pode receber mensagens.");
            return;
        }
    
        Comunicavel cRemetente = (Comunicavel) remetente;
        Comunicavel cDestinatario = (Comunicavel) destinatario;
    
        try {
            cRemetente.enviarMensagem(cDestinatario, mensagem);
        } catch (RoboDesligadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    
}
    

