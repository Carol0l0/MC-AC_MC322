import java.util.ArrayList;

// Classe que representa o ambiente onde os robôs se movimentam
public class Ambiente{
    
    private int X;
    private int Y;
    private int Z;
    public ArrayList<Entidade> listaEntidades;
    public int som[][][];                       //registra a intensidade do som no ambiente
    private TipoEntidade[][][] mapa;
    int largura = mapa.length;                 //tamanho eixo x
    int altura = mapa[0].length;               //tamanho eixo y
    int profundidade = mapa[0][0].length;      //tamanho eixo z


    //Construtor para inicializar o ambiente com as dimensões específicas (+)
    public Ambiente(int X, int Y, int Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.listaEntidades=new ArrayList<Entidade>();
        this.som=new int[X][Y][Z];
    }

    //inicializa o mapa de acordo com as coordenadas iniciais (+)
    public void inicializarMapa() {
        mapa = new TipoEntidade[X][Y][Z];
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                for (int z = 0; z < Z; z++) {
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    //tem o msm funcionamento do adicionarRobo mas para entidades em geral e considerei 3 exceptions, se n cair nelas ataliza lista e mapa (+)
    public void adicionarEntidade(Entidade e) {
        int x = e.getX();
        int y = e.getY();
        int z = e.getZ();
    
        try {
            if (!dentroDosLimites(x, y, z)) {
                throw new ForaDosLimitesException("Posição da entidade '" + e.getDescricao() + "' está fora dos limites.");
            }
    
            if (BloqueioAoAdicionar(x, y, z)) {
                throw new PosicaoOcupadaException("Espaço já ocupado por outra entidade em (" + x + ", " + y + ", " + z + ").");
            }
    
            if (e.getTipo() == TipoEntidade.ROBO) {
                for (Entidade existente : listaEntidades) {
                    if (existente.getTipo() == TipoEntidade.ROBO && existente.getId().equals(e.getId())) {
                        throw new NomeDuplicadoException("Este nome já esta sendo usando por outro rôbo!");
                    }
                }
            }
    
            listaEntidades.add(e);
            mapa[x][y][z] = e.getTipo();
            System.out.println("\nEntidade '" + e.getDescricao() + "' adicionada com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException | NomeDuplicadoException ex ) {
            System.out.println("Erro ao adicionar entidade: " + ex.getMessage());
        }
    }
    
    //Método para verificar se uma posição está dentro dos limites do ambiente (+)
    public boolean dentroDosLimites (int x, int y, int z){
        return x>=0 && x<this.X && y>=0 && y<this.Y && z>=0 && z<this.Z;
    }

    //Para conferir se a entidade pode ser adicionada ao ambiente em erelação as outras entidades (+) ARRUMAR AINDA!!!!!!!
    public boolean BloqueioAoAdicionar(int x, int y, int z) {
        for (Entidade e : this.listaEntidades) {
    
            // Verifica se ocupa apenas um ponto (entidades "pontuais" como robôs)
            if (e.getX() == e.getX1() && e.getY() == e.getY1()) {
                if (e.getX() == x && e.getY() == y && e.getZ() == z) {
                    return true;
                }
            } 
            // Verifica se ocupa uma área (entidades como obstáculos)
            else {
                int altura = e.getZ(); // Altura do obstáculo
                if (x >= e.getX1() && x <= e.getX2() &&
                    y >= e.getY1() && y <= e.getY2() &&
                    z <= altura) {
                    return true;
                }
            }
        }
    
        return false;
    }
    
    //(-)
    public void adicionarRobo(Robo r) { 
        int x = r.getX1();
        int y = r.getY1();
        int z = r.getZ();
    
        r.setAmbiente(this);
    
        // Verifica se o robô está dentro dos limites
        if (!dentroDosLimites(x, y, z)) {
            System.out.println("Erro: Posição do robô " + r.getNome() + " está fora dos limites do ambiente.");
            return;
        }
    
        // Verifica se existe um obstáculo ou robô já ocupando o espaço
        if (this.BloqueioAoAdicionar(x, y, z)) {
            System.out.println("Erro: Não pode adicionar robô, obstáculo ou outro robô detectado em (" + x + ", " + y + ", " + z + ")!");
            return;
        }

        //verifica se existe outro robô com o mesmo nome
        for(Entidade existente : this.listaEntidades){
            if(existente.getTipoEntidade()==TipoEntidade.ROBO && r.getNome()==existente.getNome()){ 
                return;
            }
        }
    
        // Adiciona o robô à lista
        this.listaEntidades.add(r);
        //ADICIONAR FEATURE DA MATRIZ++
        System.out.println("\nRobô " + r.getNome() + " adicionado com sucesso!");
    }
    
    //Remover entidade do mapa e da lista (+)
    public void removerEntidade(Entidade e) {
        if (this.listaEntidades.remove(e)) {

            int x = e.getX();
            int y = e.getY();
            int z = e.getZ();
    
            mapa[x][y][z] = TipoEntidade.VAZIO;
    
            System.out.println("\nEntidade " + e.getId()+ ", " + e.getDescricao() + " removida com sucesso!");
        } else {
            System.out.println("Entidade " + e.getId() + ", " + e.getDescricao() + " não encontrada no ambiente.");
        }
    }

    //(-)
    public boolean adicionarObstaculo(Obstaculo o) {
        if (o.podeAdicionar(this)) {
            this.listaEntidades.add(o);
            //ADICIONAR FEATURE DA MATRIZ
            System.out.println("Obstáculo do tipo " + o.getTipoObstaculo() + " adicionado.");
            return true;
        } else {
            System.out.println("Erro: Obstáculo não pôde ser adicionado, pois há sobreposição!");
            return false;
        }
    }

    //(-)
    public void removerObstaculo(Obstaculo o){
        this.listaEntidades.remove(o);
        //ADICIONAR FEATURE DA MATRIZ
        System.out.println("Obstáculo do tipo " + o.getTipoObstaculo() + " removido.");
    }

    //Encontra Rôbo pela lista (+)
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
    
    //Adiciona caixa de som (não sei se posso retirar)
    public boolean adicionaCaixaDeSom(CaixaDeSom c){
        if(adicionarObstaculo(c)){
            int x=c.getX1(), y=c.getY1(), z=0, intensidade=c.getIntensidade();
            for (int i=intensidade; i>0; i--) {
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
            return true;
        }
        else{
            return false;
        }
    
    }

    //remove caixa de som do ambiente (não sei se posso retirar)
    public void removeCaixaDeSom(CaixaDeSom c){
        removerObstaculo(c);
        int x=c.getX1(), y=c.getY1(), z=0, intensidade=c.getIntensidade();
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
    }

    //(CONSERTAR) acredito que criar um getColisão em entidades, e tentar calcular colisões de robos-robos e robos-obstaculos (+)
    public void verificarColisoes() {
        int totalColisoes = 0;
    
        for (Entidade entidade : listaEntidades) {
            totalColisoes += entidade.getTotalColisoes();
        }
    
        System.out.println("Já houveram " + totalColisoes/2 + " colisões no total!");

    }

    //impressão de XY na camada Z escolhida (+)
    public void visualizarAmbiente(int z) {
        
        System.out.println("\n--- Visão do ambiente no plano XY com Z = " + z + " ---");
    
        for (int y = altura - 1; y >= 0; y--) { 
            for (int x = 0; x < altura; x++) {
                Entidade entidade = getEntidadeNaPosicao(x, y, z);
                System.out.print(entidade.getRepresentacao() + " ");
                
            }
            System.out.println();
        }
    }

    //retorna a entidade pra ser usada na impressão do mapa (+)
    private Entidade getEntidadeNaPosicao(int x, int y, int z) {
        for (Entidade e : listaEntidades) {
            if (e.getX() == x && e.getY() == y && e.getZ() == z) {
                return e;
            }
        }
        return null;
    }
    
    // Usa método da classe robo para chamar sensor (+)
    public void executarSensores() {
        for (Entidade e : listaEntidades) {
            if (e instanceof Robo) {
                ((Robo) e).usarSensores();
            } else {
                throw new UsavelApenasPorRobosException(
                    "A entidade '" + e.getId() + "' do tipo " + e.getTipo() + " não pode usar sensores."
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
    
            mapa[novoX][novoY][novoZ] = e.getTipo();
    
            System.out.println("Entidade '" + e.getDescricao() + "' movida com sucesso!");
    
        } catch (ForaDosLimitesException | PosicaoOcupadaException ex) {
            System.out.println("Erro ao mover entidade: " + ex.getMessage());
        }
    }
    
}


