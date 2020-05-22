package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.OrderDao;
import ru.shop.model.Order;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public void addOrder(Order order) {
        this.orderDao.addOrder(order);
    }

    @Transactional
    @Override
    public void updateOrder(Order order) {
        this.orderDao.updateOrder(order);
    }

    @Transactional
    @Override
    public void removeOrder(int id) {
        this.orderDao.removeOrder(id);
    }

    @Transactional
    @Override
    public Order getOrderById(int id) {
        return this.orderDao.getOrderById(id);
    }

    @Transactional
    @Override
    public List<Order> listOrders() {
        return this.orderDao.listOrders();
    }

    @Transactional
    @Override
    public List<Order> listOrdersByUser(int id) {
        return this.orderDao.listOrdersByUser(id);
    }
}
