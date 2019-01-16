package javaBean;

import dao.ordersDao;
import databaseFactory.databaseFactory;
import entity.Orders;

import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;

@Stateless(name = "ordersBean")
public class ordersBean implements ordersDao {
//    private static ordersDaoImpl ordersDao = new ordersDaoImpl();
    private DataSource dataSource;

    public ordersBean(){}

    @Override
    public void saveOrders(List<Orders> orders) {
        Connection connection = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        databaseFactory databaseFactory = new databaseFactory();
        dataSource = databaseFactory.getConnection(dataSource);
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String sql = "insert into orders(username,commodityId,quantity,createTime) values(?,?,?,?)";
            pre = connection.prepareStatement(sql);
            for(int i=0;i<orders.size();i++){
                Orders myOrder = orders.get(i);
                pre.setString(1,myOrder.getUsername());
                pre.setInt(2,myOrder.getCommodityId());
                pre.setDouble(3,myOrder.getQuantity());
                pre.setTimestamp(4,timestamp);
                pre.addBatch();
            }
            int[] a = pre.executeBatch();
//            System.out.println(Arrays.toString(a));
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Orders> findOrders(String username) {
        return null;
    }

//    public static ordersDaoImpl getInstance() {
//        return ordersDao;
//    }
}
