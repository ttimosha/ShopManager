package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.dao.UserDao;
import ru.shop.model.UserEntity;


@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserEntity getUserById(int id) {
        return this.userDao.getUserById(id);
    }
}
