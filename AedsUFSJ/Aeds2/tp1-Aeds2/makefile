all: main

main: Tp_main.c F_tp.o
	gcc F_tp.o Tp_main.c -o MAIN

tp.o: F_tp.h F_tp.c
	gcc -g -c F_tp.c

clear:
	rm *.o
