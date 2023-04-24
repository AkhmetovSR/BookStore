package com.example.bookstore.repositories;

import com.example.bookstore.models.Order;
import com.example.bookstore.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);
    List<Order> findAll();
    Order findById(int id);
}
