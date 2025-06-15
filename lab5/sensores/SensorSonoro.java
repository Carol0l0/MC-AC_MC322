package sensores;
import robos.Robo;
import ambiente.Ambiente;

public class SensorSonoro extends Sensor{

    public SensorSonoro(int raio, Robo robo){
        super(0, robo);
    }

    @Override
    public int monitorar(){
        int barulho=this.robo.ambiente.som[this.robo.getX1()][this.robo.getY1()][this.robo.getZ()];
        System.out.println("A intensidade do som é "+ barulho);
        return barulho;
    }
    
}
