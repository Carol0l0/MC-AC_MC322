import java.util.Scanner;

//criei essa main aq pra testa o blindado q nao ta indo aaaaaaaaaaaaaa
//ele ta pedindo x e y e depois voce escolhe qual dos dois (ficou meio redundande dps mudar)



class Main {
    public static void main(String[] args) {

        String nomeRobo, checar;
        int x, y, z, aX, aY, aZ, movX, movY, a_max;
        Ambiente a1;
        AereoXY aero;
        Scanner entrada = new Scanner(System.in);

        //ambiente
        System.out.println("eixo X:");
        aX = entrada.nextInt();

        System.out.println("eixo Y:");
        aY = entrada.nextInt();

        System.out.println("eixo Z:");
        aZ = entrada.nextInt();

        a1 = new Ambiente(aX, aY, aZ);

        System.out.println("Nome:");
            nomeRobo=entrada.next();

        System.out.println("altitude máxima:");
            a_max=entrada.nextInt();

        System.out.println("posicao X:");
            x=entrada.nextInt();
        
        System.out.println("posicao Y:");
            y=entrada.nextInt();

        System.out.println("posicao Z:");
            z=entrada.nextInt();

        aero=new AereoXY(nomeRobo, x, y, z, a_max, "vermelho");
        a1.listadeRobos.add(aero);
        System.out.println(aero.getCor());


        while(a1.dentroDosLimites(aero.posicaoY, aero.posicaoX, aero.posicaoZ)){
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
            if(aero.mover(movX, movY, a1)){
                break;
            }
            aero.exibirPosicao();
        }

        entrada.close();
    }
}
