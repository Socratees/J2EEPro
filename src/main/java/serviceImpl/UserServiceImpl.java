package serviceImpl;

import dao.userDao;
import entity.User;
//import factory.DaoFactory;
import factory.EJBFactory;
import javaBean.userBean;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService = new UserServiceImpl();
    public static UserServiceImpl getInstance(){
        return userService;
    }
    @Override
    public boolean isUser(User user) {
        userBean myUser = (userBean) EJBFactory.getEJB("ejb:/ejb/userBean!dao.userDao");
        List<User> userList = myUser.findUser(user);
        return (userList != null);
    }
}
