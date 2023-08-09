package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.OrderItem;
import it.beije.suormary.repository.OrderItemRepository;
@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemsById(Integer id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id); 
        if(orderItem.isPresent()) {
            return orderItem.get();
        }
        return null;
    }

    public OrderItem insertOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItemById(Integer id) {
        orderItemRepository.deleteById(id);

    }

    public void deleteOrderItemByOrderId(Integer orderId) {
        orderItemRepository.deleteOrderItemByOrderId(orderId);
    }

    public OrderItem updateOrderItem(OrderItem orderItem) {
        Optional<OrderItem> o = orderItemRepository.findById(orderItem.getId());

        if (!o.isPresent())
            throw new RuntimeException("ID ERRATO!!!");

        OrderItem or = o.get();

        BeanUtils.copyProperties(orderItem, or);

        orderItemRepository.save(or);

        System.out.println("updated contact : " + or);

        return orderItem;
    }

}
