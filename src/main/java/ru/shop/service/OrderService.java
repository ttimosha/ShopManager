package ru.shop.service;

import ru.shop.model.Order;

import java.util.List;

public interface OrderService {
    public void addOrder(Order order);

    public void updateOrder(Order order);

    public void removeOrder(int id);

    public Order getOrderById(int id);

    public List<Order> listOrders();

    public List<Order> listOrdersByUser(int id);
}
