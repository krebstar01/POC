package com.audibene.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class BaseDao<E> extends AbstractDAO<E> {


	 private SessionFactory sessionFactory;
    
    public BaseDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }


    protected Session releaseSession(Session session) {
        if (session != null) {
            if (session.isOpen()) {
                session.close();
            }
        }

        return session;
    }

    protected Session flushSession(Session session) {
        if (session != null) {
            if (session.isOpen()) {
                session.flush();
            }
        }

        return session;
    }

    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    

}