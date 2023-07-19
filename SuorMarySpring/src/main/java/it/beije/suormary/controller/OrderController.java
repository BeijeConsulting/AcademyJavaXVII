package it.beije.suormary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.beije.suormary.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

}
