package sensores;
import java.util.ArrayList;

import entidades.Entidade;
import obstaculos.Obstaculo;
import robos.Robo;

/**
 * Sensor que detecta a presença de obstáculos e outros robôs em um determinado raio.
 */
public class SensorProximidade extends Sensor {

    // Lista de obstáculos detectados dentro do raio.
    private ArrayList<Obstaculo> obstaculosNoRaio;
    // Lista de robôs detectados dentro do raio.
    private ArrayList<Robo> robosNoRaio;
    // Contador total de obstáculos e robôs detectados.
    private int numDeObstaculos;


    public SensorProximidade(Robo robo, int raio) {
        super(raio, robo); 
        this.robo = robo;
        this.obstaculosNoRaio=new ArrayList<>();
        this.robosNoRaio=new ArrayList<>();
    }

    /**
     * Verifica a existência de obstáculos (incluindo outros robôs) no raio do sensor.
     * Preenche as listas `obstaculosNoRaio` e `robosNoRaio`.
     * @return A lista de obstáculos (apenas objetos Obstaculo, não Robos).
     */
    private ArrayList<Obstaculo> existenciaObstaculos() {

        int x = this.robo.getX1();
        int y = this.robo.getY1();
        int z = this.robo.getZ();

        // Limpa as listas de detecção para uma nova varredura.
        this.obstaculosNoRaio.clear();
        this.robosNoRaio.clear();

        for(Entidade e : this.robo.getAmbiente().listaEntidades){
            if(e instanceof Robo){ // Se a entidade for um robô
                // Calcula se o robô está dentro do raio X, Y, Z.
                boolean dentroX =  e.getX1()>= x - raio &&  e.getX1()<= x + raio;
                boolean dentroY =  e.getY1()>= y - raio &&  e.getY1()<= y + raio;
                boolean dentroZ =  e.getZ()>= z - raio &&  e.getZ()<= z + raio;

                // Adiciona à lista de robôs detectados, excluindo o próprio robô que possui o sensor.
                if (dentroX && dentroY && dentroZ && this.robo!=e) {
                    Robo r = (Robo) e;
                    this.robosNoRaio.add(r);
                }
            }
            else{ // Se a entidade for um obstáculo (não um robô)
                // Calcula as dimensões do obstáculo.
                int xMin = Math.min(e.getX1(), e.getX2());
                int xMax = Math.max(e.getX1(), e.getX2());
                int yMin = Math.min(e.getY1(), e.getY2());
                int yMax = Math.max(e.getY1(), e.getY2());
                int zMin = 0; // Obstáculos começam na altura 0
                int zMax = e.getZ(); // Altura máxima do obstáculo

                // Verifica sobreposição de áreas no raio.
                boolean dentroX = xMax >= x - raio && xMin <= x + raio;
                boolean dentroY = yMax >= y - raio && yMin <= y + raio;
                boolean dentroZ = zMax >= z - raio && zMin <= z + raio;

                if (dentroX && dentroY && dentroZ) {
                    Obstaculo o = (Obstaculo) e;
                    this.obstaculosNoRaio.add(o);
                }
            }
        }

        // Atualiza o contador total de obstáculos (incluindo robôs).
        this.numDeObstaculos=(this.obstaculosNoRaio.size()+this.robosNoRaio.size());
        // Exibe os obstáculos detectados no console.
        exibirObstaculosProximos();

        return obstaculosNoRaio;
    }

    /**
     * Imprime no console os detalhes dos obstáculos e robôs detectados no raio.
     */
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

    /**
     * Retorna uma string formatada com os detalhes dos obstáculos e robôs detectados.
     * Utilizado para registrar no log.
     * @return String com os obstáculos próximos.
     */
    public String retornarObstaculosProximos() {
        if (this.numDeObstaculos==0) {
            return "Nenhum obstáculo detectado no raio.";
        } 
        else {
            String retorno="";
            retorno+=this.numDeObstaculos+" obstáculos detectados próximos ao robô " + this.robo.getId() + ":";
            for (Obstaculo o : this.obstaculosNoRaio) {
                retorno+="\n - " + o.getTipoObstaculo() + " na área (" + o.getX1() + "," + o.getY1() + ") até (" + o.getX2() + "," + o.getY2() + "), altura: " + o.getZ();
            }
            for (Robo r: this.robosNoRaio){
                retorno+="\n - "+r.getId()+" na posição ("+r.getX1()+", "+r.getY1()+", "+r.getZ()+")";
            }
            return retorno;
        }
    }

    /**
     * Realiza a monitorização de proximidade.
     * @return Uma string detalhando os obstáculos e robôs próximos.
     */
    @Override
    public String monitorar() {
        existenciaObstaculos(); // Executa a lógica de detecção.
        return this.retornarObstaculosProximos(); // Retorna o resultado formatado.
    }
}