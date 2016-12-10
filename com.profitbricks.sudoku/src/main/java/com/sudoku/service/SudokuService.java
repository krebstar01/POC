package com.sudoku.service;

import com.sudoku.config.SudokuConfiguration;
import com.sudoku.rest.resource.SudokuApplication;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SudokuService extends Application<SudokuConfiguration>{
//persistence
	// http://stackoverflow.com/questions/28155220/hsqldb-persist-in-memory-database
	
	
	public static void main(String[] args) throws Exception {
		new SudokuService().run(args);
	}

	@Override
	public void initialize(Bootstrap<SudokuConfiguration> bootstrap) {
	    bootstrap.addBundle(new SwaggerBundle<SudokuConfiguration>() {
	    	
	        @Override
	        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SudokuConfiguration configuration) {
	            return configuration.swaggerBundleConfiguration;
	        }
	    });
	}
	
	
	
	
	@Override
	public void run(SudokuConfiguration configuration, Environment environment) throws Exception {
		
		
		
		environment.jersey().register(new SudokuApplication());
	}
	
	
}
