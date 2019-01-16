package daoImpl;

import dao.userDao;
import databaseFactory.databaseFactory;
import entity.User;
import entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDaoImpl implements userDao {
    private static userDaoImpl userDao = new userDaoImpl();
    private DataSource dataSource;

    @PersistenceContext
    private static EntityManager em;

    @Override
    public List<UserEntity> findUser(UserEntity user) {
        List<UserEntity> findUser = new ArrayList<>();
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
        em.getTransaction().begin();
        Query query = em.createQuery("select u from UserEntity u where u.username=?1 and u.password=?2");
        query.setParameter(1,user.getUsername());
        query.setParameter(2,user.getPassword());
        List<UserEntity> myUser = query.getResultList();
        em.clear();
        if(myUser != null){
            findUser.add(myUser.get(0));
        }
        em.close();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            String sql = "select * from user where username=? and password=?";
//            pre = connection.prepareStatement(sql);
//            pre.setString(1, user.getUsername());
//            pre.setString(2, user.getPassword());
//            resultSet = pre.executeQuery();
//            if (resultSet.next()) {
//                User myUser = new User();
//                myUser.setUsername(resultSet.getString(2));
//                myUser.setPassword(resultSet.getString(3));
//                findUser.add(myUser);
////                System.out.println(myUser.getUsername());
//            }
//
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return findUser;
    }

    @Override
    public void saveUser(UserEntity user) {

    }

    @Override
    public void deleteUser(UserEntity user) {

    }

    public static userDaoImpl getInstance(){
        return userDao;
    }
}
