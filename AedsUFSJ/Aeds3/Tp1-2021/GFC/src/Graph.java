import java.util.ArrayList;
import java.util.List;

public class Graph {
	private static List<Node> Nodes = new ArrayList<Node>();
	private static List<Edge> Edges = new ArrayList<Edge>();

	public void createNode(Node node) {
		Nodes.add(node);
		return;
	}

	public void createEdge(Edge edge) {
		Edges.add(edge);
		return;
	}

	public void printGraph() {

		for (Node node : Nodes) {
			System.out.println("B" + (node.getId() + 1) + ":");
			node.print();
			System.out.println();
		}

		for (Edge edge : Edges) {
			edge.printEdge();
		}
	}

	public List<Node> getNodes() {
		return Nodes;
	}

	public List<Edge> getEdges() {
		return Edges;
	}
}
