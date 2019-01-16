package javaBean;

import dao.counterDao;
import databaseFactory.databaseFactory;
import entity.Counter;

import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name="counterBean")
public class counterBean implements counterDao {
    private DataSource dataSource;
//    private static counterDaoImpl counterDao = new counterDaoImpl();

//    public static counterDaoImpl getInstance(){
//        return counterDao;
//    }

    public counterBean(){}

    @Override
    public List<Counter> getCounter() {
        List<Counter> counterList = new ArrayList<>();
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
        try{
            String sql = "select * from counter";
            pre = connection.prepareStatement(sql);
            resultSet = pre.executeQuery();
            Counter counter = new Counter();
            if (resultSet.next()){
                counter.setUserNum(resultSet.getInt(2));
                counter.setVisitorNum(resultSet.getInt(3));
                counterList.add(counter);
            }
            connection.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return counterList;
    }

    @Override
    public void saveCounter(Counter counter) {
        Connection connection = null;
        PreparedStatement pre = null;
        databaseFactory databaseFactory = new databaseFactory();
        dataSource = databaseFactory.getConnection(dataSource);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql = "update counter set userNum=?,visitorNum=? where counterId=1";
            pre = connection.prepareStatement(sql);
            pre.setInt(1,counter.getUserNum());
            pre.setInt(2,counter.getVisitorNum());
            int n = pre.executeUpdate();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
