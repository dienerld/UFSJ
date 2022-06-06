import java.util.ArrayList;
import java.util.List;

public class Master {

	// Inicializa listas que guarda os dados de entrada
	private static List<Instruction> Instructions = new ArrayList<Instruction>();
	private static List<Node> Nodes = new ArrayList<Node>();
	private static List<Edge> Edges = new ArrayList<Edge>();
	private static Graph Graph = new Graph();

	public static void main(String[] args) {
		String path = "";
		if (args.length == 0) {
			System.err.println("\nPassar endereço de entrada com os dados\nFormato: Java Master exemplo.txt\n");
			return;
		} else {
			int i = 0;
			while (i < args.length) {
				if (args[i].equals("-i")) {
					path = args[i + 1];
					if (path.contains(".txt"))
						break;
					else {
						System.err.println("\nPassar endereço de entrada com os dados\nFormato: Java Master exemplo.txt\n");
						return;
					}
				}
				i++;
			}
		}

		// Aloca as instruções passadas de maneira desmembrada
		Instructions = TreatmentInstructions.createInstructionList(Instructions, path);

		// Define as instruções que serão líderes de blocos
		Instructions = TreatmentInstructions.setLeaderList(Instructions);

		// Cria os blocos de códigos separando a sequência de instruções até o proximo
		// líder
		Nodes = TreatmentInstructions.CreateCodeBlocks(Instructions);
		Instructions.clear(); // remove dados para economizar memória
		defineEdge();
		insertEdgesInGraph(); // Insere cada ligação entre blocos de código no grafo
		insertNodesInGraph(); // Insere cada bloco de código no grafo
		Graph.printGraph();
	}

	public static void defineEdge() {
		boolean haveUnconditional = true;
		for (Node node : Nodes) {
			haveUnconditional = true;
			for (Instruction instruction : node.getCodeBlocks()) {

				// se o comando for do tipo incondicional não instauro uma aresta entre o bloco
				// de código sequencial e o atual

				if (!instruction.getDst().equals("")) {
					for (Node nodeLabel : Nodes) {
						for (Instruction instructionLabel : nodeLabel.getCodeBlocks()) {

							if (instruction.getDst().equals(instructionLabel.getLabel())) {
								Edges.add(new Edge(node.getId(), nodeLabel.getId()));
							}

						}
					}
				}

				if (instruction.getType().equals("unconditional")) {
					haveUnconditional = false;
				}
			}

			if (haveUnconditional) {
				if (node.getId() + 1 < Nodes.size()) {
					Edges.add(new Edge(node.getId(), node.getId() + 1));
				}
			}
		}
	}

	public static void insertNodesInGraph() {
		for (Node node : Nodes) {
			Graph.createNode(node);
		}
	}

	public static void insertEdgesInGraph() {
		for (Edge edge : Edges) {
			Graph.createEdge(edge);
		}
	}
}
