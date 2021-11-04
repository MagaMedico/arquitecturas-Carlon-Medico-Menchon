package edu.grocery.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.grocery.pojo.Bill;

public interface BillRepository extends JpaRepository<Bill, Object> {

}
