package com.watermark.manager;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.watermark.dao.WatermarkDao;
import com.watermark.model.BaseDocument;
import com.watermark.model.Book;
import com.watermark.model.BookTopic;
import com.watermark.model.Journal;
import com.watermark.model.ProcessingStatus;
import com.watermark.model.TicketRequest;
import com.watermark.model.Watermark;

public class WatermarkManager {
	
	public WatermarkManager() {
	}

	public WatermarkManager(WatermarkDao watermarkDao) {
		super();
		this.watermarkDao = watermarkDao;
	}

	WatermarkDao watermarkDao = new WatermarkDao();

	private String createTicket() {
		UUID ticket = UUID.randomUUID();
		return ticket.toString();
	}
	
	
	public List<BaseDocument> getAllBaseDocuments(){
		return watermarkDao.getAllBaseDocuments();
	}
	
	
	public List<Watermark> getAllWatermarks(){
		return watermarkDao.getAllWatermarks();
	}

	

	public HashMap<String, String> retrieveTicketForWatermarkRequest(String content, String title, String author,
			BookTopic topic) {

		HashMap<String, String> results = new HashMap<>();

		String ticketId = createTicket();
		String entity = "";

		if (topic != null) {
			Book book = new Book();
			book.setTicketId(ticketId);
			book.setProcessingStatus(ProcessingStatus.SUBMITTED);

			book.setContent(content);
			book.setTitle(title);
			book.setAuthor(author);
			book.setTopic(topic);

			watermarkDao.createBaseDocumentAsObject(book);
			entity = "Book";
		} else {
			Journal journal = new Journal();
			journal.setTicketId(ticketId);
			journal.setProcessingStatus(ProcessingStatus.SUBMITTED);

			journal.setContent(content);
			journal.setTitle(title);
			journal.setAuthor(author);
			watermarkDao.createBaseDocumentAsObject(journal);
			entity = "Journal";
		}

		String ticketAndMessage = "You have requested a " + entity + " to be processed \n ";
		ticketAndMessage += "please check back later with the following ticket number \n ";
		// TODO get context
		ticketAndMessage += "/service/status/retrieveDocumentByTicket/" + ticketId;

		results.put("Ticket", ticketId);
		results.put("Message", ticketAndMessage);

		return results;
	}

	// here we create a mock watermark process
	public BaseDocument processDocument(BaseDocument docForProcessing) {
		if (docForProcessing != null) {
			ProcessingStatus status = docForProcessing.getProcessingStatus();

			if (status == ProcessingStatus.SUBMITTED) {
				docForProcessing.setProcessingStatus(ProcessingStatus.UNDERWAY);
			}

			 if (status == ProcessingStatus.UNDERWAY) {
				docForProcessing.setProcessingStatus(ProcessingStatus.FINISHED);
					Watermark watermark = new Watermark();
					
					watermark.setContent(docForProcessing.getContent());
					watermark.setTitle(docForProcessing.getTitle());
					watermark.setAuthor(docForProcessing.getAuthor());
					

					if (docForProcessing instanceof Book) {
						Book book = (Book) docForProcessing;
						watermark.setBookTopic(book.getTopic());
					}
					
					docForProcessing.setWatermark(watermark);
					watermark.setBaseDocument(docForProcessing);
			}

					
			 docForProcessing = watermarkDao.updateDocument(docForProcessing);			

		}

		return docForProcessing;
	}

	public BaseDocument fetchBaseDocumentByTicketId(String ticketId) {
		BaseDocument results = null;

		BaseDocument foundDocument = watermarkDao.fetchBaseDocumentByTicketId(ticketId);
		

		if (foundDocument != null) {
				results = new TicketRequest();
				results.setId(foundDocument.getId());
				results.setTicketId(foundDocument.getTicketId());
				results.setProcessingStatus(foundDocument.getProcessingStatus());
				
				if(foundDocument.getWatermark()!=null){
					results.setWatermark(foundDocument.getWatermark());
				}
				processDocument(foundDocument);
		}
		
		if(foundDocument==null) {
            String message = "The document you have requested with ticketId: "+ticketId+" does not exist.";
            message = generateCustomErrorMessageBadRequest(message);
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
                    .entity(message).build());
		}


		return results;
	}
	
	public String generateCustomErrorMessageBadRequest(String message){
		String result = "{";
		result += "\"status\" : " + "\"" + Status.BAD_REQUEST + "\"";
		result += ",\"status code\" : " + "\"" + Status.BAD_REQUEST.getStatusCode() + "\"";
		result += ",\"message\" : ";
		result +="\"" + message + "\""
        		+ "}";
		return result;
	}

}
