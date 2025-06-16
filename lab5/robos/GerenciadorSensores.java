package robos;
// GerenciadorSensores.java
import java.util.ArrayList;

import exception.RoboDesligadoException;
import sensores.*;

public class GerenciadorSensores implements Sensoreavel{
    private Robo robo; // Referência ao robô que este gerenciador pertence
    private ArrayList<Sensor> sensores; // A lista de sensores agora é encapsulada aqui

    public GerenciadorSensores(Robo robo) {
        this.robo = robo;
        this.sensores = new ArrayList<>();
    }

    public void adicionarSensor(Sensor sensor) { //adiciona na lista de sensores do rôbo
        sensores.add(sensor);
    }

    public void removerSensor(Sensor sensor) { //remove sensor da lista de sensores do rôbo
        if (sensor != null) {
            this.sensores.remove(sensor);
            System.out.println("Sensor " + sensor.getClass().getSimpleName() + " removido do robô " + robo.getId());
        }
    }

    public void acionarSensores() throws RoboDesligadoException { //confere se o rôbo está ligado e utiliza os sensores
        if (this.robo.getEstado() == EstadoRobo.DESLIGADO) {
            throw new RoboDesligadoException("Robô " + this.robo.getId() + " está desligado e não pode usar sensores.");
        }
        if(sensores.isEmpty()){
            System.out.println("Nenhum sensor configurado para este robô.");
            return;
        }
        System.out.println("Sensores de " + this.robo.getId() + " em ação:");
        for (Sensor s : sensores) {
            s.monitorar();
        }
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }
}