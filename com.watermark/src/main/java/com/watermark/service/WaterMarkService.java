package com.watermark.service;

import com.watermark.config.WatermarkConfiguration;
import com.watermark.rest.resource.WatermarkStatusApplication;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class WaterMarkService extends Application<WatermarkConfiguration> {

	public static void main(String[] args) throws Exception {
		new WaterMarkService().run(args);
	}

	@Override
	public void run(WatermarkConfiguration configuration, Environment environment) throws Exception {
		//TODO
		// environment.jersey().register(new RuntimeExceptionMapper());
		environment.jersey().register(new WatermarkStatusApplication());
	}

}