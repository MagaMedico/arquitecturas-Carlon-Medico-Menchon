package edu.grocery.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.grocery.model.Client;

public interface ClientRepository extends JpaRepository<Client, Object>  {

	static final String NEWDNI = "newdni",
						NAME = "name",
						LASTNAME = "lastname",
						DNI = "dni";
	@Modifying
	@Query(value="UPDATE Client SET dni = :newdni, name = :name, lastname = :lastname WHERE dni = :dni", nativeQuery = true)
	public void updateClient(@Param(NEWDNI) long newdni, @Param(NAME) String name, @Param(LASTNAME) String lastname, @Param(DNI) long dni);
}
