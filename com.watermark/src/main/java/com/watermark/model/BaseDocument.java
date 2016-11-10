package com.watermark.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

//see http://www.thejavageek.com/2014/05/14/jpa-single-table-inheritance-example/
//for more info on SINGLE_TABLE and @MappedSuperclass


@Table(name = "BASE_DOCUMENT")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DOCUMENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("baseDocument") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742417229958674544L;

	private int id;
	private ProcessingStatus processingStatus;
	private Watermark watermark;
	private String ticketId;
	private String content; 
	private String title;
	private String author;

	
	
	@Id
	@Column(name = "DOCUMENT_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "PROCESSING_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	public ProcessingStatus getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(ProcessingStatus processingStatus) {
		this.processingStatus = processingStatus;
	}

	@Column(name = "TICKET_ID", nullable = false)
	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = com.watermark.model.Watermark.class )
	@JoinColumn(name = "WATERMARK_ID")
	@PrimaryKeyJoinColumn
	public Watermark getWatermark() {
		return watermark;
	}

	public void setWatermark(Watermark watermark) {
		this.watermark = watermark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	

}