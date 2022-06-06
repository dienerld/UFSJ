/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#include "header.h"

//Faz a inserção dos pontos na lista indicada
void InsertSpot(SPOT* Base, SPOT* listpoint, int Range) {
	SPOT* HelperPoint;
	HelperPoint = Base;

	int count = 0;
	while (count < Range) {
		HelperPoint->ID = count + 1;
		HelperPoint->X = listpoint[count].X;
		HelperPoint->Y = listpoint[count].Y;

		HelperPoint->NextPoint = malloc(sizeof(SPOT));
		HelperPoint = HelperPoint->NextPoint;
		HelperPoint->NextPoint = NULL;
		count++;
	}
}
