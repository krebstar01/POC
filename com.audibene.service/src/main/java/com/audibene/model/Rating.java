package com.audibene.model;

public enum Rating {

	SUPER("super","awesome sauce",1),
	GREAT("great","wunderbar!",2),
	GOOD("good","loved it",3),
	OK("ok","could've been better",4),
	MEH("MEH","not so crazy about it",5);
	
	
	private final String name;
	private final String description;
	private int rating;
	
	Rating(String name, String description, int rating){
		this.name = name;
		this.description = description;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	
	
	
}
