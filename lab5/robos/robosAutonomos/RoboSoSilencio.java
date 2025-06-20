package robos.robosAutonomos;

import java.util.Scanner;

import exception.RoboDesligadoException;
import robos.robosAutonomos.missoes.MissaoFiscalizarSom;

public class RoboSoSilencio extends AgenteInteligente {
    MissaoFiscalizarSom missaoSoS;
    public RoboSoSilencio(String id, int x1, int y1, int z){
        super(id, x1, y1, z);
    }

    @Override
    public void executarMissao() throws RoboDesligadoException{//chama missao.executar()
        this.missaoSoS.executar(this);
    }

    /*
     cria uma nova missão fiscalizar baseada no Som Máximo Permitido
     chama executarMissao()
    */
    public void fiscalizar(int somPermitido){
        missaoSoS= new MissaoFiscalizarSom(somPermitido, this);
        try {
            this.executarMissao();
        } catch (RoboDesligadoException e) {
            System.out.println();
        }
    }

    /*
     função que vai ser utilizada na main
     lê o som máximo permitido
     chama fiscalizar(som máximo)
    */
    @Override
    public void executarTarefa() {
        Scanner som = new Scanner(System.in);
        int somMax=som.nextInt();
        fiscalizar(somMax);
        som.close();
    }

    
}
