/*CODIGO DESENVOLVIDO POR DIENER DORNELAS E FRANCIANE GONCALVES*/

#ifndef HEADER_H
#define HEADER_H

//Bibliotecas utilizadas
#include<stdio.h>            
#include<stdlib.h>
#include <sys/time.h>
#include <sys/resource.h>

//assinaturas das funções
long int fatorial(long int p);
long int arranjo(long int qtdD, long int p);
long int combinacao(long int qtdD, long int p);
void combina(long int qtdD, FILE *arq_saida);
void fatoracao(FILE *entrada, FILE* saida);

#endif