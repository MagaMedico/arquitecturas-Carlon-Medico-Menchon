package edu.grocery.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.model.Client;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Repositorio de la entidad @see Client que implementa la interfaz
 * propia de JPA @see JpaRepository que contiene las operaciones básicas
 * que se pueden realizar a la base de datos por medio del ORM, además
 * de consultas específicas que se definen en esta interfaz.
 */
public interface ClientRepository extends JpaRepository<Client, Object>  {
	/**
	 * Constantes utilizadas en los parámetros
	 */
	static final String NEWDNI = "newdni",
						NAME = "name",
						LASTNAME = "lastname",
						DNI = "dni";
	
	/**
	 * Actualiza los datos pasados por parámetro en
	 * la tabla Client mediante una consulta JPQL
	 * @param newdni long dni de cliente a reemplazar
	 * @param name String nombre de cliente a reemplazar
	 * @param lastname String apellido de cliente a reemplazar
	 * @param dni long identificador único de cliente a modificar
	 */
	@Modifying
	@Query(value="UPDATE Client SET dni = :newdni, name = :name, lastname = :lastname WHERE dni = :dni", nativeQuery = true)
	public void updateClient(@Param(NEWDNI) long newdni, @Param(NAME) String name, @Param(LASTNAME) String lastname, @Param(DNI) long dni);
}
