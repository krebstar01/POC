package com.audibene.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.audibene.config.AudibeneConfiguration;
import com.audibene.manager.AudibeneManager;
import com.audibene.model.Appointment;
import com.audibene.model.Audiologist;
import com.audibene.model.Customer;
import com.audibene.rest.resource.AudibeneApplication;
import com.audibene.rest.resource.CustomerApplication;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.swagger.annotations.SwaggerDefinition;
import io.federecio.dropwizard.swagger.SwaggerBundle;

public class AudibeneService extends Application<AudibeneConfiguration>{
//persistence
	// http://stackoverflow.com/questions/28155220/hsqldb-persist-in-memory-database
	
	
	public static void main(String[] args) throws Exception {
		new AudibeneService().run(args);
	}

	@Override
	public void initialize(Bootstrap<AudibeneConfiguration> bootstrap) {
	    bootstrap.addBundle(new SwaggerBundle<AudibeneConfiguration>() {
	    	
	        @Override
	        protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AudibeneConfiguration configuration) {
	            return configuration.swaggerBundleConfiguration;
	        }
	    });
	}
	
	
	private final HibernateBundle<AudibeneConfiguration> hibernate = new HibernateBundle<AudibeneConfiguration>(
			Customer.class, Audiologist.class, Appointment.class) {

		@Override
		public DataSourceFactory getDataSourceFactory(AudibeneConfiguration audibeneConfiguration) {
			return audibeneConfiguration.getDatabase();
		}

//		@Override
//		protected Hibernate4Module createHibernate4Module() {
//			return new Hibernate4Module();
//		}
		
		   @Override
		    protected String name() {
		        return "hibernate.accesslog";
		    }
	};
	
	
	// https://github.com/smoketurner/dropwizard-swagger
	
	
	@Override
	public void run(AudibeneConfiguration configuration, Environment environment) throws Exception {
		
		List<Class<?>> entities = new ArrayList<>();
		
		entities.add(Customer.class); 
		entities.add(Audiologist.class); 
		entities.add(Appointment.class); 
		
		PooledDataSourceFactory dbConfig = hibernate.getDataSourceFactory(configuration);
		SessionFactoryFactory ssn = new SessionFactoryFactory();
		SessionFactory sessionFactory = ssn.build(hibernate, environment, dbConfig, entities);		
		AudibeneManager audibeneManager = new AudibeneManager(sessionFactory);
		//TODO apply sessionfactory to app on to manager onto daos
		environment.jersey().register(new AudibeneApplication(audibeneManager));
		environment.jersey().register(new CustomerApplication(audibeneManager));
	}
	
	
}
