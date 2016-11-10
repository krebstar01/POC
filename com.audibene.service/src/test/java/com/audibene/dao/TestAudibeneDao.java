package com.audibene.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.audibene.manager.AudibeneManager;
import com.audibene.model.Appointment;
import com.audibene.model.Audiologist;
import com.audibene.model.Customer;
import com.audibene.model.Rating;

public class TestAudibeneDao {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernateTest.cfg.xml").build();
			Metadata metadata = new MetadataSources(standardRegistry).addAnnotatedClass(Audiologist.class)
					.addAnnotatedClass(Customer.class).addAnnotatedClass(Appointment.class).getMetadataBuilder()
					.build();
			return metadata.getSessionFactoryBuilder().build();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	AudibeneManager audibeneManager = new AudibeneManager(sessionFactory);
	AudibeneDao audibeneDao = new AudibeneDao(sessionFactory);
	CustomerDao customerDao = new CustomerDao(sessionFactory);

	// setup, test the tests.... toggle to make sure testing framework is
	// working
	@Test
	public void testTheTests() throws Exception {

		assertFalse(false);
	}

	@Test
	public void testsaveCustomer() throws Exception {
		Audiologist audiologist = new Audiologist();
		String audiologistName = UUID.randomUUID().toString();
		audiologist.setAudiologistName(audiologistName);
		audibeneDao.saveAudiologist(audiologist);
		Audiologist audiologistPersisted = audibeneDao.getAudiologistByName(audiologistName);
		assertNotNull(audiologistPersisted);
		
		Customer customer = new Customer();
		customer.setAudiologistId(audiologistPersisted.getId());
//		customer.setAudiologist(audiologistPersisted);
		String customerName = UUID.randomUUID().toString();
		customer.setCustomerName(customerName);
		customerDao.saveCustomer(customer);
		Customer customerPersisted = customerDao.getCustomerByName(customerName);
		assertNotNull(customerPersisted);
	}

	@Test
	public void testsaveAudiologist() throws Exception {
		Audiologist audiologist = new Audiologist();
		String audiologistName = UUID.randomUUID().toString();
		audiologist.setAudiologistName(audiologistName);
		audibeneDao.saveAudiologist(audiologist);
		Audiologist audiologistPersisted = audibeneDao.getAudiologistByName(audiologistName);
		assertNotNull(audiologistPersisted);
	}

	@Test
	public void testsaveAppointments() throws Exception {
		
		// create audiologist
		Audiologist audiologist = new Audiologist();
		String audiologistName = UUID.randomUUID().toString();
		audiologist.setAudiologistName(audiologistName);
		audibeneDao.saveAudiologist(audiologist);
		Audiologist audiologistPersisted = audibeneDao.getAudiologistByName(audiologistName);
		assertNotNull(audiologistPersisted);

		
		
		// create customer
		Customer customer = new Customer();
		String customerName = UUID.randomUUID().toString();
		customer.setCustomerName(customerName);
		customer.setAudiologistId(audiologistPersisted.getId());
		customerDao.saveCustomer(customer);
		Customer customerPersisted = customerDao.getCustomerByName(customerName);
		assertNotNull(customerPersisted);


		// create appointment
		LocalDateTime nowPlusweek = LocalDateTime.now().plusWeeks(1);

		Appointment appointment = new Appointment();
		
		appointment.setAppointmentDate(nowPlusweek);
//		appointment.setCustomer(customerPersisted);
		appointment.setCustomerId(customerPersisted.getId());
//		appointment.setAudiologist(audiologistPersisted);
		appointment.setAudiologistId(audiologistPersisted.getId());
		
		appointment.setRating(Rating.MEH);
		String appointmentIdentifier = UUID.randomUUID().toString();
		appointment.setAppointmentIdentifier(appointmentIdentifier);
		
		audibeneDao.saveAppointment(appointment);
		
		
		Appointment appointmentPersisted = audibeneDao.getAppointmentByIdentifier(appointmentIdentifier);
		assertNotNull(appointmentPersisted);
		
		
		Appointment appointment2 = new Appointment();
		appointment2.setAppointmentDate(nowPlusweek);
//		appointment2.setCustomer(customerPersisted);
		appointment2.setCustomerId(customerPersisted.getId());
//		appointment2.setAudiologist(audiologistPersisted);
		appointment2.setAudiologistId(audiologistPersisted.getId());
		
		appointment2.setRating(Rating.GOOD);
		String appointmentIdentifier2 = UUID.randomUUID().toString();
		appointment2.setAppointmentIdentifier(appointmentIdentifier2);
		audibeneDao.saveAppointment(appointment2);
		Appointment appointmentPersisted2 = audibeneDao.getAppointmentByIdentifier(appointmentIdentifier2);
		assertNotNull(appointmentPersisted2);
		
		
		Appointment appointment3 = new Appointment();
		appointment3.setAppointmentDate(nowPlusweek);
		appointment2.setRating(Rating.GREAT);
//		appointment3.setCustomer(customerPersisted);
		appointment3.setCustomerId(customerPersisted.getId());
//		appointment3.setAudiologist(audiologistPersisted);
		appointment3.setAudiologistId(audiologistPersisted.getId());
		String appointmentIdentifier3 = UUID.randomUUID().toString();
		appointment3.setAppointmentIdentifier(appointmentIdentifier3);
		audibeneDao.saveAppointment(appointment3);
		Appointment appointmentPersisted3 = audibeneDao.getAppointmentByIdentifier(appointmentIdentifier3);
		assertNotNull(appointmentPersisted3);
	}
	
	
	@Test
	public void testDateParse() throws Exception {
		String input = "2016-12-14T18:59:10.637Z";
		audibeneManager.parseStringTODateTime(input);
	     //String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
		assertTrue(true);
	}
	
	

}
