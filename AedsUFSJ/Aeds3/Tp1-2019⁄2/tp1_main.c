/*CODIGO DESENVOLVIDO POR DIENER DORNELAS E FRANCIANE GONCALVES*/

#include"header.h"
#include<getopt.h>

void main(int argc, char* argv[]){

	FILE* entrada;   //abre p arquivo de entrada
	FILE* saida;     //e o de saída.
	int option;
	struct rusage TempoI, TempoF;
	struct timeval ru_stime, ru_utime;
	if(argc < 5){
		printf("Digite: ./tp1 -i entrada.txt -o saida.txt\n");
		return;
	}
	while((option = getopt(argc, argv, "i:o:")) != -1){ //Getopt recebe os parâmetros
		switch(option){                                  //definindo os arquivos de
			case 'i':                                      //entrada e saída. i - refere-se
				entrada = fopen(optarg,"r");               //à input e o - refere-se à output.
				break;
				case 'o':
				saida = fopen(optarg, "w");
				break;
			}
		}
		if (getrusage(RUSAGE_SELF, &TempoI) == -1 ){
			printf("Erro\n");
			return;
		}
		fatoracao(entrada, saida);
		
		if (getrusage(RUSAGE_SELF, &TempoF) == -1 ){
			printf("Erro\n");
			return;
		}
		double SysTemp, UserTemp;
		SysTemp = ((double)TempoF.ru_stime.tv_sec + (double)TempoF.ru_stime.tv_usec*1.e-6) -
		((double)TempoI.ru_stime.tv_sec + (double)TempoI.ru_stime.tv_usec*1.e-6);
		UserTemp = ((double)TempoF.ru_utime.tv_sec + (double)TempoF.ru_utime.tv_usec*1.e-6) -
		((double)TempoI.ru_utime.tv_sec + (double)TempoI.ru_utime.tv_usec*1.e-6);
		printf("SysTemp: %f  UserTemp: %f  Total: %f\n", SysTemp, UserTemp, (SysTemp + UserTemp));
	}
