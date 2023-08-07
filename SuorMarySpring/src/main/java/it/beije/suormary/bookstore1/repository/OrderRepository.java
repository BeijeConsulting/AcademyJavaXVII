package it.beije.suormary.bookstore1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore1.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
