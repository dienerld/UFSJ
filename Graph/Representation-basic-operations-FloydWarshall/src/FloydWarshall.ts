/* eslint-disable no-param-reassign */
import { Node } from './Node';

function FloydWarshall() {
	function convertObjectToMatrix(graph: Node[]): number[][] {
		const matrix: number[][] = [];
		matrix[0] = [];
		matrix[0][0] = -1;

		const numberNodes = graph.length;
		for (let i = 1; i <= numberNodes; i++) {
			matrix[i] = [];
			for (let j = 0; j <= numberNodes; j++) {
				matrix[i][j] = Infinity;
			}
			matrix[i][0] = parseInt(graph[i - 1].getIndex(), 10);
			matrix[0][i] = parseInt(graph[i - 1].getIndex(), 10);
			matrix[i][i] = 0;
		}

		graph.forEach((node) => { // i -> partida e j-> chegada
			const edges = node.getEdges();
			edges.forEach((edge) => {
				for (let i = 0; i <= numberNodes; i++) {
					if (matrix[i][0] === parseInt(node.getIndex(), 10)) {
						for (let j = 0; j <= numberNodes; j++) {
							if (matrix[0][j] === parseInt(edge.destination, 10)) {
								matrix[i][j] = parseInt(edge.weight, 10);
							}
						}
					}
				}
			});
		});

		return matrix;
	}

	function floydWarshall(matrixDistance: number[][]) {
		const lengthMatrix = matrixDistance.length;

		for (let k = 1; k < lengthMatrix; k++) {
			for (let i = 1; i < lengthMatrix; i++) {
				for (let j = 1; j < lengthMatrix; j++) {
					if ((matrixDistance[i][k] + matrixDistance[k][j]) < matrixDistance[i][j]) {
						matrixDistance[i][j] = matrixDistance[i][k] + matrixDistance[k][j];
					}
				}
			}
		}

		return matrixDistance;
	}
	return { convertObjectToMatrix, floydWarshall };
}
export { FloydWarshall };
