/*CODIGO DESENVOLVIDO POR DIENER DORNELAS E FRANCIANE GONCALVES*/

#ifndef HEADER_H
#define HEADER_H

//Bibliotecas utilizadas
#include<stdio.h>            
#include<stdlib.h>
#include<sys/time.h>
#include<sys/resource.h>
#include<getopt.h>

#define MAX 100000000

typedef struct Povo{
	int id;
	float peso;
	float habil;
	float util;
}Povo;

typedef struct Rotas{
	int Pi;
	int Pj;
	int Dij;
}Rotas;

typedef struct Visitados{
	int id;
	int qtd;
	int habTotal;
}Visitados;


//assinaturas das funções
void recrutaree(int rotas, int aldeias, Povo *vtPovo, Rotas  *vtRotas, int pesoMax, int distanciaMax, FILE *saida);
void print(FILE *saida, Visitados *Nave, int iPv);
void leitura(FILE *entrada, FILE *saida);

#endif