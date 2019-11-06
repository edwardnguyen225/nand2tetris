package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.controller.Controller;
import service.InstructionService;

public class main {

	public static void main(String[] argsStrings) throws Exception {
		// TODO Auto-generated method stub
		// GET FILE PATH
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter file path:");
		String filePathIn = scanner.nextLine();
		//filePathIn.replaceAll("\\", "\\\\");
		
		// CREAT OUTPUT FILE PATH
		String filePathOut = filePathIn.replace("asm", "hack");
		
		// READ AND TRANSLATE FILE
		Controller controller = new Controller();
		InstructionService instructionService = new InstructionService(controller);
		File fileIn = new File(filePathIn),
			 fileOut = new File(filePathOut);
		FileWriter fileWriter = new FileWriter(fileOut);
		scanner = new Scanner(fileIn);
		String binary;
		// Read all line to find defied symbol between ()
		List<String> lineList = new ArrayList<String>();
		String str;
		int lineCounter = 0;
		while (scanner.hasNextLine()){
			str = (String) scanner.nextLine();
			str = instructionService.eraseComment(str);
			if (str.isEmpty())
				continue;
			else if (str.startsWith("(")) {
				String name = str.substring(1, str.indexOf(")"));
				controller.addSymbol(name, lineCounter);
				continue;
			}
			lineList.add(str);
			lineCounter++;
		}
		
		for (String string : lineList) {
			str = instructionService.translateToBinary(string);
			if (str != "")
				fileWriter.write(str + "\n");
		}
		fileWriter.close();
		System.out.println("Write file successfully");
	}
}
