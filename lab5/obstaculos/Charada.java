package obstaculos;

/**
 * Representa uma charada com seu enunciado e resposta.
 */
public class Charada {
    // A pergunta da charada.
    private final String enunciado;
    // A resposta correta da charada.
    private final String resposta;

    /**
     * Construtor para criar uma nova charada.
     * @param enunciado O texto da pergunta.
     * @param resposta A resposta esperada.
     */
    public Charada(String enunciado, String resposta) {
        this.enunciado = enunciado;
        this.resposta = resposta;
    }

    /**
     * Retorna o enunciado da charada.
     * @return O enunciado.
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * Retorna a resposta da charada.
     * @return A resposta.
     */
    public String getResposta() {
        return resposta;
    }
}