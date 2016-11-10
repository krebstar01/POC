package com.audibene.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class AudibeneConfiguration extends Configuration{
	
	
	@Valid
    @NotNull    
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	@JsonProperty("database")
	public DataSourceFactory getDatabase() {
		return dataSourceFactory;
	}

	@JsonProperty("database")
	public void setDatabase(DataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}
	
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
	
}