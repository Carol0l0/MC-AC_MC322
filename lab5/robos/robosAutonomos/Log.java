package robos.robosAutonomos;
import java.util.ArrayList;
import robos.robosAutonomos.missoes.*;

public class Log {
    ArrayList<String> texto = new ArrayList<>();

    public void adicionarPosicao(int x, int y, Missao m){//Adiciona mensagem registrando o movimento
        texto.add(m.getRobo().getId()+" andou para ("+x+", "+y+")");
    }

    public void adicionarSensorAtivado(Missao m){
        
        texto.add("");
    }

    public void adicionarLinha(String s){
        texto.add(s);
    }

    public void exibirLog(){
        System.out.println("--- Log das Missões ---");
        for(String linha : texto){
            System.out.println(linha);
        }
    }
}