package com.example.resource;

import java.util.Collections;

import com.codahale.metrics.MetricRegistry;

import io.dropwizard.views.ViewMessageBodyWriter;
import io.dropwizard.views.freemarker.FreemarkerViewRenderer;

/**
 * Created by justin on 05.08.15.
 */
public class TestBaseApplication {

	// IMPORTANT!! freemarker MessageBodyWriter is added by default in
	// ViewBundle, but needs to be added again here for tests
	protected static ViewMessageBodyWriter viewMessageBodyWriter = new ViewMessageBodyWriter(new MetricRegistry(),
			Collections.singleton(new FreemarkerViewRenderer()));

}