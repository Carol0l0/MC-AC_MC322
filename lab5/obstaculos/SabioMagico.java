package obstaculos;
import java.util.Scanner;
import entidades.TipoEntidade;

/**
 * Representa o Sábio Mágico, um obstáculo interativo que propõe charadas.
 * O robô só pode passar se responder corretamente.
 */
public class SabioMagico extends Obstaculo{

    // Banco de charadas que o Sábio Mágico utiliza.
    private final BancoDeCharadas banco;


    public SabioMagico(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, TipoObstaculo.SABIOMAGICO);
        this.banco = new BancoDeCharadas(); // Inicializa o banco de charadas.
        this.tipo_e = TipoEntidade.SABIOMAGICO; // Define o tipo de entidade.
    }

    /**
     * Inicia o desafio da charada com o jogador.
     * O jogador deve digitar a resposta correta para passar.
     * @return Verdadeiro se o jogador acertar a charada, falso caso contrário.
     */
    public boolean desafiar() {
        Charada charada = banco.getCharadaAleatoria(); // Obtém uma charada aleatória.
        Scanner dialogo = new Scanner(System.in); // Scanner para entrada do jogador.

        // Exibe o diálogo de introdução e a charada.
        System.out.println("✧･ﾟ: *✧･ﾟ:* 　　 *:･ﾟ✧*:･ﾟ✧");
        System.out.println("As névoas se abrem...");
        System.out.println("Um ser de sabedoria ancestral emerge diante de você.");
        System.out.println("sua voz ecoa pelos ventos do tempo...");
        System.out.println("✧･ﾟ: *✧･ﾟ:* 　　 *:･ﾟ✧*:･ﾟ✧");
        System.out.println("Para conseguir passar, uma charada você deve acertar! " + charada.getEnunciado());
        System.out.print("Digite sua resposta: ");
        String respostaJogador = dialogo.nextLine().trim().toLowerCase(); // Lê e formata a resposta do jogador.

        String respostaCorreta = charada.getResposta().trim().toLowerCase(); // Formata a resposta correta.

        dialogo.close(); // Fecha o scanner para evitar vazamento de recursos.

        // Verifica se a resposta do jogador está correta e exibe a mensagem apropriada.
        if (respostaJogador.equals(respostaCorreta)) {
            System.out.println("O Sábio Mágico levanta a mão e o ar ao redor começa a brilhar como se estivesse tocado por uma aurora.\n" + //
                                "\"Com grande sabedoria, você conquistou o direito de seguir...\"\n" + //
                                "\"O véu que bloqueia seu caminho se desfaz, e agora você pode seguir em frente.\"\n" + //
                                "\"Vá com a luz da sabedoria sempre ao seu lado.\"");
            return true; // Acertou.
        } else {
            System.out.println("O Sábio Mágico olha para você com uma expressão de sabedoria sombria.\n" + //
                                "\"Você falhou em desvendar o enigma... A passagem permanece fechada.\"\n" + //
                                "\"Mas não desista. A verdadeira sabedoria não é conquistada com facilidade.\"\n" + //
                                "\"Volte quando estiver mais preparado.\"\n" + //
                                "");
            return false; // Errou.
        }
    }
}