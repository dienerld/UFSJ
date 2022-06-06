import java.util.ArrayList;
import java.util.List;

public class Node {
	private int id;
	private List<Instruction> codeBlocks;

	public Node(int id) {
		this.id = id;
		this.codeBlocks = new ArrayList<Instruction>();
	}

	public void insertCode(Instruction instruction) {
		this.codeBlocks.add(instruction);
	}

	public List<Instruction> getCodeBlocks() {
		return codeBlocks;
	}

	public void printDst() {
		for (Instruction instruction : codeBlocks) {
			instruction.getDst();
		}
	}

	public void print() {
		for (Instruction instruction : codeBlocks) {
			instruction.print();
		}
	}

	public int getId() {
		return id;
	}
}
