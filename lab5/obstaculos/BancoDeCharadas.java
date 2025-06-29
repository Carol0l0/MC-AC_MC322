package obstaculos;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Armazena e fornece charadas aleatórias para o Sábio Mágico.
 */

public class BancoDeCharadas {
    // Lista de charadas disponíveis.
    private final List<Charada> charadas;
    // Gerador de números aleatórios para selecionar charadas.
    private final Random random;

    public BancoDeCharadas() {
        charadas = new ArrayList<>();
        random = new Random();
        carregarCharadas();
    }

    /**
     * Carrega uma lista predefinida de charadas no banco.
     */
    private void carregarCharadas() {
        charadas.add(new Charada("Quanto mais você tira de mim, maior eu fico. Quem sou?", "buraco"));
        charadas.add(new Charada("Sou invisível, mas posso te carregar por mundos inteiros. Estou em livros e feitiços. O que sou?", "imaginação"));
        charadas.add(new Charada("Apareço à noite, brilho como prata e sou o segredo dos lobisomens.", "lua cheia"));
        charadas.add(new Charada("Sou feito de madeira ou marfim, muitas vezes canto, mas não tenho voz. Quem sou eu?", "flauta"));
        charadas.add(new Charada("Se me esquecer, sua memória vai sofrer. Se me usar bem, tudo vai correr. Quem sou eu?", "free"));
        charadas.add(new Charada("Sou o maior medo dos programadores. Apareço sem ser chamado, e às vezes, nem sei onde estou", "segmentation fault"));
        charadas.add(new Charada("Guardo passos, executo em ordem, mas não tenho pés. Quem sou eu?", "algoritmo"));
        charadas.add(new Charada("Só aceito dois valores, e mesmo assim sou essencial. Verdade ou mentira, quem sou eu?", "boolean"));
        charadas.add(new Charada("Qual o tipo de café preferido dos programadores?", "java"));

    }

    /**
     * Retorna uma charada aleatória do banco.
     * @return Uma instância de Charada.
     */
    public Charada getCharadaAleatoria() {
        int index = random.nextInt(charadas.size());
        return charadas.get(index);
    }
}