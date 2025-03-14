import java.util.Scanner;

class Main{
    public static void main (String[] args) {

        String nomeRobo, checar;
        int x, y, alt, larg, movX, movY;
        Robo r1;
        Ambiente a1;

        Scanner entrada=new Scanner(System.in);
        
        System.out.println("Largura:");
            larg=entrada.nextInt();
        
        System.out.println("Altura:");
            alt=entrada.nextInt();

        a1=new Ambiente(alt, larg);

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