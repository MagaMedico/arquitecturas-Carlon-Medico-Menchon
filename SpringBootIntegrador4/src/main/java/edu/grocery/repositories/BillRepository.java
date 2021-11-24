package edu.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.grocery.model.Bill;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Repositorio de la entidad @see Bill que implementa la interfaz
 * propia de JPA @see JpaRepository que contiene las operaciones básicas
 * que se pueden realizar a la base de datos por medio del ORM, además
 * de consultas específicas que se definen en esta interfaz.
 * Se utiliza en la clase @see DBFiller
 */
public interface BillRepository extends JpaRepository<Bill, Object> {

}
