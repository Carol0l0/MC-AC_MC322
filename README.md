# MC-AC_MC322
Ana Carolina Vieira Araújo (248734)
Maria Clara Martinez Oliveira (281315)

**Versão final do código**


**Anteriormente no lab 4**

Em relação ao laboratório anterior, foram implementadas mudanças significativas com o objetivo de aprimorar a estrutura, a organização e a escalabilidade do projeto. A principal modificação consistiu na criação de uma interface comum denominada `Entidade`, que estabeleceu um padrão de comportamento para todos os elementos presentes no ambiente, como robôs, obstáculos e o sábio mágico. Essa padronização permitiu o uso do polimorfismo, facilitando o tratamento genérico das entidades e a expansão do sistema de forma mais modular. Ressaltamos que relacionado a `CaixaDeSom` e `SábioMágico` tiveram alguns métodos que julgamos melhor tratar por fora do escopo das entidades para menor complexidade de seus métodos.

Além disso, foram introduzidas enumerações (enums) para representar os diferentes tipos de entidades. Essa abordagem contribuiu para tornar o código mais claro, seguro e legível, possibilitando a diferenciação entre os tipos de forma mais confiável, sem a necessidade do uso de literais ou verificações manuais de instância.

Outra mudança relevante foi a criação de interfaces adicionais, como `Atacante`, `Colorido` e `Explorador`, permitindo a composição de comportamentos distintos por meio da simulação de herança múltipla. Com isso, foi possível atribuir capacidades específicas a certos robôs de maneira flexível e reutilizável, promovendo maior organização na definição das funcionalidades.

Por fim, foi implementado um sistema de tratamento de exceções mais robusto, com a criação de classes específicas para diferentes tipos de erro, como `ForaDosLimitesException`, `ObstaculoException` e `NomeDuplicadoException`, organizadas em um pacote próprio. Esse aprimoramento permitiu uma gestão mais precisa e informativa das falhas durante a execução, resultando em maior confiabilidade do sistema. De modo geral, essas alterações contribuíram para tornar o projeto mais consistente, extensível e alinhado com boas práticas de desenvolvimento orientado a objetos.

**Novidades do Laboratório 5**

Este laboratório trouxe avanços significativos na autonomia e no registro de operações dos robôs, com a introdução dos seguintes conceitos:

* **Robôs Autônomos (`AgenteInteligente` e suas especializações):**
    * Foi introduzida a classe abstrata `AgenteInteligente`, que serve como base para robôs capazes de executar missões complexas de forma autônoma, sem intervenção direta do usuário.
    * Três tipos de robôs autônomos foram desenvolvidos:
        * `RoboExplorador`: Capaz de se mover autonomamente para um ponto específico no ambiente (`MissaoBuscarPonto`).
        * `RoboPatrulheiro`: Desenvolvido para seguir uma rota predefinida, patrulhando uma série de pontos no ambiente (`MissaoPatrulhar`).
        * `RoboSoSilencio`: Especializado em fiscalizar o nível de som no ambiente, acionando alertas se o limite permitido for excedido (`MissaoFiscalizarSom`).
    * Cada robô autônomo agora possui uma `Missao` associada, que define seu comportamento e objetivo, promovendo um design modular e extensível para novas missões.

* **Sistema de Log (`Log`):**
    * Um sistema de log foi implementado para registrar as atividades dos robôs autônomos durante a execução de suas missões.
    * A classe `Log` permite registrar movimentos, acionamento de sensores, detecção de obstáculos e erros. Isso oferece uma visão detalhada do comportamento do robô e facilita a depuração e análise pós-execução.
    * Este log pode ser exibido ao final das operações para auditoria e acompanhamento do desempenho dos agentes.
    * Para salvar os logs, selecione a opção 9 e depois 0 para fechar o programa.

* **Interfaces para Sensores e Comunicação:**
    * As interfaces `Comunicavel` e `Sensoreavel` foram aprimoradas e agora são implementadas diretamente pela classe `Robo` genérica. Isso garante que todos os robôs, incluindo os autônomos, possuam capacidades de comunicação e sensorização.
    * A integração de `GerenciadorSensores` e `ModuloComunicacao` dentro da classe `Robo` centraliza o controle dessas funcionalidades, tornando o design mais coeso.

**Exceções personalizadas e onde são lançadas:**

* `UsavelApenasPorRobosException`
    * Usado em: `Ambiente.executarSensores`

* `PosicaoOcupadaException`
    * Usado em: `Ambiente.moverEntidade`

* `ObstaculoException`
    * Usado em: `Robo.moverPara`

* `NomeDuplicadoException`
    * Usado em: `Ambiente.adicionarEntidade`, `Ambiente.adicionarCaixaDeSom`

* `ForaDosLimitesException`
    * Usado em: `Ambiente.adicionarEntidade`, `Ambiente.moverEntidade`, `Ambiente.adicionarCaixaDeSom`, `Robo.moverPara`

* `RoboDesligadoException`
    * Usado em: `CentralComunicacao.enviarMensagem`, `Comunicavel.enviarMensagem`, `Comunicavel.receberMensagem`, `Sensoreavel.acionarSensores`, `Robo.moverPara`, `MissaoFiscalizarSom.executar`

**Interfaces criadas e por quem são implementadas:**

* `Entidade` - Implementado por `Robo` (e portanto por suas classes filhas) e `Obstaculo`.
* `Comunicavel` - Implementado por `Robo` (e portanto por suas classes filhas).
* `Sensoreavel` - Implementado por `Robo` (e portanto por suas classes filhas).
* `Colorido` - Implementado por `RoboTerrestreDeCarga` e `RoboAereoXY`.
* `Atacante` - Implementado pela classe `RoboTerrestreBlindado`.
* `LigaDesliga` - Implementado por `RoboAereoYX`.
* `Missao` - Implementado por `MissaoBuscarPonto`, `MissaoPatrulhar`, `MissaoFiscalizarSom`.

*Como Compilar e Executar:*

1.  **Criação da Pasta `bin/`**
    Se a pasta `bin/` não existir, crie-a com o seguinte comando:
    ```bash
    mkdir -p bin
    ```

2.  **Compilação**
    Para compilar o código-fonte, use o comando:
    ```bash
    javac -d bin src/*.java src/ambientes/*.java src/comunicacao/*.java src/entidades/*.java src/exception/*.java src/obstaculos/*.java src/robos/*.java src/robos/robosAutonomos/*.java src/robos/robosAutonomos/missoes/*.java src/sensores/*.java
    ```

3.  **Execução**
    Para rodar o programa, execute o seguinte comando:
    ```bash
    java -cp bin Main
    ```

*Diagrama de Classes*
![Diagrama de Classes](lab5/Diagrama.png)
