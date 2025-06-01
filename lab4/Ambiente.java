import java.util.ArrayList;
import java.util.Arrays;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int aX;
    private int aY;
    private int aZ;
    public ArrayList<Entidade> listaEntidades;
    public TipoEntidade mapa[][][]; //mostra o que ocupa cada espaço
    public int som[][][]; //registra a intensidade se som no ambiente
    int largura = mapa.length;                 //tamanho eixo x
    int altura = mapa[0].length;               //tamanho eixo y
    int profundidade = mapa[0][0].length;      //tamanho eixo z
    

    //Construtor para inicializar o ambiente com as dimensões específicas
    public Ambiente(int aX, int aY, int aZ){
        this.aX = aX;
        this.aY = aY;
        this.aZ = aZ;
        this.listaEntidades=new ArrayList<Entidade>();
        this.mapa=new TipoEntidade[aX][aY][aZ];
        this.som=new int[aX][aY][aZ];
    }

    //inicializa o mapa
    public void inicializarMapa(){
        Arrays.fill(this.mapa, TipoEntidade.VAZIO);
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

    //tem o msm funcionamento do adicionarRobo mas para entidades em geral e considerei 3 exceptions, se n cair nelas ataliza lista e mapa (+)
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
    
            if (e.podeAdicionar(this)) {
                throw new PosicaoOcupadaException("Espaço já ocupado por outra entidade.");
            }
    
            if (e.getTipoEntidade() == TipoEntidade.ROBO) {
                for (Entidade existente : listaEntidades) {
                    if (existente.getNome().equals(e.getNome())) {
                        throw new NomeDuplicadoException("Este nome já esta sendo usando por outro rôbo!");
                    }
                }
            }
    
            listaEntidades.add(e);
            if(e.getTipoEntidade()==TipoEntidade.ROBO){
                mapa[x1][y1][z]=TipoEntidade.ROBO;
            }
            else{ //Entidade é um obstáculo
                for(int i=x1; i<x2; i++){
                    for(int j=y1; j<=y2; j++){
                        for(int k=0; k<z; k++){
                            if(mapa[i][j][k]==TipoEntidade.VAZIO){
                                mapa[i][j][k]=TipoEntidade.OBSTACULO;
                            }
                        }
                    }
                }
            }
            System.out.println("\nEntidade '" + e.getTipoEntidade() + "' adicionada com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException | NomeDuplicadoException ex ) {
            System.out.println("Erro ao adicionar entidade: " + ex.getMessage());
        }
    }

    //Remover entidade do mapa e da lista (+)
    public void removerEntidade(Entidade e) {
        int x1 = e.getX1();
        int x2=e.getX2();
        int y1 = e.getY1();
        int y2 = e.getY2();
        int z = e.getZ();

        if (this.listaEntidades.remove(e)) {

            if(e.getTipoEntidade()==TipoEntidade.ROBO){
                mapa[x1][y1][z]=TipoEntidade.VAZIO;
            }
            else{ //Entidade é um obstáculo
                for(int i=x1; i<x2; i++){
                    for(int j=y1; j<=y2; j++){
                        for(int k=0; k<z; k++){
                            if(!temRobo(i, j, k)){//Se não tem rôbo embaixo do sábio mágico
                                mapa[i][j][k]=TipoEntidade.VAZIO;
                            }
                            else{
                                mapa[i][j][k]=TipoEntidade.ROBO;
                            }
                        }
                    }
                }
            }
    
            System.out.println("\nEntidade " + e.getNome()+ ", " + e.getTipoEntidade() + " removida com sucesso!");
        } 
        else {
            System.out.println("Entidade " + e.getNome() + ", " + e.getTipoEntidade() + " não encontrada no ambiente.");
        }
    }

    //Utilizado para remover obstáculos porque pode ocorrer uma sobreposição entre o sábio mágico e um rôbo
    Boolean temRobo(int x, int y, int z){
        for(Entidade e : listaEntidades){
            if(e.getTipoEntidade()==TipoEntidade.ROBO){
                if(e.getX1()==x && e.getY1()==y && e.getZ()==z){
                    return true;
                }
            }
        }
        return false;
    }

    //Encontra Rôbo pela lista (+)
    public Robo buscarRoboPorNome(String nome) {
        for (Entidade e : listaEntidades) {
            if (e instanceof Robo r) {
                if (r.getNome().equalsIgnoreCase(nome)) {
                    return r;
                }
            }
        }
        return null;
    }
    
    //Adiciona caixa de som (não sei se posso retirar)
    public void adicionaCaixaDeSom(CaixaDeSom c){
        int X = c.getX1();
        int Y = c.getY1();
        int Z = c.getZ();
    
        try {
            if (!dentroDosLimites(X, Y, Z)) {
                throw new ForaDosLimitesException("Posição da entidade '" + c.getDescricao() + "' está fora dos limites.");
            }
    
            if (c.podeAdicionar(this)) {
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

            System.out.println("\nEntidade '" + TipoEntidade.CAIXADESOM + "' adicionada com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException ex ) {
            System.out.println("Erro ao adicionar entidade: " + ex.getMessage());
        }
    
    }

    //remove caixa de som do ambiente (não sei se posso retirar)
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
        
            System.out.println("\nEntidade " + c.getNome()+ ", " + c.getTipoEntidade() + " removida com sucesso!");
        } 
        else {
            System.out.println("Entidade " + c.getNome() + ", " + c.getTipoEntidade() + " não encontrada no ambiente.");
        }
        
    }

    //(CONSERTAR) acredito que criar um getColisão em entidades, e tentar calcular colisões de robos-robos e robos-obstaculos (+)
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
    
    
    // Usa método da classe robo para chamar sensor (+)
    public void executarSensores() {
        for (Entidade e : listaEntidades) {
            if (e instanceof Robo) {
                ((Robo) e).usarSensores();
            } else {
                throw new UsavelApenasPorRobosException(
                    "A entidade '" + e.getNome() + "' do tipo " + e.getTipoEntidade() + " não pode usar sensores."
                );
            }
        }
    }
    
    // Move entidade(+)
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) {
        int xAtual = e.getX();
        int yAtual = e.getY();
        int zAtual = e.getZ();
    
        try {
            if (!dentroDosLimites(novoX, novoY, novoZ)) {
                throw new ForaDosLimitesException("Nova posição está fora dos limites.");
            }
    
            if (BloqueioAoAdicionar(novoX, novoY, novoZ)) {
                throw new PosicaoOcupadaException("Posição (" + novoX + ", " + novoY + ", " + novoZ + ") já está ocupada.");
            }
    
            mapa[xAtual][yAtual][zAtual] = TipoEntidade.VAZIO;
    
            e.setX(novoX);
            e.setY(novoY); //para esta parte funcionar eu adicionei os seters na entidade, mas como somente robos se movem, daria para modificar esse metodo pra tratar 
            e.setZ(novoZ); //somente robos, não sei qual ficaria melhor
    
            mapa[novoX][novoY][novoZ] = e.getTipoEntidade();
    
            System.out.println("Entidade '" + e.getDescricao() + "' movida com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException ex) {
            System.out.println("Erro ao mover entidade: " + ex.getMessage());
        }
    }
    
}


