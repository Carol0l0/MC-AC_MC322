public enum TipoObstaculo {

    //priemiro parametro qual até qual altura ele bloqueia
    PAREDE(3, true),
    ARVORE(5, true),
    PREDIO(10, true),
    BURACO(0, true),
    OUTRO(-1, false); 

    private final int alturaPadrao;
    private final boolean bloqueiaPassagem;

    TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagem) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagem = bloqueiaPassagem;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    public boolean bloqueiaPassagem() {
        return bloqueiaPassagem;
    }
}
