#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//Q01

char *itoa(int v, char *str, int b) {
	char *ret = (char*)malloc(sizeof(50));
	int i;
	for (i = 0; v > 0; i++) {
		ret[i] = v % 10;
		v /= 10;
	}

	int j;

	for (j = 0; j < i; j++)
		str[j] = ret[j];
	str[j] = '\0';

}

typedef struct{
	char *Nome;
	float Valor;
} Produto;

typedef struct{
	Produto item;
	int qtdVendida;
} ItemVenda;

typedef struct ListaProduto{
	ItemVenda produto;
	struct ListaProduto *proximo;
} ListaProduto;

//Q02
void imprimeLinha(int a, char b){
	int i = 0;
	for(; i < a; i++){
		printf("%c", b);
	}
}

//Q03
void imprimeAEsquerda(char str[], char c, int cont){
	int i;
	i = strlen(str);
	printf("%s", str);
	for( i = 0;i<cont; i++){
		printf("%c", c);
	}
}

void imprimeADireita(char str[], char c, int cont){
	int i;
	i = strlen(str);
	for(; i < cont; i++){
		printf("%c", c);
	}
	printf("%s", str);
}

//Q04
void imprimeItemVenda(ItemVenda vend){
	int tam;
	float subT;
	char str[8], aux[8], nome[50];
	printf("%s", vend.item.Nome);
	strcpy(nome, vend.item.Nome);
	tam = strlen(nome);
	for(tam; tam == 30; tam++){
		printf(" ");
	}
	printf("%2.f X %d", vend.item.Valor, vend.qtdVendida);
	itoa(vend.qtdVendida, str, 10);
	itoa(vend.item.Valor, aux, 10);
	strcat(str, aux);
	tam = strlen(str);
	if(tam < 9)
		for(tam; tam == 9; tam++){
			printf(" ");
		}
	subT = (vend.qtdVendida) * (vend.item.Valor);
	itoa(subT, str, 10);
	tam = strlen(str);
	printf(" =");
	if(tam < 5)
		for(tam; tam == 5; tam++){
			printf(" ");
		}
	printf("%2.f", subT);
	printf("\n");
}

//Q05
void novoProduto(ListaProduto *Np, int *qtd){
	if((*qtd) > 0){
		ListaProduto *novo = (ListaProduto*) malloc(sizeof(ListaProduto));
		printf("Digite o Nome do Novo Produto: \n");
		setbuf(stdin, NULL);
		novo -> produto.item.Nome = (char*) malloc(sizeof (char) * 50);
		fgets(novo -> produto.item.Nome, 50, stdin);
		novo -> produto.item.Nome[strlen(novo -> produto.item.Nome)-1] = '\0';
		printf("Digite o Valor do Produto: ");
		scanf("%f", &novo -> produto.item.Valor);
		(*qtd)++;
		novo -> proximo = NULL;
		ListaProduto *pont_aux = Np;
		while(pont_aux -> proximo != NULL)
			pont_aux = pont_aux -> proximo;
		pont_aux -> proximo = novo;
	}
	else{
		setbuf(stdin, NULL);
		printf("Digite o Nome do Novo Produto: ");
		Np -> produto.item.Nome = (char*) malloc(sizeof (char) * 50);
		printf("%d\n", Np -> produto.item.Nome == NULL);
		fgets( Np -> produto.item.Nome, 50, stdin);
		Np -> produto.item.Nome[strlen(Np -> produto.item.Nome)-1] = '\0';
		printf("Digite o Valor do Produto: ");
		scanf("%f", &Np -> produto.item.Valor);
		(*qtd)++;
	}
}

//Q06
int showMenu(){
	int opcao, c;
	for( c = 0; c < 50; c++){
		printf("*");
	}
	printf("\n");
	for(c = 0; c < 18; c++){
		printf(" ");
	}
	printf("MENU DE OPCOES\n");
	for( c = 0; c < 50; c++){
		printf("*");
	}
	printf("\n0 - SAIR\n1 - CADASTRAR NOVO PRODUTO\n2 - REALIZAR VENDA\n");
	for(c = 0; c < 50; c++){
		printf("*");
	}
	printf("\nINFORME A SUA OPCAO: ");
	scanf("%d", &opcao);

	return opcao;
}

//Q07
float calcularTotal(ItemVenda vet[], int qtd){
	int i;
	float total = 0;
	for( i = 0; i < qtd; i++){
		total += (vet[i].qtdVendida * vet[i].item.Valor);
	}
	return total;
}

