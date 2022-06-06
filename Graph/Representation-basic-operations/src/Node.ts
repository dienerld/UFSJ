import { Iedge } from '../@types/Iedge';

class Node {
	private index: string;

	private edges: Iedge[] = [];

	/**
	 *
	 * @param index Identifier vertex
	 * @param edge  Weight and destination
	 */
	constructor(index: string, edge?: Iedge) {
		this.index = index;
		if (edge) this.edges.push(edge);
	}

	addEdge(edge: Iedge):Node {
		this.edges.push(edge);
		return this;
	}

	getIndex(): string {
		return this.index;
	}

	getEdges(): Iedge[] {
		return this.edges;
	}

	printNode(): void {
		console.log(`Index: ${this.index}\nTotal Edges Leaving: ${this.edges.length}
		`, this.edges);

		console.log('___________________________________');
	}
}

export { Node };
