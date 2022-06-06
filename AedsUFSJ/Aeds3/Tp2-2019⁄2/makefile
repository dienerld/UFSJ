all: main

main: tp2_main.c recrutar.o leitura.o print.o
	gcc -O3 recrutar.o leitura.o print.o tp2_main.c -o tp2  -w

recrutar.o: recrutar.c header.h
	gcc -O3 -c  recrutar.c  -w

leitura.o: leitura.c header.h
	gcc -O3 -c  leitura.c  -w

print.o: print.c header.h
	gcc -O3 -c  print.c  -w

clear:
	rm tp2 *.o