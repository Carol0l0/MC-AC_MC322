public class Main {

    public static void main(String[] args) {

        //Criando o ambiente
        Ambiente a = new Ambiente(10, 10, 10);
        
        //Criando robôs terrestres
        RoboTerrestreDeCarga robo1 = new RoboTerrestreDeCarga("Robo1", 0, 0, 0, 5, 10, 100);
        RoboTerrestreBlindado robo2 = new RoboTerrestreBlindado("Robo2", 5, 5, 0);

        // Adicionando robôs ao ambiente
        a.adicionarRobo(robo1);
        a.adicionarRobo(robo2);

        //Caso terrestres excedam os limites do ambiente
        System.out.println("\nExcedendo limite de velocidade e ambiente do 1 e 3");
        robo1.mover(11, 3, a);  

        //Caso ultrapasse a cargaMáxima do RoboTerrestreDeCarga
        System.out.println("\nSobrecarregando 1 com 11kg:");
        robo1.carregarPeso(11);

        //Caso TerrestreDeCarga fique sem bateria
        System.out.println("\nMovimentando TerrestreDeCarga até bateria acabar:");
        robo1.mover(1, 1, a);
        robo1.getNivelBateria();
        robo1.mover(1, 1, a);
        robo1.getNivelBateria();
        robo1.mover(1, 1, a);
        robo1.getNivelBateria();
        robo1.mover(1, 1, a);
        robo1.getNivelBateria();
        robo1.mover(-1, -1, a);
        robo1.getNivelBateria();
        robo1.mover(-1, -1, a);

        //Caso queira recarregar TerrestreDeCarga
        System.out.println("\nRecarregando TerrestreDeCarga:");
        robo1.carregarBateria();
        robo1.getNivelBateria();

        //movimento TerrestreBlindado
        System.out.println("\nMovendo em X:");
        robo2.mover(-2, "X", a);
        System.out.println("\nMovendo em Y:");
        robo2.mover(-1, "Y", a);

        //Caso encontra um obstáculo + recebendo dano + caso Resistencia > 0 continuando
        System.out.println("\nRobo1 no caminho do Robo2:");
        robo2.mover(-2, "Y", a);

        //Caso encontra +1 obstáculo no meio do caminho
        System.out.println("\nCriando +1 obstáculo no caminho");
        RoboTerrestreDeCarga robo5 = new RoboTerrestreDeCarga("Robo5", 4, 2, 0, 5, 10, 100);
        RoboTerrestreDeCarga robo6 = new RoboTerrestreDeCarga("Robo6", 5, 2, 0, 5, 10, 100);
        a.adicionarRobo(robo6);
        a.adicionarRobo(robo5);
        robo2.mover(4, "X", a);

        //Caso encontra um obstáculo + recebendo dano + caso Resistencia = 0 parando no destino
        System.out.println("\nSofrendo 2 de dano:");
        robo2.sofreDano(2);

        //Verificando que após sua destruição ele não se move mais
        System.out.println("\nTentando move-lo depois de sua destruição:");
        robo2.mover(1, "X", a);
        System.out.println("\nMantém posição");

        // Teste de valores extremos
        
        System.out.println("\n=== Teste de Valores Extremos ===");
        
        // Caso 1: Movimento com delta zero (sem deslocamento)
        System.out.println("\nTentando movimento com delta 0,0 (sem deslocamento):");
        boolean movZero = robo1.mover(0, 0, a);
        System.out.println("Resultado: " + movZero);
        robo1.getNivelBateria();

        // Caso 2: Movimento com delta negativo exagerado (além dos limites do ambiente)
        System.out.println("\nTentando movimento com delta negativo exagerado:");
        boolean movNegExtremo = robo1.mover(-100, -100, a);
        System.out.println("Resultado: " + movNegExtremo);
        robo1.getNivelBateria();

        // Caso 3: Movimento com delta positivo exagerado (além dos limites do ambiente)
        System.out.println("\nTentando movimento com delta positivo exagerado:");
        boolean movPosExtremo = robo1.mover(20, 20, a);
        System.out.println("Resultado: " + movPosExtremo);
        robo1.getNivelBateria();

    }
}
