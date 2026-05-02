package com.vmc.crud.controller;

import com.vmc.crud.entity.Order;
import com.vmc.crud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public Order addOrder(@RequestBody Order order){
        System.out.println("Entered addOrder order:"+order);
        return service.addOrder(order);
    }

    @GetMapping
    public List<Order> getOrders(){
        System.out.println("Entered getOrders");
        return service.getOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id){
        System.out.println("Entered getOrderById id:"+id);
        return service.getOrderById(id);
    }
}
