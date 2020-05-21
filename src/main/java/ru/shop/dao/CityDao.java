package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.City;

import java.util.List;

@Repository
public class CityDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<City> listCities() {
        Session session = this.sessionFactory.getCurrentSession();
        List<City> cityList = session.createQuery("from City").list();
        return cityList;
    }
}
