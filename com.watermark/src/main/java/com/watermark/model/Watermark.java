package com.watermark.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "WATERMARK")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Watermark {
	
	private int id; 
	private String content;
	private String title;
	private String author;
	private BookTopic bookTopic;
	private BaseDocument baseDocument;
	
	
    @Id
    @Column(name = "WATERMARK_ID", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "CONTENT", nullable = false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "TITLE", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "AUTHOR", nullable = false)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name = "BOOK_TOPIC", nullable = true)
	@Enumerated(EnumType.STRING)
	public BookTopic getBookTopic() {
		return bookTopic;
	}
	public void setBookTopic(BookTopic bookTopic) {
		this.bookTopic = bookTopic;
	}
	
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@MapsId
	public BaseDocument getBaseDocument() {
		return baseDocument;
	}
	public void setBaseDocument(BaseDocument baseDocument) {
		this.baseDocument = baseDocument;
	}
	

}