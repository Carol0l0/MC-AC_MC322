package sensores;
import robos.Robo;

public class SensorSonoro extends Sensor{

    public SensorSonoro(int raio, Robo robo){
        super(0, robo);
    }

    @Override
    public String monitorar(){//Retorna String para registrar no log
        int barulho=this.robo.getAmbiente().som[this.robo.getX1()][this.robo.getY1()][this.robo.getZ()];
        System.out.println("A intensidade do som é "+ barulho);
        return "A intensidade do som é "+ barulho;
    }

    public int monitorarInt(){//Retorna int para a missão Fiscalizar Som
        int barulho=this.robo.getAmbiente().som[this.robo.getX1()][this.robo.getY1()][this.robo.getZ()];
        System.out.println("A intensidade do som é "+ barulho);
        return barulho;
    }
    
}
