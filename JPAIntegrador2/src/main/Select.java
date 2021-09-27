package main;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Student;

public class Select {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		/*Socio p1 = em.find(Socio.class, 44113202);
		System.out.println(p1);*/
		//Recuperar todas las personas asignadas a un turno.
		List<Student> personasMismoTurno = em.createQuery("SELECT t.jugadores FROM Turno t WHERE t.id = 13").getResultList();
		personasMismoTurno.forEach(p -> System.out.println(p));
		/*Recuperar todas las personas asignadas a un turno, y marcar cuales de ellas son socios.
		List<Socio> sociosMismoTurno = em.createQuery("SELECT p FROM Turno t JOIN t.jugadores p JOIN Socio s ON p = s.id_persona").getResultList();
		sociosMismoTurno.forEach(socios -> System.out.println(socios.toString()));*/
		System.out.println("ESPACIO");
		//Recuperar todas las personas que viven en una ciudad predeterminada
		//List<Turno> personaCalle = em.createQuery("SELECT p FROM Persona p JOIN Direccion d ON p.domicilio=d WHERE d.calle='viverra pede ac diam cras pellentesque'").getResultList();
		//personaCalle.forEach(pc -> System.out.println(pc.toString()));
		
		em.getTransaction().commit();
		em.close();	emf.close();
	}
}
