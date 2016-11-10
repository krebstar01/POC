package com.watermark.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Journal")
public class Journal extends BaseDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3170245217801672112L;

}