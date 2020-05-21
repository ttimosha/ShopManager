package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.Order;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
    }

    @Override
    public void updateOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
    }

    @Override
    public void removeOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.load(Order.class, id);
        if(order!=null){
            session.delete(order);
        }
    }

    @Override
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.get(Order.class, id);
        return order;
    }

    @Override
    public List<Order> listOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> orderList = session.createQuery("from Order").list();
        return orderList;
    }

}
