package com.watermark.model;

public enum BookTopic {
	BUSINESS("business"), SCIENCE("science"), MEDIA("media");
	
	private String value;

	private BookTopic(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public static BookTopic findBookTopic(String bookTopic){
	    for(BookTopic v : values()){
	        if( v.value.equals(bookTopic)){
	            return v;
	        }
	    }
	    return null;
	}
	
	
}