package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Order;
import it.beije.suormary.service.OrderService;

@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/orders")
    public List<Order> getOrderList() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = "/orders/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping(value = "/orders")
    public Order insertOrder(@RequestBody Order order) {
        return orderService.insertOrder(order);
    }

    @DeleteMapping(value = "/orders/{id}")
    public Map<String, String> deleteOrder(@PathVariable Integer id) {
        Map<String, String> message = new HashMap<String, String>();

        try {
            orderService.deleteOrderById(id);
            message.put("message", "order rimosso correttamente");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }

        return message;
    }

    @PutMapping(value = "/orders/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {

        if (id.compareTo(order.getId()) != 0)
            throw new RuntimeException("ID NON CORRISPONDENTI!!!");

        return orderService.updateOrder(order);
    }

}
