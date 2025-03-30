public class Main {

    public static void main(String[] args) {

        //Criando o ambiente
        Ambiente a = new Ambiente(10, 10, 10);

        //Criando robôs
        RoboTerrestreDeCarga rCarga = new RoboTerrestreDeCarga("Robo de Carga", 0, 0, 0, 5, 10, 100);
        RoboTerrestreBlindado rBlindado = new RoboTerrestreBlindado("Robo Blindado", 5, 5, 0);
        RoboAereoXY r_XY = new RoboAereoXY("Robo Aereo XY", 3, 3, 3, 7, null);
        RoboAereoYX r_YX= new RoboAereoYX("Robo Aereo YX", 3, 3, 2, 9);

        // Adicionando robôs ao ambiente
        a.adicionarRobo(rCarga);
        a.adicionarRobo(rBlindado);
        a.adicionarRobo(r_XY);
        a.adicionarRobo(r_YX);

        //Definindo cor do Robô aéreo XY
        r_XY.setCor("Azul");
        System.out.println("A cor do "+r_XY.getNome()+" é "+r_XY.getCor());

        //Caso AereoYX fique sem bateria e demonstrando a mudança de direção
        System.out.println("\nMovimentando AereoYX até bateria acabar:");
        r_YX.mover(1, 1, a);
        r_YX.exibirPosicao();
        r_YX.getNivelBateria();
        r_YX.mover(-1, -1, a);
        r_YX.exibirPosicao();
        r_YX.getNivelBateria();
        r_YX.mover(1, -1, a);
        r_YX.exibirPosicao();
        r_YX.getNivelBateria();
        r_YX.mover(-1, 1, a);
        r_YX.exibirPosicao();
        r_YX.getNivelBateria();
        r_YX.descer(1, a);
        r_YX.exibirPosicao();
        r_YX.getNivelBateria();
        r_YX.subir(1, a);

        //Caso queira recarregar AereoYX
        System.out.println("\nRecarregando AereoYX:");
        r_YX.carregarBateria();
        r_YX.getNivelBateria();

        //Caso terrestres excedam os limites do ambiente
        System.out.println("\nExcedendo limite de velocidade e ambiente do 1 e 3");
        rCarga.mover(11, 3, a);  

        //Caso ultrapasse a cargaMáxima do RoboTerrestreDeCarga
        System.out.println("\nSobrecarregando 1 com 11kg:");
        rCarga.carregarPeso(11);

        //movimento TerrestreBlindado
        System.out.println("\nMovendo em X:");
        rBlindado.mover(-2, "X", a);
        rBlindado.exibirPosicao();
        System.out.println("\nMovendo em Y:");
        rBlindado.mover(-1, "Y", a);
        rBlindado.exibirPosicao();

        //Caso encontra um obstáculo + recebendo dano + caso Resistencia > 0 continuando
        System.out.println("\nRobo de Carga no caminho do Robo Blindado:");
        rBlindado.mover(-2, "Y", a);

        //Caso encontra +1 obstáculo no meio do caminho
        System.out.println("\nCriando +1 obstáculo no caminho");
        RoboTerrestreDeCarga robo5 = new RoboTerrestreDeCarga("Robo5", 4, 2, 0, 5, 10, 100);
        RoboTerrestreDeCarga robo6 = new RoboTerrestreDeCarga("Robo6", 5, 2, 0, 5, 10, 100);
        a.adicionarRobo(robo6);
        a.adicionarRobo(robo5);
        rBlindado.mover(4, "X", a);

        //Caso encontra um obstáculo + recebendo dano + caso Resistencia = 0 parando no destino
        System.out.println("\nSofrendo 2 de dano:");
        rBlindado.sofreDano(3);

        //Verificando que após sua destruição ele não se move mais
        System.out.println("\nTentando move-lo depois de sua destruição:");
        rBlindado.exibirPosicao();
        rBlindado.mover(1, "X", a);
        System.out.println("Mantém posição\n");

        //movendo Robôs aéreos
        //testando colisão para subir
        r_XY.exibirPosicao();
        r_YX.exibirPosicao();
        r_YX.subir(2, a);
        System.out.print("\n");

        //subindo além da altitude máxima
        r_XY.subir(5, a);

        //mudança direção no Aéreo XY
        r_XY.mover(1, 1, a);
        r_XY.exibirPosicao();
        r_XY.mover(-1, -1, a);
        r_XY.exibirPosicao();
        r_XY.mover(1, -1, a);
        r_XY.exibirPosicao();
        r_XY.mover(-1, 1, a);
        r_XY.exibirPosicao();

        // Teste de valores extremos
        
        System.out.println("\n=== Teste de Valores Extremos ===");
        
        // Caso 1: Movimento com delta zero (sem deslocamento)
        System.out.println("\nTentando movimento com delta 0,0 (sem deslocamento):");
        boolean movZero = rCarga.mover(0, 0, a);
        System.out.println("Resultado: " + movZero);

        // Caso 2: Movimento com delta negativo exagerado (além dos limites do ambiente)
        System.out.println("\nTentando movimento com delta negativo exagerado:");
        boolean movNegExtremo = rCarga.mover(-100, -100, a);
        System.out.println("Resultado: " + movNegExtremo);

        // Caso 3: Movimento com delta positivo exagerado (além dos limites do ambiente)
        System.out.println("\nTentando movimento com delta positivo exagerado:");
        boolean movPosExtremo = rCarga.mover(20, 20, a);
        System.out.println("Resultado: " + movPosExtremo);

    }
}