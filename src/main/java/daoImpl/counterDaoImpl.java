package daoImpl;

import dao.counterDao;
import databaseFactory.databaseFactory;
import entity.Counter;
import entity.CounterEntity;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class counterDaoImpl implements counterDao {
    private DataSource dataSource;
    private static counterDaoImpl counterDao = new counterDaoImpl();


//    private static EntityManagerFactory emf;
    @PersistenceContext
    private static EntityManager em ;

//    public static void setUpBeforeClass() throws Exception{
//        emf = Persistence.createEntityManagerFactory("")
//    }
    public static counterDaoImpl getInstance() {
        return counterDao;
    }

    @Override
    public List<CounterEntity> getCounter() {
        List<CounterEntity> counterList = new ArrayList<>();
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
        Query query = em.createQuery("select c from CounterEntity c");
        counterList = query.getResultList();
        em.clear();
        em.close();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try{
//            String sql = "select * from counter";
//            pre = connection.prepareStatement(sql);
//            resultSet = pre.executeQuery();
//            Counter counter = new Counter();
//            if (resultSet.next()){
//                counter.setUserNum(resultSet.getInt(2));
//                counter.setVisitorNum(resultSet.getInt(3));
//                counterList.add(counter);
//            }
//            connection.close();
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

        return counterList;
    }

    @Override
    public void saveCounter(CounterEntity counter) {
        Connection connection = null;
        PreparedStatement pre = null;
        databaseFactory databaseFactory = new databaseFactory();
        dataSource = databaseFactory.getConnection(dataSource);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        em.getTransaction().begin();
        Query query = em.createQuery("update CounterEntity c set c.userNum=:userNum,c.visitorNum=:visitorNum where c.counterId=1");
        query.setParameter("userNum", counter.getUserNum());
        query.setParameter("visitorNum", counter.getVisitorNum());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            String sql = "update counter set userNum=?,visitorNum=? where counterId=1";
//            pre = connection.prepareStatement(sql);
//            pre.setInt(1,counter.getUserNum());
//            pre.setInt(2,counter.getVisitorNum());
//            int n = pre.executeUpdate();
//            connection.close();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
    }
}
