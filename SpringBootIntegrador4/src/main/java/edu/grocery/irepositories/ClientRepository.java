package edu.grocery.irepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.pojo.Client;

public interface ClientRepository extends JpaRepository<Client, Object>  {

	@Modifying
	@Query(value="UPDATE Client SET dni = :newdni, name = :name, lastname = :lastname WHERE dni = :dni", nativeQuery = true)
	public void updateClient(@Param("newdni") long newdni, @Param("name") String name, @Param("lastname") String lastname, @Param("dni") long dni);
}
