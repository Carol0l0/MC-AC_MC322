import java.util.ArrayList;

public class SensorProximidade extends Sensor {

    private Robo robo;
    private Ambiente ambiente;

    public SensorProximidade(Robo robo, Ambiente ambiente, int raio) {
        super(raio); 
        this.robo = robo;
        this.ambiente = ambiente;
    }

    private ArrayList<Obstaculo> existenciaObstaculos() {
        ArrayList<Obstaculo> obstaculosNoRaio = new ArrayList<>();

        int x = robo.getPosicaoX();
        int y = robo.getPosicaoY();
        int z = robo.getPosicaoZ();
        int raio = 2;

        for (Obstaculo o : ambiente.listadeObstaculos) {
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
                obstaculosNoRaio.add(o);
            }
        }

        return obstaculosNoRaio;
    }

    public void exibirObstaculosProximos() {
        ArrayList<Obstaculo> detectados = existenciaObstaculos();
        if (detectados.isEmpty()) {
            System.out.println("Nenhum obstáculo detectado no raio de 2 unidades.");
        } else {
            System.out.println("Obstáculos detectados próximos ao robô " + robo.getNome() + ":");
            for (Obstaculo o : detectados) {
                System.out.println(" - " + o.getTipo() + " na área (" + o.getPosicaoX1() + "," + o.getPosicaoY1() + ") até (" + o.getPosicaoX2() + "," + o.getPosicaoY2() + "), altura: " + o.getAltura());
            }
        }
    }
}