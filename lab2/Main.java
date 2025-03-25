import java.util.Scanner;

//criei essa main aq pra testa o blindado q nao ta indo aaaaaaaaaaaaaa
//ele ta pedindo x e y e depois voce escolhe qual dos dois (ficou meio redundande dps mudar)



class Main {
    public static void main(String[] args) {

        String nomeRobo;
        int x, y, larg, alt, movX, movY;
        Ambiente a1;
        Scanner entrada = new Scanner(System.in);

        //ambiente
        System.out.println("Largura:");
        larg = entrada.nextInt();

        System.out.println("Altura:");
        alt = entrada.nextInt();

        a1 = new Ambiente(alt, larg);

        //blindado
        System.out.println("Nome do Robô Blindado:");
        nomeRobo = entrada.next();

        System.out.println("Posição X do Blindado:");
        x = entrada.nextInt();

        System.out.println("Posição Y do Blindado:");
        y = entrada.nextInt();

        TerrestreBlindado blindado = new TerrestreBlindado(nomeRobo, x, y, 0);

        System.out.println("Nome do Robô Blindado:");
        nomeRobo = entrada.next();

        System.out.println("Posição X do Blindado:");
        x = entrada.nextInt();

        System.out.println("Posição Y do Blindado:");
        y = entrada.nextInt();

        //adc ele
        a1.listadeRobos.add(blindado);

        //cria um robo como obstaculo base
        Robo obstaculo = new Robo("Obstaculo", x + 2, y, 0);
        a1.listadeRobos.add(obstaculo);

        System.out.println("Blindado criado em (" + blindado.posicaoX + ", " + blindado.posicaoY + ")");
        System.out.println("Obstáculo criado em (" + obstaculo.posicaoX + ", " + obstaculo.posicaoY + ")");

        blindado.exibirPosicao();

        System.out.println("Nome:");
            nomeRobo=entrada.next();

        System.out.println("posicao X:");
            x=entrada.nextInt();
        
        System.out.println("posicao Y:");
            y=entrada.nextInt();
        
        r1=new Robo(nomeRobo, x, y);
        r1.exibirPosicao();

        while(a1.dentroDosLimites(r1.posicaoY, r1.posicaoX)){
            System.out.println("andar X:");
                checar=entrada.next();
                try{
                    movX=Integer.parseInt(checar);
                }
                catch(NumberFormatException e){
                    System.out.println("jogo terminado!");
                    break;
                }
        
            System.out.println("andar Y:");
                movY=entrada.nextInt();
            if(r1.mover(movX, movY, a1)){
                break;
            }
            r1.exibirPosicao();
        }

        entrada.close();
    }
}
