package serviceImpl;

import entity.User;
import factory.DaoFactory;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService = new UserServiceImpl();
    public static UserServiceImpl getInstance(){
        return userService;
    }
    @Override
    public boolean isUser(User user) {
        List<User> userList = DaoFactory.getUserDao().findUser(user);
        return (userList != null);
    }
}
