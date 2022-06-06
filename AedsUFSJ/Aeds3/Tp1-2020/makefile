all: main

main: main.c startCollection.o insertSpot.o compareIntersection.o readFile.o functionAux.o quickSort.o header.h
	gcc -O3  main.c startCollection.o insertSpot.o compareIntersection.o readFile.o functionAux.o quickSort.o -o main

startCollection.o: startCollection.c header.h
	gcc -O3 -c startCollection.c

insertSpot.o: insertSpot.c header.h
	gcc -O3 -c insertSpot.c

compareIntersection.o: compareIntersection.c header.h
	gcc -O3 -c compareIntersection.c

readFile.o: readFile.c header.h
	gcc -O3 -c readFile.c

functionAux.o: functionAux.c header.h
	gcc -O3 -c functionAux.c


quickSort.o: quickSort.c header.h
	gcc -O3 -c quickSort.c
clear:
	rm main *.o
