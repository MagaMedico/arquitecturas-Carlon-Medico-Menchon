package emf;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;


public class EMF implements ServletContextListener {
	
	private static EntityManagerFactory emf;
	final static String TYPE = "JPA";
	
	public EMF() { }

	@Override
	public void contextInitialized(ServletContextEvent arg) {
		emf = Persistence.createEntityManagerFactory(TYPE);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
		emf.close();
	}

	public static EntityManager createEntityManager() {
		
		if (emf == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return emf.createEntityManager();
	}

}
