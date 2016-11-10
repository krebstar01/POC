package com.example.resource;

import org.junit.Test;

import com.example.manager.AnalyzerManager;
import com.vividsolutions.jts.util.Assert;

/**
 * Created by justin on 07.07.15.
 */
public class TestAnalyzerManager {

	static final String URL_01 = "http://www.bbalbachiara.info/";
	static final String URL_02 = "http://www.cnn.com";
	static final String URL_03 = "www.cnn.com";
	static final String URL_04 = "http://www.yaddayaddayaddasomeradomNonsense.de";
	static final String URL_05_BAD = "http:www..com";

	@Test
	public void testGetHTML4() {
		AnalyzerManager analyzerManager = new AnalyzerManager(URL_01);
		String expectedValue = AnalyzerManager.HtmlVersion.HTML4.toString();
		String actualValue = analyzerManager.getHtmlVersion();
		Assert.equals(expectedValue, actualValue);
	}

	@Test
	public void testGetHTML5() {
		AnalyzerManager analyzerManager = new AnalyzerManager(URL_02);
		String expectedValue = AnalyzerManager.HtmlVersion.HTML5.toString();
		String actualValue = analyzerManager.getHtmlVersion();
		Assert.equals(expectedValue, actualValue);
	}

	@Test
	public void testNoHttpInUrl() {
		AnalyzerManager analyzerManager = null;
		try {
			analyzerManager = new AnalyzerManager(URL_03);
			String expectedValue = AnalyzerManager.HtmlVersion.HTML5.toString();
			String actualValue = analyzerManager.getHtmlVersion();
			Assert.equals(expectedValue, actualValue);
		} catch (Exception e) {
			Assert.isTrue(false);
		}
	}

	@Test
	public void testNonExisetntUrl() {
		new AnalyzerManager(URL_04);
	}

	@Test
	public void testBadUrl() {
		new AnalyzerManager(URL_05_BAD);
	}

}