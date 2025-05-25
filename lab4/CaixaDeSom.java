public class CaixaDeSom extends Obstaculo{
    
    private int intensidade;

    public CaixaDeSom(int x1, int y1, int x2, int y2, TipoObstaculo tipo, int intensidade){
        super(x1, y1, x2, y2, TipoObstaculo.CAIXADESOM);
        this.intensidade=intensidade;
    }

    public int getIntensidade(){
        return intensidade;
    }   
    
}