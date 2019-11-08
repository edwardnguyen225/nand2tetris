package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.controller.Controller;
import exception.MyException;
import service.InstructionService;

public class App {

	public static void main(String[] argsStrings) throws MyException, Exception, IOException {
		// GET FILE PATH
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("Enter file path:");
		String filePathIn = scanner1.nextLine();
		if (!filePathIn.contains(".asm")) {
			thowInvalidFile();
		}
		
		// CREAT OUTPUT FILE PATH
		String filePathOut = filePathIn.replace("asm", "hack");
		
		// READ AND TRANSLATE FILE
		Controller controller = new Controller();
		InstructionService instructionService = new InstructionService(controller);
		File fileIn = new File(filePathIn),
			 fileOut = new File(filePathOut);
		FileWriter fileWriter = new FileWriter(fileOut);
		Scanner scanner2 = new Scanner(fileIn);
		// Read all line to find defied symbol between ()
		List<String> lineList = new ArrayList<String>();
		String str;
		int lineCounter = 0;
		while (scanner2.hasNextLine()){
			str = (String) scanner2.nextLine();
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
		scanner1.close();
		scanner2.close();
		fileWriter.close();
		System.out.println("Write file successfully");
	}

	private static void thowInvalidFile() throws MyException {
		throw new MyException("invalid file");
	}
}
