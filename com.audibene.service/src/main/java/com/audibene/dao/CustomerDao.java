package com.audibene.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.audibene.model.Appointment;
import com.audibene.model.Customer;

/*
 * 
 *  NOTE: as of very recently (recent 5.x minor version)
 *  the underlying interfaces for hibernate "criteria" queries are deprecated 
 *  and hibernate will move towards JPA criteria.CriteriaQuery
 *  currently there are not Hibernate implementations for criteria.CriteriaQuery!
 * 
 * */
public class CustomerDao extends BaseDao {

	public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

	final static Logger logger = LoggerFactory.getLogger(CustomerDao.class);

	Customer getCustomerByName(String customerName, Session session) {

		Customer results = null;
		if (customerName != null && !customerName.trim().equals("")) {
			Transaction tx = session.beginTransaction();
			try {
				// see note atop this class!
				// one additional note: session.get will not work for querying a
				// super
				// class
				@SuppressWarnings("deprecation")
				Criteria criteria = session.createCriteria(Customer.class);
				criteria.add(Restrictions.eq("customerName", customerName));
				results = (Customer) criteria.uniqueResult();
				tx.commit();
			} catch (HibernateException e) {
				logger.info("getCustomerByName() -- ", e);
			} finally {
				releaseSession(session);
			}
		}

		return results;
	}

	public Customer getCustomerByName(String customerName) {
		Session session = getSessionFactory().openSession();
		return getCustomerByName(customerName, session);
	}
	
	
	
	protected void saveCustomer(Customer customer, Session session) {

		if (customer != null) {
			Transaction tx = session.beginTransaction();
			try {
				session.save(customer);
				tx.commit();
			} catch (HibernateException e) {
				logger.info("saveCustomer() -- ", e);
			} finally {
				releaseSession(session);
			}
		}
	}
	
	public void saveCustomer(Customer customer) {
		Session session = getSessionFactory().openSession();
		saveCustomer(customer, session);
	}

	public void updateAppointment(Appointment appointment) {
		Session session = getSessionFactory().openSession();
		updateAppointment(appointment, session);
	}

	protected void updateAppointment(Appointment appointment, Session session) {

		if (appointment != null) {
			Transaction tx = session.beginTransaction();
			try {
				session.update(appointment);
				tx.commit();
			} catch (HibernateException e) {
				logger.info("rateAppointment() -- ", e);
			} finally {
				releaseSession(session);
			}
		}
	}


	protected List<Customer> getAllCustomers(Session session) {
		List<Customer> results = new ArrayList<>();
		Transaction tx = session.beginTransaction();

		// see note atop this class!
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Customer.class);

		try {
			results.addAll(criteria.list());
			tx.commit();

		} catch (HibernateException e) {
			logger.info("getAllCustomers() -- ", e);
		} finally {
			releaseSession(session);
		}

		return results;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		Session session = getSessionFactory().openSession();
		customers.addAll(getAllCustomers(session));
		return customers;
	}

}