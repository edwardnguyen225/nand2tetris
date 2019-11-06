package service;

import javax.xml.stream.events.ProcessingInstruction;

import data.controller.Controller;
import exception.MyException;

public class InstructionService {
	Controller controller = new Controller();
	String instruction = "";
	int lineCounter = 0;
	// i __ __ a c1 c2 c3 c4 c5 c6 d1 d2 d3 j1 j2 j3
	// 0 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15

	public InstructionService(Controller _controller) {
		controller = _controller;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String translateToBinary(String instructionIn) throws Exception {
		this.instruction = processingInstruction(instructionIn);

		if (instruction.isEmpty() || instruction.equals(""))
			return "";

		String binaryCode;
		if (instruction.startsWith("@"))
			binaryCode = translateAInStruction(instruction);
		else
			binaryCode = translateCInstruction(instruction);

		lineCounter++; // only when translated
		return binaryCode;
	}

	public String processingInstruction(String str) {
		str = eraseAllSpaces(str);
		if (instruction.contains("//"))
			str = eraseComment(str);
		return str;
	}

	public String eraseAllSpaces(String str) {
		char[] strArray = str.toCharArray();  
        StringBuffer stringBuffer = new StringBuffer();  
        for (int i = 0; i < strArray.length; i++) {  
            if ((strArray[i] != ' ') && (strArray[i] != '\t')) {  
                stringBuffer.append(strArray[i]);  
            }  
        }  
        str = stringBuffer.toString();
        return str;
	}

	public String eraseComment(String str) {
		StringBuilder tmpStr = new StringBuilder(str);
		int index = tmpStr.indexOf("//");
		if (index != -1) // got issue here
			tmpStr.delete(index, tmpStr.length());
		str = tmpStr.toString();
		return str;
	}

	String translateAInStruction(String str) {
		String binary = "0";

		str = str.substring(1); //erase '@'
		int value = 0;
		if (isNumeric(str))
			value = Integer.parseInt(str);
		else {
			if (controller.isSymbolExisted(str)) {
				value = controller.getValueOfSymbol(str);
			}
			else {
				controller.addSymbol(str);
				value = controller.getValueOfSymbol(str);
			}
		}
		binary += value2binary15bits(value);
		return binary;
	}

	String translateCInstruction(String str) throws Exception {
		StringBuffer binary = new StringBuffer("1110000000000000");

		String tmp, binaryTmp;

		// Process destination
		if (str.contains("=")) {
			tmp = str.substring(0, str.indexOf('='));
			binaryTmp = controller.getDestBinary(tmp);
			if (binaryTmp == "-1")
				throw new MyException("invalid destination Instruction");
			binary.replace(10, 13, binaryTmp);
			str = str.substring(str.indexOf('=') + 1);
		}

		// Process jump
		if (str.contains(";")) {
			tmp = str.substring(str.indexOf(';') + 1);
			binaryTmp = controller.getJumpBinary(tmp);
			if (binaryTmp == "-1")
				throw new MyException("invalid jump Instruction");
			binary.replace(13, 16, binaryTmp);
			str = str.substring(0, str.indexOf(';'));
		}

		// Process comp
		if (str.contains("M"))
			binary.replace(3, 4, "1");
		binaryTmp = controller.getCompBinary(str);
		if (binaryTmp == "-1")
			throw new MyException("invalid comp Instruction");
		binary.replace(04, 10, binaryTmp);

		return binary.toString();
	}

	String value2binary15bits(int value) {
		String tmp = "";
		int pow2 = (int) Math.pow(2, 14), flagOdd = 0;
		if (value % 2 != 0) {
			flagOdd = 1;
			value--;
		}
		for (int i = 0; i < 14; i++) {
			if (value >= pow2 && value != 0) {
				tmp += '1';
				value -= pow2;
			} else {
				tmp += '0';
			}

			pow2 /= 2;
		}
		if (flagOdd == 1)
			tmp += '1';
		else
			tmp += '0';
		return tmp;
	}

	boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
