import { Iedge } from '../@types/Iedge';
import { Distances } from './Distance';
import { FloydWarshall } from './FloydWarshall';
import { GraphLog } from './GraphLog';
import { Menu } from './Menu';
import { Node } from './Node';

const Core = () => {
	const graphLog = GraphLog();
	const fw = FloydWarshall();

	const Distance = Distances();

	const START = (response: string[], isNotFull?: boolean) => {
		const vertexIncluded: string[] = [];
		const rootGraph: Node[] = [];

		const verifyIncluded = (index: string) => vertexIncluded.find((ver) => ver === index);
		/**
*Cria uma ligação no grafo adicionando os vertices repassados
*
* @param start Index de partida da aresta
* @param destination Index de chegada da aresta
* @param weight Peso da aresta
*
*/
		const createVertex = (start: string, destination: string, weight: string) => {
			/**
* Estrutura o objeto a ser usado na criação de uma nova ligação
*/
			const edge: Iedge = {
				destination,
				weight,
			};
			//
			if (vertexIncluded.find((ver) => ver === start)) {
				rootGraph.forEach((node) => {
					if (node.getIndex() === start) {
						node.addEdge(edge);
					}
				});
			} else {
				rootGraph.push(new Node(start, edge));
				vertexIncluded.push(start);
			}

			if (!verifyIncluded(destination)) {
				rootGraph.push(new Node(destination));
				vertexIncluded.push(destination);
			}
		};

		try {
			const parseStrings = (str: string): string[] => str.split(' '); // função complementar para conversão de string em array

			response.forEach((line) => {
				const [start, destination, weight] = parseStrings(line);
				createVertex(start, destination, weight);
			});
			let options = [];
			if (isNotFull) {
				const matrix = fw.convertObjectToMatrix(rootGraph);
				fw.floydWarshall(matrix);
				console.log(matrix);
				Distance.logDistances(matrix);
			} else {
				do {
					options = Menu();

					switch (options[0]) {
					case '1':
						graphLog.printGraph(rootGraph);
						break;

					case '2':
						console.log(graphLog.findSuccessors(options[1], rootGraph));
						break;

					case '3': {
						const Antecedents: string[] = graphLog.findAntecedents(options[1], rootGraph);
						if (Antecedents.length === 0) {
							console.log(`O vertice ${options[1]} não possui antecessor`);
						} else if (Antecedents.length > 1) {
							console.log(`Os vertices ${Antecedents.join(', ')} são antecessores do vertice  ${options[1]}`);
						} else {
							console.log(`O vertice ${Antecedents.join(', ')} é antecessor do vertice ${options[1]}`);
						}
						break;
					}
					case '4':
						graphLog.summary(vertexIncluded, rootGraph, response.length);
						break;

					case '5': {
						const { Antecedents, successors } = graphLog.degreeVertex(options[1], rootGraph);
						if (successors.message) {
							console.log('Vertice não encontrado');
						} else {
							console.log(`Grau de entrada do vertice: ${Antecedents}\nGrau de saída do vertice: ${successors}`);
						}
						break;
					}
					case '6': {
						const matrix = fw.convertObjectToMatrix(rootGraph);
						fw.floydWarshall(matrix);
						console.log(matrix);
						Distance.logDistances(matrix);
						break;
					}
					default:
						break;
					}
				} while (options[1] !== '0');
			}

			// eslint-disable-next-line @typescript-eslint/no-explicit-any
		} catch (error: any) {
			console.log(error.message);
		}
	};

	return { START };
};

export { Core };
