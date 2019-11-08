package data.controller;

import java.util.ArrayList;
import java.util.List;

import data.model.CompInstruction;
import data.model.DestInstruction;
import data.model.JumpInstruction;
import data.model.Symbol;

public class Controller {
	List<CompInstruction> compList = new ArrayList<CompInstruction>();
	List<DestInstruction> destList = new ArrayList<DestInstruction>();
	List<JumpInstruction> jumpList = new ArrayList<JumpInstruction>();
	List<Symbol> symbolList = new ArrayList<Symbol>();
	int variableValue = 16;

	public Controller() {
		LoadData();
	}

	void LoadData() {
		LoadCompInstruction();
		LoadDestInstruction();
		LoadJumpInstruction();
		LoadSymbolList();
	}

	void LoadCompInstruction() {
		CompInstruction comp;

		comp = new CompInstruction("0", "", "", "", "101010");
		compList.add(comp);

		comp = new CompInstruction("1", "", "", "", "111111");
		compList.add(comp);

		comp = new CompInstruction("-1", "", "", "", "111010");
		compList.add(comp);

		comp = new CompInstruction("D", "", "", "", "001100");
		compList.add(comp);

		comp = new CompInstruction("A", "M", "", "", "110000");
		compList.add(comp);

		comp = new CompInstruction("!D", "", "", "", "001101");
		compList.add(comp);

		comp = new CompInstruction("!A", "!M", "", "", "110001");
		compList.add(comp);

		comp = new CompInstruction("-D", "", "", "", "001111");
		compList.add(comp);

		comp = new CompInstruction("-A", "-M", "", "", "110011");
		compList.add(comp);

		comp = new CompInstruction("D+1", "", "", "", "011111");
		compList.add(comp);

		comp = new CompInstruction("A+1", "M+1", "", "", "110111"); // Không đảo vì Assembler gốc không đảo, cần bổ sung
																	// sau
		compList.add(comp);

		comp = new CompInstruction("D-1", "", "", "", "001110");
		compList.add(comp);

		comp = new CompInstruction("A-1", "M-1", "", "", "110010");
		compList.add(comp);

		comp = new CompInstruction("D+A", "D+M", "A+D", "M+D", "000010");
		compList.add(comp);

		comp = new CompInstruction("D-A", "D-M", "", "", "010011");
		compList.add(comp);

		comp = new CompInstruction("A-D", "M-D", "", "", "000111");
		compList.add(comp);

		comp = new CompInstruction("D&A", "D&M", "A&D", "M&D", "000000");
		compList.add(comp);

		comp = new CompInstruction("D|A", "D|M", "A|D", "M|D", "010101");
		compList.add(comp);
	}

	void LoadDestInstruction() {
		DestInstruction dest;

		dest = new DestInstruction("", "000");
		destList.add(dest);

		dest = new DestInstruction("M", "001");
		destList.add(dest);

		dest = new DestInstruction("D", "010");
		destList.add(dest);

		dest = new DestInstruction("MD", "011");
		destList.add(dest);

		dest = new DestInstruction("A", "100");
		destList.add(dest);

		dest = new DestInstruction("AM", "101");
		destList.add(dest);

		dest = new DestInstruction("AD", "110");
		destList.add(dest);

		dest = new DestInstruction("AMD", "111");
		destList.add(dest);
	}

	void LoadJumpInstruction() {
		JumpInstruction jump;

		jump = new JumpInstruction("", "000");
		jumpList.add(jump);

		jump = new JumpInstruction("JGT", "001");
		jumpList.add(jump);

		jump = new JumpInstruction("JEQ", "010");
		jumpList.add(jump);

		jump = new JumpInstruction("JGE", "011");
		jumpList.add(jump);

		jump = new JumpInstruction("JLT", "100");
		jumpList.add(jump);

		jump = new JumpInstruction("JNE", "101");
		jumpList.add(jump);

		jump = new JumpInstruction("JLE", "110");
		jumpList.add(jump);

		jump = new JumpInstruction("JMP", "111");
		jumpList.add(jump);
	}

	void LoadSymbolList() {
		Symbol symbol;
		String name;
		for (int i = 0; i <= 15; i++) {
			name = "R" + Integer.toString(i);
			symbol = new Symbol(name, i);
			symbolList.add(symbol);
		}
		symbol = new Symbol("SCREEN", 16384);
		symbolList.add(symbol);
		symbol = new Symbol("KBD", 24576);
		symbolList.add(symbol);
		symbol = new Symbol("SP", 0);
		symbolList.add(symbol);
		symbol = new Symbol("LCL", 1);
		symbolList.add(symbol);
		symbol = new Symbol("ARG", 2);
		symbolList.add(symbol);
		symbol = new Symbol("THIS", 3);
		symbolList.add(symbol);
		symbol = new Symbol("THAT", 4);
		symbolList.add(symbol);
	}
	
	public String getDestBinary(String str) {
		for (DestInstruction destInstruction : destList) {
			if (str.equals(destInstruction.getName()))
				return destInstruction.getBinaryCode();
		}
		return "-1";
	}

	public String getCompBinary(String str) {
		for (CompInstruction compInstruction : compList) {
			if (str.equals(compInstruction.getName1()) || str.equals(compInstruction.getName2())
					|| str.equals(compInstruction.getName3()) || str.equals(compInstruction.getName4())) {
				return compInstruction.getBinaryCode();
			}

		}
		return "-1";
	}

	public String getJumpBinary(String str) {
		for (JumpInstruction jumpInstruction : jumpList) {
			if (str.equals(jumpInstruction.getName()))
				return jumpInstruction.getBinaryCode();
		}
		return "-1";
	}

	public int getValueOfSymbol(String name) {
		for (Symbol symbol : symbolList) {
			if (symbol.getName().equals(name))
				return symbol.getValue();
		}
		return -1;
	}

	public boolean isSymbolExisted(String name) {
		for (Symbol symbol : symbolList) {
			if (symbol.getName().equals(name))
				return true;
		}
		return false;
	}

	public void addSymbol(String name) {
		Symbol symbol = new Symbol(name, variableValue);
		symbolList.add(symbol);
		variableValue++;
	}
	
	public void addSymbol(String name, int value) {
		Symbol symbol = new Symbol(name, value);
		symbolList.add(symbol);
	}
}
