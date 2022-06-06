#ifndef F_TP_H
#define F_TP_H

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef	struct CaixaEntrada{//Essa e uma lista bifurcada e simplismente encadeada criada para a caixa de entrada
	char Mensagem[250];
	int prioridade;
	int id;
	struct CaixaEntrada *proxId;
	struct CaixaEntrada *proxEmail;
} CaixaEntrada;

typedef struct indice {//E uma lista simplismente encadeada de contas dos usuarios
	int id;
	struct indice *prox;
	CaixaEntrada *CaixaE;
} Conta;

#define MAX_TAM 100000

int verificarID(int login, Conta *ContaMain);
void CadastraID(Conta *conta, CaixaEntrada *email, FILE *Arquivo);
void ImprimeCaixa(CaixaEntrada *caixa);
void OrdenaMsg(CaixaEntrada *dest, char email[], int prioridade, int id);
void InserirMsg(CaixaEntrada *email, int id, Conta *EmailMain, FILE *Arquivo);
void RemoverID(Conta *Usuario,CaixaEntrada *Caixa, int endereco);
void ConsultarID(CaixaEntrada *UsuarioMain, int endereco, Conta *ListaConta);

#endif
