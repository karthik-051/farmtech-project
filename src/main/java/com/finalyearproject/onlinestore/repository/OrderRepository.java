package com.mitron.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitron.onlinestore.domain.entities.Order;
import com.mitron.onlinestore.domain.entities.enumeration.Status;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllOrdersByCustomer_UsernameOrderByIssuedOn(String customerName);

    List<Order> findAllOrdersByStatus_OrderByIssuedOn(Status status);

    List<Order> findAllOrdersByCustomerUsernameAndStatus_OrderByIssuedOn(String customerName, Status status);
}
