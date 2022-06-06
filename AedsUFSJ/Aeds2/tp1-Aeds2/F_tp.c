#include "F_tp.h"

int verificarID(int login, Conta *ContaMain){  //Utilizada para verificar se o id pretendido é valido ou não.
	Conta *NovoU = ContaMain->prox;
	if(login > MAX_TAM ){
		return 2;
	}
	while(NovoU != NULL){
		if(NovoU->id == login)
			return 0;
		NovoU = NovoU->prox;
	}
	return 1;
}
void CadastraID(Conta *conta, CaixaEntrada *email, FILE *Arquivo){ // Cadastra o Usuário
	int login;
	Conta *NovoU = conta; //cria um ponteiro auxliar para percorrer a lista de contas
	CaixaEntrada *NovaC = email; //cria um ponteiro auxliar para percorrer a lista de caixa de entrada
	fscanf(Arquivo, "%d", &login);
	int a = verificarID(login, NovoU); // utiliza a função para a verificar se ID e Valido para o caso
	if(a == 0){
		printf("ERRO: CONTA %d JA CADASTRADA\n", login);
		return;
	}
	else if(a == 1){
		while(NovoU->prox != NULL){
			NovoU = NovoU->prox;
			NovaC = NovaC->proxId;
		}
		NovaC->proxId = (CaixaEntrada*)malloc(sizeof(CaixaEntrada)); // Faz alocacão dinâmica da caixa de entrada para a conta recém criada
		NovaC->proxId->proxId = NULL; // Faz o ID proximo ao da Celula Cabeça ser NULL
		NovaC->proxId->id = login; // Recebe o ID do usúario
		NovaC->proxId->proxEmail = NULL; 
		NovoU->prox =(Conta*) malloc(sizeof(Conta)); // Faz alocação dinâmica da proxima conta a ser criada, sendo indicada pelo ponteiro próximo
		NovoU->prox->prox = NULL; // Faz o proximo Email  da Celula recém adicionada ser NULL
		NovoU->prox->id = login; // Recebe o ID do usúario
		printf("CONTA %d CADASTRADA\n", login);
	}
	else{
		printf("ERRO: %d INVALIDO\n", login);
		return;
	}

}


void OrdenaMsg(CaixaEntrada *dest, char email[], int prioridade, int id){ // Função para fazer a alocação e ordenação dos emails nas caixas de CaixaEntrada
    CaixaEntrada *pont_aux = dest;
	CaixaEntrada *ant ;												// recebe como paramêtros um vetor de char contendo o conteúdo do email, prioridade e endereço
	int aux = 0;
	while(pont_aux != NULL){
		if(pont_aux->id == id){
			break;
		}
		pont_aux = pont_aux->proxId;
	}

	ant = pont_aux;
	while(pont_aux != NULL){
		if(pont_aux->prioridade < prioridade && pont_aux != ant){
			CaixaEntrada *novo = (CaixaEntrada*)malloc(sizeof(CaixaEntrada));
			strcpy(novo->Mensagem, email);
			novo->prioridade = prioridade;
			novo->id = id;
			novo->proxEmail = pont_aux;
			ant->proxEmail = novo;
			aux = 1;
			break;
		}
		ant = pont_aux;
		pont_aux = pont_aux->proxEmail;
	}
	if(ant->proxEmail == NULL){
		CaixaEntrada *novo = (CaixaEntrada*)malloc(sizeof(CaixaEntrada));
		strcpy(novo->Mensagem, email);
		novo->prioridade = prioridade;
		novo->id = id;
		novo->proxEmail = NULL;
		novo->proxId = NULL;
		ant->proxEmail = novo;
		aux = 1;
	}
}


