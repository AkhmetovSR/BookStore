package com.example.bookstore.services;

import com.example.bookstore.models.Order;
import com.example.bookstore.models.Product;
import com.example.bookstore.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void updateStatus(int id, Order order){
        Order updateOrder = orderRepository.findById(id);
        updateOrder.setStatus(order.getStatus());
        orderRepository.save(updateOrder);
    }
}
