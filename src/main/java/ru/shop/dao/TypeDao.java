package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.Type;

import java.util.List;

@Repository
public class TypeDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Type> listTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Type> typeList = session.createQuery("From Type").list();
        return typeList;
    }
}
