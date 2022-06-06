
fatorial (p) {
	resultado =1
	enquanto (p for maior 1 ){
		resultado = fatorial de p 
	}
	retorna resultado
}

arranjo ( qtdD, p){
	arr=1
	enquanto (p maior ou igual a 1){                        
		arr = arr * quantidade de divisores do valor de entrada 
		decrementa quantidade de divisores
		decrementa p        
	}                                    
	retorna o arranjo
}

combinacao(qtdD,p){
	retorna a divisão do arranjo de qtdD e p, pelo fatorial de p
}

combina(qtdD, arquivo de saída){
	x=0
	for(p inicializa em 2 e é sempre menor ou igual à quantidade de divisores primos, incrementa p){
		x recebe a soma de combinação todas as vezes, é a quantidade de divisores Xulambs
	}
	imprime a quantidade de Xulambs no arquivo de saída
}

fatoracao(){ na primeira chamada aloco dinamicamente o vetor onde
	guardarei os numeros primos de 2 ate a Raiz Quadrada  do maior numero
	que eu tiver como entrada

	enquanto (num diferente de 1){ Verifico se o numero que recebi do
		arquivo de texto(num), é divisivel por 2 que é o item na posição 0  do
		meu vetor de numeros primos(VetPrimos), caso seja a divisão é
		repetida, mas se o resto da divisão  for diferente de 0 a proxima
		etapa é procurar o número primo sucessor ao que foi adicionado no
		vetor  na posição (NumPrimos-1). Então retorno a tentativa de divisão
		até encontrar o divisor de num que o  resultado é 1 e quebrar a
		condição que mantém dentro do laço. Se após verificar todos os números
		até a Raiz Quadrada de num e não tiver achado nenhum divisor que 
		quebre a condição do laço, o programa assume que o numero de entrada é
		primo e termina o processo atual.  
	}
}