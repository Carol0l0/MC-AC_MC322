# MC-AC_MC322
Ana Carolina Vieira Araújo (248734)
Maria Clara Martinez Oliveira (281315)

**Mudanças em relação ao laboratório anterior**

    Em relação ao laboratório anterior, foram implementadas mudanças significativas com o objetivo de aprimorar a estrutura, a organização e a escalabilidade do projeto. A principal modificação consistiu na criação de uma interface comum denominada Entidade, que estabeleceu um padrão de comportamento para todos os elementos presentes no ambiente, como robôs, obstáculos e o sábio mágico. Essa padronização permitiu o uso do polimorfismo, facilitando o tratamento genérico das entidades e a expansão do sistema de forma mais modular. Ressaltamos que relacionado a CaixaDeSom e SábioMágico tiveram alguns métodos que julgamos melhor tratar por fora do escopo das entidades para menor complexidade de seus métodos.

    Além disso, foram introduzidas enumerações (enums) para representar os diferentes tipos de entidades. Essa abordagem contribuiu para tornar o código mais claro, seguro e legível, possibilitando a diferenciação entre os tipos de forma mais confiável, sem a necessidade do uso de literais ou verificações manuais de instância.

    Outra mudança relevante foi a criação de interfaces adicionais, como Atacante, Colorido e Explorador, permitindo a composição de comportamentos distintos por meio da simulação de herança múltipla. Com isso, foi possível atribuir capacidades específicas a certos robôs de maneira flexível e reutilizável, promovendo maior organização na definição das funcionalidades.

    Por fim, foi implementado um sistema de tratamento de exceções mais robusto, com a criação de classes específicas para diferentes tipos de erro, como ForaDosLimitesException, ObstaculoException e NomeDuplicadoException, organizadas em um pacote próprio. Esse aprimoramento permitiu uma gestão mais precisa e informativa das falhas durante a execução, resultando em maior confiabilidade do sistema. De modo geral, essas alterações contribuíram para tornar o projeto mais consistente, extensível e alinhado com boas práticas de desenvolvimento orientado a objetos.


**Exceções personalizadas e onde são lançadas:**

    -UsavelApenasPorRobosException
        -Usado em:
        -método executarSensores na classe Ambiente
 
    -PosicaoOcupadaException
        -Usado em:
        -método moverEntidade da classe Ambiente

    -ObstaculoException
        Usado em:
	    -método MoverPara na classe Robô

    -NomeDuplicadoException
        Usado em:
        -método AdicionarEntidade na classe Ambiente
        -método AdicionaCaixaDeSom da classe Ambiente

    -ForaDosLimitesException
    	Usado em:
	    -método AdicionarEntidade da classe Ambiente
	    -método MoverEntidade da classe Ambiente
        -método AdicionaCaixaDeSom da classe Ambiente
        -método MoverPara da classe Robo


    -DesligadoException:
	    Usado em: 
        -método EnviarMensagem da classe CentralComunicação
        -métodos enviar e receber mensagem da interface Comunicavel
        -método acionarSensores da interface Sensoreavel


**Interfaces criadas e por quem são implementadas:**

    Entidade - Implementado por Robo (e portanto por suas classes filhas) e Obstáculo
    
    Comunicável - Implementado por Robo (e portanto por suas classes filhas)

    Sensorável - Implementado por Robo (e portanto por suas classes filhas)

    Colorido - Implementado por RoboTerrestreDeCarga e RoboAereoXY

    Atacante - Implementado pela classe RoboTerrestreBlindado

    LigaDesliga - Implementado por RoboAereoYX


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


*Diagrama de Classes*
![Diagrama UML]()