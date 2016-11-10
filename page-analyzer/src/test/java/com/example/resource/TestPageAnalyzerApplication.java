package com.example.resource;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * Created by justin on 07.07.15.
 */
public class TestPageAnalyzerApplication extends TestBaseApplication {

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addProvider(viewMessageBodyWriter)
			.addResource(new PageAnalyzerApplication()).build();

	@Test
	public void testEntryPage() {
		Client client = resources.client();
		WebTarget target = client.target("/entry");
		Integer actual = target.request(MediaType.TEXT_HTML).get().getStatus();
		assertThat(actual).isEqualTo(200);
	}

	@Test
	public void testResultsPageNonexistentUrl() {
		Client client = resources.client();
		WebTarget target = client.target("/results");

		Form form = new Form();
		form.param("url", "http://www.yadda.com");
		Integer actual = target.request(MediaType.TEXT_HTML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE)).getStatus();
		assertThat(actual).isEqualTo(400);
	}
	
	@Test
	public void testResultsPageNoHttp() {
		Client client = resources.client();
		WebTarget target = client.target("/results");

		Form form = new Form();
		form.param("url", "www.yadda.com");
		Integer actual = target.request(MediaType.TEXT_HTML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE)).getStatus();
		assertThat(actual).isEqualTo(400);
	}

	@Test
	public void testResultsPageBadUrl() {
		Client client = resources.client();
		WebTarget target = client.target("/results");

		Form form = new Form();
		form.param("url", "httpwww..com");
		Integer actual = target.request(MediaType.TEXT_HTML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE)).getStatus();
		assertThat(actual).isEqualTo(400);
	}
	
	@Test
	public void testVerifyPageBadMediaType() {
		Client client = resources.client();
		WebTarget target = client.target("/entry");
		Integer actual = target.request(MediaType.APPLICATION_JSON).get().getStatus();
		assertThat(actual).isEqualTo(406);
	}

}