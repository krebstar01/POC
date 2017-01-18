package com.watermark.rest.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.watermark.dao.WatermarkDao;
import com.watermark.manager.WatermarkManager;
import com.watermark.model.BaseDocument;
import com.watermark.model.Book;
import com.watermark.model.BookTopic;
import com.watermark.model.ProcessingStatus;
import com.watermark.model.Watermark;

import io.dropwizard.testing.junit.DropwizardClientRule;
import io.dropwizard.testing.junit.ResourceTestRule;

public class TestWatermarkStatusApplication {

	private static final WatermarkDao watermarkDao = mock(WatermarkDao.class);
	private static final WatermarkManager watermarkManager = mock(WatermarkManager.class);

	
	@ClassRule
    public static final DropwizardClientRule dropwizard = new DropwizardClientRule(new WatermarkStatusApplication(watermarkManager));

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(watermarkDao)
			.addResource(new WatermarkStatusApplication(watermarkManager))
			.setTestContainerFactory(new GrizzlyWebTestContainerFactory()).build();

	@Before
	public void setup() {		
		setUpData();
	}

	@After
	public void tearDown() {
		reset(watermarkDao);
		reset(watermarkManager);
	}

	private void setUpData() {
		Book baseDocument1 = new Book();
		baseDocument1.setAuthor("author1");
		baseDocument1.setContent("content1");
		baseDocument1.setTitle("title1");
		baseDocument1.setTopic(BookTopic.BUSINESS);

		Book baseDocument2 = new Book();
		baseDocument2.setId(1);
		baseDocument2.setAuthor("author1");
		baseDocument2.setContent("content1");
		baseDocument2.setTitle("title1");
		baseDocument2.setTopic(BookTopic.BUSINESS);
		baseDocument2.setProcessingStatus(ProcessingStatus.SUBMITTED);
		baseDocument2.setTicketId("111");

		ArrayList<BaseDocument> documents = new ArrayList<>();
		documents.add(baseDocument2);

		Watermark watermark1 = new Watermark();
		watermark1.setId(1);
		watermark1.setAuthor("Kurt Vonnegut");
		watermark1.setTitle("Cat's Cradle");
		watermark1.setContent("Awesome Book");
		watermark1.setBookTopic(BookTopic.MEDIA);

		ArrayList<Watermark> watermarks = new ArrayList<>();
		watermarks.add(watermark1);

		when(watermarkDao.createBaseDocumentAsObject(baseDocument1)).thenReturn(baseDocument2);
		when(watermarkManager.fetchBaseDocumentByTicketId("111")).thenReturn(baseDocument2);
		when(watermarkDao.fetchBaseDocumentByTicketId("111")).thenReturn(baseDocument2);

		when(watermarkManager.getAllBaseDocuments()).thenReturn(documents);
		when(watermarkManager.getAllWatermarks()).thenReturn(watermarks);
	}

	@Test
	public void testRetrieveDocumentByTicket() {
		Client client = resources.client();
		WebTarget target = client.target(dropwizard.baseUri()+"/status/retrieveDocumentByTicketAsync/111/2");
		Integer actual = target.request(MediaType.APPLICATION_JSON).get().getStatus();
		assertThat(actual).isEqualTo(200);
	}

	@Test
	public void testGetAllBaseDocuments() {
		
		Client client = resources.client();
		WebTarget target = client.target(dropwizard.baseUri()+"/status/retrieveAllDocuments");
		Integer actual = target.request(MediaType.APPLICATION_JSON).get().getStatus();
		assertThat(actual).isEqualTo(200);
	}

	@Test
	public void testRetrieveAllWatermarks() {
		Client client = resources.client();
		WebTarget target = client.target(dropwizard.baseUri()+"/status/retrieveAllWatermarks");
		Integer actual = target.request(MediaType.APPLICATION_JSON).get().getStatus();
		assertThat(actual).isEqualTo(200);
	}

	@Test
	public void testRequestTicketJournal() {
		Client client = resources.client();
		WebTarget target = client.target(dropwizard.baseUri()+"/status/requestTicket");

		Form form = new Form();
		form.param("content", "content111");
		form.param("title", "title111");
		form.param("author", "author111");

		Integer actual = target.request(MediaType.APPLICATION_JSON).post(Entity.form(form)).getStatus();
		assertThat(actual).isEqualTo(200);
	}

	@Test
	public void testRequestTicketBook() {
		Client client = resources.client();
		WebTarget target = client.target(dropwizard.baseUri()+"/status/requestTicket");

		Form form = new Form();
		form.param("content", "content111");
		form.param("title", "title111");
		form.param("author", "author111");
		form.param("topic", BookTopic.BUSINESS.getValue());
		Integer actual = target.request(MediaType.APPLICATION_JSON).post(Entity.form(form)).getStatus();
		assertThat(actual).isEqualTo(200);
	}

	@Test
	public void testRequestTicketBookBad() {
		Client client = resources.client();
		WebTarget target = client.target(dropwizard.baseUri()+"/status/requestTicket");

		Form form = new Form();
		form.param("content", "content111");
		form.param("title", "title111");
		form.param("author", "author111");
		form.param("topic", "non existent topic");

		Integer actual = target.request(MediaType.APPLICATION_JSON).post(Entity.form(form)).getStatus();
		assertThat(actual).isEqualTo(400);
	}

}
