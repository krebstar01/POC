package com.watermark.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.watermark.model.BaseDocument;
import com.watermark.model.Watermark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 
 *  NOTE: as of very recently (recent 5.x minor version)
 *  the underlying interfaces for hibernate "criteria" queries are deprecated 
 *  and hibernate will move towards JPA criteria.CriteriaQuery
 *  currently there are not Hibernate implementations for criteria.CriteriaQuery!
 * 
 * */
public class WatermarkDao extends BaseDao {

	public WatermarkDao() {
	}

	final static Logger logger = LoggerFactory.getLogger(WatermarkDao.class);

	public List<BaseDocument> getAllBaseDocuments() {
		List<BaseDocument> documents = new ArrayList<>();
		Session session = getStaticSessionFactory().openSession();
		documents.addAll(getAllBaseDocuments(session));
		return documents;
	}

	protected List<BaseDocument> getAllBaseDocuments(Session session) {
		List<BaseDocument> results = new ArrayList<>();
		Transaction tx = session.beginTransaction();

		// see note atop this class!
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(BaseDocument.class);

		try {
			results.addAll(criteria.list());
			tx.commit();

		} catch (HibernateException e) {
			logger.info("getAllBaseDocuments() -- ", e);
		} finally {
			releaseSession(session);
		}

		return results;
	}
	
	public List<Watermark> getAllWatermarks() {
		List<Watermark> watermarks = new ArrayList<>();
		Session session = getStaticSessionFactory().openSession();
		watermarks.addAll(getAllWatermarks(session));
		return watermarks;
	}

	protected List<Watermark> getAllWatermarks(Session session) {
		List<Watermark> results = new ArrayList<>();
		Transaction tx = session.beginTransaction();

		// see note atop this class!
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Watermark.class);

		try {
			results.addAll(criteria.list());
			tx.commit();

		} catch (HibernateException e) {
			logger.info("getAllWatermarks() -- ", e);
		} finally {
			releaseSession(session);
		}

		return results;
	}

	public BaseDocument fetchBaseDocumentByTicketId(String ticketId) {
		Session session = getStaticSessionFactory().openSession();
		return fetchBaseDocumentByTicketId(ticketId, session);
	}

	protected BaseDocument fetchBaseDocumentByTicketId(String ticketId, Session session) {
		Transaction tx = session.beginTransaction();
		String logBase = this.getClass() + " : createBaseDocumentAsObject ";
		BaseDocument result = null;

		try {
			// see note atop this class!
			@SuppressWarnings("deprecation")
			Criteria criteria = session.createCriteria(BaseDocument.class);
			criteria.add(Restrictions.eq("ticketId", ticketId));
			tx.commit();
			result = (BaseDocument) criteria.uniqueResult();

		} catch (HibernateException e) {
			logger.warn(logBase, e);
		} finally {
			releaseSession(session);
		}

		return result;
	}

	public BaseDocument createBaseDocumentAsObject(BaseDocument baseDocument) {
		Session session = getStaticSessionFactory().openSession();
		return createBaseDocumentAsObject(baseDocument, session);
	}

	protected BaseDocument createBaseDocumentAsObject(BaseDocument baseDocument, Session session) {
		Transaction tx = session.beginTransaction();
		String logBase = this.getClass() + " : createBaseDocumentAsObject ";
		BaseDocument result = null;
		Integer resultId = 0;
		try {
			resultId = (Integer) session.save(baseDocument);
			result = getBaseDocumentNestedByIdTx(resultId, session);
			tx.commit();

		} catch (ConstraintViolationException e) {
			logger.error(logBase + " : " + baseDocument.getId());
			throw e;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			logger.warn("createBaseDocumentAsObject Tx Failed", e);
			throw new HibernateException("createBaseDocumentAsObject Tx Failed", e);
		} finally {
			releaseSession(session);
		}

		return result;
	}

	BaseDocument getBaseDocumentNestedByIdTx(int id, Session session) {
		// see note atop this class!
		// one additional note: session.get will not work for querying a super
		// class
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(BaseDocument.class);
		criteria.add(Restrictions.eq("id", id));

		return (BaseDocument) criteria.uniqueResult();
	}
	

	public BaseDocument updateDocument(BaseDocument baseDocument) {
		Session session = getStaticSessionFactory().openSession();
		return updateDocument(baseDocument, session);
	}

	protected BaseDocument updateDocument(BaseDocument baseDocument, Session session) {
		Transaction tx = session.beginTransaction();
		String logBase = this.getClass() + " : updateDocument ";

		try {
			session.update(baseDocument);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			logger.warn(logBase, e);
		} finally {
			releaseSession(session);
		}

		return baseDocument;
	}
	
	public Watermark createWatermarkAsObject(Watermark watermark) {
		Session session = getStaticSessionFactory().openSession();
		return createWatermarkAsObject(watermark, session);
	}

	protected Watermark createWatermarkAsObject(Watermark watermark, Session session) {
		Transaction tx = session.beginTransaction();
		String logBase = this.getClass() + " : createWatermarkAsObject ";
		Watermark result = null;
		Integer resultId = 0;
		try {
			resultId = (Integer) session.save(watermark);
			result = getWatermarkNestedByIdTx(resultId, session);
			tx.commit();

		} catch (ConstraintViolationException e) {
			logger.error(logBase + " : " + watermark.getId());
			throw e;

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			logger.warn("createWatermarkAsObject Tx Failed", e);
			throw new HibernateException("createWatermarkAsObject Tx Failed", e);
		} finally {
			releaseSession(session);
		}

		return result;
	}

	Watermark getWatermarkNestedByIdTx(int id, Session session) {
		// see note atop this class!
		// one additional note: session.get will not work for querying a super
		// class
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Watermark.class);
		criteria.add(Restrictions.eq("id", id));

		return (Watermark) criteria.uniqueResult();
	}
	

}