//Q08
void notaFiscal(ItemVenda vs[], int qtd){
	float total;
	char str[20];
	int cont,c,i;
	for(c = 0; c < 50; c++){
		printf("*");
	}
	for(c = 0; c < 21; c++){
		printf(" ");
	}
		printf("NOTA FISCAL");
	for(c = 0; c < 50; c++){
		printf("*");
	}
	for( i = 0; i < qtd; i++){
		imprimeItemVenda(vs[i]);
	}
	for( c = 0; c < 50; c++){
		printf("*");
	}
	total = calcularTotal(vs, qtd);

	itoa((int)total, str, 10);
	cont = strlen(str) + 3;
	for(; cont == 43; cont++);{
		printf(" ");
	}
	printf("TOTAL: %f\n", total);
	for(c = 0; c < 50; c++){
		printf("*");
	}
	printf("\n");
}

//Q09
ListaProduto* carregarProdutos(int *qtd){
	FILE* pont_arq;
	char str[50];

	pont_arq = fopen("arquivo.txt", "r");
	ListaProduto *novo = (ListaProduto*) malloc(sizeof(ListaProduto));
	if(pont_arq == NULL){
		*qtd = 0;
		return novo;
	}
	fscanf(pont_arq, "%f%c%c",  &novo -> produto.item.Valor, &str[0], &str[0]);
	fgets(str, 50, pont_arq);
	fgetc(pont_arq);
	novo -> produto.item.Nome = (char*)malloc(sizeof(char)*50);
	strcpy(novo -> produto.item.Nome, str);
	novo -> produto.item.Nome[strlen(novo -> produto.item.Nome)-1] = '\0';
	printf("Carregado %s, valor %f\n", novo -> produto.item.Nome, novo -> produto.item.Valor);
	(*qtd)++;
	novo -> proximo = NULL;
	ListaProduto *pont_aux = novo;

	while(!feof(pont_arq)){
		ListaProduto *novo = (ListaProduto*) malloc(sizeof(ListaProduto));
		fscanf(pont_arq, "%f%c%c",  &novo -> produto.item.Valor, &str[0], &str[0]);
		fgets(str, 50, pont_arq);
		fgetc(pont_arq);
		novo -> produto.item.Nome = (char*)malloc(sizeof(char)*50);
		strcpy(novo -> produto.item.Nome, str);
		novo -> produto.item.Nome[strlen(novo -> produto.item.Nome)-1] = '\0';
		printf("Carregado %s, valor %f\n", novo -> produto.item.Nome, novo -> produto.item.Valor);
		(*qtd)++;
		novo -> proximo = NULL;
		// while(pont_aux -> proximo != NULL)
		// 	pont_aux = pont_aux -> proximo;
		pont_aux -> proximo = novo;
		pont_aux = pont_aux->proximo;
	}

	fclose(pont_arq);
}

void gravarProdutos(ListaProduto *Lprod, int qtd){
	int i;
	FILE* pont_arq = fopen("arquivo.txt", "w");
	ListaProduto *pont_aux = Lprod, *des;
	for( i = 0; i < qtd; i++){
		des=pont_aux;
	fprintf(pont_arq, "%f, %s\n", pont_aux -> produto.item.Valor, pont_aux -> produto.item.Nome);
	free(des);
	pont_aux = pont_aux -> proximo;

	}
}

//Q10
Produto* buscarProduto(ListaProduto *Lprod, char produto[]){
	ListaProduto *pont_aux = Lprod;
	while(pont_aux -> proximo != NULL){
		printf("%s\n", pont_aux->produto.item.Nome);
		if (strcmp( pont_aux -> produto.item.Nome, produto) == 0){
			return &pont_aux->produto.item;
		}
	}
	return NULL;
}

//Q11
void realizarVenda(ListaProduto *Lprod){
	char produto[50];
	int qtd=0, i=0;
	ItemVenda prodV[10];
	Produto *p;

	while(1){
		printf("Digite o Nome do Produto: ");
		setbuf(stdin, NULL);
		gets(produto);
		if(strcmp(produto,"")==0)
			break;
		p=buscarProduto(Lprod,produto);
		if(p==NULL){
			printf("Produto Inexistente!");
		continue;
	}

	printf("Digite a Quantidade: ");
	scanf("%d", &qtd);

	prodV[i].qtdVendida = qtd;

	prodV[i].item=*p;

	i++;
	}
		notaFiscal(prodV,i);
	}

void main(){
	ListaProduto *estoque=NULL;
	int qtd_prod = 0, op;

	estoque = carregarProdutos(&qtd_prod);

	while(1){
	op=showMenu();

	switch(	op){
		case 0:
			gravarProdutos(estoque,qtd_prod);
			return;
			break;

		case 1:
			novoProduto(estoque,&qtd_prod);
			break;

		case 2:
			realizarVenda(estoque);
			break;
	}
	}

}
