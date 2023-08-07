package it.beije.suormary.bookstore4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order> findByUserId(Integer userId);
}
