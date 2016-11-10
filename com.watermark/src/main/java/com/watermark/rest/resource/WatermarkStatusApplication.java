package com.watermark.rest.resource;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.watermark.manager.WatermarkManager;
import com.watermark.model.BaseDocument;
import com.watermark.model.BookTopic;
import com.watermark.model.Watermark;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class WatermarkStatusApplication {

	private WatermarkManager watermarkManager = new WatermarkManager();

	
	

	public WatermarkStatusApplication() {
		super();
	}
	
	public WatermarkStatusApplication(WatermarkManager watermarkManager) {
		super();
		this.watermarkManager = watermarkManager;
	}


	private BookTopic verifyBookTopic(String topic){
		BookTopic results = null;
		if(topic!=null && !topic.trim().equals("")) {
		topic = topic.toLowerCase();
		results = BookTopic.findBookTopic(topic);
		}
		
		return results;
	}
	
	

	
	
	@POST
	@Path("/requestTicket")
	public HashMap<String, String> retrieveTicketForWatermarkRequest(@FormParam("content") String content,
			@FormParam("title") String title, @FormParam("author") String author, @FormParam("topic") String topic) {

		if ((content == null || content.trim().equals("")) || 
			(title == null || title.trim().equals("")) || 
			(author == null || author.trim().equals("")))
		{
	           String message = " The following values are required in order to process a document: a) content b) title c) author";
	           message = watermarkManager.generateCustomErrorMessageBadRequest(message);
	            
	            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
	                    .entity(Status.BAD_REQUEST + message).build());
	            
		}
		
		BookTopic bookTopic = verifyBookTopic(topic);
		if(topic!=null && !topic.trim().equals("") && bookTopic == null){
			

	           String message = "If you have chosen to watermark a book, please choose from one of the following three topics: ";
	           message +=" business, science, media";
	           message = watermarkManager.generateCustomErrorMessageBadRequest(message);		
	            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
	                    .entity(message).build());
		}
		return watermarkManager.retrieveTicketForWatermarkRequest(content, title, author, bookTopic);
	}

	@GET
	@Path("retrieveDocumentByTicket/{ticketId}")
	public BaseDocument getDocumentByTicketId(@PathParam("ticketId") String ticketId) {

		if ((ticketId == null || ticketId.trim().equals(""))) {
	           String message = "The following values are required in order to retrieve the status of your document: ticketId";	  
	           message = watermarkManager.generateCustomErrorMessageBadRequest(message);
	           throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
	                    .entity(message).build());
		}
		
		BaseDocument foundDocument = watermarkManager.fetchBaseDocumentByTicketId(ticketId);
		
//		if(foundDocument==null) {
//            String message = "The document you have requested with ticketId: "+ticketId+" does not exist.";
//            message = generateCustomErrorMessageBadRequest(message);
//            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
//                    .entity(message).build());
//		}

		return foundDocument;
	}
	
	//purely for testing
	@GET
	@Path("retrieveAllDocuments")
	public List<BaseDocument> getAllBaseDocuments() {
		return watermarkManager.getAllBaseDocuments();
	}

	//purely for testing
	@GET
	@Path("retrieveAllWatermarks")
	public List<Watermark> getAllWatermarks() {
		return watermarkManager.getAllWatermarks();
	}

	

}