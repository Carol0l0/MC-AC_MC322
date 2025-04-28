import java.util.ArrayList;

public class SensorProximidade extends Sensor {

    private ArrayList<Obstaculo> obstaculosNoRaio;
    private ArrayList<Robo> robosNoRaio;
    private int numDeObstaculos;

    public SensorProximidade(Robo robo, int raio) {
        super(raio, robo); 
        this.robo = robo;
        this.obstaculosNoRaio=new ArrayList<>();
        this.robosNoRaio=new ArrayList<>();
    }

    private ArrayList<Obstaculo> existenciaObstaculos() {

        int x = this.robo.getPosicaoX();
        int y = this.robo.getPosicaoY();
        int z = this.robo.getPosicaoZ();

        for (Obstaculo o : this.robo.ambiente.listadeObstaculos) {
            int xMin = Math.min(o.getPosicaoX1(), o.getPosicaoX2());
            int xMax = Math.max(o.getPosicaoX1(), o.getPosicaoX2());
            int yMin = Math.min(o.getPosicaoY1(), o.getPosicaoY2());
            int yMax = Math.max(o.getPosicaoY1(), o.getPosicaoY2());
            int zMin = 0;
            int zMax = o.getAltura();

            boolean dentroX = xMax >= x - raio && xMin <= x + raio;
            boolean dentroY = yMax >= y - raio && yMin <= y + raio;
            boolean dentroZ = zMax >= z - raio && zMin <= z + raio;

            if (dentroX && dentroY && dentroZ) {
                this.obstaculosNoRaio.add(o);
            }
        }

        for(Robo r : this.robo.ambiente.listadeRobos){
            if (this.robo!=r && r.posicaoX == x && r.posicaoY == y && r.posicaoZ == z) {
                this.robosNoRaio.add(r);
            }
        }

        this.numDeObstaculos=this.obstaculosNoRaio.size()+this.robosNoRaio.size();
        exibirObstaculosProximos();

        return obstaculosNoRaio;
    }

    public void exibirObstaculosProximos() {
        if (this.numDeObstaculos==0) {
            System.out.println("Nenhum obstáculo detectado no raio.");
        } else {
            System.out.println(this.obstaculosNoRaio.size()+" obstáculos detectados próximos ao robô " + this.robo.getNome() + ":");
            for (Obstaculo o : this.obstaculosNoRaio) {
                System.out.println(" - " + o.getTipo() + " na área (" + o.getPosicaoX1() + "," + o.getPosicaoY1() + ") até (" + o.getPosicaoX2() + "," + o.getPosicaoY2() + "), altura: " + o.getAltura());
            }
            for (Robo r: this.robosNoRaio){
                System.out.println(" - "+r.getNome()+" na posição ("+r.getPosicaoX()+", "+r.getPosicaoY()+", "+r.getPosicaoZ()+")");
            }
        }
    }

    @Override
    public int monitorar() {
        existenciaObstaculos();
        return this.obstaculosNoRaio.size()+this.robosNoRaio.size();
    }
}
