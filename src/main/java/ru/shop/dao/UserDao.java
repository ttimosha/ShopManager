package ru.shop.dao;

import ru.shop.model.UserEntity;

public interface UserDao {
    public UserEntity getUserById(int id);
}
