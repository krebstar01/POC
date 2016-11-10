package com.audibene.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentJSONWrapper extends Appointment {

	private String appointmentCustomerName;
	private String appointmentAudiologistName;
	
//	@JsonBackReference
	public String getAppointmentCustomerName() {
		return appointmentCustomerName;
	}

//	@JsonBackReference
	public String getAppointmentAudiologistName() {
		return appointmentAudiologistName;
	}

	public void setAppointmentCustomerName(String appointmentCustomerName) {
		this.appointmentCustomerName = appointmentCustomerName;
	}

	public void setAppointmentAudiologistName(String appointmentAudiologistName) {
		this.appointmentAudiologistName = appointmentAudiologistName;
	}


}