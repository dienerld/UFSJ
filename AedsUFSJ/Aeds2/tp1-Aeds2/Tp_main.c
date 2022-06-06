#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "F_tp.h"

int main(){
	char opcao[400], aux[1];
	Conta *Email;
	Email = (Conta*)malloc(sizeof(Conta));// Criação dinâmicamente da célula cabeça para a lista de conta
	Email->prox = NULL;
	int endereco, i = 0, contador = 0;
	Email->id = -MAX_TAM;
	CaixaEntrada *email;
	email = (CaixaEntrada*)malloc(sizeof(CaixaEntrada));
	email->proxId = NULL;
	email->proxEmail = NULL;
	FILE *Arquivo = fopen("Comandos.txt", "r"); // abertura do arquivo dos comandos 
	while(!feof(Arquivo)){                              //while feito para contar quantas linhas de comando existem no arquivo
		fscanf(Arquivo, "%[^\n] %s", &opcao, &opcao);
			i++;
	}
	fclose(Arquivo);                      //fecha o arquivo        
	Arquivo = fopen("Comandos.txt", "r");                //nova abertura do arquivo
	while(contador < i){                                 //while que possui um contador menor que a quantidade de linhas do arquivo
		
		fscanf(Arquivo, "%s%c", &opcao, &aux);
		if(strcmp(opcao, "CADASTRA") == 0){           //comparação se o comando chamado foi CADASTRA
			CadastraID(Email, email, Arquivo);        //chamada da função cadastra
		}
		else if(strcmp( opcao,"REMOVE") == 0){        //verificação se o comando do arquivo é remove usuário 
			
			fscanf(Arquivo, "%d", &endereco);
			RemoverID(Email, email, endereco);         //chamada da função
			}
			else if(strcmp(opcao, "ENTREGA") == 0){ //verificação do comando pedido no arquivo
			
			int aux;
			fscanf(Arquivo, "%d",&aux);
			setbuf(stdin, NULL);
			InserirMsg(email, aux, Email, Arquivo);   //chamada da função que insere a mensagem na caixa de entrada do usuário
		}
		else if(strcmp(opcao,"CONSULTA") == 0){        // verificação do comando pedido
			
			fscanf(Arquivo, "%d", &endereco);
			ConsultarID(email, endereco, Email);   //chamada da função que mostra sempre o ultimo email da caixa de entrada de acordo com sua prioridade
			setbuf(stdin, NULL); 
		}
		else if(strcmp(opcao, "SAIR") == 0){       //verificação do comando de saída do programa
			fclose(Arquivo);                       //fechamento do arquivo
			return;
		}
		contador++;
	}
}
