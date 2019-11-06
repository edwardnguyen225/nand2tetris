package data.model;

public class CompInstruction {
	String name1;
	String name2;
	String name3;
	String name4;
	String binaryCode;

	public CompInstruction() {
		this.name1 = "";
		this.name2 = "";
		this.binaryCode = "";
	}
	
	public CompInstruction(String name1, String name2, String name3, String name4, String binaryCode) {
		super();
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
		this.name4 = name4;
		this.binaryCode = binaryCode;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public String getBinaryCode() {
		return binaryCode;
	}

	public void setBinaryCode(String binaryCode) {
		this.binaryCode = binaryCode;
	}

	
}
