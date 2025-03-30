# MC-AC_MC322
Ana Carolina Vieira Araújo (248734)
Maria Clara Martinez Oliveira (281315)

*Sobre o código:*

O código simula um ambiente onde diferentes tipos de robôs (terrestres, aéreos, blindados,) podem se mover, interagir e realizar tarefas, como carregar peso ou recarregar a bateria. O ambiente possui limites e obstáculos, e o comportamento dos robôs é condicionado por esses fatores.

Classes

1. Ambiente:

Representa o espaço onde os robôs operam. A classe possui as dimensões do ambiente (X, Y, Z) e uma lista de robôs presentes.

    Construtor: Inicializa o ambiente com as dimensões passadas e cria uma lista de robôs.

    Métodos:

        dentroDosLimites: Verifica se uma posição está dentro dos limites do ambiente.
        adicionarRobo: Adiciona um robô à lista de robôs no ambiente.

2. Robo

Classe base que representa um robô genérico com características como nome, direção e posição (X, Y, Z).

    Construtor: Inicializa o robô com nome e posição inicial.

    Métodos:

        mover: Move o robô dentro do ambiente, verificando se a nova posição está dentro dos limites e se não há obstáculos.
        identificarObstaculo: Verifica se há outro robô na posição de destino.
        exibirPosicao: Exibe a posição atual do robô.

3. RoboTerrestre

Classe derivada de Robo, representando um robô terrestre com uma velocidade máxima.

    Construtor: Inicializa o robô com nome, posição e velocidade máxima.

    Métodos:

        mover: Move o robô, verificando se o movimento não excede a velocidade máxima.

4. RoboTerrestreDeCarga

Classe derivada de RoboTerrestre, representando um robô terrestre que pode transportar carga.

    Construtor: Inicializa o robô com nome, posição, velocidade máxima e capacidade de carga.

    Métodos:

        carregarPeso: Permite carregar um peso, verificando se não excede a capacidade máxima.
        mover: Move o robô, verificando a velocidade máxima e exibindo a nova posição.

5. RoboTerrestreBlindado

Classe derivada de Robo, representando um robô terrestre blindado com resistência.

    Construtor: Inicializa o robô com nome, posição e resistência inicial.

    Métodos:

        mover: Move o robô, verificando se ele está funcionando e calculando os danos sofridos ao colidir com obstáculos.
        contarObstaculos: Conta quantos obstáculos estão no caminho e calcula o dano recebido.
        sofreDano: Aplica dano à resistência do robô e verifica se ele foi destruído.

6. RoboAereo

Classe derivada de Robo, representando um robô aéreo com capacidade de subir e descer.

    Construtor: Inicializa o robô com nome, posição e altura máxima.

    Métodos:

        subir: Aumenta a altitude do robô até o limite máximo.
        descer: Diminui a altitude do robô, mas garante que ele não ultrapasse o limite inferior.

7. RoboAereoYX

Classe derivada de RoboAereo, representando um robô aéreo com nível de bateria.

    Construtor: Inicializa o robô com nome, posição, altura máxima e nível de bateria.

    Métodos:

        carregarBateria: Recarrega a bateria do robô.
        getNivelBateria: Retorna o nível atual da bateria.
        subir e descer: Movimenta o robô, reduzindo a bateria a cada movimento.
        mover: Move o robô na direção X e Y, verificando obstáculos no caminho e consumindo a bateria.

Interação entre as classes

    O ambiente contém uma lista de robôs e interage com suas movimentações.
    Os robôs podem ser adicionados ao ambiente e se mover de acordo com suas características (como velocidade, capacidade de carga, resistência e bateria).
    Movimentos de robôs são limitados por obstáculos e limites do ambiente. Quando um robô colide com um obstáculo, ele pode sofrer danos (para robôs blindados) ou ser impedido de se mover.


*Como Compilar e Executar:*

1. Criação da Pasta bin/
Se a pasta bin/ não existir, crie-a com o seguinte comando:
mkdir -p bin

2. Compilação
Para compilar o código-fonte, use o comando:
javac -d bin *.java

3. Execução
Para rodar o programa, execute o seguinte comando:
java -cp bin Main