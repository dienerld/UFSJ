/*----------------------------------------------*/
//	CÓDIGO ADAPTADO DO SLIDE DE ORDENAÇÃO		//
//	INTERNA DO PROFESSOR RAFAEL SACHETTO		//
/*----------------------------------------------*/
#include "header.h"

void particionar(int esq, int dir, int* i, int* j, SPOT* vector) {
	SPOT x, w;
	*i = esq;
	*j = dir;
	/* obtem o pivo x */
	x = vector[(*i + *j) / 2];
	do {
		while (x.Y < vector[*i].Y)
			(*i)++;
		while (x.Y > vector[*j].Y)
			(*j)--;
		if (*i <= *j) {
			w = vector[*i];
			vector[*i] = vector[*j];
			vector[*j] = w;
			(*i)++;
			(*j)--;
		}
	} while (*i <= *j);
}

void ordenar(int esq, int dir, SPOT* vector) {
	int i, j;
	particionar(esq, dir, &i, &j, vector);
	if (esq < j)
		ordenar(esq, j, vector);
	if (i < dir)
		ordenar(i, dir, vector);
}

//Ordena de forma decrescente a lista
void quicksort(SPOT* vector, int* n) {
	ordenar(0, *n, vector);
}
