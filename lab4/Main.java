import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Criação do ambiente
        Ambiente ambiente = new Ambiente(50, 50, 50);

        //Instanciamento e ligando robôs
        Robo robo1 = new RoboTerrestreBlindado("r1", 1, 1, 0, 20);
        robo1.ligar();
        Robo robo2 = new RoboAereoXY("r2", 10, 10, 10, 50, null);
        robo2.ligar();
        Robo robo3 = new RoboTerrestreDeCarga("r3", 30, 30, 0, 30, 0, null);
        robo3.ligar();
        Robo robo4 = new RoboAereoYX("r4", 20, 20, 20, 50);
        robo4.ligar();

        //Adição dos robôs no ambiente
        ambiente.adicionarEntidade(robo1);
        ambiente.adicionarEntidade(robo2);
        ambiente.adicionarEntidade(robo3);
        ambiente.adicionarEntidade(robo4);

        //Criação de obstáculos
        Obstaculo obstaculo  = new Obstaculo(0, 0, 0, 0, TipoObstaculo.ARVOREMISTICA);
        Obstaculo obstaculo1 = new Obstaculo(0, 0, 0, 0, TipoObstaculo.SABIOMAGICO);
        Obstaculo obstaculo2 = new Obstaculo(0, 0, 0, 0, TipoObstaculo.CAIXADESOM);
        Obstaculo obstaculo3 = new Obstaculo(0, 0, 0, 0, TipoObstaculo.FORTEVENTANIA);
        Obstaculo obstaculo4 = new Obstaculo(0, 0, 0, 0, TipoObstaculo.LAGODEACIDO);
        
        //Adição de obstaculos no ambiente
        ambiente.adicionarEntidade(obstaculo);
        ambiente.adicionarEntidade(obstaculo1);
        ambiente.adicionarEntidade(obstaculo2);
        ambiente.adicionarEntidade(obstaculo3);
        ambiente.adicionarEntidade(obstaculo4);

        //Menu Interativo
        int opcao;
        do {
            System.out.println("\nMENU INTERATIVO");
            System.out.println("1. Listar robôs por tipo");
            System.out.println("2. Listar robôs por estado");
            System.out.println("3. Selecionar robô para interagir/executar tarefa");
            System.out.println("4. Visualizar status do robô e ambiente"); 
            System.out.println("5. Visualizar mapa 2D do ambiente");
            System.out.println("6. Enviar mensagem");
            System.out.println("7. Movimentar robô");
            System.out.println("8. Usar sensor");
            System.out.println("9. Ativar/Desligar robô");
            System.out.println("10. Registro mensagens trocadas entre robôs");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("===== ESTADO DOS ROBÔS =====");
                    System.out.println("Robô 1 (" + robo1.getClass().getSimpleName() + "): " + robo1.getEstado());
                    System.out.println("Robô 2 (" + robo2.getClass().getSimpleName() + "): " + robo2.getEstado());
                    System.out.println("Robô 3 (" + robo3.getClass().getSimpleName() + "): " + robo3.getEstado());
                    System.out.println("Robô 4 (" + robo4.getClass().getSimpleName() + "): " + robo4.getEstado());
                    System.out.println("============================");
                    break;
                case 2:

                    break;
                case 3:
                
                    break;
                case 4:

                    break;
                case 5:
                    System.out.print("Escolha uma altura para a impressão: ");
                    int z = scanner.nextInt();
                    ambiente.visualizarAmbiente(z);
                    break;
                case 6:
          
                    break;
                case 7:
   
                    break;
                case 8:
                    
                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
