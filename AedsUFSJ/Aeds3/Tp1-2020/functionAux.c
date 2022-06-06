/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#include "header.h"

int direction(SPOT Pi, SPOT Pj, SPOT Pk) {
	int P1x, P1y;
	P1x = Pj.X - Pi.X;
	P1y = Pj.Y - Pi.Y;
	int P2x, P2y;
	P2x = Pk.X - Pi.X;
	P2y = Pk.Y - Pi.Y;

	return P1x * P2y - P2x * P1y;
}

//Verifica os segmentos e retorna um Bool false caso se intersectem ou true caso não
bool SegmentIntersect(SEGMENT S1, SEGMENT S2) {
	int d1 = direction(S2.P1, S2.P2, S1.P1);
	int d2 = direction(S2.P1, S2.P2, S1.P2);
	int d3 = direction(S1.P1, S1.P2, S2.P1);
	int d4 = direction(S1.P1, S1.P2, S2.P2);

	if ((d1 < 0 && d2 > 0) || (d1 > 0 && d2 < 0)) {
		if ((d3 < 0 && d4 > 0) || (d3 > 0 && d4 < 0)) {
			return false;
		} else {
			return true;
		}
	}
}

//Desaloca cada item criado dinamicamente da lista passada
void clearList(SPOT* Point) {
	SPOT* aux;

	while (Point->NextPoint != NULL) {
		aux = Point->NextPoint;
		free(Point);
		Point = aux;
	}
	free(Point);
}
