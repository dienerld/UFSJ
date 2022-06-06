function createMatrixAdj(vecData: string[]) {
  const matrix: number[][] = [];
  matrix[0] = [];

  const vertexes:number[] = [];

  const numberNodes = vecData.length;
  // Armazeno os vertices em um vetor sem repetir
  for (let line = 0; line < numberNodes; line++) {
    const [start, end, weight] = vecData[line].split(' ');

    if (!vertexes.includes(parseInt(start))) {
      vertexes.push(parseInt(start));
    }
    if (!vertexes.includes(parseInt(end))) {
      vertexes.push(parseInt(end));
    }
  }

  // completo a inicialização da matriz preenchendo com Zeros
  for (let index = 0; index <= vertexes.length; index++) {
    matrix[index] = [];

    for (let column = 0; column <= vertexes.length; column++) {
      matrix[index][column] = Infinity;
    }
  }
  // completa a inicialização da matriz com valores entre vértices

  // Corrigir
  const lenMatrix = matrix.length;
  for (let iVecData = 0; iVecData < numberNodes; iVecData++) {
    const [start, end, weight] = vecData[iVecData].split(' ');
    console.log(`${start}-${end}-${weight}`);

    for (let i = 0; i < lenMatrix; i++) {
      if (matrix[i][0] === parseInt(start)) {
        for (let j = 0; j < lenMatrix; j++) {
          console.log(matrix[0][j]);

          if (matrix[0][j] === parseInt(end)) {
            matrix[i][j] = parseInt(weight);
            break;
          }
        }
      }
      matrix[i][i] = 0;
    }
  }

  return matrix;
}

export { createMatrixAdj };
