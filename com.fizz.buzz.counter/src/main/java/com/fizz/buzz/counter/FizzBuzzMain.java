package com.fizz.buzz.counter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FizzBuzzMain {

	private static final String VALIDATION_MSG_01 = "Please enter valid integers for your Start and End values.";
	private static final String START_VALUE_MSG = "Please enter a Start value: ";
	private static final String END_VALUE_MSG = "Please enter an End value: ";
	private static final String TRY_AGAIN_MSG = "End Value must be greater then Start value!  Please try again. \n";
	private static final String DRASTICALLY_WRONG_MSG = "Something must have gone drastically wrong! Please rerun the program: java -jar FizzBuzz.jar";

	
	public static void createFile(String textTpAppend){
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("FizzBuzz_Output.txt", "UTF-8");
			writer.append(textTpAppend);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.print("FizzBuzz.jar wants to create a file in the directory where it's sitting! ");
			System.out.print("Please check to all that your Operating System will allow this action!");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.print("FizzBuzz.jar wants to create a file formatted in UTF-8!");
			System.out.print("Please check to all that your Operating System will support this format!");

			e.printStackTrace();
		}
		
		
	}
	
	
	public static void printNumbers(int start, int end) {

		
		
		String message = "";
		
		for (int i = start; i <= end; i++) {
			// add count to message
			
			// get modus of 5 only, if 0 show Fizz
			//prints Text in place of number
			if ((i % 3) == 0 && (i % 5) != 0) {
				message += "Fizz \n";
			} else if ((i % 3) != 0 && (i % 5) == 0) {
				message += (i + " Buzz \n");
			} else if ((i % 3) == 0 && (i % 5) == 0) {
				message += (i + " FizzBuzz \n");
			} else {
				message += (i + " \n");
			}
			
		}
		System.out.println(message);
		createFile(message);

	}

	public static HashMap<Integer, Integer> retrieveValuesFromUser() {

		HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();

		Scanner scanner = new Scanner(System.in);
		int startValue = 0;
		int endValue = 0;

		//validation condition set to while loop.. will not succeed until start value added that is less then end value
		while (!(endValue > startValue)) {
			System.out.print(START_VALUE_MSG);

			try {
				//validation scanner exception caught if non integer added
				startValue = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(VALIDATION_MSG_01);
				scanner.nextLine();
				System.out.print(START_VALUE_MSG);
			}

			System.out.print(END_VALUE_MSG);

			try {
				//validation scanner exception caught if non integer added
				endValue = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(VALIDATION_MSG_01);
				scanner.nextLine();
				System.out.print(END_VALUE_MSG);
			}

			if ((endValue < startValue) || (endValue == startValue)) {
				System.out.print(TRY_AGAIN_MSG);
			}
		}

		scanner.nextLine();

		scanner.close();

		results.put(1, startValue);
		results.put(2, endValue);

		return results;
	}

	public static void main(String[] args) {
		HashMap<Integer, Integer> results = retrieveValuesFromUser();
		if (!results.isEmpty()) {
			printNumbers(results.get(1), results.get(2));
		} else {
			//If something altogether unexpected happens, cue the user to try again
			System.out.println(DRASTICALLY_WRONG_MSG);
		}

	}

}
