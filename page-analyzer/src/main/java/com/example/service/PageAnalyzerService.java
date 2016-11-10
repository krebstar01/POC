package com.example.service;

import com.example.config.GigAdminConfiguration;
import com.example.resource.PageAnalyzerApplication;
import com.example.views.RuntimeExceptionMapper;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;


/**
 * @author Justin Theiss
 */
public class PageAnalyzerService extends Application<GigAdminConfiguration> {

    public static void main(String[] args) throws Exception {
        new PageAnalyzerService().run(args);
    }

    @Override
    public void initialize(Bootstrap<GigAdminConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<GigAdminConfiguration>());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(GigAdminConfiguration configuration, Environment environment) {
        environment.jersey().register(new RuntimeExceptionMapper());
        environment.jersey().register(new PageAnalyzerApplication());
    }
}