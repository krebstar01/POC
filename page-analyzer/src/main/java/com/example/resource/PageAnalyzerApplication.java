package com.example.resource;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.manager.AnalyzerManager;
import com.example.views.EntryView;
import com.example.views.ResultsView;

/**
 * Created by justin theiss
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class PageAnalyzerApplication extends BaseApplication {

	final static Logger LOGGER = LoggerFactory.getLogger(PageAnalyzerApplication.class);
	
	@GET
	@Path("/entry")
	public EntryView entryView() {
		return new EntryView();
	}

	@POST
	@Path("/results")
	public ResultsView resultsView(@FormParam("url") String url) {
		
		
		AnalyzerManager manager = null;
		
		try {
			manager = new AnalyzerManager(url);
			validateUrl(manager.getUrl());	
			manager.getJsoupDocument();
		} catch (BadRequestException e) {
			LOGGER.error(e.getCause() + " : bad url: " + url);
			throw e;
		}
		
		return new ResultsView(manager);
	}
}