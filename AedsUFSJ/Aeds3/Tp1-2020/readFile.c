/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#include "header.h"

//Responsável pela leitura do arquivo com os dados para o programa
void readFile(FILE* input, COLLECTION_SPOT* Base, float* MaiorX, float* MaiorY) {
	char lixo;
	int totalPoints = 0;
	int i = 0;
	char reader[30];
	fscanf(input, "%d%c%f%c%f%c", &totalPoints, &lixo, &Base->Anchor_A.X, &lixo, &Base->Anchor_B.X, &lixo);

	//vetor para armazenar todos os pontos recolhidos do arquivo de entrada
	SPOT* listpoints = malloc(totalPoints * sizeof(SPOT));
	while (i < totalPoints) {
		fscanf(input, "%f%c%f%c", &listpoints[i].X, &lixo, &listpoints[i].Y, &lixo);
		findExtremeValue(listpoints[i].X, listpoints[i].Y, MaiorX, MaiorY);	 //Procuro os maiores valores de X e Y contidos no arquivo de entrada
		i++;
	}
	fclose(input);
	int auxpoint = totalPoints - 1;
	quicksort(listpoints, &auxpoint);				   //Ordeno minha lista de forma decrescente
	InsertSpot(Base->Point, listpoints, totalPoints);  //insiro os pontos na minha lista iniciada na variavel Base->Point
	free(listpoints);								   //faz desalocação do vetor que não será mais útil
	return;
}

//verifica se os novos valores são maior que os antigos
void findExtremeValue(float X, float Y, float* MaiorX, float* MaiorY) {
	if (*MaiorX < X) {
		*MaiorX = X;
	}
	if (*MaiorY < Y) {
		*MaiorY = Y;
	}
	return;
}
