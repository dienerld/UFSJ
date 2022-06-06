/* eslint-disable no-unused-vars */
import { Node } from './Node';

const GraphLog = () => {
	/**
	 *
	 * @param vertexIncluded
	 * @param rootGraph
	 */
	const summary = (vertexIncluded: string[], rootGraph: Node[], totalEdges: number) => {
		// eslint-disable-next-line max-len
		const totalVertex = vertexIncluded.filter((element, index) => vertexIncluded.indexOf(element) === index);
		const density = totalEdges / (totalVertex.length * (totalVertex.length - 1));
		console.log('----------------------------------------\n           Grafo Direcionado\n----------------------------------------');

		console.log(`Os vertices do grafo são: [${totalVertex.sort((a, b) => parseInt(a, 10) - parseInt(b, 10)).join(', ')}}]`);
		console.log(`totalEdges: ${totalEdges}\ntotalVertex: ${totalVertex.length}\nDensidade : ${density}`);
	};

	/**
	 * Imprime todos vertices do grafo com respectivos sucessores
	 * @param Graph
	 */
	const printGraph = (Graph: Node[]):void => {
		Graph.forEach((node) => node.printNode());
	};

	/**
	 * Busca sucessores de 1º grau do vertice
	 * @param index
	 * @param graph
	 * @returns Vetor de sucessores ou String declarativa (**sem sucessores** ou **vertice não encontrado**)
	 */
	const findSuccessors = (index: string, graph: Node[]): any => {
		const vertex = graph.find((element) => (element.getIndex() === index ? element : 0));
		if (vertex) {
			return vertex.getEdges();
		}
		return { message: 'Vertice não encontrado' };
	};

	const findAntecessors = (index: string, graph: Node[]) => {
		const Antecessors: string[] = [];
		graph.forEach((element) => {
			const edges = element.getEdges(); // todas arestas de saida
			edges.forEach((edgeLeaving) => {
				if (edgeLeaving.destination === index) {
					Antecessors.push(element.getIndex());
				}
				return true;
			});
		});
		return Antecessors;
	};

	const degreeVertex = (index: string, graph: Node[]) => {
		const antecessors = findAntecessors(index, graph).length;
		const successors = findSuccessors(index, graph).length;
		if (successors.message === undefined) {
			return { antecessors, successors };
		}

		return { antecessors, successors };
	};
	// Retorno AuxFunc
	return {
		summary,
		printGraph,
		findSuccessors,
		findAntecessors,
		degreeVertex,
	};
};

export { GraphLog };
