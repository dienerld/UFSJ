/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#include "header.h"
void main(int argc, char* argv[]) {
	int option;
	FILE* input;
	while ((option = getopt(argc, argv, "i:")) != -1) {	 //Getopt recebe os parâmetros
		switch (option) {								 //definindo os argumentos de acordo com o parâmetro estabelecido
		case 'i':
			input = fopen(optarg, "r");
			break;
		default:
			printf("Formato de comando incorreto!!\nInserir no formato: ./main -i entrada.txt");
		}
	}

	COLLECTION_SPOT* Base;	//Raiz da lista
	SPOT* HighestPoint = malloc(sizeof(SPOT));
	int count = 1;

	float* MaiorX = malloc(sizeof(int));  //Ponto mais distante da origem no eixo X
	float* MaiorY = malloc(sizeof(int));  //ponto mais distante da origem no eixo Y

	Base = startCollection(Base);
	readFile(input, Base, MaiorX, MaiorY);
	*HighestPoint = findHighestPoint(Base, MaiorX, MaiorY);	 //Busca o ponto mais alto e centralizado em relação aos âncoras

	compareIntersection(Base, &count, HighestPoint);
	printf("%d\n", count);

	//Remove da memória todos os elementos alocados dinamicamente
	free(HighestPoint);
	free(MaiorX);
	free(MaiorY);
	clearList(Base->Point);
	free(Base);
}
