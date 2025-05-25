public class SensorSonoro extends Sensor{

    public SensorSonoro(int raio, Robo robo){
        super(0, robo);
    }

    @Override
    public int monitorar(){
        int barulho=this.robo.ambiente.som[this.robo.getPosicaoX()][this.robo.getPosicaoY()][this.robo.getPosicaoZ()];
        System.out.println("A intensidade do som é "+ barulho);
        return barulho;
    }
    
}
