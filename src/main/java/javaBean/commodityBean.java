package javaBean;

import dao.commodityDao;
import databaseFactory.databaseFactory;
import entity.Commodity;

import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless(name="commodityBean")
public class commodityBean implements commodityDao {
//    private static commodityDaoImpl commodityDao = new commodityDaoImpl();
    private DataSource dataSource;
//    public static commodityDaoImpl getInstance(){
//        return commodityDao;
//    }

    public commodityBean(){}

    @Override
    public List<Commodity> findCommodity() {
        List<Commodity> commodityList = new ArrayList<>();
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
            String sql = "select * from commodity";
            pre = connection.prepareStatement(sql);
            resultSet = pre.executeQuery();
            while(resultSet.next()){
                Commodity commodity = new Commodity();
                commodity.setCommodityId(resultSet.getInt(1));
                commodity.setCommodityName(resultSet.getString(2));
                commodity.setPrice(resultSet.getDouble(3));
                commodity.setAmount(resultSet.getDouble(4));
                commodityList.add(commodity);
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return commodityList;
    }

    @Override
    public void saveCommodity(Commodity commodity) {

    }

    @Override
    public void deleteCommodity(int commodityId) {

    }
}
