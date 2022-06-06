public class Instruction {
	int ID;
	String register;
	String type; // Atribuição, cond, incondicional
	String label;
	String dst; // Label de destino ou resultado da atribuição
	String ope1; // operando fonte 1
	String ope2; // operando fonte 2
	String op; // operador
	String ifType;
	boolean leader; // Se é líder, vc coloca true

	public Instruction(String type, String label, String dst, String ope1, String ope2, String op, int id, String ifType,
			String register) {
		this.register = register;
		this.type = type;
		this.label = label;
		this.dst = dst;
		this.ope1 = ope1;
		this.ope2 = ope2;
		this.op = op;
		this.ID = id;
		this.leader = false;
		this.ifType = ifType;
		return;
	}

	public void setLeader() {
		this.leader = true;
		return;
	}

	public String getLabel() {
		return label;
	}

	public String getType() {
		return type;
	}

	public boolean isLeader() {
		return leader;
	}

	public String getDst() {
		return dst;
	}

	public void print() {
		String msg = ": ";
		if (label == "") {
			msg = "";
		}
		if (type == "attribution") {
			System.out.println(label + msg + register + " = " + ope1 + " " + op + " " + ope2);
		} else if (type == "") {
			System.out.println(label);
		} else if (type == "unconditional") {
			System.out.println("goto " + dst);
		} else {
			System.out.println(label + msg + ifType + " " + ope1 + " " + op + " " + ope2 + " goto " + dst);
		}
	}

}
