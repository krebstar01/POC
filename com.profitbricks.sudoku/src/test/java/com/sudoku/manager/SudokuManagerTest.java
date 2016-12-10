package com.sudoku.manager;

import static org.junit.Assert.*;
import org.junit.Test;

public class SudokuManagerTest {

	
	SudokuSolutionManager manager = new SudokuSolutionManager();
	SudokuApplicationManager applicationManager = new SudokuApplicationManager();
	
	public final static String SUDOKU_COMPLETE_VALID_01 =   "4,3,5,2,6,9,7,8,1,6,8,2,5,7,1,4,9,3,1,9,7,8,3,4,5,6,2,8,2,6,1,9,5,3,4,7,3,7,4,6,8,2,9,1,5,9,5,1,7,4,3,6,2,8,5,1,9,3,2,6,8,7,4,2,4,8,9,5,7,1,3,6,7,6,3,4,1,8,2,5,9";
	public final static String SUDOKU_INCOMPLETE_VALID_01 = "x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
	
	//DIFFICULT level Sudoku, taken from New York Post Plains, Trains and Sudoku 
	public final static String SUDOKU_INCOMPLETE_VALID_02 = "x,x,x,x,x,x,x,x,x,7,x,x,x,x,4,8,x,x,3,5,x,x,8,2,1,x,x,5,1,x,x,x,3,6,x,x,8,x,x,x,x,x,x,x,9,x,x,2,5,x,x,x,1,7,x,x,9,3,7,x,x,4,1,x,x,1,2,x,x,x,x,3,x,x,x,x,x,x,x,x,x";
	
	
	public final static String SUDOKU_INCOMPLETE_INVALID_01 = "x,x,x,2,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
	public final static String SUDOKU_INCOMPLETE_INVALID_02 = "zzz,x,x,2,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
	public final static String SUDOKU_INCOMPLETE_INVALID_03 = "x,x,x,02,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
	public final static String SUDOKU_INCOMPLETE_INVALID_04 = "x,x,x,99,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";

	public final static String UNSOLVABLE = "UNSOLVABLE";
	
	
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_01 = "x,x,x,2,6,x,7,x,1";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_02 = "6,8,x,x,7,x,x,9,x";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_03 = "1,9,x,x,x,4,5,x,x";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_04 = "8,2,x,1,x,x,x,4,x";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_05 = "x,x,4,6,x,2,9,x,x";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_06 = "x,5,x,x,x,3,x,2,8";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_07 = "x,x,9,3,x,x,x,7,4";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_08 = "x,4,8,9,5,x,x,3,6";
//	public final static String SUDOKU_INCOMPLETE_VALID_ROW_09 = "7,x,3,x,1,8,x,x,x";
	
	@Test
	public void testEasySudoku(){
		String actual = manager.solveSudoku(SUDOKU_INCOMPLETE_VALID_01);
		String expected = SUDOKU_COMPLETE_VALID_01;
		assertEquals(expected, actual);
	}
	
	
	//note this one is currently unsolvable given my current implementation!
	@Test
	public void testUnsolvableSudoku(){
		String actual = manager.solveSudoku(SUDOKU_INCOMPLETE_VALID_02);
		String expected = UNSOLVABLE;
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testRegexValidSudoku(){
		boolean actual = applicationManager.verifyRegexString(SUDOKU_INCOMPLETE_VALID_01);
		assertTrue(actual);
	}
	
	@Test
	public void testRegexInValidSudokuTooLong(){
		boolean actual = applicationManager.verifyCorrectSize(SUDOKU_INCOMPLETE_INVALID_01);
		assertFalse(actual);
	}
	
	@Test
	public void testRegexInValidSudoku(){
		boolean actual = applicationManager.verifyRegexString(SUDOKU_INCOMPLETE_INVALID_02);
		assertFalse(actual);
	}
	
	@Test
	public void testRegexInValidSudokuBadNumber(){
		boolean actual = applicationManager.verifyRegexString(SUDOKU_INCOMPLETE_INVALID_03);
		assertFalse(actual);
	}
	
	@Test
	public void testRegexInValidSudokuBadNumber2(){
		boolean actual = applicationManager.verifyRegexString(SUDOKU_INCOMPLETE_INVALID_04);
		assertFalse(actual);
	}

}





