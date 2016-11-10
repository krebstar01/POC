package com.audibene.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.audibene.model.Appointment;
import com.audibene.model.Audiologist;
import com.audibene.model.Customer;

/*
 * 
 *  NOTE: as of very recently (recent 5.x minor version)
 *  the underlying interfaces for hibernate "criteria" queries are deprecated 
 *  and hibernate will move towards JPA criteria.CriteriaQuery
 *  currently there are not Hibernate implementations for criteria.CriteriaQuery!
 * 
 * */
public class AudibeneDao extends BaseDao {

	public AudibeneDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

	final static Logger logger = LoggerFactory.getLogger(AudibeneDao.class);

	Audiologist getAudiologistByName(String audiologistName, Session session) {

		Audiologist result = null;
		if (audiologistName != null && !audiologistName.trim().equals("")) {
			Transaction tx = session.beginTransaction();
			try {
				// see note atop this class!
				// one additional note: session.get will not work for querying a
				// super
				// class
				@SuppressWarnings("deprecation")
				Criteria criteria = session.createCriteria(Audiologist.class);
				criteria.add(Restrictions.eq("audiologistName", audiologistName));
				result = (Audiologist) criteria.uniqueResult();
				tx.commit();
			} catch (HibernateException e) {
				logger.info("getAudiologistByName() -- ", e);
			} finally {
				releaseSession(session);
			}
		}

		return result;
	}

	public Audiologist getAudiologistByName(String audiologistName) {
		Session session = getSessionFactory().openSession();
		return getAudiologistByName(audiologistName, session);
	}

	protected void saveAppointment(Appointment appointment, Session session) {

		if (appointment != null) {
			Transaction tx = session.beginTransaction();
			try {
				session.save(appointment);
				tx.commit();
			} catch (HibernateException e) {
				logger.info("saveAppointmentReturnObject() -- ", e);
			} finally {
				releaseSession(session);
			}
		}
	}

	public void saveAppointment(Appointment appointment) {
		Session session = getSessionFactory().openSession();
		saveAppointment(appointment, session);
	}

	protected List<Appointment> getAllAppointments(Session session) {
		List<Appointment> results = new ArrayList<>();
		Transaction tx = session.beginTransaction();

		// see note atop this class!
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Appointment.class);

		try {
			results.addAll(criteria.list());
			tx.commit();

		} catch (HibernateException e) {
			logger.info("getAllAppointments() -- ", e);
		} finally {
			releaseSession(session);
		}

		return results;
	}

	public List<Appointment> getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(LocalDateTime future, Customer customer,
			Audiologist audiologist) {
		List<Appointment> customers = new ArrayList<Appointment>();
		Session session = getSessionFactory().openSession();
		customers
				.addAll(getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(future, customer, audiologist, session));
		return customers;
	}

	protected List<Appointment> getAllAppointmentsForTimePeriodAoCustomerAoAudiologist(LocalDateTime future,
			Customer customer, Audiologist audiologist, Session session) {
		List<Appointment> results = new ArrayList<>();
		Transaction tx = session.beginTransaction();
		
		LocalDateTime now = LocalDateTime.now();
		

		// see note atop this class!
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Appointment.class);

		if (customer != null) {
			criteria.add(Restrictions.ge("customer", customer));
		}

		if (audiologist != null) {
			criteria.add(Restrictions.ge("audiologist", audiologist));
		}

		if (future != null) {
			criteria.add(Restrictions.between("appointmentDate", now ,future));
		}
		try {
			results.addAll(criteria.list());
			tx.commit();

		} catch (HibernateException e) {
			logger.info("getAllAppointmentsForTimePeriod() -- ", e);
		} finally {
			releaseSession(session);
		}

		return results;
	}

	public List<Appointment> getAllAppointments() {
		List<Appointment> customers = new ArrayList<Appointment>();
		Session session = getSessionFactory().openSession();
		customers.addAll(getAllAppointments(session));
		return customers;
	}


	protected void saveAudiologist(Audiologist audiologist, Session session) {

		if (audiologist != null) {
			Transaction tx = session.beginTransaction();
			try {
				session.save(audiologist);
				tx.commit();
			} catch (HibernateException e) {
				logger.info("saveCustomer() -- ", e);
			} finally {
				releaseSession(session);
			}
		}
	}
	
	public void saveAudiologist(Audiologist audiologist) {
		Session session = getSessionFactory().openSession();
		saveAudiologist(audiologist, session);
	}
	
	Appointment getAppointmentByIdentifier(String appointmentIdentifier, Session session) {

		Appointment results = null;
		if (appointmentIdentifier != null && !appointmentIdentifier.trim().equals("")) {
			Transaction tx = session.beginTransaction();
			try {
				// see note atop this class!
				// one additional note: session.get will not work for querying a
				// super
				// class
				@SuppressWarnings("deprecation")
				Criteria criteria = session.createCriteria(Appointment.class);
				criteria.add(Restrictions.eq("appointmentIdentifier", appointmentIdentifier));
				results = (Appointment) criteria.uniqueResult();
				tx.commit();
			} catch (HibernateException e) {
				logger.info("getAppointmentByIdentifier() -- ", e);
			} finally {
				releaseSession(session);
			}
		}

		return results;
	}

	public Appointment getAppointmentByIdentifier(String appointmentIdentifier) {
		Session session = getSessionFactory().openSession();
		return getAppointmentByIdentifier(appointmentIdentifier, session);
	}
	
	
	protected List<Audiologist> getAllAudiologists(Session session) {
		List<Audiologist> results = new ArrayList<>();
		Transaction tx = session.beginTransaction();

		// see note atop this class!
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Audiologist.class);
		try {
			results.addAll(criteria.list());
			tx.commit();

		} catch (HibernateException e) {
			logger.info("getAllAudiologists() -- ", e);
		} finally {
			releaseSession(session);
		}
		return results;
	}

	public List<Audiologist> getAllAudiologists() {
		List<Audiologist> audiologists = new ArrayList<Audiologist>();
		Session session = getSessionFactory().openSession();
		audiologists.addAll(getAllAudiologists(session));
		return audiologists;
	}

}