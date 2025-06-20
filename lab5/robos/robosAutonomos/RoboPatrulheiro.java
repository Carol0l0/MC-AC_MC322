package robos.robosAutonomos;
// RoboPatrulheiro.java

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import robos.robosAutonomos.missoes.MissaoPatrulhar;

public class RoboPatrulheiro extends AgenteInteligente{

    MissaoPatrulhar missaoPatrulha;
    // Construtor
    public RoboPatrulheiro(String id, int x1, int y1, int z) {
        super(id, x1, y1, z); 
    }

    @Override
    public void executarMissao() {//chama missao.executar()
        this.missaoPatrulha.executar(this);
    }
    

    public void patrulhar(int tam_caminho) {
        Scanner registro = new Scanner(System.in);
        List<int[]> caminho = new ArrayList<>(tam_caminho);
        for(int i=0; i<tam_caminho; i++){ //registra o caminho pelo o qual o rôbo vai passar
            System.out.println("Coordenada " + (i + 1) + ":");

            System.out.print("x = ");
            int x = registro.nextInt();

            System.out.print("y = ");
            int y = registro.nextInt();

            caminho.add(new int[]{x, y});
        }
        registro.close();
        missaoPatrulha = new MissaoPatrulhar(caminho, this, this.getLog());
        this.executarMissao();
    }


    @Override
    public void executarTarefa() { //realiza uma patrulha
        Scanner entrada_tamanho = new Scanner(System.in);
        int tam_caminho=entrada_tamanho.nextInt(); //pede o tamanho do caminho da patrulha
        this.patrulhar(tam_caminho);
        entrada_tamanho.close();
    }


}