void InserirMsg(CaixaEntrada *email, int id, Conta *EmailMain, FILE *Arquivo){ //Função responsável por receber os dados que serão inseridos no email
	CaixaEntrada *percorre = email;
	int auxPriori;
	char auxEmail[400];
	
	fscanf(Arquivo, "%d", &auxPriori);
	setbuf(Arquivo, NULL);
	
	fscanf(Arquivo, "%c%[^FIM]", &auxEmail[0], &auxEmail);
	setbuf(Arquivo, NULL);
	switch(verificarID(id, EmailMain)){ //Faz a verificação do ID e retorna um inteiro que indicará se é valido ou não
		case 0:
		while(percorre->id != id || percorre == NULL){
			percorre = percorre->proxId;
		}
		if(percorre->id == id)
			if(auxPriori > 9){
				printf("ERRO: PRIORIDADE INVALIDA.\n");
				return;
			}
			else{
				OrdenaMsg(email, auxEmail, auxPriori, percorre->id); // chama a função que vai ordenar e implantar o email na caixa de entrada
				printf("OK: MENSAGEM PARA %d ENTREGUE\n", id);
				break;
			}
		case 1:
			printf("CONTA %d NAO EXISTENTE\n", id);
			break;
		case 2:
			printf("CONTA %d  NAO EXISTENTE\n", id);
	}
	fscanf(Arquivo,"%s", auxEmail);
}

void RemoverID(Conta *Usuario,CaixaEntrada *Caixa, int endereco){ // Responsável por remover acaixa de entrada e a conta do Usuário selecionado
	Conta *aux = Usuario; // Ponteiro auxuliar para percorrer as Contas afim de remover a conta selecionada, sendo esse o
						  //responsavel por está sempre uma célula anterior ao outro ponteiro auxiliar fim de não deixar perder o ponteiro.
	Conta *aux2; // Ponteiro auxuliar para percorrer as Contas afim de remover a conta selecionada
	CaixaEntrada *Cx_aux = Caixa;
	CaixaEntrada *Cx_ant = Caixa;
	while(aux!=NULL){
		if(aux->id == endereco){
			aux2->prox = aux->prox;
			free(aux);
			if(Cx_aux->proxEmail != NULL){
				CaixaEntrada *percorre = Cx_aux->proxEmail;
				CaixaEntrada *limpa = percorre;
					while(percorre != NULL){
					percorre = percorre->proxEmail;
					free(limpa);
					limpa = percorre;
				}
			}
			if(Cx_aux->proxId == NULL){
				Cx_aux->id = 0;
				Cx_aux->proxId = NULL;
				Cx_aux->proxEmail = NULL;
			}
			else if(Cx_aux == Cx_ant){
				Cx_aux = Cx_aux->proxId;
				free(Cx_ant);
			}
			else{
				Cx_ant->proxId = Cx_aux->proxId;
				free(Cx_aux);
			}
			printf("CONTA %d REMOVIDA\n", endereco);
			break;
			}
		aux2 = aux;
		aux = aux->prox;
		Cx_ant = Cx_aux;
		Cx_aux = Cx_aux->proxId;

	}
	if(aux == NULL)
		printf("CONTA %d NAO EXISTE\n", endereco);
	return;
}

void ConsultarID(CaixaEntrada *UsuarioMain, int endereco, Conta *ListaConta){//responsavel por identificar a conta e ser consultada e imprimir as mensagens dessa caixa de acordo com a prioridade
	CaixaEntrada *pont_aux = UsuarioMain;
	int a = verificarID(endereco, ListaConta);
	while(pont_aux != NULL){
		if(pont_aux->id == endereco)
			break;
		pont_aux = pont_aux->proxId;
	}
		if(a == 1){
			printf("CAIXA DE ENTRADA INEXISTENTE\n");
			return;
		}
		if(pont_aux->proxEmail == NULL){
				printf("CAIXA DE ENTRADA VAZIA\n");
				return;
			}
			else{
				printf("%s\n", pont_aux->proxEmail->Mensagem);
				CaixaEntrada *aux;
				aux = pont_aux->proxEmail;
				pont_aux->proxEmail = pont_aux->proxEmail->proxEmail;
				free(aux);
				return;
			}
		return;
}
