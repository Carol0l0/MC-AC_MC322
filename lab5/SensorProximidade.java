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

        for(Entidade e : this.robo.ambiente.listaEntidades){
            if(e instanceof Robo){ //Se for um rôbo
                boolean dentroX =  e.getX1()>= x - raio &&  e.getX1()<= x + raio;
                boolean dentroY =  e.getY1()>= y - raio &&  e.getY1()<= y + raio;
                boolean dentroZ =  e.getZ()>= z - raio &&  e.getZ()<= z + raio;

                if (dentroX && dentroY && dentroZ && this.robo!=e) {
                    Robo r = (Robo) e;
                    this.robosNoRaio.add(r);
                }
            }
            else{ // Obstáculo
                int xMin = Math.min(e.getX1(), e.getX2());
                int xMax = Math.max(e.getX1(), e.getX2());
                int yMin = Math.min(e.getY1(), e.getY2());
                int yMax = Math.max(e.getY1(), e.getY2());
                int zMin = 0;
                int zMax = e.getZ();

                boolean dentroX = xMax >= x - raio && xMin <= x + raio;
                boolean dentroY = yMax >= y - raio && yMin <= y + raio;
                boolean dentroZ = zMax >= z - raio && zMin <= z + raio;

                if (dentroX && dentroY && dentroZ) {
                    Obstaculo o = (Obstaculo) e;
                    this.obstaculosNoRaio.add(o);
                }
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
            System.out.println(this.numDeObstaculos+" obstáculos detectados próximos ao robô " + this.robo.getId() + ":");
            for (Obstaculo o : this.obstaculosNoRaio) {
                System.out.println(" - " + o.getTipoObstaculo() + " na área (" + o.getX1() + "," + o.getY1() + ") até (" + o.getX2() + "," + o.getY2() + "), altura: " + o.getZ());
            }
            for (Robo r: this.robosNoRaio){
                System.out.println(" - "+r.getId()+" na posição ("+r.getX1()+", "+r.getY1()+", "+r.getZ()+")");
            }
        }
    }

    @Override
    public int monitorar() {
        existenciaObstaculos();
        return this.obstaculosNoRaio.size()+this.robosNoRaio.size();
    }
}