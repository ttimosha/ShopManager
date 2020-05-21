package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.Size;

import java.util.List;

@Repository
public class SizeDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Size> listSizes(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Size> sizeList = session.createQuery("from Size").list();
        return sizeList;
    }
}
