package edu.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.grocery.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Object> {

}
