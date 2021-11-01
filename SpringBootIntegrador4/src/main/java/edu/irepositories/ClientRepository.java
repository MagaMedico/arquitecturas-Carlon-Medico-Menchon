package edu.irepositories;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.pojo.Client;

public interface ClientRepository extends JpaRepository<Client, Object>  {

}
