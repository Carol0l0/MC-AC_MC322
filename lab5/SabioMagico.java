import java.util.Scanner;

public class SabioMagico extends Obstaculo{

    private final BancoDeCharadas banco;

    public SabioMagico(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2, TipoObstaculo.SABIOMAGICO);
        this.banco = new BancoDeCharadas();
        this.tipo_e = TipoEntidade.SABIOMAGICO;
    }

    public boolean desafiar() {
        Charada charada = banco.getCharadaAleatoria();
        Scanner scanner = new Scanner(System.in);

        System.out.println("✧･ﾟ: *✧･ﾟ:* 　　 *:･ﾟ✧*:･ﾟ✧");
        System.out.println("As névoas se abrem...");
        System.out.println("Um ser de sabedoria ancestral emerge diante de você.");
        System.out.println("sua voz ecoa pelos ventos do tempo...");
        System.out.println("✧･ﾟ: *✧･ﾟ:* 　　 *:･ﾟ✧*:･ﾟ✧");
        System.out.println("Para conseguir passar, uma charada você deve acertar! " + charada.getEnunciado());
        System.out.print("Digite sua resposta: ");
        String respostaJogador = scanner.nextLine().trim().toLowerCase();

        String respostaCorreta = charada.getResposta().trim().toLowerCase();

        if (respostaJogador.equals(respostaCorreta)) {
            System.out.println("O Sábio Mágico levanta a mão e o ar ao redor começa a brilhar como se estivesse tocado por uma aurora.\n" + //
                                "\"Com grande sabedoria, você conquistou o direito de seguir...\"\n" + //
                                "\"O véu que bloqueia seu caminho se desfaz, e agora você pode seguir em frente.\"\n" + //
                                "\"Vá com a luz da sabedoria sempre ao seu lado.\"");
            return true;
        } else {
            System.out.println("O Sábio Mágico olha para você com uma expressão de sabedoria sombria.\n" + //
                                "\"Você falhou em desvendar o enigma... A passagem permanece fechada.\"\n" + //
                                "\"Mas não desista. A verdadeira sabedoria não é conquistada com facilidade.\"\n" + //
                                "\"Volte quando estiver mais preparado.\"\n" + //
                                "");
            return false;
        }
    }
}
 