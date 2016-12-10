package com.sudoku.manager;

import java.util.Arrays;
import java.util.List;

public class SudokuApplicationManager {


	public boolean verifyRegexString(String sudokuToValidate) {
		List<String> items = Arrays.asList(sudokuToValidate.split(","));

		String REGEX = "[x]|[1-9]";

		for (String x : items) {
			if (!x.matches(REGEX)) {
				return false;
			}
		}

		return true;
	}

	public boolean verifyCorrectSize(String sudokuToValidate) {
		List<String> items = Arrays.asList(sudokuToValidate.split(","));
		if (items.size() != 81) {
			return false;
		}

		return true;
	}
	
	

}
