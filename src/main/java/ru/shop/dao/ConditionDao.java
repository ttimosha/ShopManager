package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.Condition;

import java.util.List;

@Repository
public class ConditionDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Condition> listConditions(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Condition> conditionList = session.createQuery("from Condition").list();
        return conditionList;
    }
}
