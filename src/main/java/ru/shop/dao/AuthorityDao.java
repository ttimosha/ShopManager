package ru.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.shop.model.Authority;

@Repository
public class AuthorityDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveAuth(Authority authority){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(authority);
    }

    public Authority getAuthorityById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Authority authority = (Authority) session.get(Authority.class, id);
        return authority;
    }
}
