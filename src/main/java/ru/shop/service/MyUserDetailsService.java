package ru.shop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.AuthorityDao;
import ru.shop.dao.UserDao;
import ru.shop.model.User;

import java.util.List;


@Service
public class MyUserDetailsService implements UserService, UserDetailsService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        return this.userDao.saveUser(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(int userId) {
        return this.userDao.deleteUser(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> allUsers() {
        return this.userDao.allUsers();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDao.loadUserByUsername(username);
    }
}
