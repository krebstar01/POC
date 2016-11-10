package com.watermark.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Book")
public class Book extends BaseDocument{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1970864658294379723L;
	
	@Column(name = "PROCESSING_STATUS", nullable = true)
	private BookTopic topic;
	
	public BookTopic getTopic() {
		return topic;
	}
	public void setTopic(BookTopic topic) {
		this.topic = topic;
	}

}