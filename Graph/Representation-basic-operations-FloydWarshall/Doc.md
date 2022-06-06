<center>

# Documentação Atividade Grafos



<center>

#### Diener Lourenço Dornelas

##### Matrícula: 182050081







## Index.ts

&nbsp;&nbsp;&nbsp; Contém a função file que é a inicial do programa, onde quando instanciada faz a leitura do arquivo de entrada com os dados e repassa os mesmos ja tratados para a callback `Core.START`. O arquivo ainda faz a verificação se foi obedecido os parâmetros necessários para inicialização correta do programa, e ainda contém um breve menu que explica a maneira de inicializar o programa caso tente executar de maneira incorreta.



## Core.ts

&nbsp;&nbsp;&nbsp; Encapsula outras funções dentro de uma função `Core`, de modo que só fica pública as funções que são necessárias fora do arquivo, todas outras funções contidas no arquivo ficam privada para uso somente dentro do escopo do arquivo. A principal função do arquivo é o gerenciamento do programa, desde a criação do grafo a quais informações serão apresentadas. Possui um loop que faz a iteratividade com o usuário para a escolha do que ser apresentado.

#### verifyIncluded

&nbsp;&nbsp;&nbsp; Faz a verificação se determinado vértice ja foi incluído no grafo. Em um vetor onde é armazenado todos os vertices já incluídos é feita a verificação, e caso vértice passado como parâmetro dê match com algum do vetor é retornado `TRUE`.

#### createVertex

&nbsp;&nbsp;&nbsp; A função que faz a inserção de um novo vértice ao grafo. Com o auxilio de um forEach é feita a verificação se o nó a ser inserido já está no grafo, caso esteja é feita a inserção de uma nova aresta de saída dele para o outro vértice que representa a chegada, caso ainda não exista no grafo será feita a alocação de sua aresta e a inserção no grafo.



## Menu.ts

&nbsp;&nbsp;&nbsp; Modulo onde foi feita implementação do menu da aplicação, contém apenas a função com nome `Menu` que imprime as opções na tela e recebe a opção do usuário através da função `Console` que faz parte do módulo `Console.ts`.



## Console.ts

&nbsp;&nbsp;&nbsp; Módulo onde é feita a leitura das informações solicitadas ao usuário de forma assíncrona.



## GraphLog.ts

&nbsp;&nbsp;&nbsp; Módulo que armazena as funções relacionadas a informações sobre os vértices do grafo. O módulo encapsula as funções `summary`, `printGraph`, `findSuccessors`, `findAntecedents` e `degreeVertex`.

#### summary

&nbsp;&nbsp;&nbsp; Retorna visualmente ao usuário breves informações sobre o grafo como, quais vértices compõem o grafo, o total de arestas, o total de vertices e densidade do grafo.

#### printGraph

&nbsp;&nbsp;&nbsp; Função que faz a impressão completa do grafo com todos vertices com suas respectivas arestas e a distância até o outro vértice

#### findSuccessors

&nbsp;&nbsp;&nbsp; Função que retorna os sucessores de primeiro grau do vértice desejado.

#### findAntecedents

&nbsp;&nbsp;&nbsp; Função que retorna os antecessores de primeiro grau do vértice desejado.

#### degreeVertex

&nbsp;&nbsp;&nbsp; Verifica quais as arestas que chegam e saem do vértice e retorna a quantidade de cada respectivamente.



## Node.ts

&nbsp;&nbsp;&nbsp; Única classe do programa, ela é responsável por dar estrutura ao grafo, contendo um index do tipo string e um vetor de arestas. Para cada novo vértice é instanciada uma nova classe que vai armazenar todas as informações sobre o respectivo vértice, informações que são, quais os possíveis caminhos do vértice e os métodos responsáveis para ter acesso aos seus dados.



## FloydWarshall.ts

&nbsp;&nbsp;&nbsp; Módulo que armazena duas funções responsáveis por converter o grafo para matriz de adjacência e outra para calcular a distância entre todos os vértices. Estas funções são `convertObjectToMatrix` e `floydWarshall`.

#### convertObjectToMatrix

&nbsp;&nbsp;&nbsp; Esta função recebe como parâmetro o grafo e fica responsável de converter o modelo em que é apresentado para um modelo de matriz de adjacência para utilização no algoritmo de Floyd-Warshall.

#### floydWarshall

&nbsp;&nbsp;&nbsp; O algoritmo de Floyd-Warshall é responsável por apresentar a menor distância entre os vertices de um grafo e apresenta-las num esquema de matriz de adjacência. Portanto a função `floydWarshall` recebe como parâmetro uma matriz de adjacência e por meio de 3 for's aninhados extrai o menor caminho entre os vértices e os retorna em uma nova matriz.
