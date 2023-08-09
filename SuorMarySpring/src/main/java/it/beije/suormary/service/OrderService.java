package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Order;
import it.beije.suormary.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        return null;

    }

    public Order insertOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Order order) {
        Optional<Order> o = orderRepository.findById(order.getId());

        if (!o.isPresent())
            throw new RuntimeException("ID ERRATO!!!");

        Order or = o.get();

        BeanUtils.copyProperties(order, or);

        orderRepository.save(or);

        System.out.println("updated contact : " + or);

        return order;
    }
}
