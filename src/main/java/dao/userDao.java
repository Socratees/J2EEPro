package dao;

import entity.User;

import java.util.List;

public interface userDao {
    public List<User> findUser(User user);

    public void saveUser(User user);

    public void deleteUser(User user);
}
