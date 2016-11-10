package com.example.views;

import java.nio.charset.Charset;

import com.example.manager.AnalyzerManager;

import io.dropwizard.views.View;

/**
 * Created by justin on 06.07.15.
 */
public class ResultsView extends View {

	private AnalyzerManager analyzerManager;

	public AnalyzerManager getAnalyzerManager() {
		return analyzerManager;
	}

	public ResultsView(AnalyzerManager analyzerManager) {
		super("results.ftl", Charset.forName("UTF-8"));
		this.analyzerManager = analyzerManager;
	}
}