package com.sudoku.manager;

public class SudokuUtilManager {
	
	public boolean isInteger(String s, int radix) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1)
					return false;
				else
					continue;
			}
			if (Character.digit(s.charAt(i), radix) < 0)
				return false;
		}
		return true;
	}
	
	
	public boolean isCellNumberBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}
	



}
