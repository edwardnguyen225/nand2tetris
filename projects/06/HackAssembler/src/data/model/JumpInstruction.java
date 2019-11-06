package data.model;

public class JumpInstruction {
	String name;
	String binaryCode;

	public JumpInstruction() {
		// TODO Auto-generated constructor stub
	}

	public JumpInstruction(String name, String binaryCode) {
		super();
		this.name = name;
		this.binaryCode = binaryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBinaryCode() {
		return binaryCode;
	}

	public void setBinaryCode(String binaryCode) {
		this.binaryCode = binaryCode;
	}
}
