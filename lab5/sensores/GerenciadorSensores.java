package sensores;
// GerenciadorSensores.java
import java.util.ArrayList;

import robos.Robo;

public class GerenciadorSensores {
    private Robo robo; // Referência ao robô que este gerenciador pertence
    private ArrayList<Sensor> sensores; // A lista de sensores agora é encapsulada aqui

    public GerenciadorSensores(Robo robo) {
        this.robo = robo;
        this.sensores = new ArrayList<>();
    }

    public void adicionarSensor(Sensor sensor) {
        if (sensor != null) {
            this.sensores.add(sensor);
            System.out.println("Sensor " + sensor.getClass().getSimpleName() + " adicionado ao robô " + robo.getId());
        }
    }

    public void removerSensor(Sensor sensor) {
        if (sensor != null) {
            this.sensores.remove(sensor);
            System.out.println("Sensor " + sensor.getClass().getSimpleName() + " removido do robô " + robo.getId());
        }
    }

    /**
     * Itera sobre os sensores do robô e os aciona.
     */
    public void usarSensores() {
        System.out.println("Sensores de " + robo.getId() + " em ação:");
        if (sensores.isEmpty()) {
            System.out.println("Nenhum sensor configurado para este robô.");
            return;
        }
        for (Sensor s : sensores) {
            try {
                // Sensores também precisam de robo.ambiente para funcionar
                s.monitorar();
            } catch (Exception e) {
                System.out.println("Erro ao monitorar com sensor " + s.getClass().getSimpleName() + ": " + e.getMessage());
            }
        }
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }
}