package com.audibene.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.audibene.manager.AudibeneManager;
import com.audibene.model.AppointmentJSONWrapper;
import com.audibene.model.Audiologist;

import io.swagger.annotations.Api;


@Path("/audiologist")
@Produces(MediaType.APPLICATION_JSON)
@Api
public class AudibeneApplication {

	private AudibeneManager audibeneManager;

	public AudibeneApplication(AudibeneManager audibeneManager) {		
		super();
		this.audibeneManager = audibeneManager;
	}
	
	@GET
	@Path("/audiologists")
	public List<Audiologist> getAudiologists() {
		return audibeneManager.getAllAudiologists();
	}

	
	@GET
	@Path("/appointments")
	public List<AppointmentJSONWrapper> getAppointments() {
		return audibeneManager.getAllAppointments();
	}
	
	@GET
	@Path("/appointmentsNextWeek")
	public List<AppointmentJSONWrapper> getAppointmentsNextWeek(@QueryParam(value = "audiologistName") String audiologistName) {
		
		if (audiologistName == null || audiologistName.trim().equals("")) {
			String message = " Please enter a valid audiologistName";
			message = audibeneManager.generateCustomErrorMessageBadRequest(message);

			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST + message).build());
		}
		
		
		return audibeneManager.getAllAudiologistAppointmentsForNextWeek(audiologistName);
	}

}