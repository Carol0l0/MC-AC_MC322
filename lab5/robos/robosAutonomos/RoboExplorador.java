package robos.robosAutonomos;
// RoboExplorador.java

import java.util.Scanner;

import robos.robosAutonomos.missoes.*;

public class RoboExplorador extends AgenteInteligente{

    MissaoBuscarPonto missaoBusca;
    // Construtor
    public RoboExplorador(String id, int x1, int y1, int z) {
        super(id, x1, y1, z);
    }

    @Override
    public void executarMissao() {//chama missao.executar()
        this.missaoBusca.executar(this);
    }

    /*
     cria uma nova missão busca por ponto baseado nas coordenadas escolhidas
     chama executarMissao()
    */
    public void explorar(int x, int y){
        missaoBusca= new MissaoBuscarPonto(x, y, this);
        this.executarMissao();
    }


    /*
     função que vai ser utilizada na main
     lê as posições para onde vai o rôbo
     chama explorar(x, y)
    */
    @Override
    public void executarTarefa() {
        Scanner coordenadas = new Scanner(System.in);
        System.out.print("x = "); //pede as coordenadas onde o rôbo vai explorar
        int x = coordenadas.nextInt();

        System.out.print("y = ");
        int y = coordenadas.nextInt();
        this.explorar(x, y); //executa a missão
        coordenadas.close();
    }
    
}