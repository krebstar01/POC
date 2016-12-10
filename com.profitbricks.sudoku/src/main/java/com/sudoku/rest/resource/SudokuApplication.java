package com.sudoku.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sudoku.manager.SudokuApplicationManager;
import com.sudoku.manager.SudokuSolutionManager;
import com.sudoku.model.SudokuResponse;

import io.swagger.annotations.Api;

@Path("/sudoku")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class SudokuApplication {

	SudokuSolutionManager solutionManager = new SudokuSolutionManager();
	SudokuApplicationManager applicationManager = new SudokuApplicationManager();

	@GET
	@Path("/solve")
	public SudokuResponse solveSudoku(@QueryParam("unsolvedSudoku") String unsolvedSudoku) {

		SudokuResponse response = new SudokuResponse();
		
		
		
		if (unsolvedSudoku == null || unsolvedSudoku.trim().equals("")) {
			response.setError(SudokuSolutionManager.INVALID_ERROR_MESSAGE);
			return response;
		}

		if (!applicationManager.verifyCorrectSize(unsolvedSudoku)) {
			response.setError(SudokuSolutionManager.INVALID_ERROR_MESSAGE);
			return response;
		}

		if (!applicationManager.verifyRegexString(unsolvedSudoku)) {
			response.setError(SudokuSolutionManager.INVALID_ERROR_MESSAGE);
			return response;
			
		}
		
		String solution = solutionManager.solveSudoku(unsolvedSudoku);
		if(solution.equals(SudokuSolutionManager.UNSOLVABLE)){
			response.setError(SudokuSolutionManager.UNSOLVABLE_MESSAGE);
			return response;
		}

		response.setSolution(solutionManager.solveSudoku(unsolvedSudoku));
		return response;
	}

}