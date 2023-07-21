package it.beije.suormary.bookstore2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order as o WHERE user_id = :userId")
	public List<Order> findOrderListByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT oi FROM OrderItem as oi WHERE order_id = :orderId")
	public List<OrderItem> findOrderItemsOfAOrder(@Param("orderId") Integer orderId);
	
}
