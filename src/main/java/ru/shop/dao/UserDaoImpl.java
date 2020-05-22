package ru.shop.dao;

import org.dom4j.util.UserDataElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.shop.model.User;
import ru.shop.model.Authority;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao, UserDetailsService {
    private SessionFactory sessionFactory;

    @Autowired
    BCryptPasswordEncoder encoder;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private AuthorityDao authorityDao;
    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    @Override
    public User getUserById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        try {
            User user = (User) session.get(User.class, id);
            return user;
        } catch (Exception e) { }
        return null;
}

    @Override
    public boolean saveUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        User _user = (User) this.loadUserByUsername(user.getUsername());
        if (_user != null){
            return false;
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            Collection<Authority> authorityCollection = new ArrayList<Authority>();
            authorityCollection.add(authorityDao.getAuthorityById(1));
            user.setAuthorities(authorityCollection);
            session.persist(user);
            return true;
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, userId);
        if(user!=null){
            session.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        user.setPassword(encoder.encode(user.getPassword()));
        session.update(user);
    }

    @Override
    public List<User> allUsers() {
        Session session =this.sessionFactory.getCurrentSession();
        List<User> listUsers = session.createQuery("from User").list();
        return listUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "from User u where u.username = :username";
        List<User> usersList = session.createQuery(hql)
                .setParameter("username", username)
                .list();
        if(CollectionUtils.isEmpty(usersList)){
            return null;
        } else {
            UserDetails user = usersList.get(0);
            return user;
        }
    }
}
