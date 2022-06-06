/*CODIGO DESENVOLVIDO POR DIENER DORNELAS E FRANCIANE GONCALVES*/

#include"header.h"

long int fatorial(long int p){   //a função fatorial é responsável por fazer o fatorial do
	         	                 // número de entrada p(n° de valores combinados)
	long int resultado = 1;   
	while(p > 1){
		resultado = p * resultado; //multiplica p, por p-1 até que seja 1
		p--;
	}
	return resultado;      //e então retorna o fatorial.
}


long int arranjo(long int qtdD, long int p){  //essa função faz o arranjo entre a quantidade de divisores
	long int arr = 1;                         //e a quantidade de valores a serem combinados
	while(p>=1){                              //enquanto p>=1 arr recebe o arranjo inicialmente como 1 * quantidade 
		arr = arr*qtdD;                       //decrementada a cada volta no laço 
		qtdD--;
		p--;
	}
	return arr;
}

long int combinacao(long int qtdD, long int p){ //esta função recebe qtdD e p e chama a função de arranjo para organiza-los
	return arranjo(qtdD,p)/fatorial(p);         //e fatorial quantidade de numeros a serem combinados  
}

void combina(long int qtdD, FILE *arq_saida){ 	//a função combina recebe
	int x = 0;									// qtdD que é a quantidade de divisores do número de entrada.
	for(int p = 2; p <= qtdD; p++){				//onde laço tem como parâmetro o número
		x +=combinacao(qtdD, p);				//de valores que serão combinados ex:2 a 2.
	}
	fprintf(arq_saida, "%d\n", x);				//imprime no arquivo de saída o total
}												//de divisores xulambs.

void fatoracao(FILE *entrada, FILE* saida){	//a função fatoração fatora o número de entrada.
	long int num, qtdD = 0, div = 2, temp = 0, numPrimos = 0, n = 3, limite_aux = 50;
	long int *vetPrimos = (long int*)malloc(sizeof(long int) * 50), sqrtN, limitep; //aloca o vetor que armazenará os primos
	int primalidade;
	vetPrimos[0] = 2;
	numPrimos++;
	long int raizN;
	int lp=0;

    while(!feof(entrada)){ //o laço conta quantas vezes a função vai percorrer o arq de entrada
    	fscanf(entrada,"%ld\n",&num);  // lê o inteiro do arquivo para fatorar
		qtdD = 0;
		raizN = (long int)sqrt(num) + 1; // raiz do num de entrada para saber até onde é necessário procurar números primos
		limitep = num;
		if(limitep > 1000000){ //verifico se o numero que recebi foi maior que 1 milhão, se for realoco o vetor com um tamanho máximo que tenho condições para alocar todos os primos que vou precisar
			vetPrimos = realloc(vetPrimos, (1000001 * sizeof(long int)));
		}

		else if(limitep > limite_aux){	// se a condição anterior não foi satisfeita eu verifico essa, para evitar de realocar o vetor para um 
										//tamanho menor e perder números primos já encontrados
			limite_aux = limitep;
			vetPrimos = realloc(vetPrimos, (limitep * sizeof(long int)));
		}
		int j = 0;
		while(num != 1){ // inicio a fatoração do número
			if((num/vetPrimos[j]) * vetPrimos[j] == num){	// tento dividir o número recebido do arquivo, pelo primo na posição j 
															//do vetor iniciando a partir de 0 onde o valor do vetor nessa posição é o primo número 2
				num = num/vetPrimos[j]; // divido o número que recebi do arquivo para poder de fato fazer a fatoração dele
				if(vetPrimos[j] != temp){	// verifico se o o divisor que acabei de dividir o número já foi inserido na lista de divisores 
											//para poder fazer a combinações dos Xulambs, e se já foi inserido eu pulo ele, pois não posso repetir o número
					qtdD++;
					temp = vetPrimos[j]; // salvo o último divisor do número de entrada(num), para fazer a verificação no if acima
				}
			}
			
			else if(n < raizN){ //parte onde eu procuro e insiro no vetor de primos o próximo número primo, chegando no máximo a raiz quadrada do número que estou fazendo a fatoração
				primalidade = 1; // assumo que o numero n vai ser primo
				sqrtN = (long int)sqrt(n); // acho a raiz quadrada do numero para no for a seguir não rodar mais que o necessário
				for(int i = 0; vetPrimos[i] <= sqrtN; i++){ // for para rodar o vetor dividindo o número 'n' pelos primo que já foram inseridos no vetor
					if((n/vetPrimos[i]) * vetPrimos[i] == n){
						primalidade = 0;
						break;
					}
				}
				if(primalidade){ // se a verificação no if anterior der falso eu faço a inserção do número que foi provado ser primo no vetor que contém todos os primos já descoberto até ele
					vetPrimos[numPrimos] = n;
					numPrimos++;
					j++;
				}
				n+=2;
			}
			else {// se n já for maior que a raizN, não tenho a necessidade de procurar primo e inserir no vetor, logo eu simplesmente preciso percorrer o vetor de números primos que já tenho
				j++; 	
				if(j == numPrimos){ // se por fim após percorrer todas as posições do vetor e não achar divisor eu posso assumir que esse número é primo, 
									//assim adiciono ele como o próximo divisor dele e encerro a verificação deste número
					qtdD++;
					break;
				}
			}
		}
		combina(qtdD, saida);// chamo a função que faz a combinação de quantos Xulambs tenho passando a quantidade de divisores de entrada teve e 
	}                        //o arquivo de saída para fazer a impressão final no arquivo de texto
	fclose(entrada);
	fclose(saida);
	free(vetPrimos);  //limpa o vetor
}
