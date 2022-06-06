/*----------------------------------------------*/
//	CÓDIGO DESENVOLVIDO POR DIENER DORNELAS		//
/*----------------------------------------------*/
#include "header.h"
//Faço a inicialização da minha estrutura salvando os pontos âncoras e criando o local de armazená-los
COLLECTION_SPOT* startCollection(COLLECTION_SPOT* Base) {
	Base = malloc(sizeof(COLLECTION_SPOT));
	Base->Point = malloc(sizeof(SPOT));

	//Ancora A
	Base->Anchor_A.X = 0;
	Base->Anchor_A.Y = 0;
	Base->Anchor_A.ID = 1;
	Base->Anchor_A.NextPoint = NULL;
	//Ancora B
	Base->Anchor_B.X = 0;
	Base->Anchor_B.Y = 0;
	Base->Anchor_B.ID = 2;
	Base->Anchor_B.NextPoint = NULL;
	return Base;
}
