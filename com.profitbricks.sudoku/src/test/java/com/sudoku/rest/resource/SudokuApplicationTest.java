package com.sudoku.rest.resource;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.sudoku.manager.SudokuManagerTest;
import com.sudoku.manager.SudokuSolutionManager;
import com.sudoku.model.SudokuResponse;

import io.dropwizard.testing.junit.ResourceTestRule;

public class SudokuApplicationTest {

	@BeforeClass
	public static void setUp() {
	}

	static final String URL_COMMA = "%2C";
	
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new SudokuApplication())
			.build();

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testTestFramework() {
		assertTrue(true);
	}

	//
	@Test
	public void testResponseSolved() {
		Client client = resources.client();
		String sudokuAsWebParam = SudokuManagerTest.SUDOKU_INCOMPLETE_VALID_01.replace(",", URL_COMMA);
		WebTarget target = client.target("/sudoku/solve?unsolvedSudoku="+sudokuAsWebParam);
		SudokuResponse response = target.request().get(SudokuResponse.class);
		String expected = SudokuManagerTest.SUDOKU_COMPLETE_VALID_01;
		String actual = response.getSolution();
		assertEquals(expected, actual);
		assertNull(response.getError());
	}

	@Test
	public void testResponseUnsolved() {
		Client client = resources.client();
		String sudokuAsWebParam = SudokuManagerTest.SUDOKU_INCOMPLETE_VALID_02.replace(",", URL_COMMA);
		WebTarget target = client.target("/sudoku/solve?unsolvedSudoku="+sudokuAsWebParam);
		SudokuResponse response = target.request().get(SudokuResponse.class);
		String actual = response.getError();
		assertEquals(SudokuSolutionManager.UNSOLVABLE_MESSAGE, actual);
		assertNull(response.getSolution());
	}
	
	
	@Test
	public void testResponseInvalidSudokuTooManyValues() {
		//Unsolved Sudoku contains one too many values  
		Client client = resources.client();
		String sudokuAsWebParam = SudokuManagerTest.SUDOKU_INCOMPLETE_INVALID_01.replace(",", URL_COMMA);
		WebTarget target = client.target("/sudoku/solve?unsolvedSudoku="+sudokuAsWebParam);
		SudokuResponse response = target.request().get(SudokuResponse.class);
		assertNull(response.getSolution());
		String actual = response.getError();
		String expected = SudokuSolutionManager.INVALID_ERROR_MESSAGE;
		assertEquals(expected, actual);
		assertNull(response.getSolution());
	}
	
	
	@Test
	public void testResponseInvalidSudokuINvalidCharacter() {
		//Unsolved Sudoku contains invalid placeholder character "zzz", instead if "x"
		Client client = resources.client();
		String sudokuAsWebParam = SudokuManagerTest.SUDOKU_INCOMPLETE_INVALID_02.replace(",", URL_COMMA);
		WebTarget target = client.target("/sudoku/solve?unsolvedSudoku="+sudokuAsWebParam);
		SudokuResponse response = target.request().get(SudokuResponse.class);
		assertNull(response.getSolution());
		String actual = response.getError();
		String expected = SudokuSolutionManager.INVALID_ERROR_MESSAGE;
		assertEquals(expected, actual);
		assertNull(response.getSolution());
	}
	
	@Test
	public void testResponseInvalidSudokuBadNumber() {
		//Unsolved Sudoku contains invalid numeric value "09"
		Client client = resources.client();
		String sudokuAsWebParam = SudokuManagerTest.SUDOKU_INCOMPLETE_INVALID_03.replace(",", URL_COMMA);
		WebTarget target = client.target("/sudoku/solve?unsolvedSudoku="+sudokuAsWebParam);
		SudokuResponse response = target.request().get(SudokuResponse.class);
		assertNull(response.getSolution());
		String actual = response.getError();
		String expected = SudokuSolutionManager.INVALID_ERROR_MESSAGE;
		assertEquals(expected, actual);
		assertNull(response.getSolution());
	}
	
	@Test
	public void testResponseInvalidSudokuNumberOutofRange() {
		//Unsolved Sudoku contains invalid numeric value "99"
		Client client = resources.client();
		String sudokuAsWebParam = SudokuManagerTest.SUDOKU_INCOMPLETE_INVALID_04.replace(",", URL_COMMA);
		WebTarget target = client.target("/sudoku/solve?unsolvedSudoku="+sudokuAsWebParam);
		SudokuResponse response = target.request().get(SudokuResponse.class);
		assertNull(response.getSolution());
		String actual = response.getError();
		String expected = SudokuSolutionManager.INVALID_ERROR_MESSAGE;
		assertEquals(expected, actual);
		assertNull(response.getSolution());
	}
	
	
	

}