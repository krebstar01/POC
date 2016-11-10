package com.audibene.rest.resource;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.audibene.manager.AudibeneManager;
import com.audibene.model.Appointment;
import com.audibene.model.AppointmentJSONWrapper;
import com.audibene.model.Customer;
import com.audibene.model.Rating;

import io.swagger.annotations.Api;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class CustomerApplication {

	private AudibeneManager audibeneManager;

	public CustomerApplication(AudibeneManager audibeneManager) {
		super();
		this.audibeneManager = audibeneManager;
	}

	@GET
	@Path("/customers")
	public List<Customer> getCustomers() {
		return audibeneManager.getAllCustomers();
	}

	@POST
	public Customer createCustomers(@FormParam("customerName") String customerName,
			@FormParam("audiologistName") String audiologistName) {

		if (customerName == null || customerName.trim().equals("")) {
			String message = " Please enter a valid customerName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());
		}

		if (audiologistName == null || audiologistName.trim().equals("")) {
			String message = " Please enter a valid audiologistName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message)
					.type("text/plain").build());
		}

		return audibeneManager.createCustomer(customerName, audiologistName);
	}

	@POST
	@Path("/appointment")
	public Appointment createAppointmentWithCustomer(@FormParam("customerName") String customerName,
			@FormParam("audiologistName") String audiologistName, @FormParam("localDateTime") String localDateTime) {
		
		
		
		if (customerName == null || customerName.trim().equals("")) {
			String message = " Please enter a valid customerName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());
		}

		if (audiologistName == null || audiologistName.trim().equals("")) {
			String message = " Please enter a valid audiologistName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message)
					.type("text/plain").build());
		}
		
		if (localDateTime == null || localDateTime.trim().equals("")) {
			String message = " Please enter a valid localDateTime. Example: '2016-11-08T00:18:15.294Z' ";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message)
					.type("text/plain").build());
		}
		
		
		try {
			audibeneManager.parseStringTODateTime("2016-11-08T00:18:15.294Z");
		} catch (Exception e) {
			
			String message = " Please enter a valid localDateTime. Example: '2016-11-08T00:18:15.294Z' ";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);
			
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message)
					.type("text/plain").build());
		}

		

		return audibeneManager.CreateAppointmentForCustomer(localDateTime, customerName, audiologistName);
	}

	@GET
	@Path("/nextAppointment")
	public AppointmentJSONWrapper getCustomersNextAppointment(@QueryParam(value = "customerName") String customerName) {
		
		if (customerName == null || customerName.trim().equals("")) {
			String message = " Please enter a valid customerName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());
		}
		
		return audibeneManager.getNextAppointmentForCustomer(customerName);
	}

	@PUT
	@Path("/rateAppointment")
	public AppointmentJSONWrapper rateappointmentCustomerAppointment(
			@QueryParam(value = "customerName") String customerName, @QueryParam(value = "rating") Rating rating) {
		
		if (customerName == null || customerName.trim().equals("")) {
			String message = " Please enter a valid customerName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());
		}
		
		
		if (rating == null) {
			String message = " Please enter a valid customerName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());
		}
		
		return audibeneManager.rateAppointment(customerName, rating);
	}

}