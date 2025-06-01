public enum TipoColorido {
    RESET ("\u001B[0m"),    
    RED ("\u001B[31m"),
    ORANGE ("\u001B[38;2;255;165;0m"),
    YELLOW ("\u001B[33m"),
    GREEN ("\u001B[32m"),
    CYAN ("\u001B[36m"),
    BLUE ("\\u001B[34m"),
    PURPLE ("\u001B[35m");

    public final String cor;

    TipoColorido(String cor){
        this.cor=cor;
    }
}
