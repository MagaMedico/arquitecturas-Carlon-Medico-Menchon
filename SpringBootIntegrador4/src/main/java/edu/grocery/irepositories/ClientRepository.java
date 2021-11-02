package edu.grocery.irepositories;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.grocery.pojo.Client;

public interface ClientRepository extends JpaRepository<Client, Object>  {

}
