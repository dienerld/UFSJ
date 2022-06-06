#include"header.h"
void print(FILE *saida, Visitados *Nave, int iPv){
	int i = 0, total = 0;
	for(;i < iPv; i++){
		total += Nave[i].habTotal;
	}
	printf("Hab_Total:%d ", total);
	fprintf(saida, "%d ", total);
	for(i = 0; i < iPv; i++){
		fprintf(saida, "%d %d ", Nave[i].id, Nave[i].qtd);
		printf("Povo_V:%d Qtd:%d ", Nave[i].id, Nave[i].qtd);
	}
	printf("\n");
	fprintf(saida, "\n");
}
