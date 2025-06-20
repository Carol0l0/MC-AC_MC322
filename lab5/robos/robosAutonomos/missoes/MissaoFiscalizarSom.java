package robos.robosAutonomos.missoes;

import robos.EstadoRobo;
import robos.Robo;
import robos.robosAutonomos.*;
import sensores.SensorSonoro;
import exception.RoboDesligadoException;

public class MissaoFiscalizarSom implements Missao{
    private String descricao;
    private int somPermitido;
    private TipoMissao tipoM;
    private Log log;
    private Robo robo;

    public MissaoFiscalizarSom(int somPermitido, Robo robo) { 
        this.somPermitido=somPermitido;
        this.tipoM=TipoMissao.FISCALIZARSOM;
        this.robo = robo;
        this.descricao="O robô irá checar se o som em sua posição está dentro dos limites permitidos para evitar incômodos";
    }

    @Override
    public TipoMissao getTipoMissao(){
        return this.tipoM;
    }
    
    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public Robo getRobo(){
        return this.robo;
    }

    @Override
    public boolean executar(AgenteInteligente agente) throws RoboDesligadoException{
        if(agente.getEstado()==EstadoRobo.DESLIGADO){
            log.adicionarLinha(this.robo.getId()+" falhou na missão pois está desligado");
            throw new RoboDesligadoException("Robô falhou na missão pois está desligado");
        }
        else{
            SensorSonoro sensorSom=new SensorSonoro(0, agente);
            if(sensorSom.monitorarInt()>somPermitido){
                log.adicionarLinha(this.robo.getId()+" acionou sensor sonoro:");
                log.adicionarLinha("A intensidade sonora está acima do permitido: "+this.somPermitido);
                System.out.println("A intensidade sonora está acima do permitido: "+this.somPermitido);
                System.out.println("--- Missão de Fiscalização de Som concluída com sucesso por " + agente.getId() + " ---");
                return false;
            }
            log.adicionarLinha(this.robo.getId()+" acionou sensor sonoro:");
            log.adicionarLinha("A intensidade sonora está dentro do permitido: "+this.somPermitido);
            System.out.println("A intensidade sonora está dentro do permitido: "+this.somPermitido);
            System.out.println("--- Missão de Fiscalização de Som concluída com sucesso por " + agente.getId() + " ---");
            return true;
        }
    }

}
