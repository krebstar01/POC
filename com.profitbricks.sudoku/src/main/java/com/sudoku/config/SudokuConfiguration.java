package com.sudoku.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SudokuConfiguration extends Configuration{
	

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
	
}