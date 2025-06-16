package robos.robosAutonomos.missoes;

import robos.EstadoRobo;
import robos.robosAutonomos.*;
import sensores.SensorSonoro;
import exception.RoboDesligadoException;

public class MissaoFiscalizarSom implements Missao{
    public String descricao;
    int somPermitido;

    public MissaoFiscalizarSom(int somPermitido) { 
        this.somPermitido=somPermitido;
        this.descricao="O robô irá checar se o som em sua posição está dentro dos limites permitidos para evitar incômodos";
    }
    
    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean executar(AgenteInteligente agente) throws RoboDesligadoException{
        if(agente.getEstado()==EstadoRobo.DESLIGADO){
            throw new RoboDesligadoException("Robô falhou na missão pois está desligado");
        }
        else{
            SensorSonoro sensorSom=new SensorSonoro(0, agente);
            if(sensorSom.monitorar()>somPermitido){
                System.out.println("A intensidade sonora está acima do permitido: "+this.somPermitido);
                System.out.println("--- Missão de Fiscalização de Som concluída com sucesso por " + agente.getId() + " ---");
                return false;
            }
            System.out.println("A intensidade sonora está dentro do permitido: "+this.somPermitido);
            System.out.println("--- Missão de Fiscalização de Som concluída com sucesso por " + agente.getId() + " ---");
            return true;
        }
    }

}
