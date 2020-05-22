package ru.shop.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.shop.model.User;

import java.util.List;

public interface UserDao {
    public User getUserById(int id);
    public boolean saveUser(User user);
    public boolean deleteUser(int userId);
    public void updateUser(User user);
    public List<User> allUsers();
    public UserDetails loadUserByUsername(String username);
}
