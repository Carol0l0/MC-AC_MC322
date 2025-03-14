import java.util.Scanner;

class Main{
    public static void main (String[] args) {

        String nomeRobo;
        int x, y, alt, larg, movX, movY;
        Robo r1;
        Ambiente a1;

        Scanner entrada=new Scanner(System.in);
        
        System.out.println("Altura:");
            alt=entrada.nextInt();

        System.out.println("Largura:");
            larg=entrada.nextInt();

        a1=new Ambiente(alt, larg);

        System.out.println("Nome:");
            nomeRobo=entrada.next();

        System.out.println("posicao X:");
            x=entrada.nextInt();
        
        System.out.println("posicao Y:");
            y=entrada.nextInt();
        
        r1=new Robo(nomeRobo, x, y);
        r1.exibirPosicao();

        System.out.println(a1.dentroDosLimites(r1.posicaoY, r1.posicaoX));
        if(a1.dentroDosLimites(r1.posicaoY, r1.posicaoX)){
            System.out.println("andar X:");
                movX=entrada.nextInt();
        
            System.out.println("andar Y:");
                movY=entrada.nextInt();
            r1.mover(movX, movY, a1);
            r1.exibirPosicao();
        }


    }

}