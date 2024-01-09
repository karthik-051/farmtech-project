package com.mitron.onlinestore.service;

import java.util.List;

import com.mitron.onlinestore.domain.entities.enumeration.Status;
import com.mitron.onlinestore.domain.models.service.OrderServiceModel;

public interface OrderService {

    void createOrder(OrderServiceModel orderServiceModel);

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByCustomer(String username);

    OrderServiceModel findOrderById(String id);

    List<OrderServiceModel> findOrdersByStatus(Status status);

    void changeOrderStatus(String id);

    List<OrderServiceModel> findOrdersByCustomerAndStatus(String customerName, Status status);
    void acceptOrder(String orderId);
    
    void shipOrder(String orderId);
}
