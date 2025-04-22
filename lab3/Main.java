public class Main {

    public static void main(String[] args) {

        //*Criando o ambiente
        Ambiente a = new Ambiente(10, 10, 10);
        CaixaDeSom c = new CaixaDeSom(4, 5, 0, 0, TipoObstaculo.CAIXADESOM, 3);
        System.err.println(c.getTipo());
        a.adicionaCaixaDeSom(c);
        for (int i = 0; i < a.som.length; i++) {
            // Loop interno para iterar sobre as colunas
            for (int j = 0; j < a.som[i].length; j++) {
                // Acessar e imprimir o elemento atual
                System.out.print(a.som[i][j][0] + " ");
            }
            // Quebra de linha após imprimir cada linha
            System.out.println();
        }

        /* 
        //Criando robôs
        RoboTerrestreDeCarga rCarga = new RoboTerrestreDeCarga("Robo de Carga", 3, 5, 0, 5, 10);
        RoboTerrestreBlindado rBlindado = new RoboTerrestreBlindado("Robo Blindado", 5, 0, 0, 7);
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
        System.out.println("\nExcedendo limite de velocidade e ambiente do Robô de Carga e do Robô Blindado");
        rCarga.mover(11, 3, a);
        rBlindado.mover(8, "Y", a);

        //Caso ultrapasse a cargaMáxima do RoboTerrestreDeCarga
        System.out.println("\nSobrecarregando 1 com 11kg:");
        rCarga.carregarPeso(11);

        //movimento TerrestreBlindado
        System.out.println("\nMovendo em X:");
        rBlindado.mover(-2, "X", a);
        rBlindado.exibirPosicao();
        System.out.println("\nMovendo em Y:");
        rBlindado.mover(2, "Y", a);
        rBlindado.exibirPosicao();

        //Caso encontra um obstáculo + recebendo dano + caso Resistencia > 0 continuando
        System.out.println("\nRobo de Carga no caminho do Robo Blindado:");
        rCarga.exibirPosicao();
        rBlindado.exibirPosicao();
        rBlindado.mover(5, "Y", a);
        rBlindado.exibirPosicao();
        rBlindado.mover(-5, "Y", a);
        rBlindado.exibirPosicao();

        //Caso encontra +1 obstáculo no meio do caminho
        System.out.println("\nCriando +1 obstáculo no caminho");
        RoboTerrestre obs1 = new RoboTerrestre("obstaculo 1", 3, 6, 0, 6);
        a.adicionarRobo(obs1);
        rBlindado.mover(5, "Y", a);

        //Caso encontra um obstáculo + recebendo dano + caso Resistencia = 0 parando no destino
        System.out.println("\nSofrendo 1 de dano:");
        rBlindado.sofreDano(1);

        //Verificando que após sua destruição ele não se move mais
        System.out.println("\nTentando move-lo depois de sua destruição:");
        rBlindado.exibirPosicao();
        rBlindado.mover(1, "X", a);
        System.out.println("Mantém posição");
        rBlindado.exibirPosicao();

        //movendo Robôs aéreos
        //testando colisão para subir
        System.out.println("\nColisão de robôs aéreos tentando subir:");
        r_XY.exibirPosicao();
        r_YX.exibirPosicao();
        r_YX.subir(2, a);

        //subindo além da altitude máxima
        System.out.println("\nSubindo acima da altitude máxima:");
        r_XY.subir(5, a);

        //mudança direção no Aéreo XY
        System.out.println("\nMovimentação de direção do Aéreo XY:");
        r_XY.mover(1, 1, a);
        r_XY.exibirPosicao();
        r_XY.mover(-1, -1, a);
        r_XY.exibirPosicao();
        r_XY.mover(1, -1, a);
        r_XY.exibirPosicao();
        r_XY.mover(-2, 1, a);
        r_XY.exibirPosicao();

        //colisão robôs aéreos
        System.out.println("\nColisão de robôs aéreos:");
        r_YX.subir(2, a);
        r_YX.exibirPosicao();
        r_XY.mover(1, 2, a);
        r_YX.mover(0, -2, a);
        r_YX.exibirPosicao();
        r_XY.mover(1, -2, a);
        r_XY.exibirPosicao();

        // Teste de valores extremos
        
        System.out.println("\n=== Teste de Valores Extremos ===");
        
        // Caso 1: Movimento com delta zero (sem deslocamento)
        System.out.println("\nTentando movimento com delta 0,0 (sem deslocamento):");
        r_YX.mover(0, 0, a);
        r_YX.descer(0, a);

        // Caso 2: Movimento com delta negativo exagerado (além dos limites do ambiente)
        System.out.println("\nTentando movimento com delta negativo exagerado:");
        boolean movNegExtremo = rCarga.mover(-100, -100, a);
        System.out.println("Resultado: " + movNegExtremo);

        // Caso 3: Movimento com delta positivo exagerado (além dos limites do ambiente)
        System.out.println("\nTentando movimento com delta positivo exagerado:");
        boolean movPosExtremo = rCarga.mover(20, 20, a);
        System.out.println("Resultado: " + movPosExtremo);

        //Caso4: número de obstáculos maior que o dano permitido
        System.out.println("\nMais obstáculos no meio do caminho do que a resistência");
        rBlindado.recuperaDano(2);
        rBlindado.exibirPosicao();
        RoboTerrestre obs2 = new RoboTerrestre("obstaculo 2", 3, 4, 0, 6);
        a.adicionarRobo(obs2);
        rBlindado.mover(-4, "Y", a);
        rBlindado.exibirPosicao();
        */


    }
}