package edu.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.model.Product;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Repositorio de la entidad @see Product que implementa la interfaz
 * propia de JPA @see JpaRepository que contiene las operaciones básicas
 * que se pueden realizar a la base de datos por medio del ORM, además
 * de consultas específicas que se definen en esta interfaz.
 */
public interface ProductRepository extends JpaRepository<Product, Object> {
	/**
	 * Constantes utilizadas en los parámetros
	 */
	static final String NAME = "name",
						UNITPRICE = "unitPrice",
						ID = "id";

	/**
	 * Actualiza los datos pasados por parámetro en
	 * la tabla Product mediante una consulta JPQL
	 * @param name Stringnombre de producto a reemplazar
	 * @param unitPrice int precio unitario del producto a reemplazar
	 * @param id long identificador único del producto a modificar
	 */
	@Modifying
	@Query("UPDATE Product SET name = :name, unitPrice = :unitPrice WHERE id = :id")
	public void updateProduct(@Param("name") String name, @Param("unitPrice") int unitPrice, @Param("id") long id);
	
}
