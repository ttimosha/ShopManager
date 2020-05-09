package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity getUserById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        UserEntity user = (UserEntity) session.get(UserEntity.class, new Integer(id));
        return user;
}
}
