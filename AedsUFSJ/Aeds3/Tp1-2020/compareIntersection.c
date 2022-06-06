/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#include "header.h"

/*
Busca encontrar um ponto que está mais ao centro dos âncoras e o mais elevado possível para usar como referência
*/
SPOT findHighestPoint(COLLECTION_SPOT* Base, float* MaiorX, float* MaiorY) {
	*MaiorX = (*MaiorX / 2) + Base->Anchor_A.X;
	SPOT* Point = Base->Point;
	SPOT* anterior = Base->Point;

	while (Point->NextPoint != NULL) {
		if (Point->Y >= *MaiorY) {
			//Faz comparação se o ponto atual está na margem limita para ser uo ponto inicial
			if (Point->X <= (*MaiorX - ((*MaiorX * 20) / 100)) || Point->X >= (*MaiorX + ((*MaiorX * 20) / 100))) {
				anterior->NextPoint = Point->NextPoint;	 //faz a exclusão do ponto eleito da lista de prováveis pontos válidos, visto que ele já está sendo o 1º a ser inserido
				return *Point;
			}
		}
		anterior = Point;
		Point = Point->NextPoint;
	}
	*MaiorX = (*MaiorX * 2) - Base->Anchor_A.X;
	*MaiorY -= 1;
	return findHighestPoint(Base, MaiorX, MaiorY);	// caso não enconte nenhum ponto válido naquele Y, repito a busca com Y-1
}
// Cria os segmentos de reta a serem analisados
bool setCollection(SPOT Point, SPOT* HighestPoint, SPOT AnchorNewLine, SPOT AnchorOpposite) {
	SEGMENT S1, S2;
	S1.P1 = Point;
	S1.P2 = AnchorNewLine;
	S2.P1 = *HighestPoint;
	S2.P2 = AnchorOpposite;

	return SegmentIntersect(S2, S1);  //Verifica se os segmentos de reta se intersectam
}

// faço a comparação dos segmentos na ordem AP(ponto novo) vs BA(ponto adicionado anteriormente)
void compareIntersection(COLLECTION_SPOT* Base, int* count, SPOT* HighestPoint) {
	COLLECTION_SPOT* auxBase = Base;
	SPOT* auxPoint = Base->Point;
	bool intersectionA = true, intersectionB = true;
	while (auxPoint->NextPoint != NULL) {
		if (auxPoint->Y < HighestPoint->Y) {  //corta todos os pontos que estiverem na mesma altura de um já inserido
			if (auxPoint->X > HighestPoint->X) {
				intersectionA = setCollection(*auxPoint, HighestPoint, Base->Anchor_A, Base->Anchor_B);
			} else {
				intersectionA = true;
			}
			if (intersectionA) {
				intersectionB = setCollection(*auxPoint, HighestPoint, Base->Anchor_B, Base->Anchor_A);
			}
		}
		if (intersectionA && intersectionB) {
			(*count)++;
			intersectionA = false;
			HighestPoint = auxPoint;  //Altera o ponto mais alto para o último inserido
		}
		auxPoint = auxPoint->NextPoint;
	}
	return;
}
