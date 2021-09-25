package edu.isistan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.dao.*;


public class Insert {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		/*
		Student s = new Student("Balcarce", "Favaloro"); em.persist(s);
		Persona p1 = new Persona(44113202, "Cecilia", 19, d); 
		Persona p2 = new Persona(12551830, "Susana", 63, d); em.persist(p1);	em.persist(p2);
		Socio s1 = new Socio(44113202, "Premium");
		Socio s2 = new Socio(12551830, "Vip"); em.persist(s1); em.persist(s2);
		Turno t = new Turno(2021); em.persist(t);
		t.addJugadores(p1); t.addJugadores(p2);*/
		
		em.getTransaction().commit();
		em.close();	emf.close();
		
	}
}
