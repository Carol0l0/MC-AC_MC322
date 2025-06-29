import java.util.*;

import ambientes.Ambiente;
import comunicacao.CentralComunicacao;
import comunicacao.Comunicavel;
import entidades.Entidade;
import exception.RoboDesligadoException;
import obstaculos.Obstaculo;
import obstaculos.TipoObstaculo;
import robos.Robo;
import robos.RoboAereoXY;
import robos.RoboAereoYX;
import robos.RoboTerrestreBlindado;
import robos.RoboTerrestreDeCarga;
import obstaculos.CaixaDeSom;
import obstaculos.SabioMagico;
import robos.TipoColorido;

import sensores.Sensor;
import sensores.SensorProximidade;
import sensores.SensorSonoro;
import sensores.Sensoreavel;


import robos.robosAutonomos.RoboExplorador;
import robos.robosAutonomos.RoboPatrulheiro;
import robos.robosAutonomos.RoboSoSilencio;
import robos.robosAutonomos.missoes.Missao; 
import robos.robosAutonomos.Log; 

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CentralComunicacao central = new CentralComunicacao();
        //Criação do ambiente
        Ambiente ambiente = new Ambiente(50, 50, 50, central);
        ambiente.inicializarMapa();

        //Instanciamento e ligando robôs não autônomos
        RoboTerrestreBlindado roboBlindado = new RoboTerrestreBlindado("Blind", 1, 1, 0, 20);
        roboBlindado.ligar();
        RoboAereoXY roboAereoXY = new RoboAereoXY("Voa XY", 10, 10, 10, 50, TipoColorido.GREEN);
        roboAereoXY.ligar();
        RoboTerrestreDeCarga roboCargueiro = new RoboTerrestreDeCarga("Cargueiro", 30, 30, 0, 30, 0, TipoColorido.PURPLE);
        roboCargueiro.ligar();
        RoboAereoYX roboAereoYX = new RoboAereoYX("Voa YX", 20, 20, 20, 50);
        roboAereoYX.ligar();

        //Instanciamento e ligando robôs autônomos
        RoboExplorador roboExplorador = new RoboExplorador("Explorer", 5, 5, 0);
        roboExplorador.ligar();
        RoboPatrulheiro roboPatrulheiro = new RoboPatrulheiro("Patrol", 2, 2, 0);
        roboPatrulheiro.ligar();
        RoboSoSilencio roboSilencioso = new RoboSoSilencio("Silencer", 15, 15, 0);
        roboSilencioso.ligar();

        //Adição dos robôs no ambiente
        ambiente.adicionarEntidade(roboBlindado);
        ambiente.adicionarEntidade(roboAereoXY);
        ambiente.adicionarEntidade(roboCargueiro);
        ambiente.adicionarEntidade(roboAereoYX);
        ambiente.adicionarEntidade(roboExplorador);
        ambiente.adicionarEntidade(roboPatrulheiro);
        ambiente.adicionarEntidade(roboSilencioso);


        //Criação de obstáculos
        Obstaculo arvore  = new Obstaculo(40, 40, 40, 40, TipoObstaculo.ARVOREMISTICA);         //ArvoreMistica
        SabioMagico sabio = new SabioMagico(12, 12, 12, 12);                                    //SabioMagico
        CaixaDeSom caixa = new CaixaDeSom(16, 16, 16, 16, 5);                       //CaixaDeSom
        Obstaculo forteVento = new Obstaculo(26, 26, 26, 26, TipoObstaculo.FORTEVENTANIA);      //ForteVentania
        Obstaculo lago = new Obstaculo(3, 3, 4, 4, TipoObstaculo.LAGODEACIDO);                  //LagoDeAcido
        
        //Adição de obstaculos no ambiente
        ambiente.adicionarEntidade(arvore);
        ambiente.adicionarEntidade(sabio);
        ambiente.adicionaCaixaDeSom(caixa); 
        ambiente.adicionarEntidade(forteVento);
        ambiente.adicionarEntidade(lago); 

        //Adição dos sensores nos autônomos
        roboExplorador.gerenciadorSens.adicionarSensor(new SensorProximidade(roboExplorador, 5));
        roboPatrulheiro.gerenciadorSens.adicionarSensor(new SensorProximidade(roboPatrulheiro, 7));
        roboSilencioso.gerenciadorSens.adicionarSensor(new SensorSonoro(5, roboSilencioso)); 

        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_PURPLE = "\u001B[35m";
        
        System.out.println(ANSI_BLUE + "╔══════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "║" + ANSI_YELLOW + "       BEM-VINDO AO SIMULADOR DE ROBÔS        " + ANSI_BLUE + "║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "║" + ANSI_GREEN + "   Prepare-se para explorar e interagir!      " + ANSI_BLUE + "║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "║" + ANSI_CYAN + "    Navegue, observe e comande seus robôs!    " + ANSI_BLUE + "║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════╣" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "║" + ANSI_PURPLE + "🤖 Desenvolvido por: Maria Clara/Ana Carolina " + ANSI_BLUE + "║" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "╚══════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println();
        

        //Menu Interativo
        int opcao;
        int opcaoRobo;
        do {
            System.out.println("\n" + ANSI_YELLOW + "MENU INTERATIVO" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "1" + ANSI_RESET + ". Listar robôs por tipos e estados"); 
            System.out.println(ANSI_CYAN + "2" + ANSI_RESET + ". Selecionar robô para mover/executar tarefa"); 
            System.out.println(ANSI_CYAN + "3" + ANSI_RESET + ". Visualizar mapa 2D do ambiente"); 
            System.out.println(ANSI_CYAN + "4" + ANSI_RESET + ". Enviar mensagem");
            System.out.println(ANSI_CYAN + "5" + ANSI_RESET + ". Usar sensor");
            System.out.println(ANSI_CYAN + "6" + ANSI_RESET + ". Ativar/Desligar robô"); 
            System.out.println(ANSI_CYAN + "7" + ANSI_RESET + ". Registro mensagens trocadas entre robôs");
            System.out.println(ANSI_CYAN + "8" + ANSI_RESET + ". Iniciar Missão de Robô Autônomo"); 
            System.out.println(ANSI_CYAN + "9" + ANSI_RESET + ". Salvar log de missẽs dos robôs e encerrar programa"); 
            System.out.print(ANSI_GREEN + "Escolha uma opção: " + ANSI_RESET);
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: //Mostrar estados dos robôs
                    System.out.println("===== ESTADO DOS ROBÔS =====");
                    System.out.println("Robô 1 (" + roboBlindado.getClass().getSimpleName() + "): " + roboBlindado.getEstado());
                    System.out.println("Robô 2 (" + roboAereoXY.getClass().getSimpleName() + "): " + roboAereoXY.getEstado());
                    System.out.println("Robô 3 (" + roboCargueiro.getClass().getSimpleName() + "): " + roboCargueiro.getEstado());
                    System.out.println("Robô 4 (" + roboAereoYX.getClass().getSimpleName() + "): " + roboAereoYX.getEstado());
                    roboBlindado.exibirPosicao();
                    roboAereoXY.exibirPosicao();
                    roboCargueiro.exibirPosicao();
                    roboAereoYX.exibirPosicao();
                    System.out.println("============================");
                    break;
                case 2: //Realizar ação com algum robô
                    System.out.println("Escolha o robô:");
                    System.out.println("1. Robô 1 (" + roboBlindado.getClass().getSimpleName() + ")");
                    System.out.println("2. Robô 2 (" + roboAereoXY.getClass().getSimpleName() + ")");
                    System.out.println("3. Robô 3 (" + roboCargueiro.getClass().getSimpleName() + ")");
                    System.out.println("4. Robô 4 (" + roboAereoYX.getClass().getSimpleName() + ")");
                    System.out.print("Opção: ");
                    int opcaoAcao;
                    opcaoRobo = scanner.nextInt();
                
                    switch (opcaoRobo) { //Escolhe em qual robô vai realizar a ação
                        case 1: //Robô Blindado
                            System.out.println("Escolha a ação:");
                            System.out.println("1. Mover");
                            System.out.println("2. Recuperar Resistência");
                            System.out.println("3. Executar Tarefa (Atacar)");
                            System.out.print("Opção: ");
                            opcaoAcao=scanner.nextInt();
                            scanner.nextLine();

                            switch (opcaoAcao) {
                                case 1: //Move Blindado
                                    System.out.print("Digite a direção: ");
                                    String direcao = scanner.nextLine();
                                    System.out.print("Digite o delta: ");
                                    int delta = scanner.nextInt();
                
                                    if(roboBlindado.mover(delta, direcao)){
                                        System.out.println("Robô movido para " + roboBlindado.getX1() + "," + roboBlindado.getY1() + " com sucesso!");
                                    }
                                    ambiente.detectarColisoes();
                                    break;
                                case 2:
                                System.out.print("Quanto você quer recarregar? ");
                                    delta = scanner.nextInt();
                                    roboBlindado.recuperaDano(delta);
                                    break;
                                case 3: //Ataca
                                    roboBlindado.executarTarefa();
                                    break;
                                    
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                                }
                            break;
                        case 2: //Robô aéreo XY
                            System.out.println("Escolha a ação:");
                            System.out.println("1. Mover");
                            System.out.println("2. Subir");
                            System.out.println("3. Descer");
                            System.out.println("4. Executar Tarefa (girar no sentido anti-horário)");
                            System.out.print("Opção: ");
                            opcaoAcao=scanner.nextInt();
                            int metros;
                            switch (opcaoAcao) {
                                case 1: //Move Aéreo XY
                                    System.out.print("Digite o deltaX: ");
                                    int deltaX = scanner.nextInt();
                                    System.out.print("Digite o deltaY: ");
                                    int deltaY = scanner.nextInt();
                
                                    if(roboAereoXY.mover(deltaX, deltaY)){
                                        System.out.println("Robô movido para " + roboAereoXY.getX1() + "," + roboAereoXY.getY1() + " com sucesso!");
                                    }            

                                    break;
                                case 2: //Subir
                                    metros=scanner.nextInt();
                                    roboAereoXY.subir(metros, ambiente);
                                    break;
                                case 3: //Descer
                                    metros=scanner.nextInt();
                                    roboAereoXY.descer(metros, ambiente);
                                    break;
                                case 4: //Girar robô
                                    roboAereoXY.executarTarefa();
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                                    break;
                                }
                            break;
                        case 3: //Robô Cargueiro
                        System.out.println("Escolha a ação:");
                        System.out.println("1. Mover");
                        System.out.println("2. Carregar entrega ");
                        System.out.println("3. Descarregar entrega ");
                        System.out.println("4. Executar Tarefa (entregar encomenda)");
                        System.out.print("Opção: ");
                        opcaoAcao=scanner.nextInt();
                        int carga;
                        switch (opcaoAcao) {
                            case 1: //Move Cargueiro
                                System.out.print("Digite o deltaX: ");
                                int deltaX = scanner.nextInt();
                                System.out.print("Digite o deltaY: ");
                                int deltaY = scanner.nextInt();
            
                                if(roboCargueiro.mover(deltaX, deltaY)){
                                    System.out.println("Robô movido para " + roboCargueiro.getX1() + "," + roboCargueiro.getY1() + " com sucesso!");
                                }            

                                break;
                            case 2: //Carregar peso
                                carga=scanner.nextInt();
                                roboCargueiro.carregarEntrega(carga);
                                break; 
                            case 3:
                                carga=scanner.nextInt();
                                roboCargueiro.descarregarEntrega(carga);
                                break;
                            case 4: //Entregar encomenda
                                roboCargueiro.executarTarefa();
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                            }
                            break;
                        case 4: 
                        System.out.println("Escolha a ação:");
                        System.out.println("1. Mover");
                        System.out.println("2. Subir");
                        System.out.println("3. Descer");
                        System.out.println("4. Switch");
                        System.out.println("5. Executar Tarefa (carregar bateria)");
                        System.out.print("Opção: ");
                        opcaoAcao=scanner.nextInt();
                        int metros2;
                        switch (opcaoAcao) {
                            case 1: //Move Aéreo YX
                                System.out.print("Digite o deltaX: ");
                                int deltaX = scanner.nextInt();
                                System.out.print("Digite o deltaY: ");
                                int deltaY = scanner.nextInt();
            
                                if(roboAereoYX.mover(deltaX, deltaY)){
                                    System.out.println("Robô movido para " + roboAereoYX.getX1() + "," + roboAereoYX.getY1() + " com sucesso!");
                                }            

                                break;
                            case 2: //Subir
                                metros2=scanner.nextInt();
                                roboAereoYX.subir(metros2, ambiente);
                                break;
                            case 3: //Descer
                                metros2=scanner.nextInt();
                                roboAereoYX.descer(metros2, ambiente);
                                break;
                            case 4: //Troca o estado dos robôs adjacentes
                                roboAereoYX.trocar();
                                break;
                            case 5: //Carregar bateria
                                roboAereoYX.executarTarefa();
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                            } 
                            break;
                        default:
                        System.out.println("Opção inválida.");
                        break;
                    }

                    break;
                
                case 3: //Vizualização do mapa
                    System.out.print("Escolha uma altura para a impressão: ");
                    int z = scanner.nextInt();
                    ambiente.visualizarAmbiente(z);
                    break;
                case 4: //Enviar mensagem
                System.out.println("===== ENVIAR MENSAGEM ENTRE ROBÔS =====");
                System.out.println("Escolha o robô remetente:");
                System.out.println("1. Robô 1 (" + roboBlindado.getClass().getSimpleName() + ")");
                System.out.println("2. Robô 2 (" + roboAereoXY.getClass().getSimpleName() + ")");
                System.out.println("3. Robô 3 (" + roboCargueiro.getClass().getSimpleName() + ")");
                System.out.println("4. Robô 4 (" + roboAereoYX.getClass().getSimpleName() + ")");
                System.out.print("Opção: ");
                int remetenteOp = scanner.nextInt();
                    
                Comunicavel remetente = null;
                switch (remetenteOp) {
                    case 1: remetente = roboBlindado; break;
                    case 2: remetente = roboAereoXY; break;
                    case 3: remetente = roboCargueiro; break;
                    case 4: remetente = roboAereoYX; break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                
                    System.out.println("Escolha o robô destinatário:");
                    System.out.println("1. Robô 1 (" + roboBlindado.getClass().getSimpleName() + ")");
                    System.out.println("2. Robô 2 (" + roboAereoXY.getClass().getSimpleName() + ")");
                    System.out.println("3. Robô 3 (" + roboCargueiro.getClass().getSimpleName() + ")");
                    System.out.println("4. Robô 4 (" + roboAereoYX.getClass().getSimpleName() + ")");
                    System.out.print("Opção: ");
                    int destinatarioOp = scanner.nextInt();
                    scanner.nextLine();

                    Comunicavel destinatario = null;
                    switch (destinatarioOp) {
                        case 1: destinatario = roboBlindado; break;
                        case 2: destinatario = roboAereoXY; break;
                        case 3: destinatario = roboCargueiro; break;
                        case 4: destinatario = roboAereoYX; break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                
                    System.out.print("Digite a mensagem: ");
                    String mensagem = scanner.nextLine();
                
                    if (remetente != null && destinatario != null) {
                        CentralComunicacao.enviarMensagem(remetente, destinatario, mensagem);
                        ambiente.centralAmbiente.registrarMensagem("Robô " + remetenteOp, mensagem);


                    } else {
                        System.out.println("Robôs inválidos.");
                    }
                    break;
                case 5: //Usar sensor
                System.out.println("Digite o nome do robô (Blind, Voa XY, Cargueiro, Voa YX):");
                String nomeRoboSensor = scanner.nextLine().trim();
                    
                Robo roboSensorSelecionado = null;
                    
                switch (nomeRoboSensor.toLowerCase()) {
                    case "blind" -> roboSensorSelecionado = roboBlindado;
                    case "voa xy" -> roboSensorSelecionado = roboAereoXY;
                    case "cargueiro" -> roboSensorSelecionado = roboCargueiro;
                    case "voa yx" -> roboSensorSelecionado = roboAereoYX;
                    default -> {
                        System.out.println("Nome de robô inválido.");
                        break;
                    }
                }
                
                if (roboSensorSelecionado == null) break;
                
                System.out.println("Escolha o tipo de sensor:");
                System.out.println("1. Sensor de Proximidade");
                System.out.println("2. Sensor Sonoro");
                int tipoSensor = scanner.nextInt();
                scanner.nextLine(); 
                
                Sensor sensor = null;
                switch (tipoSensor) {
                    case 1 -> sensor = new SensorProximidade(roboSensorSelecionado, 2);
                    case 2 -> sensor = new SensorSonoro(2, roboSensorSelecionado);
                    default -> {
                        System.out.println("Tipo de sensor inválido.");
                        break;
                    }
                }
                
                if (sensor != null) {
                    roboSensorSelecionado.gerenciadorSens.adicionarSensor(sensor);
                    System.out.println("Sensor adicionado com sucesso ao robô " + nomeRoboSensor);
                    
                    try {
                        roboSensorSelecionado.gerenciadorSens.acionarSensores();
                    } catch (RoboDesligadoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }
                
                break;
                case 6: //Ligar e desligar robôs
                    System.out.println("===== ATIVAR/DESLIGAR ROBÔ =====");
                    System.out.println("Escolha o robô:");
                    System.out.println("1. Robô 1 (" + roboBlindado.getClass().getSimpleName() + ")");
                    System.out.println("2. Robô 2 (" + roboAereoXY.getClass().getSimpleName() + ")");
                    System.out.println("3. Robô 3 (" + roboCargueiro.getClass().getSimpleName() + ")");
                    System.out.println("4. Robô 4 (" + roboAereoYX.getClass().getSimpleName() + ")");
                    System.out.print("Opção: ");
                    opcaoRobo = scanner.nextInt();
            
                        Robo roboSelecionado = null;
            
                        switch (opcaoRobo) {
                            case 1: roboSelecionado = roboBlindado; break;
                            case 2: roboSelecionado = roboAereoXY; break;
                            case 3: roboSelecionado = roboCargueiro; break;
                            case 4: roboSelecionado = roboAereoYX; break;
                            default:
                            System.out.println("Opção inválida.");
                            break;
                        }
                        
                            if (roboSelecionado != null) {
                                System.out.println("Estado atual: " + roboSelecionado.getEstado());
                                System.out.println("Deseja:");
                                System.out.println("1. Ligar");
                                System.out.println("2. Desligar");
                                System.out.print("Escolha: ");
                                int acao = scanner.nextInt();
                        
                                if (acao == 1) {
                                    roboSelecionado.ligar();
                                    System.out.println("Robô ligado com sucesso.");
                                } else if (acao == 2) {
                                    roboSelecionado.desligar();
                                    System.out.println("Robô desligado com sucesso.");
                                } else {
                                    System.out.println("Ação inválida.");
                                }
                            }
                    break;
                case 7: //Registrar mensagens trocadas pelos robos
                System.out.println("Mensagens trocadas entre os robôs:");
                ambiente.centralAmbiente.exibirMensagens();
                break;
                case 8: // Executar Missão Autônoma
                System.out.println("Escolha o robô:");
                System.out.println("1. Robô 1 (" + roboExplorador.getClass().getSimpleName() + ")");
                System.out.println("2. Robô 2 (" + roboPatrulheiro.getClass().getSimpleName() + ")");
                System.out.println("3. Robô 3 (" + roboSilencioso.getClass().getSimpleName() + ")"); 
            
                System.out.print("Opção: ");
                int opcaoRobo2 = scanner.nextInt(); 
            
                Robo agente = null; 
            
                switch (opcaoRobo2) {
                    case 1:
                        agente = roboExplorador;
                        break;
                    case 2:
                        agente = roboPatrulheiro;
                        break;
                    case 3:
                        agente = roboSilencioso;
                        break;
                    default:
                        System.out.println("Robô inválido.");
                        break;
                }
            
                if (agente != null) {
                    System.out.println("\nEscolha o tipo de missão para " + agente.getId() + ":");
                    System.out.println("1. Missão de Busca por Ponto (Explorador)");
                    System.out.println("2. Missão de Patrulha (Patrulheiro)");
                    System.out.println("3. Missão de Fiscalizar Som (Só Silêncio)");
                    System.out.print("Escolha: ");
            
                    try {
                        int tipoMissao = scanner.nextInt();
                        scanner.nextLine(); 
            
                        switch (tipoMissao) {
                            case 1:
                                if (agente instanceof RoboExplorador) {
                                    System.out.print("Digite a coordenada X alvo: ");
                                    int targetX = scanner.nextInt();
                                    System.out.print("Digite a coordenada Y alvo: ");
                                    int targetY = scanner.nextInt();
                                    ((RoboExplorador) agente).explorar(targetX, targetY);
                                } else {
                                    System.out.println("Este robô não é um Robo Explorador e não pode executar esta missão.");
                                }
                                break;
            
                            case 2:
                                if (agente instanceof RoboPatrulheiro) {
                                    System.out.print("Digite o número de pontos no caminho de patrulha: ");
                                    int numPontos = scanner.nextInt();
                                    scanner.nextLine();
                                    ((RoboPatrulheiro) agente).patrulhar(numPontos);
                                } else {
                                    System.out.println("Este robô não é um Robo Patrulheiro e não pode executar esta missão.");
                                }
                                break;
            
                            case 3:
                                if (agente instanceof RoboSoSilencio) {
                                    System.out.print("Digite o nível de som máximo permitido: ");
                                    int somMax = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character
                                    ((RoboSoSilencio) agente).fiscalizar(somMax);
                                } else {
                                    System.out.println("Este robô não é um Robo So Silencio e não pode executar esta missão.");
                                }
                                break;
            
                            default:
                                System.out.println("Tipo de missão inválido.");
                                break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, digite um número.");
                        scanner.nextLine(); 
                    }
                } else {
                    System.out.println("Seleção de robô inválida. Por favor, tente novamente.");
                }
                break;
                case 9:
                System.out.println("Encerrando...");
                //aqui salva todas as infos finais
                System.out.println("Salvando logs finais antes de sair...");
                if (roboExplorador.getLog() != null) {
                    roboExplorador.getLog().salvarLogEmArquivo("log_final_explorador.txt");
                }
                if (roboPatrulheiro.getLog() != null) {
                    roboPatrulheiro.getLog().salvarLogEmArquivo("log_final_patrulheiro.txt");
                }
                if (roboSilencioso.getLog() != null) {
                    roboSilencioso.getLog().salvarLogEmArquivo("log_final_silencioso.txt");
                }
                System.out.println("Logs finais salvos.");
                break;
            default:
                System.out.println("Opção inválida.");
        }

    } while (opcao != 0);

    scanner.close();
}
}





