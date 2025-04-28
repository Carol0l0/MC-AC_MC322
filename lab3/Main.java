import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Ambiente a = new Ambiente(50,50,50);
        Robo robo = null;
        Obstaculo obstaculo = null;

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Criar Robô Terrestre Blindado");
            System.out.println("2. Criar Robô Terrestre de Carga");
            System.out.println("3. Criar Robô Aéreo XY");             
            System.out.println("4. Criar Robô Aéreo YX");
            System.out.println("5. Criar obstáculo");
            System.out.println("6. Mover Robô");
            System.out.println("7. Escolher Sensor");
            System.out.println("8. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    robo = criarRoboTerrestreBlindado(a);
                    break;

                case 2:
                    robo = criarRoboTerrestreDeCarga(a);
                    break;

                case 3:
                    robo = criarRoboAereoXY(a);
                    break;

                case 4:
                    robo = criarRoboAereoYX(a);
                    break;

                case 5:
                    obstaculo = criarobstaculo(a);
                    break;

                case 6:
                System.out.println("Digite o nome do robô que deseja mover:");
                String nomeRoboMover = scanner.nextLine();
                Robo roboSelecionado = a.buscarRoboPorNome(nomeRoboMover);
            
                if (roboSelecionado == null) {
                    System.out.println("Robô não encontrado.");
                    break;
                }
                        
                if (roboSelecionado instanceof RoboTerrestreBlindado) {
                    System.out.print("Digite a direção: ");
                    String direcao = scanner.nextLine(); 
                    System.out.print("Digite o delta: ");
                    int delta = scanner.nextInt();  

                    ((RoboTerrestreBlindado)roboSelecionado).mover(delta, direcao, a);
                    System.out.println("Robô movido para " + roboSelecionado.getPosicaoX() + "," + roboSelecionado.getPosicaoY() + "com sucesso!");
                    break;

                } else if (roboSelecionado instanceof RoboTerrestreDeCarga) {
                    System.out.print("Digite o deltaY: ");
                    int deltaY = scanner.nextInt(); 
                    System.out.println("Digite o deltaX: ");
                    int deltaX = scanner.nextInt();  

                    if(!r.identificarObstaculo(roboSelecionado, deltaX, deltaY, 0)){
                        ((RoboTerrestreDeCarga)roboSelecionado).mover(deltaX, deltaY);
                        System.out.println("Robô movido para " + roboSelecionado.getPosicaoX() + "," + roboSelecionado.getPosicaoY() + "com sucesso!");
                    }
                    else
                        System.out.println("obstáculo no caminho! Robô não se moveu.");
                    break;

                } else if (roboSelecionado instanceof RoboAereoXY) {
                    System.out.print("Digite o deltaX: ");
                    int deltaX = scanner.nextInt(); 
                    System.out.print("Digite o deltaY: ");
                    int deltaY = scanner.nextInt();  

                    ((RoboAereoXY)roboSelecionado).mover(deltaX, deltaY);
                    System.out.println("Robô movido para " + roboSelecionado.getPosicaoX() + "," + roboSelecionado.getPosicaoY() + "com sucesso!");
                    break;

                } else if(roboSelecionado instanceof RoboAereoYX) {
                    System.out.print("Digite o deltaX: ");
                    int deltaX = scanner.nextInt(); 
                    System.out.print("Digite o deltaY: ");
                    int deltaY = scanner.nextInt();  

                    ((RoboAereoYX)roboSelecionado).mover(deltaX, deltaY);
                    System.out.println("Robô movido para " + roboSelecionado.getPosicaoX() + "," + roboSelecionado.getPosicaoY() + "com sucesso!");
                    break;
                }

                case 7:
                System.out.println("Digite o nome do robô que deseja adicionar o sensor:");
                String nomeRoboSensor = scanner.nextLine();
                Robo roboSensorSelecionado = a.buscarRoboPorNome(nomeRoboSensor);
            
                if (roboSensorSelecionado == null) {
                    System.out.println("Robô não encontrado.");
                    break;
                }
            
                System.out.println("Escolha o tipo de sensor:");
                System.out.println("1. Sensor de Proximidade");
                System.out.println("2. Sensor Sonoro");
                int tipoSensor = scanner.nextInt();
                scanner.nextLine(); // limpar o buffer
            
                Sensor sensor = null;
                switch (tipoSensor) {
                    case 1 -> sensor = new SensorProximidade(roboSensorSelecionado,2);
                    case 2 -> sensor = new SensorSonoro(2, roboSensorSelecionado);
                    default -> {
                        System.out.println("Tipo de sensor inválido.");
                        break;
                    }
                }
            
                if (sensor != null) {
                    roboSensorSelecionado.adicionarSensor(sensor);
                    System.out.println("Sensor adicionado com sucesso ao robô " + nomeRoboSensor);
                    sensor.monitorar();
                }
                break;
            }
        }

    }

        private static RoboTerrestreBlindado criarRoboTerrestreBlindado(Ambiente a) {
            System.out.println("Criando Robo Terrestre Blindado...");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Posição X: ");
            int posicaoX = scanner.nextInt();
            System.out.print("Posição Y: ");
            int posicaoY = scanner.nextInt();
            int posicaoZ = 0;
            System.out.print("Velocidade Máxima: ");
            int v_max = scanner.nextInt();
            scanner.nextLine(); 
    
            RoboTerrestreBlindado roboTerrestreBlindado = new RoboTerrestreBlindado(nome, posicaoX, posicaoY, posicaoZ, v_max);
            a.adicionarRobo(roboTerrestreBlindado);
            System.out.println("Robô adicionado em " + roboTerrestreBlindado.getPosicaoX() + "," + roboTerrestreBlindado.getPosicaoY() + "com sucesso!");
            return null;
        }
    
        private static RoboTerrestreDeCarga criarRoboTerrestreDeCarga(Ambiente a) {
            System.out.println("Criando Robo Terrestre de Carga...");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Posição Y: ");
            int posicaoY = scanner.nextInt();
            System.out.print("Posição X: ");
            int posicaoX = scanner.nextInt();
            int posicaoZ = 0;
            System.out.print("Velocidade Máxima: ");
            int v_max = scanner.nextInt();
            System.out.print("Carga Máxima: ");
            int cargaMaxima = scanner.nextInt();
            scanner.nextLine();
    
            RoboTerrestreDeCarga roboTerrestreDeCarga = new RoboTerrestreDeCarga(nome, posicaoY, posicaoX, posicaoZ, v_max, cargaMaxima);
            a.adicionarRobo(roboTerrestreDeCarga);
            System.out.println("Robô adicionado em " + roboTerrestreDeCarga.getPosicaoX() + "," + roboTerrestreDeCarga.getPosicaoY() + "com sucesso!");
            return null;
        }
    
        private static RoboAereoXY criarRoboAereoXY(Ambiente a) {
            System.out.println("Criando Robo Aéreo...");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Posição X: ");
            int posicaoX = scanner.nextInt();
            System.out.print("Posição Y: ");
            int posicaoY = scanner.nextInt();
            System.out.print("Posição Z: ");
            int posicaoZ = scanner.nextInt();
            System.out.print("Altitude Máxima: ");
            int altitudeMax = scanner.nextInt();
            System.out.print("Cor: ");
            String cor = scanner.nextLine();
    
            RoboAereoXY roboAereoXY = new RoboAereoXY(nome, posicaoX, posicaoY, posicaoZ, altitudeMax, cor);
            a.adicionarRobo(roboAereoXY);
            System.out.println("Robô adicionado em " + roboAereoXY.getPosicaoX() + "," + roboAereoXY.getPosicaoY() + "com sucesso!");
            return null;
        }
    
        private static RoboAereoYX criarRoboAereoYX(Ambiente a) {
            System.out.println("Criando Robo Aéreo...");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Posição X: ");
            int posicaoX = scanner.nextInt();
            System.out.print("Posição Y: ");
            int posicaoY = scanner.nextInt();
            System.out.print("Posição Z: ");
            int posicaoZ = scanner.nextInt();
            System.out.print("Altitude Máxima: ");
            int altitudeMax = scanner.nextInt();
            scanner.nextLine(); 
    
            RoboAereoYX roboAereoYX = new RoboAereoYX(nome, posicaoX, posicaoY, posicaoZ, altitudeMax);
            a.adicionarRobo(roboAereoYX);
            System.out.println("Robô adicionado em " + roboAereoYX.getPosicaoX() + "," + roboAereoYX.getPosicaoY() + "com sucesso!");
            return null;
    
        }
    
        private static Obstaculo criarobstaculo(Ambiente a) {
            System.out.println("Escolha o tipo de otáculo:");
            System.out.println("1. Caixa de Som");
            System.out.println("2. Lago de Ácido");
            System.out.println("3. Forte Ventania");
            System.out.println("4. Firewall Malicioso");
            System.out.println("5. Sábio Mágico");
            int opcao = scanner.nextInt();
            scanner.nextLine();
        
            System.out.print("Posição X1: ");
            int x1 = scanner.nextInt();
            System.out.print("Posição X2: ");
            int x2 = scanner.nextInt();
            System.out.print("Posição Y1: ");
            int y1 = scanner.nextInt();
            System.out.print("Posição Y2: ");
            int y2 = scanner.nextInt();
            scanner.nextLine();
        
            TipoObstaculo tipo = switch (opcao) {
                case 1 -> TipoObstaculo.CAIXADESOM;
                case 2 -> TipoObstaculo.LAGODEACIDO;
                case 3 -> TipoObstaculo.FORTEVENTANIA;
                case 5 -> TipoObstaculo.SABIOMAGICO;
                default -> null;
            };
        
            if (tipo == null) {
                System.out.println("Tipo inválido.");
                return null;
            }
        
            Obstaculo o = new Obstaculo(x1, y1, x2, y2, tipo);
            a.adicionarObstaculo(o);
            System.out.print("obstaculo criado em: " + o.getPosicaoX1() +","+o.getPosicaoY1() +","+o.getPosicaoX2()+","+o.getPosicaoY2());
            return null;

        }
        
    }








