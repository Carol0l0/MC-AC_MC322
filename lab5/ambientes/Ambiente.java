package ambientes;
import java.util.ArrayList;

import comunicacao.CentralComunicacao;
import entidades.Entidade;
import robos.TipoColorido;
import entidades.TipoEntidade;
import exception.ForaDosLimitesException;
import exception.NomeDuplicadoException;
import exception.PosicaoOcupadaException;
import exception.RoboDesligadoException;
import exception.UsavelApenasPorRobosException;
import obstaculos.CaixaDeSom;
import robos.Colorido;
import robos.Robo;

//Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int aX; //tamanho eixo x
    private int aY; //tamanho eixo y
    private int aZ; //tamanho eixo z
    public ArrayList<Entidade> listaEntidades;
    public TipoEntidade mapa[][][]; //mostra o que ocupa cada espaço
    public int som[][][]; //registra a intensidade se som no ambiente
    public CentralComunicacao centralAmbiente;

    //Construtor para inicializar o ambiente com as dimensões específicas
    public Ambiente(int aX, int aY, int aZ, CentralComunicacao centralAmbiente){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listaEntidades=new ArrayList<Entidade>();
        this.mapa=new TipoEntidade[aX][aY][aZ];
        this.som=new int[aX][aY][aZ];
        this.centralAmbiente = new CentralComunicacao();
    }

    //inicializa o mapa
    public void inicializarMapa(){
    mapa = new TipoEntidade[aX][aY][aZ];
        for (int x = 0; x < aX; x++) {
            for (int y = 0; y < aY; y++) {
                for (int z = 0; z < aZ; z++) {
                mapa[x][y][z] = TipoEntidade.VAZIO;
            }
        }
    }
}

    //Método para verificar se uma posição está dentro dos limites do ambiente
    public boolean dentroDosLimites (int x, int y, int z){
        return x>=0 && x<this.aX && y>=0 && y<this.aY && z>=0 && z<this.aZ;
    }

    //Para conferir se a entidade pode ser adicionada ao ambiente
    public boolean BloqueioAoAdicionar(int x, int y, int z) {

        for(Entidade e : this.listaEntidades){
            if(e.getTipoEntidade()==TipoEntidade.ROBO){//Se for Rôbo
                if (e.getX1() == x && e.getY1() == y && e.getZ() == z) {
                    return true;
                }
            }
            else{//Se for objeto
                int altura = e.getZ();
                if (x >= e.getX1() && x <= e.getX2() &&
                    y >= e.getY1() && y <= e.getY2() && 
                    z <= altura) {
                    return true;
                }
            }
        }
    
        return false;
    }

    //tem o msm funcionamento do adicionarRobo mas para entidades em geral e considerei 3 exceptions, se n cair nelas ataliza lista e mapa 
    public void adicionarEntidade(Entidade e) {
        int x1 = e.getX1();
        int x2=e.getX2();
        int y1 = e.getY1();
        int y2 = e.getY2();
        int z = e.getZ();
    
        try {
            if (!dentroDosLimites(x1, y1, z) || !dentroDosLimites(x2, y2, z)) {
                throw new ForaDosLimitesException("Posição da entidade '" + e.getDescricao() + "' está fora dos limites.");
            }

            if(!e.podeAdicionar(this)){
                throw new PosicaoOcupadaException("Espaço já ocupado por outra entidade!");
            }
    
            if (e.getTipoEntidade() == TipoEntidade.ROBO) {
                for (Entidade existente : listaEntidades) {
                    if (existente.getId().equals(e.getId())) {
                        throw new NomeDuplicadoException("Este nome já esta sendo usando por outro rôbo!");
                    }
                }
            }
    
            listaEntidades.add(e);
            if(e.getTipoEntidade()==TipoEntidade.ROBO){ //entidade é um robô
                Robo r = (Robo) e;
                r.setAmbiente(this);
                r.setCentral(this.centralAmbiente);
                mapa[x1][y1][z]=TipoEntidade.ROBO;
            }
            else{ //Entidade é um obstáculo
                for(int i=x1; i<x2; i++){
                    for(int j=y1; j<=y2; j++){
                        for(int k=0; k<z; k++){
                            mapa[i][j][k]=e.getTipoEntidade(); //Pode ser um sábio mágico ou obstáculo comum
                        }
                    }
                }
            }
            System.out.println("\nEntidade '" + e.getId() + "' adicionada com sucesso!");
    
        } catch (ForaDosLimitesException | NomeDuplicadoException | PosicaoOcupadaException ex ) {
            System.out.println("Erro ao adicionar entidade: " + ex.getMessage());
        }


    }

    //Remover entidade do mapa e da lista 
    public void removerEntidade(Entidade e) {
        int x1 = e.getX1();
        int x2 = e.getX2();
        int y1 = e.getY1();
        int y2 = e.getY2();
        int z = e.getZ();

        if (this.listaEntidades.remove(e)) {

            if(e.getTipoEntidade()==TipoEntidade.ROBO){
                if(!temObstaculo(x1, y1, z)){ //se não existe obstáculo 'embaixo'
                    mapa[x1][y1][z]=TipoEntidade.VAZIO;
                }
                else{ //conferindo qual é o tipo do obstáculo 'embaixo' do rôbo
                    mapa[x1][y1][z]=qualObstaculo(x1, y1, z).getTipoEntidade();
                }
            }
            else{ //Entidade é um obstáculo
                for(int i=x1; i<x2; i++){
                    for(int j=y1; j<=y2; j++){
                        for(int k=0; k<z; k++){
                            mapa[i][j][k]=TipoEntidade.VAZIO;
                        }
                    }
                }
            }
    
            System.out.println("\nEntidade " + e.getId()+ ", " + e.getTipoEntidade() + " removida com sucesso!");
        } 
        else {
            System.out.println("Entidade " + e.getId() + ", " + e.getTipoEntidade() + " não encontrada no ambiente.");
        }
    }

    //Utilizado para remover obstáculos porque pode ocorrer uma sobreposição entre o sábio mágico e um rôbo
    Boolean temObstaculo(int x, int y, int z){
        for(Entidade e : listaEntidades){
            if(e.getTipoEntidade()!=TipoEntidade.ROBO){
                if(e.getX1()<=x && x<=e.getX2() && e.getY1()<=y && y<=e.getY2() && z<=e.getZ()){
                    return true;
                }
            }
        }
        return false;
    }

    public Entidade qualObstaculo(int x, int y, int z){
        for(Entidade e : listaEntidades){
            if(e.getTipoEntidade()!=TipoEntidade.ROBO){
                if(e.getX1()<=x && x<=e.getX2() && e.getY1()<=y && y<=e.getY2() && z<=e.getZ()){
                    return e;
                }
            }
        }
        return null;
    }

    //Encontra Rôbo pela lista 
    public Robo buscarRoboPorNome(String nome) {
        for (Entidade e : listaEntidades) {
            if (e instanceof Robo r) {
                if (r.getId().equalsIgnoreCase(nome)) {
                    return r;
                }
            }
        }
        return null;
    }
    
    //Adiciona caixa de som 
    public void adicionaCaixaDeSom(CaixaDeSom c){
        int X = c.getX1();
        int Y = c.getY1();
        int Z = c.getZ();
    
        try {
            if (!dentroDosLimites(X, Y, Z)) {
                throw new ForaDosLimitesException("Posição da entidade '" + c.getDescricao() + "' está fora dos limites.");
            }
    
            if (!c.podeAdicionar(this)) {
                throw new PosicaoOcupadaException("Espaço já ocupado por outra entidade.");
            }
    
            listaEntidades.add(c); //adiciona nalista de entidades
            
            mapa[X][Y][Z]=TipoEntidade.CAIXADESOM;//modifica o mapa
            
            int x=c.getX1(), y=c.getY1(), z=0, intensidade=c.getIntensidade();
            for (int i=intensidade; i>0; i--) {//Modifica a matriz do som
                for (int j=x-i; j<=x+i; j++) {
                    for (int k=y-i; k<=y+i; k++) {
                        for (int l=z-i; l<=z+i; l++) {
                            if (j>=0 && j<10 && k>=0 && k<10 && l>=0 && l<10) {
                                this.som[j][k][l]++;
                            }
                        }
                    }
                }
            }

            System.out.println("\nEntidade '" + c.getId() + "' adicionada com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException ex ) {
            System.out.println("Erro ao adicionar entidade: " + ex.getMessage());
        }
    
    }

    //remove caixa de som do ambiente 
    public void removeCaixaDeSom(CaixaDeSom c){
        int x = c.getX1();
        int y = c.getY1();
        int z = c.getZ();

        if (this.listaEntidades.remove(c)) {
            mapa[x][y][z]=TipoEntidade.VAZIO;
            int intensidade=c.getIntensidade();
            for (int i=intensidade; i>0; i--) {
                for (int j=x-i; j<=x+i; j++) {
                    for (int k=y-i; k<=y+i; k++) {
                        for (int l=z-i; l<=z+i; l++) {
                            if (j>=0 && j<10 && k>=0 && k<10 && l>=0 && l<10) {
                                this.som[j][k][l]--;
                            }
                        }
                    }
                }
            }
        
            System.out.println("\nEntidade " + c.getId()+ ", " + c.getTipoEntidade() + " removida com sucesso!");
        } 
        else {
            System.out.println("Entidade " + c.getId() + ", " + c.getTipoEntidade() + " não encontrada no ambiente.");
        }
        
    }

    public void detectarColisoes(){ //Conta o número total de colisões até agora
    
        int totalColisoes = 0;
    
        for (Entidade e : listaEntidades) {
            if(e.getTipoEntidade()==TipoEntidade.ROBO){
                Robo robo = (Robo) e;
                totalColisoes += robo.getTotalColisoes();
            }
        }
    
        System.out.println("Já houveram " + totalColisoes/2 + " colisões no total!");

    }

    //Mostra o Ambiente em uma altura z
    public void visualizarAmbiente(int z) {
        System.out.println("\n--- Visão do ambiente no plano XY com Z = " + z + " ---");
    
        for (int i = 0; i < aX; i++) {
            for (int j = 0; j < aY; j++) {
                char representacao = mapa[i][j][z].representacao;
                String cor = "";
    
                // Procura uma entidade naquela posição
                for (Entidade e : listaEntidades) {
                    if (e.getZ() == z &&
                        i >= e.getX1() && i <= e.getX2() &&
                        j >= e.getY1() && j <= e.getY2()) {
    
                        if (e instanceof Colorido colorido) {
                            cor = colorido.getCor().cor;
                        }
                        break;
                    }
                }
    
                // Imprime com ou sem cor
                System.out.print(cor + representacao + TipoColorido.RESET.cor + " ");
            }
            System.out.println();
        }
    }
    
    // Usa método da classe robo para chamar sensor 
    public void executarSensores() {
        for (Entidade e : listaEntidades) {
            if (e instanceof Robo) {
                try{
                    ((Robo) e).gerenciadorSens.acionarSensores();
                }
                catch(RoboDesligadoException ex){
                    System.out.println("Robô "+e.getId()+" desligado!");
                }
                
            } else {
                throw new UsavelApenasPorRobosException(
                    "A entidade '" + e.getId() + "' do tipo " + e.getTipoEntidade() + " não pode usar sensores."
                );
            }
        }
    }
    
    // Move entidade
    public void moverEntidade(Entidade e, int novoX1, int novoY1){
        int largura = e.getX2() - e.getX1(); // diferença entre X2 e X1
        int profundidade = e.getY2() - e.getY1();
        int z = e.getZ();

        int novoX2 = novoX1 + largura;
        int novoY2 = novoY1 + profundidade;
    
        try {
            // Verificar se todos os pontos da nova posição estão dentro dos limites
            for (int x = novoX1; x <= novoX2; x++){
                for (int y = novoY1; y <= novoY2; y++){
                    if (!dentroDosLimites(x, y, z)){
                        throw new ForaDosLimitesException("Nova posição está fora dos limites.");
                    }
                }
            }
            
            // Verificar colisão com outras entidades
            for (Entidade outra : listaEntidades){
                if (outra == e){
                    continue;
                } 

                // Para obstáculos, comparar todos os níveis de z se for um obstáculo volumétrico
                int zIni = (outra.getTipoEntidade() == TipoEntidade.OBSTACULO) ? 0 : outra.getZ();
                int zFim = (outra.getTipoEntidade() == TipoEntidade.OBSTACULO) ? outra.getZ() : outra.getZ();

                for (int i = zIni; i <= zFim; i++){
                    if (i != z){ 
                        continue;
                    }

                    //estão no mesmo plano Z
                    boolean colisao =
                        novoX2 >= outra.getX1() && novoX1 <= outra.getX2() &&
                        novoY2 >= outra.getY1() && novoY1 <= outra.getY2();

                    if (colisao) {
                        throw new PosicaoOcupadaException("Posição ("+novoX1+" - "+novoX2+", "+novoY1+" - "+novoY2+", "+z+") já está ocupada.");
                    }
                }
            }
    
            //ajustando o mapa
            int x1=e.getX1(), y1=e.getY1();
            if(e.getTipoEntidade()==TipoEntidade.ROBO){
                if(!temObstaculo(x1, y1, z)){ //se não existe obstáculo 'embaixo'
                    mapa[x1][y1][z]=TipoEntidade.VAZIO;
                }
                else{ //conferindo qual é o tipo do obstáculo 'embaixo' do rôbo
                    mapa[x1][y1][z]=qualObstaculo(x1, y1, z).getTipoEntidade();
                }
                mapa[novoX1][novoY1][z]=TipoEntidade.ROBO; //adicionando robô na posição nova
            }
            else{ //Entidade é um obstáculo
                for(int i=0; i<largura; i++){
                    for(int j=0; j<=profundidade; j++){
                        for(int k=0; k<z; k++){
                            mapa[x1+i][y1+j][k]=TipoEntidade.VAZIO; //removendo obstáculo da posição antiga
                            mapa[novoX1+i][novoY1+j][k]=e.getTipoEntidade(); //adicionando obstáculo na posição nova
                        }
                    }
                }
            }
    
            //atualizando a posição da entidade
            e.setX1(novoX1);
            e.setX2(novoX2);
            e.setY1(novoY1);
            e.setY2(novoY2);  
    
            System.out.println("Entidade '" + e.getId() + "' movida com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException ex) {
            System.out.println("Erro ao mover entidade: " + ex.getMessage());
        }
    }
    
}


