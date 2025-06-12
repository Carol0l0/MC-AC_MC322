// ModuloComunicacao.java
public class ModuloComunicacao {
    private Robo robo; // Referência ao robô que este módulo pertence
    private CentralComunicacao central; // Referência à Central de Comunicação (para registrar mensagens)

    public ModuloComunicacao(Robo robo, CentralComunicacao central) {
        this.robo = robo;
        this.central = central;
    }

    /**
     * Envia uma mensagem para outro comunicável, registrando na Central de Comunicação.
     * @param destinatario O objeto que implementa Comunicavel (geralmente outro Robo).
     * @param mensagem A mensagem a ser enviada.
     * @throws RoboDesligadoException Se o robô estiver desligado.
     */
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException {
        if (this.robo.getEstado() == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.robo.getId() + " está desligado e não pode enviar mensagens.");
        }
        // Registra a mensagem na Central de Comunicação
        if (central != null) {
            central.registrarMensagem(this.robo.getId(), mensagem + " (Para: " + ((Robo)destinatario).getId() + ")");
        }
        // Chama o método receberMensagem do destinatário
        destinatario.receberMensagem(mensagem);
    }

    /**
     * Recebe uma mensagem (simulação de recebimento).
     * @param mensagem A mensagem recebida.
     * @throws RoboDesligadoException Se o robô estiver desligado.
     */
    public void receberMensagem(String mensagem) throws RoboDesligadoException {
        if (this.robo.getEstado() == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.robo.getId() + " está desligado e não pode receber mensagens.");
        }
        System.out.println("[" + this.robo.getId() + "] recebeu mensagem: " + mensagem);
    }
}