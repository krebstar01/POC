package com.watermark.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.google.common.base.Preconditions;
import com.watermark.model.BaseDocument;
import com.watermark.model.Book;
import com.watermark.model.Journal;
import com.watermark.model.Watermark;

public class BaseDao {



    public BaseDao(SessionFactory staticSessionFactory) {
        BaseDao.staticSessionFactory = (SessionFactory) Preconditions.checkNotNull(staticSessionFactory);
    }
    public BaseDao(){

    }


    private static SessionFactory staticSessionFactory = buildSessionFactory();


    public static SessionFactory getStaticSessionFactory() {
        return staticSessionFactory;
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

    private static SessionFactory buildSessionFactory() {
        if (staticSessionFactory == null) {
        	//staticSessionFactory = new Configuration().configure().buildSessionFactory();
        	//note with 5.X you no longer need to use ServiceRegistery
        	//http://stackoverflow.com/questions/32405031/hibernate-5-org-hibernate-mappingexception-unknown-entity/32711654#32711654
        	//ServiceRegistery seems to blow away cof configurations
        	
        	///4.3.5 and before? see below example...
//            Configuration config = new Configuration();
//            config.configure(HIBERNATE_XML);
//            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
//            staticSessionFactory = config.buildSessionFactory(sr);
        	
        	
        	
        	
        	
        	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
        			.configure( "hibernate.cfg.xml" )
        			.build();

        			Metadata metadata = new MetadataSources( standardRegistry )
        			.addAnnotatedClass(BaseDocument.class)
        			.addAnnotatedClass(Book.class)
        			.addAnnotatedClass(Journal.class)
        			.addAnnotatedClass(Watermark.class)
        			.getMetadataBuilder()
        			.applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
        			.build();

        			staticSessionFactory = metadata.getSessionFactoryBuilder().build();
        	
        	
        }
        return staticSessionFactory;
    }

}
