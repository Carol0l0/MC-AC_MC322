import java.util.Scanner;

public class TerrestreBlindado extends Robo {

    String direcao;
    private int resistencia;
    private boolean funcionando;

    //criado resistencia pra ser quantos obstaculos(outros robos) o robo blindado tanka
    public TerrestreBlindado(String nome, int posicaoX, int posicaoY, int posicaoZ) {
        super(nome, posicaoX, posicaoY, posicaoZ);
        this.resistencia = 5; 
        this.funcionando = true; 
    }

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {

        if (!funcionando) {
            System.out.println(getNome() + " está destruído! Não pode mais se mover.");
            return false;
        }

        Scanner entrada = new Scanner(System.in);

        System.out.println("\nO sistema de navegação do robô blindado foi projetado para priorizar estabilidade e segurança.");
        System.out.println("Ele permite movimentos apenas em uma direção por vez para evitar falhas de locomoção.");
        System.out.print("Escolha a direção (X ou Y): ");

        //aceita maiusculo e minusculo
        direcao = entrada.next().toUpperCase();
        entrada.close();

        boolean conseguiuMover = false;

        if (direcao.equals("Y")) {

            if (!caminhoLivre(a, posicaoX, posicaoY, posicaoX, posicaoY + deltaY)) {
                System.out.println("Caminho bloqueado no eixo Y!");
                sofreDano(); 
                conseguiuMover = super.mover(0, deltaY, a);
                return true;
            }

            conseguiuMover = super.mover(0, deltaY, a);

        } else if (direcao.equals("X")) {

            if (!caminhoLivre(a, posicaoX, posicaoY, posicaoX + deltaX, posicaoY)) {
                System.out.println("Caminho bloqueado no eixo X!");
                sofreDano();
                conseguiuMover = super.mover(0, deltaY, a);
                return true;
            }

            conseguiuMover = super.mover(deltaX, 0, a);

        } else {
            System.out.println("Direção inválida! Digite X ou Y.");
            return false;
        }

        if (conseguiuMover) {
            System.out.println(getNome() + " se moveu para (" + posicaoX + ", " + posicaoY + ")");
        } 


        return conseguiuMover;
    }

    //contador da res do blind, quando acaba retorna falso pro funcionando e ele retorna como destruido
    private void sofreDano() {
        resistencia--;
        System.out.println(getNome() + " sofreu dano! Resistência atual: " + resistencia);

        if (resistencia <= 0) {
            funcionando = false;
            System.out.println(getNome() + " foi destruído após múltiplas colisões! Fim de linha...");
        }
    }

    //bendita q compara cada parte do caminho
    private boolean caminhoLivre(Ambiente a, int inicioX, int inicioY, int destinoX, int destinoY) {

        //verificsndo horizontal
        if (inicioY == destinoY) {
            int passo = (destinoX > inicioX) ? 1 : -1;
            //ai um for com o inicio até o fim pulando -1 ou +1 de passo pra verificar cada localzinho
            for (int x = inicioX + passo; x != destinoX + passo; x += passo) {
                //ai chama idobs pra saber se tem algo, (só testei pra quando existe um obstaculo apenas no caminho, dps vou ver como vai funcionar com varios)
                //provavelmente um contador pra dar +2,+3 etc de dano no robo se aparecer +1 obstaculo de uma vez
                if (identificarObstaculo(a, x, inicioY, posicaoZ)) {
                    System.out.println("Obstáculo detectado no caminho! Em (" + x + ", " + inicioY + ")");
                    return false; 
                }
            }
        }

        //verificando vertical
        if (inicioX == destinoX) {
            //aqui ele verifica se o final é maior ou menor que o inicial pra saber se ele verifica pra esquerda ou direita
            int passo = (destinoY > inicioY) ? 1 : -1;
            for (int y = inicioY + passo; y != destinoY + passo; y += passo) {
                if (identificarObstaculo(a, inicioX, y, posicaoZ)) {
                    System.out.println("Obstáculo detectado no caminho! Em (" + inicioX + ", " + y + ")");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Boolean identificarObstaculo(Ambiente a, int x, int y, int z) {
        for (Robo robo : a.listadeRobos) {
            if (robo.posicaoX == x && robo.posicaoY == y && robo.posicaoZ == z && robo != this) {
                System.out.println("Obstáculo detectado!");
                return true;
            }
        }
        return false;
    }

    public int getResistencia() {
        return resistencia;
    }

    public boolean estaFuncionando() {
        return funcionando;
    }
}