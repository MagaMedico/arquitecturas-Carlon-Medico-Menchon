package edu.grocery.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.grocery.model.Product;
import edu.grocery.repositories.ProductRepository;
/**
 * 
 * @author Cecilia Carlón: ceciliacarlon2@gmail.com
		   Magalí Médico: magamedico@gmail.com
		   Magalí Menchón: mamenchon@alumnos.exa.unicen.edu.ar	
 * @version 2.0
 * @since 22/11/2021
 * Permite acceder al repositorio @see ProductRepository
 * a fin de desacoplar la implementación del mismo y posibilitar
 * el acceso a los servicios que brindaría la aplicación
 * relacionados a la entidad @see Product
 */
@Service
public class ProductService {
	
	/**
	 * Atributo instanciado por única vez y automáticamente
	 * @see Autowired
	 */
	@Autowired
	private ProductRepository products;
	
	/**Obtiene todos productos
	 * @return una lista de @see Product
	 * @see List
	 */
	public List<Product> getProducts() {
		return this.products.findAll();
	}
	
	/**
	 * Agrega un producto dado por parámetro a la base de datos
	 * @param p entidad de tipo @see Product
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean insert(Product p) {
		this.products.save(p);
		return true;
	}
	
	/**
	 * Reemplaza los datos de un producto identificado
	 * por su id (autogenerado) con los datos recibidos por parámetro
	 * @param name String nombre del producto a reemplazar
	 * @param unitPrice int precio unitario del producto a reemplazar
	 * @param id identificador único del producto a modificar
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean update(String name, int unitPrice, long id) {
		this.products.updateProduct(name, unitPrice, id);
		return true;
	}
	
	/**
	 * Elimina de la base de datos el producto con el id dado por parámetro
	 * @param id long del identificador único del producto
	 * @return boolean del resultado de la transacción
	 */
	@Transactional
	public boolean delete(Long id) {
		this.products.deleteById(id);
		return true;
	}
	
	/**
	 * Retorna una entidad de @see Product buscada en la base de datos
	 * @param id identificador único del producto a buscar
	 * @return entidad @see Product
	 */
	@Transactional
	public Product findById(Long id) {
		return this.products.findById(id).get();
	}

}
