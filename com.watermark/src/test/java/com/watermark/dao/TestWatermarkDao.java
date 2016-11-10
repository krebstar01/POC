package com.watermark.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.watermark.model.BaseDocument;
import com.watermark.model.Book;
import com.watermark.model.Journal;
import com.watermark.model.ProcessingStatus;

public class TestWatermarkDao {

	final WatermarkDao watermarkDao = new WatermarkDao();

	//setup, test the tests.... toggle to make sure testing framework is working
	@Test
	public void testTheTests() throws Exception {
		assertFalse(false);
	}

	
	@Test
	public void testCreateBaseDocumentAsObject() throws Exception {
		BaseDocument baseDocument = new Book();
		baseDocument.setProcessingStatus(ProcessingStatus.UNDERWAY);
		baseDocument.setTicketId("1234");
		baseDocument.setWatermark(null);
		BaseDocument result = watermarkDao.createBaseDocumentAsObject(baseDocument);
		assertNotNull(result);
	}
	
	
	@Test
	public void testGetAllBaseDocuments() throws Exception {
		
		BaseDocument baseDocument = new Book();
		baseDocument.setProcessingStatus(ProcessingStatus.UNDERWAY);
		baseDocument.setTicketId("1234");
		baseDocument.setWatermark(null);
		BaseDocument result = watermarkDao.createBaseDocumentAsObject(baseDocument);
		assertNotNull(result);
		
		
		baseDocument = new Book();
		baseDocument.setProcessingStatus(ProcessingStatus.UNDERWAY);
		baseDocument.setTicketId("4567");
		baseDocument.setWatermark(null);
		result = watermarkDao.createBaseDocumentAsObject(baseDocument);
		assertNotNull(result);
		
		
		baseDocument = new Journal();
		baseDocument.setProcessingStatus(ProcessingStatus.UNDERWAY);
		baseDocument.setTicketId("9999");
		baseDocument.setWatermark(null);
		result = watermarkDao.createBaseDocumentAsObject(baseDocument);
		assertNotNull(result);

		
		List<BaseDocument> results = watermarkDao.getAllBaseDocuments();
		assertNotNull(results);
		
		assertEquals(3, results.size());
	}

}
