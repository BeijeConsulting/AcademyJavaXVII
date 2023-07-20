package it.beije.suormary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.Order;
import it.beije.suormary.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	
	public Order findByOrderId(Integer orderId);
	
	public List<OrderItem> getListByOrderId(Integer orderId);

}
