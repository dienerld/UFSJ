#include"header.h"
void leitura(FILE *entrada, FILE *saida){ //função para fazer a leitura do arquivo de entrada e armazenar as informações
	char aux;
	int instancia, aldeias, distanciaMax, rotas, j, pesoMax;
	fscanf(entrada, "%d\n", &instancia);
	if( instancia < 1){
		printf("Erro: Instancia menor que 1\n");
		return;
	}
	float pesoaux, habilaux;
	float util_aux;
	Povo *vtPovo = malloc(sizeof(Povo)*10); //esse vetor guarda as informações de cada aldeia
	Rotas *vtRotas = malloc(sizeof(Rotas)*10); // esse vetor guarda todas as rotas possiveis
	while(instancia > 0){
		fscanf(entrada, "%d%c", &aldeias, &aux);
		fscanf(entrada, "%d%c", &distanciaMax, &aux);
		fscanf(entrada, "%d%c", &pesoMax, &aux);
		fscanf(entrada, "%d\n", &rotas);
		vtRotas = realloc(vtRotas,(sizeof(Rotas)*rotas));
		vtPovo = realloc(vtPovo,(sizeof(Povo)*aldeias));
		j = 0;
		while(j < aldeias){//recolho as informações sobre cada aldeia
			fscanf(entrada, "%d%c", &vtPovo[j].id, &aux);
			fscanf(entrada, "%f%c", &vtPovo[j].peso, &aux);
			fscanf(entrada, "%f\n", &vtPovo[j].habil);
			habilaux = vtPovo[j].habil;
			pesoaux = vtPovo[j].peso;
			//encontro a utilidade dos povos
			util_aux = (habilaux/pesoaux);
			vtPovo[j].util = util_aux;
			j++;
		}
		j =0;
		while(j < rotas){//recolho informações sobre as possiveis rotas
			fscanf(entrada, "%d%c", &vtRotas[j].Pi, &aux);
			fscanf(entrada, "%d%c", &vtRotas[j].Pj, &aux);
			fscanf(entrada, "%d\n", &vtRotas[j].Dij);
			j++;
		}
		recrutar(rotas, aldeias, vtPovo, vtRotas, pesoMax, distanciaMax, saida);
		instancia--;

	}
}
