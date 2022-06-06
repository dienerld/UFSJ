import fs from 'fs';

type vecVertex = {
	distance: number;
	exit: number;
	input: number
}

type sumCentroid={ index: number; sum: number };

function Distances() {
	function searchMaxMinDistance(VecA: vecVertex[], VecB:vecVertex[]) {
		const eccentricA = VecA.reduce((idxA, idxB) => (idxA.distance > idxB.distance ? idxA : idxB));
		const eccentricB = VecB.reduce((idxA, idxB) => (idxA.distance > idxB.distance ? idxA : idxB));

		const radiusA = VecA.reduce((idxA, idxB) => (idxA.distance < idxB.distance ? idxA : idxB));
		const radiusB = VecB.reduce((idxA, idxB) => (idxA.distance < idxB.distance ? idxA : idxB));

		const eccentric = eccentricA.distance > eccentricB.distance ? eccentricA : eccentricB;
		const radius = radiusA.distance > radiusB.distance ? radiusA : radiusB;
		return { eccentric, radius };
	}

	function searchCentroid(sumA: sumCentroid[], sumB: sumCentroid[]) {
		const centroidA = sumA.reduce((idxA, idxB) => (idxA.sum < idxB.sum ? idxA : idxB));
		const centroidB = sumB.reduce((idxA, idxB) => (idxA.sum < idxB.sum ? idxA : idxB));

		return centroidA < centroidB ? centroidA : centroidB;
	}

	function logDistances(matrix: number[][]) {
		const vecVertexA = [];
		const vecVertexB = [];
		const sumVertexA = [];
		const sumVertexB = [];
		const { length } = matrix;
		for (let i = 1; i < length; i++) {
			const vertexA = { distance: 0, exit: 0, input: 0 };
			const vertexB = { distance: 0, exit: 0, input: 0 };
			const sumA = { index: matrix[i][0], sum: 0 };
			const sumB = { index: matrix[0][i], sum: 0 };

			for (let j = 1; j < length; j++) {
				if (vertexA.distance < matrix[i][j] && matrix[i][j] !== Infinity) {
					vertexA.distance = matrix[i][j];
					vertexA.exit = matrix[i][0];
					vertexA.input = matrix[0][j];
				}
				if (vertexB.distance < matrix[j - 1][i] && matrix[j - 1][i] !== Infinity) {
					vertexB.distance = matrix[j - 1][i];
					vertexB.exit = j === 1 ? matrix[j - 1][1] : matrix[j - 1][0];
					vertexB.input = matrix[0][i];
				}
				sumA.sum += matrix[i][j] === Infinity ? 0 : matrix[i][j];
				sumB.sum += matrix[j][i] === Infinity ? 0 : matrix[j][i];
			}
			sumVertexA.push(sumA);
			sumVertexB.push(sumB);
			vecVertexA.push(vertexA);
			vecVertexB.push(vertexB);
		}

		const { eccentric, radius } = searchMaxMinDistance(vecVertexA, vecVertexB);
		const centroid = searchCentroid(sumVertexA, sumVertexB);

		const string = `Excentricidade: { Distância: ${eccentric.distance}, Vértice de Saída: ${eccentric.exit}, Vértice de Chegada: ${eccentric.input} }
Raio: { Distância: ${radius.distance}, Vértice de Saída: ${radius.exit}, Vértice de Chegada: ${radius.input} }
Diametro: ${eccentric.distance}
Periferia: São os Vertices ${eccentric.exit}  e ${eccentric.input}
Centroide: Vértice ${centroid.index}`;
		fs.writeFileSync('logPropGraph.txt', string, 'utf-8');
	}

	return { logDistances };
}

export { Distances };
