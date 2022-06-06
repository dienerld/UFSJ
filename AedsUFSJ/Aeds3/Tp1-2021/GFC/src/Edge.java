public class Edge {
	private int idStart;
	private int idEnd;

	public Edge(int idStart, int idEnd) {
		this.idStart = idStart;
		this.idEnd = idEnd;
	}

	public int getEdgeStart() {
		return idStart;
	}

	public int setEdgeEnd() {
		return idEnd;
	}

	public void printEdge() {
		String end;
		if ((idEnd + 1) > 0) {
			end = "B" + Integer.toString(idEnd + 1);
		} else {
			end = "Exit";
		}

		System.out.println("B" + (idStart + 1) + " -> " + end);
	}
}
