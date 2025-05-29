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

        int x = this.robo.getX1();
        int y = this.robo.getY1();
        int z = this.robo.getZ();

        for (Obstaculo o : this.robo.ambiente.listadeObstaculos) {
            int xMin = Math.min(o.getX1(), o.getX2());
            int xMax = Math.max(o.getX1(), o.getX2());
            int yMin = Math.min(o.getY1(), o.getY2());
            int yMax = Math.max(o.getY1(), o.getY2());
            int zMin = 0;
            int zMax = o.getZ();

            boolean dentroX = xMax >= x - raio && xMin <= x + raio;
            boolean dentroY = yMax >= y - raio && yMin <= y + raio;
            boolean dentroZ = zMax >= z - raio && zMin <= z + raio;

            if (dentroX && dentroY && dentroZ) {
                this.obstaculosNoRaio.add(o);
            }
        }

        for(Robo r : this.robo.ambiente.listadeRobos){

            boolean dentroX =  r.posicaoX>= x - raio &&  r.posicaoX<= x + raio;
            boolean dentroY =  r.posicaoY>= y - raio &&  r.posicaoY<= y + raio;
            boolean dentroZ =  r.posicaoZ>= z - raio &&  r.posicaoZ<= z + raio;

            if (dentroX && dentroY && dentroZ && this.robo!=r) {
                this.robosNoRaio.add(r);
            }
        }

        this.numDeObstaculos=(this.obstaculosNoRaio.size()+this.robosNoRaio.size());
        exibirObstaculosProximos();

        return obstaculosNoRaio;
    }

    public void exibirObstaculosProximos() {
        if (this.numDeObstaculos==0) {
            System.out.println("Nenhum obstáculo detectado no raio.");
        } else {
            System.out.println(this.numDeObstaculos+" obstáculos detectados próximos ao robô " + this.robo.getNome() + ":");
            for (Obstaculo o : this.obstaculosNoRaio) {
                System.out.println(" - " + o.getTipoObstaculo() + " na área (" + o.getX1() + "," + o.getY1() + ") até (" + o.getX2() + "," + o.getY2() + "), altura: " + o.getZ());
            }
            for (Robo r: this.robosNoRaio){
                System.out.println(" - "+r.getNome()+" na posição ("+r.getX1()+", "+r.getY1()+", "+r.getZ()+")");
            }
        }
    }

    @Override
    public int monitorar() {
        existenciaObstaculos();
        return this.obstaculosNoRaio.size()+this.robosNoRaio.size();
    }
}
