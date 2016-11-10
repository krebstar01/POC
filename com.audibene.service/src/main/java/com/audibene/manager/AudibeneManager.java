package com.audibene.manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.SessionFactory;

import com.audibene.dao.AudibeneDao;
import com.audibene.dao.CustomerDao;
import com.audibene.model.Appointment;
import com.audibene.model.AppointmentJSONWrapper;
import com.audibene.model.Audiologist;
import com.audibene.model.Customer;
import com.audibene.model.Rating;

public class AudibeneManager {


	
	private SessionFactory sessionFactory;
	private AudibeneDao audibeneDao;
	private CustomerDao customerDao;
	
	public AudibeneManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.audibeneDao = new AudibeneDao(sessionFactory);
		this.customerDao = new CustomerDao(sessionFactory);
	}
		
	
	
	

	public Customer createCustomer(String customerName, String audiologistName) {

		Customer customer = null; 
		
		if (customerName != null && audiologistName != null && !customerName.trim().equals("")
				&& !audiologistName.trim().equals("")) {
			Audiologist audiologist = audibeneDao.getAudiologistByName(audiologistName);

			if (audiologist != null) {
				customer = new Customer();
				customer.setCustomerName(customerName);
//				customer.setAudiologist(audiologist);
				customer.setAudiologistId(audiologist.getId());
				customerDao.saveCustomer(customer);
			}

		}

		// TODO validate
		
		return customer;
	}
	
	
	
	private AppointmentJSONWrapper transformAppointmentToWrapper(Appointment appointment){
		AppointmentJSONWrapper result = new AppointmentJSONWrapper();
		if(appointment!=null){
			result.setAppointmentDate(appointment.getAppointmentDate());
			result.setAppointmentIdentifier(appointment.getAppointmentIdentifier());
			result.setAudiologist(appointment.getAudiologist());
			result.setAudiologistId(appointment.getAudiologistId());
			result.setCustomer(appointment.getCustomer());
			result.setCustomerId(appointment.getCustomerId());
			result.setId(appointment.getId());
			result.setRating(appointment.getRating());
			result.setAppointmentAudiologistName(appointment.getAudiologist().getAudiologistName());
			result.setAppointmentCustomerName(appointment.getCustomer().getCustomerName());
		}
		
		return result;
	}
	
	
	private List<AppointmentJSONWrapper> transformAppointmentListToWrappers(List<Appointment> appointments){
		ArrayList<AppointmentJSONWrapper> results = new ArrayList<>();
		if(appointments!=null && !appointments.isEmpty()){
			for(Appointment appointment: appointments) {
				results.add(transformAppointmentToWrapper(appointment));
			}
		}
		return results;
	}
	

	//String pattern = "dd-MMM-yy hh.mm.ss aa"; 
	public AppointmentJSONWrapper CreateAppointmentForCustomer(String localDateTime, String customerName, String audiologistName) {
		Appointment appointment = null;
		Customer customer = null;
		Audiologist audiologist = null;
		
		if (localDateTime != null && customerName != null && 
			!localDateTime.trim().equals("") && !customerName.trim().equals(""))
		{
			LocalDateTime appointmentDate = parseStringTODateTime(localDateTime);
			audiologist = audibeneDao.getAudiologistByName(audiologistName);
			customer = customerDao.getCustomerByName(customerName);
			
			if(customer!=null && audiologist!=null){
				appointment = new Appointment();
				appointment.setCustomerId(customer.getId());
				appointment.setAudiologistId(audiologist.getId());
				appointment.setAppointmentDate(appointmentDate);
				String appointmentIdentifier = RandomStringUtils.random(10, true, false);
				appointment.setAppointmentIdentifier(appointmentIdentifier);
				audibeneDao.saveAppointment(appointment);
				appointment = audibeneDao.getAppointmentByIdentifier(appointmentIdentifier);
			}
		}

		return transformAppointmentToWrapper(appointment);
	}

	public List<AppointmentJSONWrapper> getAllAppointmentsForAudiologist() {
		LocalDateTime future = LocalDateTime.now().plusYears(100);
		List<AppointmentJSONWrapper> results = new ArrayList<>();
		List<Appointment> appointments = audibeneDao.getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(future, null, null);
		results.addAll(transformAppointmentListToWrappers(appointments));
		return results;
	}

	public List<AppointmentJSONWrapper> getAllAudiologistAppointmentsForNextWeek(String audiologistName) {
		LocalDateTime future = LocalDateTime.now().plusWeeks(1);
		List<AppointmentJSONWrapper> results = new ArrayList<>();
		Audiologist audiologist = audibeneDao.getAudiologistByName(audiologistName);
		List<Appointment> appointments = audibeneDao.getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(future, null, audiologist);
		results.addAll(transformAppointmentListToWrappers(appointments));
		return results;
	}

	public AppointmentJSONWrapper getNextAppointmentForCustomer(String customerName) {
		AppointmentJSONWrapper nextCustomerAppointment = null;
		
		LocalDateTime future = LocalDateTime.now().plusYears(100);
		List<Appointment> results = new ArrayList<>();
		Customer customer = customerDao.getCustomerByName(customerName);
		results.addAll(audibeneDao.getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(future, customer, null));
		
		if(results!=null && !results.isEmpty()) {
			nextCustomerAppointment = transformAppointmentToWrapper(results.get(0));
		}
		
		return nextCustomerAppointment;
	}

	public AppointmentJSONWrapper rateAppointment(String customerName, Rating rating) {
		
		AppointmentJSONWrapper nextCustomerAppointment = null;
		LocalDateTime future = LocalDateTime.now().plusYears(100);
		List<Appointment> results = new ArrayList<>();
		Customer customer = customerDao.getCustomerByName(customerName);
		results.addAll(audibeneDao.getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(future, customer, null));
		
		if(results!=null && !results.isEmpty()) {
			nextCustomerAppointment = transformAppointmentToWrapper(results.get(results.size() - 1));
		}
		
		return nextCustomerAppointment;
	}
	
	
	public List<Customer> getAllCustomers() {
		List<Customer> results = new ArrayList<>();
		results.addAll(customerDao.getAllCustomers());
		return results;
	}
	
	public List<Audiologist> getAllAudiologists() {
		List<Audiologist> results = new ArrayList<>();
		results.addAll(audibeneDao.getAllAudiologists());
		return results;
	}
	
	public List<AppointmentJSONWrapper> getAllAppointments() {
		List<AppointmentJSONWrapper> results = new ArrayList<>();
		List<Appointment> appointments = audibeneDao.getAllAppointments();
		results.addAll(transformAppointmentListToWrappers(appointments));
		return results;
	}
	

	
	public static LocalDateTime parseStringTODateTime(String input){
	     String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	     LocalDateTime dateTime  = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
	     return dateTime;
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
	
	public static boolean containsRating(String test) {

	    for (Rating r : Rating.values()) {
	        if (r.name().equals(test)) {
	            return true;
	        }
	    }

	    return false;
	}

}
