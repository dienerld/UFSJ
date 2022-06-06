/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#ifndef HEADER_H
#define HEADER_H

#include <getopt.h>
#include <math.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/resource.h>
#include <sys/time.h>

//struct medição de tempo
struct rusage TempoI, TempoF;
struct timeval ru_stime, ru_utime;

/*----- TIPO ABSTRATO DE DADOS -----*/
typedef struct SPOT {
	int ID;
	float X, Y;
	struct SPOT* NextPoint;
} SPOT;

typedef struct SEGMENT {
	SPOT P1, P2;
} SEGMENT;

typedef struct COLLECTION_SPOT {
	SPOT* Point;
	SPOT Anchor_A;
	SPOT Anchor_B;
} COLLECTION_SPOT;
/*----- ASSINATURA DAS FUNÇÕES -----*/
//QuickSort
void ordenar(int esq, int dir, SPOT* vector);
void particionar(int esq, int dir, int* i, int* j, SPOT* vector);
void quicksort(SPOT* vector, int* n);
//------------

COLLECTION_SPOT* startCollection(COLLECTION_SPOT* Base);
void readFile(FILE* input, COLLECTION_SPOT* Base, float* MaiorX, float* MaiorY);
void findExtremeValue(float X, float Y, float* MaiorX, float* MaiorY);
void InsertSpot(SPOT* Base, SPOT* listpoint, int Range);

SPOT findHighestPoint(COLLECTION_SPOT* Base, float* MaiorX, float* MaiorY);
bool setCollection(SPOT Point, SPOT* HighestPoint, SPOT AnchorNewLine, SPOT AnchorOpposite);
void compareIntersection(COLLECTION_SPOT* Base, int* count, SPOT* HighestPoint);
bool SegmentIntersect(SEGMENT S1, SEGMENT S2);
void clearList(SPOT* Point);  //Remove da memoria todos os elementos alocados dinamicamente
int direction(SPOT Pi, SPOT Pj, SPOT Pk);

void main(int argc, char* argv[]);
#endif
