import java.util.ArrayList;
import java.util.List;

import util.Console;

public class TreatmentInstructions {

	public static List<Instruction> createInstructionList(List<Instruction> Instructions, String path) {
		String[] archive = Console.Read(path).split("\n");
		String[] vecCommands;

		int id = 0;
		boolean label = false, nextIsOperand = false;
		for (String command : archive) {
			String type = "", operand = "", dst = "", ope1 = "", ope2 = "", pathLabel = "", ifType = "", register = "";
			vecCommands = command.split(" ");
			for (String instruction : vecCommands) {
				if (instruction.contains("L") && !label) {
					pathLabel = instruction.replace(":", "");
				} else if (label) {
					dst = instruction;
					label = false;
				} else if (nextIsOperand) {
					if (ope1.isEmpty()) {
						ope1 = instruction;
						nextIsOperand = false;
					} else {
						ope2 = instruction;
						nextIsOperand = false;
					}
				} else if (instruction.equals("ifnot") || instruction.equals("if")) {
					type = "conditional";
					ifType = instruction;
					nextIsOperand = true;
				} else if (instruction.equals("=")) {
					type = "attribution";
					nextIsOperand = true;
				} else if (instruction.equals("+") || instruction.equals("-") || instruction.equals("*")
						|| instruction.equals("/") || instruction.equals(">") || instruction.equals("<") || instruction.equals("==")
						|| instruction.equals("!=")) {
					operand = instruction;
					nextIsOperand = true;
				} else if (instruction.equals("goto")) {
					if (type.isEmpty())
						type = "unconditional";
					label = true;
				} else {
					register = instruction;
				}
			}
			Instruction instruction = new Instruction(type, pathLabel, dst, ope1, ope2, operand, id++, ifType, register);
			Instructions.add(instruction);
		}
		return Instructions;
	}

	public static List<Instruction> setLeaderList(List<Instruction> Instructions) {

		Instructions.get(0).setLeader();
		boolean nextInstructionLeader = false;
		for (Instruction instruction : Instructions) {
			if (nextInstructionLeader) {
				instruction.setLeader();
				nextInstructionLeader = false;
			}
			if (!instruction.getLabel().isEmpty()) {
				instruction.setLeader();
			}
			if (instruction.getType().equals("conditional") || instruction.getType().equals("unconditional")) {
				// instruction.setLeader();
				nextInstructionLeader = true;
			}
		}
		return Instructions;
	}

	public static List<Node> CreateCodeBlocks(List<Instruction> Instructions) {
		int id = 0;
		Node codeBlock = null;
		List<Node> Nodes = new ArrayList<Node>();
		for (Instruction instruction : Instructions) {
			if (instruction.isLeader()) {
				if (codeBlock != null) {
					Nodes.add(codeBlock);
				}
				codeBlock = new Node(id++);
				codeBlock.insertCode(instruction);
			} else {
				codeBlock.insertCode(instruction);
			}
		}
		Nodes.add(codeBlock);
		return Nodes;
	}
}
