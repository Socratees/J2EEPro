package javaBean;


import dao.userDao;
import databaseFactory.databaseFactory;
import entity.User;

import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "userBean")
public class userBean implements userDao {
//    private static userDaoImpl userDao = new userDaoImpl();
    private DataSource dataSource;

    public userBean(){ }

    @Override
    public List<User> findUser(User user) {
        List<User> findUser = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        databaseFactory databaseFactory = new databaseFactory();
        dataSource = databaseFactory.getConnection(dataSource);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql = "select * from user where username=? and password=?";
            pre = connection.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            resultSet = pre.executeQuery();
            if (resultSet.next()) {
                User myUser = new User();
                myUser.setUsername(resultSet.getString(2));
                myUser.setPassword(resultSet.getString(3));
                findUser.add(myUser);
//                System.out.println(myUser.getUsername());
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findUser;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

//    public static userDaoImpl getInstance(){
//        return userDao;
//    }
}
