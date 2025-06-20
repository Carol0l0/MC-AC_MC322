package robos.robosAutonomos;
import java.util.ArrayList;
import robos.robosAutonomos.missoes.*;
import java.io.FileWriter; // Importar FileWriter
import java.io.IOException; // Importar IOException
import java.io.PrintWriter; // Importar PrintWriter

public class Log {
    ArrayList<String> texto = new ArrayList<>();

    public Log(String string) {
        //TODO Auto-generated constructor stub
    }

    public void adicionarPosicao(int x, int y, Missao m){
        texto.add(m.getRobo().getId()+" andou para ("+x+", "+y+")");
    }

    public void adicionarLinha(String s){
        texto.add(s);
    }

    /*
    Mostra:
    - as posições visitadas,
    - os sensores acionados,
    - os obstáculos detectados,
    - os erros detectados dos robos agentes
     */
    public void exibirLog(){
        System.out.println("--- Log das Missões ---");
        for(String linha : texto){
            System.out.println(linha);
        }
    }

    /**
     * Salva o conteúdo do log em um arquivo de texto.
     * @param nomeArquivo O nome do arquivo onde o log será salvo.
     */
    public void salvarLogEmArquivo(String nomeArquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (String linha : texto) {
                writer.println(linha);
            }
            System.out.println("Log salvo com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o log no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }
}