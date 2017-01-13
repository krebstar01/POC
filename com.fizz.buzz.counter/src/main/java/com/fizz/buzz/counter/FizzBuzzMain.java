package com.fizz.buzz.counter;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FizzBuzzMain {

	private static final String VALIDATION_MSG_01 = "Please enter valid integers for your Start and End values."; 
	
	public static void printNumbers(int start, int end) {

			for (int i = start; i <= end; i++) {
				if ((i % 3) == 0 && (i % 5) != 0) {
					System.out.println(i + " : Fizz");
				}

				if ((i % 3) != 0 && (i % 5) == 0) {
					System.out.println(i + " : Buzz");
				}

				if ((i % 3) == 0 && (i % 5) == 0) {
					System.out.println(i + " : FizzBuzz");
				}

			}

	}
	
	
	
public static HashMap<Integer, Integer> retrieveValuesFromUser(){
	
	HashMap<Integer, Integer>  results = new HashMap<Integer, Integer>();
	
    Scanner scanner = new Scanner(System.in);
    int startValue = 0;
    int endValue = 0; 

    
    while(!(endValue > startValue)){
        System.out.print("Please enter a start value: ");

        try {
        	startValue = scanner.nextInt();	
    	} catch (InputMismatchException e) {
    		System.out.println(VALIDATION_MSG_01);
    		scanner.nextLine();
    		System.out.print("Please enter a Start value: ");
    	}
        
        System.out.print("Please enter an End value: ");
        
        try {
        	endValue = scanner.nextInt();	
    	} catch (InputMismatchException e) {
    		System.out.println(VALIDATION_MSG_01);
    		scanner.nextLine();
    		System.out.print("Please enter an End value: ");
    	}
        
        if((endValue < startValue) || (endValue == startValue)) {
        	System.out.print("End Value must be greater then Start value!  Please try again. \n");
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
		if(!results.isEmpty()){
			printNumbers(results.get(1),results.get(2));
		} else {
			System.out.println("Something must have gone drastically wrong! Please rerun the program: java -jar FizzBuzz.jar");			
		}
		
	}

}
