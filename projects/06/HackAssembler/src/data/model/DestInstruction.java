package data.model;

public class DestInstruction {
	String name;
	String binaryCode;

	public DestInstruction() {
		// TODO Auto-generated constructor stub
	}

	public DestInstruction(String name, String binaryCode) {
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
