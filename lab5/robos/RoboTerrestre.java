package robos;
//Classe que representa um robô terrestre e a velocidade máxima

import exception.ForaDosLimitesException;
import exception.ObstaculoException;
import exception.RoboDesligadoException;

public class RoboTerrestre extends Robo {
    
    protected int v_max;
    
    public RoboTerrestre (String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max){
        super(nome, posicaoX, posicaoY, posicaoZ);
        this.v_max=v_max;
    }

    //verificar se a quantidade de movimento excede a velocidade posta
    @Override
    public boolean mover(int deltaX, int deltaY) {
        if (Math.abs(deltaX) + Math.abs(deltaY) > v_max) {
            System.out.println("Velocidade máxima excedida! " + getId() + " não conseguiu se mover!");
            return false;
        }
    
        int destinoX = this.posicaoX + deltaX;
        int destinoY = this.posicaoY + deltaY;
        int destinoZ = this.posicaoZ; // robôs terrestres não mudam Z
    
        try {
            moverPara(destinoX, destinoY, destinoZ);
            return true;
        } catch (RoboDesligadoException e) {
            System.out.println(getId() + " está desligado e não pode se mover.");
        } catch (ForaDosLimitesException e) {
            System.out.println("Destino fora dos limites: " + e.getMessage());
        } catch (ObstaculoException e) {
            System.out.println("Obstáculo no caminho: " + e.getMessage());
        }
    
        return false;
    }
    
    //metodo limpo para ser usado pelas classes filhas
    @Override
    public void executarTarefa() {
    }

}