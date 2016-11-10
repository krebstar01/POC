package com.watermark.model;

public enum ProcessingStatus {
	SUBMITTED("submitted","Your Request has been submitted.  Please try back again later"), UNDERWAY("underway","Your Request is being processed.  Please try back again later"), FINISHED("finished","Your request has been processed");
	
	private String value;
	private String message;

	private ProcessingStatus(String value, String message) {
		this.value = value;
		this.message = message;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public static ProcessingStatus findStatus(String status){
	    for(ProcessingStatus v : values()){
	        if( v.value.equals(status)){
	            return v;
	        }
	    }
	    return null;
	}
	
}