package dao;

import entity.User;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface userDao extends Serializable{
    public List<User> findUser(User user);

    public void saveUser(User user);

    public void deleteUser(User user);
}
