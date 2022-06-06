all: main

main: tp1_main.c tp1_fc.o
	gcc -O3 tp1_fc.o tp1_main.c -o tp1 -lm -w

tp1_fc.o: tp1_fc.c header.h
	gcc -O3 -c  tp1_fc.c -lm -w


clear:
	rm tp1 *.o