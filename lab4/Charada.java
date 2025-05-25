public class Charada {
    private final String enunciado;
    private final String resposta;

    public Charada(String enunciado, String resposta) {
        this.enunciado = enunciado;
        this.resposta = resposta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getResposta() {
        return resposta;
    }
}