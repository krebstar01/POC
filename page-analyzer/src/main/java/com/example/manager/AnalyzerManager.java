package com.example.manager;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalyzerManager {

	final static Logger LOGGER = LoggerFactory.getLogger(AnalyzerManager.class);
	
	/**
	 * Only public Constructor MUST get url
	 */
	public AnalyzerManager(String url) {
		this.url = verifyUrlStartsWithHttp(url);
	}

	String url;
	String pageTitle;
	String htmlVersion = "";
	int numberOfInternalLinks;
	int numberOfExternalLinks;
	int numberOfInaccessibleLinks;
	boolean containLoginForm;
	String headerCount;

	/**
	 * if user does not pass along http / https this method appends it to the
	 * url assuring we don't get a malformed url and illegal argument exception
	 */
	public String verifyUrlStartsWithHttp(String url) {
		String result = "";

		if (url != null && !url.trim().equals("")) {
			if (url.toLowerCase().startsWith("http")) {
				result = url;
			} else {
				result = "http://" + url;
			}
		}
		return result;
	}

	/**
	 * for internal use only get JSOUP document from passed in url
	 */
	public Document getJsoupDocument() throws BadRequestException {
		Document doc = null;
		try {
			doc = Jsoup.connect(this.url).get();
		}
		catch (UnknownHostException e) {
			String message = "The URL " + this.url + " is not accessable and may not be reachable";
			LOGGER.error(e.getCause() + " : " + message);
			throw new BadRequestException(message);
		}
		
		catch (IOException e) {
			String message = "The URL " + this.url + " could not be read";
			LOGGER.error(e.getCause() + " : " + message);
		}

		return doc;
	}

	/**
	 * @return String HTML version as: "HTML 4", "HTML 5" or
	 *         "HTML Version Unknown" In HTML 4.01, the <!DOCTYPE> declaration
	 *         refers to a DTD, because HTML 4.01 was based on SGML. HTML5 is
	 *         not, has not DTD ref!
	 * 
	 */
	public String getHtmlVersion() {
		String result = "";
		
		Document document = getJsoupDocument();
		
		if (document != null) {
			List<Node> nodes = document.childNodes();
			for (Node node : nodes) {
				if (node instanceof DocumentType) {
					DocumentType documentType = (DocumentType) node;
					String docValue = documentType.toString();
					if (docValue != null && !docValue.trim().equals("")) {
						if (docValue.toLowerCase().contains("dtd")) {
							result = HtmlVersion.HTML4.toString();
						} else if (docValue.toLowerCase().equals("<!doctype html>")) {
							result = HtmlVersion.HTML5.toString();
						} else {
							result = HtmlVersion.UNKNOWN.toString();
						}
					}
				}
			}
		}
		return result;
	}

	public enum HtmlVersion {
		HTML4("HTML 4"), HTML5("HTML 5"), UNKNOWN("HTML Version Unknown");

		private final String htmlVersion;

		/**
		 * @param htmlVersion
		 */
		private HtmlVersion(final String htmlVersion) {
			this.htmlVersion = htmlVersion;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return htmlVersion;
		}

	};
	
	
	/**
	 * @return String passed in via constructor url that is being analyzed
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return int
	 * @param boolean
	 *            getExternalLinks true to show external false to show internal
	 *            here we assume all internal relative links include javascript
	 *            calls!
	 */
	int getAllExternalOrInternalLinks(boolean getExternalLinks) {
		ArrayList<String> results = new ArrayList<>();
		ArrayList<String> internal = new ArrayList<>();
		ArrayList<String> external = new ArrayList<>();
		Document document = getJsoupDocument();
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String[] schemes = { "http", "https" };
			UrlValidator urlValidator = new UrlValidator(schemes);
			boolean isitOK = urlValidator.isValid(link.attr("href"));

			if (isitOK) {
				external.add(link.attr("href"));
			} else {
				internal.add(link.attr("href"));
			}
		}

		if (getExternalLinks) {
			results.addAll(external);
		} else {
			results.addAll(internal);
		}
		return results.size();
	}

	int getAllInaccessableLinks() {
		ArrayList<String> results = new ArrayList<>();
		Document document = getJsoupDocument();
		Elements links = document.select("a[href]");
		for (Element link : links) {
			String linktoCheck = link.attr("abs:href");
			if (linktoCheck != null) {
				try {
					Jsoup.connect(linktoCheck);
				} catch (Exception e) {
					results.add(linktoCheck);
					LOGGER.debug(e.getCause() + " :linktoCheck  is not accessable" + linktoCheck);
				}
			}
		}
		return results.size();
	}

	/**
	 * @return String results shown as list elements JSOUP tag/element/attribute
	 *         retrieval results from Document case insensitive
	 */
	public String assembleHeaders() {
		String results = "";

		Document document = getJsoupDocument();
		Elements hTags = document.select("h1, h2, h3, h4, h5, h6");

		Elements h1Tags = hTags.select("h1");
		if (!h1Tags.isEmpty()) {
			results += "<li> H1 : " + h1Tags.size() + " ";
		}
		Elements h2Tags = hTags.select("h2");
		if (!h2Tags.isEmpty()) {
			results += "<li> H2 : " + h2Tags.size() + " ";
		}
		Elements h3Tags = hTags.select("h3");
		if (!h3Tags.isEmpty()) {
			results += "<li> H3 : " + h3Tags.size() + " ";
		}

		Elements h4Tags = hTags.select("h4");
		if (!h4Tags.isEmpty()) {
			results += "<li> H4 : " + h4Tags.size() + " ";
		}

		Elements h5Tags = hTags.select("h5");
		if (!h5Tags.isEmpty()) {
			results += "<li> H5 : " + h5Tags.size() + " ";
		}

		Elements h6Tags = hTags.select("h6");
		if (!h6Tags.isEmpty()) {
			results += "<li> H6 : " + h6Tags.size() + " ";
		}
		return results;

	}

	/**
	 * @return String returns JSOUP document title
	 */
	public String getPageTitle() {
		String result = "";
		Document document = getJsoupDocument();
		if (document != null) {
			result = getJsoupDocument().title();
		}
		return result;
	}

	/**
	 * @return int invokes getAllExternalOrInternalLinks
	 */
	public int getNumberOfInternalLinks() {
		return getAllExternalOrInternalLinks(false);
	}

	/**
	 * @return int invokes getAllExternalOrInternalLinks
	 */
	public int getNumberOfExternalLinks() {
		return getAllExternalOrInternalLinks(true);
	}

	/**
	 * @return int invokes getAllAccessableLinks
	 */
	public int getNumberOfInaccessibleLinks() {
		return getAllInaccessableLinks();
	}

	/**
	 * @return boolean there is no deterministic way to check absolutely that a
	 *         form is a login form. i.e. the submit button might be named
	 *         "Login" in English pages only. Lacking any other specifications,
	 *         here we only check for the element type "form" and the attribute
	 *         "action" example: http://www.echoecho.com/htmlforms12.htm
	 */
	public boolean isContainLoginForm() {
		Elements results = null;
		Document document = getJsoupDocument();
		if (document != null) {
			results = getJsoupDocument().getElementsByTag("form");

			for (Element el : results) {
				if (el.hasAttr("action")) {
					return true;
				}
			}
		}
		return containLoginForm;
	}

	/**
	 * @return String invokes assembleHeaders see javadocs for assembleHeaders
	 *         for more info
	 */
	public String getHeaderCount() {
		return assembleHeaders();
	}

}