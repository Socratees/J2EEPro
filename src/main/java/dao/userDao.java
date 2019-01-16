package dao;

import entity.User;
import entity.UserEntity;

import java.util.List;

public interface userDao {
    public List<UserEntity> findUser(UserEntity user);

    public void saveUser(UserEntity user);

    public void deleteUser(UserEntity user);
}
