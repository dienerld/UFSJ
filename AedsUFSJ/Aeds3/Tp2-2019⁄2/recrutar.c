#include"header.h"
void recrutar(int rotas, int aldeias, Povo *vtPovo, Rotas  *vtRotas, int pesoMax, int distanciaMax, FILE *saida){
	int iPv = 0, Dist = MAX, Dist_aux = 0, indice_rotas;
	int partida, qtdG = 0, i = 0, fim = 0;
	int indiceVP, pesoAtual = 0, pesoAtual_aux = 0;
	int rota_aux, Dist_Total = 0, partida_aux;
	Visitados *Nave = malloc(sizeof(Visitados) * aldeias);
	float util_maior = -1;
	while(aldeias > i){
		if(vtPovo[i].util > util_maior){ //procuro a melhor utilidade para começar a partir dela, a utilidade é
			partida = vtPovo[i].id;            //obtida a partir da razão da habilidade pelo peso do povo. 
			indiceVP = partida-1; //uso o id da aldeia para localizar a posição que ela vai estar no vetor de aldeia subitraindo 1 por casa do inicio do vetor em 0
			util_maior = vtPovo[i].util;
		}
		i++;
	}
	while(Dist_Total <= distanciaMax && pesoMax > pesoAtual && fim == 0){
		qtdG = 0;
		rota_aux = 0;
		while(rota_aux < rotas ){ //verifico todas as rotas
			if(partida == vtRotas[rota_aux].Pi){ // se Pi for igual ao id do povo em que estou salvo a distancia até o ponto
				Dist_aux = vtRotas[rota_aux].Dij;
				if(Dist_aux <= Dist){ // verifico se a distancia da rota atual é menor que a de uma rota anterior que satisfez a condição de partida ser igual a Pi
					partida_aux = vtRotas[rota_aux].Pj; //salvo para onde vou 
					indiceVP = partida-1; // salvo a posição que vai estar o próximo povo que vou tentar adicionar na nave
					Dist = Dist_aux;
				}
			vtRotas[rota_aux].Pj = -1; //anulo os possiveis caminhos para a aldeia que estou
			vtRotas[rota_aux].Pi = -1;
			}
			if(partida == vtRotas[rota_aux].Pj){ // Mesmo processo feito nas 21 a 29 do codigo
				Dist_aux = vtRotas[rota_aux].Dij;
				if(Dist_aux <= Dist){
					partida_aux = vtRotas[rota_aux].Pi;
					indiceVP = partida-1;
					Dist = Dist_aux;
				}
			vtRotas[rota_aux].Pj = -1; //anulo os possiveis caminhos para a aldeia que estou
			vtRotas[rota_aux].Pi = -1;
			}
			else if(rota_aux == rotas && partida != vtRotas[rota_aux].Pj && partida != vtRotas[rota_aux].Pi ){
				fim = 1;
			}

			rota_aux++;
		} 
		while(pesoMax >= pesoAtual_aux && indiceVP < aldeias){ 
			pesoAtual_aux += vtPovo[indiceVP].peso; //faco a adição temporariamente de mais um soldado na nave
			if(pesoMax >= pesoAtual_aux){ //verifico se esse novo soldado fez ultrapassar o peso maximo, se não adiciono ele a nave em definitivo
				pesoAtual = pesoAtual_aux;
				qtdG++; //aumento meu contador de soldados a nave
			}
			else if(pesoAtual_aux > pesoMax){ // se com a adição do novo soldado ultrapassou o limite de peso então 
				Nave[iPv].id = partida; // adiciono no vetor de povos visitados o id da aldeia
				Nave[iPv].qtd = qtdG;	// adiciono no vetor de povos visitados o numero de soldados pego naquela aldeia
				Nave[iPv].habTotal = (qtdG * vtPovo[indiceVP].habil);
				iPv++;
			}
		}
		pesoAtual_aux -= vtPovo[indiceVP].peso; //removo da nave o soldado que foi adicionado por último
		indiceVP++;
		partida = partida_aux;
		Dist_Total += Dist; // adiciono a distância de Pi a Pj no meu total
		Dist = MAX;
	}
	print(saida, Nave, iPv);
}