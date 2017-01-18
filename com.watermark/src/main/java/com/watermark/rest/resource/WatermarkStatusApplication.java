package com.watermark.rest.resource;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ManagedAsync;

import com.watermark.manager.WatermarkManager;
import com.watermark.model.BaseDocument;
import com.watermark.model.BookTopic;
import com.watermark.model.Watermark;

import io.swagger.annotations.Api;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class WatermarkStatusApplication {

	private WatermarkManager watermarkManager = new WatermarkManager();

	public WatermarkStatusApplication() {
		super();
	}

	public WatermarkStatusApplication(WatermarkManager watermarkManager) {
		super();
		this.watermarkManager = watermarkManager;
	}

	private BookTopic verifyBookTopic(String topic) {
		BookTopic results = null;
		if (topic != null && !topic.trim().equals("")) {
			topic = topic.toLowerCase();
			results = BookTopic.findBookTopic(topic);
		}

		return results;
	}

	@POST
	@Path("/requestTicket")
	public HashMap<String, String> retrieveTicketForWatermarkRequest(@FormParam("content") String content,
			@FormParam("title") String title, @FormParam("author") String author, @FormParam("topic") String topic) {

		if ((content == null || content.trim().equals("")) || (title == null || title.trim().equals(""))
				|| (author == null || author.trim().equals(""))) {
			String message = " The following values are required in order to process a document: a) content b) title c) author";
			message = watermarkManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());

		}

		BookTopic bookTopic = verifyBookTopic(topic);
		if (topic != null && !topic.trim().equals("") && bookTopic == null) {

			String message = "If you have chosen to watermark a book, please choose from one of the following three topics: ";
			message += " business, science, media";
			message = watermarkManager.generateCustomErrorMessageBadRequest(message);
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(message).build());
		}
		return watermarkManager.retrieveTicketForWatermarkRequest(content, title, author, bookTopic);
	}


	@GET
	@Path("retrieveDocumentByTicketAsync/{ticketId}/{timeout}")
	@ManagedAsync
	public void getDocumentByTicketIdAsync(@PathParam("ticketId") String ticketId, @PathParam("timeout") Integer timeout, @Suspended final AsyncResponse asyncResponse) {
		
		asyncResponse.setTimeout(timeout, TimeUnit.SECONDS);
		
		asyncResponse.setTimeoutHandler(new TimeoutHandler() {
			
	        @Override
	        public void handleTimeout(AsyncResponse asyncResponse) {
	            asyncResponse.resume(Response.status(Status.REQUEST_TIMEOUT)
	                    .entity("The Operation time out. Please try again").build());
	            System.out.println("There was a request timeout for the ticket " + ticketId);
	        }
	    });
		
			asyncResponse.resume(watermarkManager.fetchBaseDocumentByTicketId(ticketId));
	    
	}

	// purely for testing
	@GET
	@Path("retrieveAllDocuments")
	public List<BaseDocument> getAllBaseDocuments() {
		return watermarkManager.getAllBaseDocuments();
	}

	// purely for testing
	@GET
	@Path("retrieveAllWatermarks")
	public List<Watermark> getAllWatermarks() {
		return watermarkManager.getAllWatermarks();
	}

}