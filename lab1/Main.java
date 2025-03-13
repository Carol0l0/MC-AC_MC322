import java.util.Scanner;

class Main{
    public static void main (String[] args) {

        String nomeRobo;
        int x, y;
        Robo r1=new Robo();

        Scanner entrada=new Scanner(System.in);
        
        System.out.println("Nome:");
            nomeRobo=entrada.nextLine();

        System.out.println("posicao X:");
            x=entrada.nextInt();
        
        System.out.println("posicao Y:");
            y=entrada.nextInt();
        
        r1.construtor(nomeRobo, x, y);

        System.out.print(entrada);

        r1.exibirPosicao();

    }

}
