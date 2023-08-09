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

import it.beije.suormary.model.OrderItem;
import it.beije.suormary.service.OrderItemService;

@RestController
@RequestMapping(value = "/api")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping(value = "/orderItems")
    public List<OrderItem> listOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping(value = "/orderItems/{id}")
    public OrderItem orderItem(@PathVariable Integer id) {
        return orderItemService.getOrderItemsById(id);
    }

    @PostMapping(value = "/orderItems")
    public OrderItem insertOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.insertOrderItem(orderItem);
    }

    @DeleteMapping(value = "/orderItems/{id}")
    public Map<String, String> deleteOrderItem(@PathVariable Integer id) {
        Map<String, String> message = new HashMap<String, String>();

        try {
            orderItemService.deleteOrderItemById(id);
            message.put("message", "autore rimosso correttamente");
        } catch (Exception e) {
            message.put("message", e.getMessage());
        }

        return message;
    }

    @PutMapping(value = "/orderItems/{id}")
    public OrderItem updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) {

        if (id.compareTo(orderItem.getId()) != 0)
            throw new RuntimeException("ID NON CORRISPONDENTI!!!");

        return orderItemService.updateOrderItem(orderItem);
    }
}