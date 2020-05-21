package ru.shop.service;

import ru.shop.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(int id);
    public boolean saveUser(User user);
    public boolean deleteUser(int userId);
    public List<User> allUsers();
}
