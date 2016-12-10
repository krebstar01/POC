package com.sudoku.manager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.IntStream;

import com.google.common.collect.Sets;

public class SudokuSolutionManager {
	
	
	
	public static final String UNSOLVABLE =  "UNSOLVABLE";
	public static final String UNSOLVABLE_MESSAGE =  "cannot be completed";
	public static final String INVALID_ERROR_MESSAGE = "Please Enter a valid sudoku string : 81 comma seperated numbers and placeholders (using the letter 'x') ";
	
	private SudokuUtilManager sudokuUtilManager = new SudokuUtilManager(); 
	

	/*
	 * internal helper method used by sudoku solving logic to give back unique possible values for a row, column or box
	 * 
	 * */
	private HashSet<String> findPossibleValues(HashSet<String> intSet) {
		HashSet<String> results = Sets.newHashSet("1", "2", "3", "4", "5", "6", "7", "8", "9");
		for (String x : intSet) {
			results.remove(x);
		}
		return results;
	}

	
	/*
	 * internal helper method used by the "doublespass" method to get (should they exist) 2 or more pairs of of possible numbers exists in a row, column or box 
	 * 
	 * */
	private HashSet<String> retrieveDouble(Collection<String> listToCheck) {
		HashSet<String> doubleSet = new HashSet<>();
		for (String x : listToCheck) {
			x = x.replace("xxx", "");
			if (x.length() == 2) {
				doubleSet.add(x);
			}
		}
		return doubleSet;
	}

	
	/*
	 * internal helper method used by the "doublespass" method to determine if a 2 or more pairs of of possible numbers exists in a row, column or box 
	 * 
	 * */
	private boolean containsPairOfDoubles(Collection<String> listToCheck) {
		String valTocheck = "";
		for (String x : listToCheck) {
			valTocheck = x.replace("xxx", "");
			if (valTocheck.length() == 2) {
				int occurrences = Collections.frequency(listToCheck, x);

				if (occurrences == 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/*
	 * internal helper method that takes comma seperate String for Sudokum and parses it into a Hasmap with the following key/values:
	 * key: position index
	 * value: given value / placeholder
	 * 
	 *  This makes life much easier when we can reference the position we want to edit 
	 * 
	 * */
	private TreeMap<Integer, String> readSudokuString(String sudokuToRead) {

		TreeMap<Integer, String> sudokuToParse = new TreeMap<Integer, String>();

		if (sudokuToRead != null && !sudokuToRead.trim().equals("")) {
			List<String> sudokuCells = Arrays.asList(sudokuToRead.split(","));

			for (int i = 1; i <= 81; i++) {
				sudokuToParse.put(new Integer(i), sudokuCells.get(i - 1));
			}
		}
		return sudokuToParse;
	}

	
	
	/*
	 * internal helper method used to extract a Sudoku column for a given Sudoku cell  
	 * 
	 * */
	private TreeMap<Integer, String> getColumnForCell(Integer cellIndex, TreeMap<Integer, String> parsedSudoku) {
		TreeMap<Integer, String> input = new TreeMap<>();

		int columnHeadIndex = 0;

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 1, 9)) {
			columnHeadIndex = cellIndex;
		} else {
			int modus = cellIndex % 9;
			if (modus == 0) {
				columnHeadIndex = 9;
			} else {
				columnHeadIndex = modus;
			}
			// if modus is 0 then column is 9!
		}

		if (columnHeadIndex > 0) {
			for (int i = columnHeadIndex; i <= 81; i = i + 9) {
				input.put(i, parsedSudoku.get(i));
			}

		}
		return input;
	}

	/*
	 * internal helper method used to extract a Sudoku row for a given Sudoku cell  
	 * 
	 * */
	private TreeMap<Integer, String> getRowForCell(Integer cellIndex, TreeMap<Integer, String> parsedSudoku) {

		TreeMap<Integer, String> input = new TreeMap<>();

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 1, 9)) {
			// System.out.println("row 1");
			input.putAll(parsedSudoku.subMap(1, 10));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 10, 18)) {
			// System.out.println("row 2");
			input.putAll(parsedSudoku.subMap(10, 19));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 19, 27)) {
			// System.out.println("row 3");
			input.putAll(parsedSudoku.subMap(19, 28));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 28, 36)) {
			// System.out.println("row 4");
			input.putAll(parsedSudoku.subMap(28, 37));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 37, 45)) {
			// System.out.println("row 5");
			input.putAll(parsedSudoku.subMap(37, 46));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 46, 54)) {
			// System.out.println("row 6");
			input.putAll(parsedSudoku.subMap(46, 55));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 55, 63)) {
			// System.out.println("row 7");
			input.putAll(parsedSudoku.subMap(55, 64));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 64, 72)) {
			// System.out.println("row 8");
			input.putAll(parsedSudoku.subMap(64, 73));
			return input;
		}

		if (sudokuUtilManager.isCellNumberBetween(cellIndex, 73, 81)) {
			input.putAll(parsedSudoku.subMap(73, 82));
			// System.out.println("row 9");
			return input;
		}

		return input;
	}

	
	/*
	 * internal helper method used to extract a Sudoku box for a given Sudoku cell  
	 * 
	 * */
	private TreeMap<Integer, String> getBoxForCell(Integer cellIndex, TreeMap<Integer, String> parsedSudoku) {
		TreeMap<Integer, String> input = new TreeMap<>();
		int[][] grids = new int[][] { { 1, 2, 3, 10, 11, 12, 19, 20, 21 }, { 4, 5, 6, 13, 14, 15, 22, 23, 24 },
				{ 7, 8, 9, 16, 17, 18, 25, 26, 27 }, { 28, 29, 30, 37, 38, 39, 46, 47, 48 },
				{ 31, 32, 33, 40, 41, 42, 49, 50, 51 }, { 34, 35, 36, 43, 44, 45, 52, 53, 54 },
				{ 55, 56, 57, 64, 65, 66, 73, 74, 75 }, { 58, 59, 60, 67, 68, 69, 76, 77, 78 },
				{ 61, 62, 63, 70, 71, 72, 79, 80, 81 } };

		for (int i = 0; i < grids.length; i++) {
			boolean isInGrid = IntStream.of(grids[i]).anyMatch(x -> x == cellIndex);
			if (isInGrid) {
				for (int x = 0; x < grids[i].length; x++) {
					input.put(grids[i][x], parsedSudoku.get(grids[i][x]));
				}

			}

		}

		return input;
	}


	
	/*
	 * internal method  that gives a first pass to solving the sudoku
	 * this alone will solve most simple sudoku puzzles, using a "brute force" approach  
	 * 
	 * */
	private TreeMap<Integer, String> reviewSudoku(String sudokuToRead) {
		TreeMap<Integer, String> parsedSudoku = readSudokuString(sudokuToRead);
		TreeMap<Integer, String> returnedBox = null;
		TreeMap<Integer, String> returnedRow = null;
		TreeMap<Integer, String> returnedColumn = null;
		Object[] singleValue = null;
		HashSet<String> intsToCheck = new HashSet<>();

		for (Entry<Integer, String> entry : parsedSudoku.entrySet()) {

			if (!sudokuUtilManager.isInteger(entry.getValue(), 10)) {

				returnedBox = getBoxForCell(entry.getKey(), parsedSudoku);
				returnedColumn = getColumnForCell(entry.getKey(), parsedSudoku);
				returnedRow = getRowForCell(entry.getKey(), parsedSudoku);

				intsToCheck.addAll(returnedBox.values());
				intsToCheck.addAll(returnedColumn.values());
				intsToCheck.addAll(returnedRow.values());

				HashSet<String> vals = findPossibleValues(intsToCheck);

				if (vals.size() == 1) {
					singleValue = vals.toArray();
					String onlyValue = singleValue[0].toString();
					parsedSudoku.put(entry.getKey(), onlyValue);
				}

				intsToCheck.clear();

			}

		}

		return parsedSudoku;
	}

	/*
	 * internal method  that gives a second pass to solving the sudoku
	 * this should solve most sudoku puzzles of medium difficulty
	 * it looks for sets of two possible numbers in a  in a row, column or box
	 * and removes those numbers from the other noted possibilities
	 * 
	 *   example:
	 *   a "worked on" row  appear as follows, where multiple digits are all possible values:
	 *   
	 *   1,29,3,4,5,629,7,8,29
	 *   
	 *   the doubles pass will remove 2 and 9 from position 6, leaving the values as "6"  
	 * 
	 * */
	private TreeMap<Integer, String> doublesPass(String sudokuToRead) {
		TreeMap<Integer, String> parsedSudoku = readSudokuString(sudokuToRead);
		TreeMap<Integer, String> returnedBox = null;
		TreeMap<Integer, String> returnedRow = null;
		TreeMap<Integer, String> returnedColumn = null;
		HashSet<String> intsToCheck = new HashSet<>();
		for (Entry<Integer, String> entry : parsedSudoku.entrySet()) {

			if (!sudokuUtilManager.isInteger(entry.getValue(), 10)) {

				returnedBox = getBoxForCell(entry.getKey(), parsedSudoku);
				returnedColumn = getColumnForCell(entry.getKey(), parsedSudoku);
				returnedRow = getRowForCell(entry.getKey(), parsedSudoku);

				intsToCheck.addAll(returnedBox.values());
				intsToCheck.addAll(returnedColumn.values());
				intsToCheck.addAll(returnedRow.values());

				HashSet<String> vals = findPossibleValues(intsToCheck);

				if (vals.size() > 1) {

					String choiceSet = "xxx" + vals.toString().replaceAll(",", "").replaceAll(" ", "")
							.replaceAll("\\[", "").replaceAll("\\]", "") + "xxx";

					parsedSudoku.put(entry.getKey(), choiceSet);

					HashSet<String> doubleSetBox = retrieveDouble(returnedBox.values());
					HashSet<String> doubleSetCol = retrieveDouble(returnedColumn.values());
					HashSet<String> doubleSetRow = retrieveDouble(returnedRow.values());

					String doubleBoxToCompare = doubleSetBox.toString().replaceAll("\\[", "").replaceAll("\\]", "");
					String doubleColToCompare = doubleSetCol.toString().replaceAll("\\[", "").replaceAll("\\]", "");
					String doubleRowToCompare = doubleSetRow.toString().replaceAll("\\[", "").replaceAll("\\]", "");

					for (Entry<Integer, String> boxEntry : returnedBox.entrySet()) {

						for (String x : doubleSetBox) {
							if (containsPairOfDoubles(returnedBox.values())) {
								Integer keyDouble = boxEntry.getKey();
								String valueDouble = boxEntry.getValue();

								if (!valueDouble.replaceAll("xxx", "").equals(doubleBoxToCompare)) {

									for (int i = 0; i < doubleBoxToCompare.length(); i++) {
										Character c = doubleBoxToCompare.charAt(i);
										valueDouble = valueDouble.replaceAll(c.toString(), "");
									}

								}

								if (valueDouble.replaceAll("xxx", "").length() == 1) {
									valueDouble = valueDouble.replaceAll("xxx", "");
								}

								parsedSudoku.put(keyDouble, valueDouble);
							}
						}
					}

					for (Entry<Integer, String> colEntry : returnedColumn.entrySet()) {
						for (String x : doubleSetCol) {

							if (containsPairOfDoubles(returnedColumn.values())) {
								Integer keyDouble = colEntry.getKey();
								String valueDouble = colEntry.getValue();

								if (!valueDouble.replaceAll("xxx", "").equals(doubleColToCompare)) {

									for (int i = 0; i < doubleColToCompare.length(); i++) {
										Character c = doubleColToCompare.charAt(i);
										valueDouble = valueDouble.replaceAll(c.toString(), "");
									}

									// }

								}

								if (valueDouble.replaceAll("xxx", "").length() == 1) {
									valueDouble = valueDouble.replaceAll("xxx", "");
								}
								parsedSudoku.put(keyDouble, valueDouble);
							}
						}
					}

					for (Entry<Integer, String> rowEntry : returnedRow.entrySet()) {

						for (String x : doubleSetRow) {
							if (containsPairOfDoubles(returnedRow.values())) {
								Integer keyDouble = rowEntry.getKey();
								String valueDouble = rowEntry.getValue();

								if (!valueDouble.replaceAll("xxx", "").equals(doubleRowToCompare)) {

									for (int i = 0; i < doubleRowToCompare.length(); i++) {
										Character c = doubleRowToCompare.charAt(i);
										valueDouble = valueDouble.replaceAll(c.toString(), "");
									}

								}

								if (valueDouble.replaceAll("xxx", "").length() == 1) {
									valueDouble = valueDouble.replaceAll("xxx", "");
								}
								parsedSudoku.put(keyDouble, valueDouble);
							}
						}

					}

				}

				intsToCheck.clear();

			}

		}

		return parsedSudoku;
	}



	/**
	 * @return String
	 * @param String sudokuToRead (a string of 81 numbers or place holders, being the letter "x", separated by commas)
	 * 
	 * this method will make two passes to try and solve a sudoku:
	 * 1) a simple brute force attempt (good for simple sudokus)
	 * 2) a "doubles pass" (good for medium difficulty  sudokus)
	 * */
	public String solveSudoku(String sudokuToRead) {
		TreeMap<Integer, String> sudokuToSolve = reviewSudoku(sudokuToRead);

		String resultsSoFar = "";

		int unsolvableCount = 10;
		int i = 0;

		while (sudokuToSolve.toString().contains("x")) {
			resultsSoFar = sudokuToSolve.values().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ",
					"");
			sudokuToSolve = reviewSudoku(resultsSoFar);
//			System.out.println(sudokuToSolve.values());
			i++;
			if (i == unsolvableCount) {
//				System.out.println(sudokuToSolve.values().toString().replaceAll("\\[", "").replaceAll("\\]", "")
//						.replaceAll(" ", ""));
				i = 0;
				break;
			}

		}

		while (sudokuToSolve.toString().contains("x")) {
			resultsSoFar = sudokuToSolve.values().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ",
					"");
			sudokuToSolve = doublesPass(resultsSoFar);
//			System.out.println(sudokuToSolve.values());
			i++;
			if (i == unsolvableCount) {
//				System.out.println(sudokuToSolve.values().toString().replaceAll("\\[", "").replaceAll("\\]", "")
//						.replaceAll(" ", ""));

				return UNSOLVABLE;
			}

		}

		return sudokuToSolve.values().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");

	}

	

}
