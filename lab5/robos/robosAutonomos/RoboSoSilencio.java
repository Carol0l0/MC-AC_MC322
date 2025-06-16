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
    public void executarMissao() throws RoboDesligadoException{
        this.missaoSoS.executar(this);
    }

    public void fiscalizar(int somPermitido){
        missaoSoS= new MissaoFiscalizarSom(somPermitido);
        try {
            this.executarMissao();
        } catch (RoboDesligadoException e) {
            System.out.println();
        }
    }

    @Override
    public void executarTarefa() {
        Scanner som = new Scanner(System.in);
        int somMax=som.nextInt();
        fiscalizar(somMax);
        som.close();
    }

    
}
