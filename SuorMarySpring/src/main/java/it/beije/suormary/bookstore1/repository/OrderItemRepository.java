package it.beije.suormary.bookstore1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore1.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	public List<OrderItem> findByOrderId(Integer idO);
}
