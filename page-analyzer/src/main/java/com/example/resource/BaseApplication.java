package com.example.resource;

import javax.ws.rs.BadRequestException;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Justin Theiss
 */
public class BaseApplication {

	final static Logger LOGGER = LoggerFactory.getLogger(BaseApplication.class);

	void validateUrl(String url) throws BadRequestException {
		if (url != null && !url.trim().equals("")) {
			String[] schemes = { "http", "https" };
			UrlValidator urlValidator = new UrlValidator(schemes);
			if (!urlValidator.isValid(url)) {
				//if not null and bad..getClass().
				String message = "Please enter a valid URL! " + url;
				LOGGER.error("BadRequestException " + message);
				throw new BadRequestException(message);
			}
			
		} else {
			//if null
			String message = "Please enter a valid URL! Your entry was empty";
			LOGGER.error("BadRequestException " + message);
			throw new BadRequestException(message);
		}
	}

}