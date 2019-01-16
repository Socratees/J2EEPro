package daoImpl;

import dao.commodityDao;
import databaseFactory.databaseFactory;
import entity.Commodity;
import entity.CommodityEntity;

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

public class commodityDaoImpl implements commodityDao {
    private static commodityDaoImpl commodityDao = new commodityDaoImpl();
    private DataSource dataSource;

    @PersistenceContext
    private static EntityManager em;

    public static commodityDaoImpl getInstance(){
        return commodityDao;
    }

    @Override
    public List<CommodityEntity> findCommodity() {
        List<CommodityEntity> commodityList = new ArrayList<>();
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
        Query query = em.createQuery("select c from CommodityEntity c");
        commodityList = query.getResultList();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try{
//            String sql = "select * from commodity";
//            pre = connection.prepareStatement(sql);
//            resultSet = pre.executeQuery();
//            while(resultSet.next()){
//                Commodity commodity = new Commodity();
//                commodity.setCommodityId(resultSet.getInt(1));
//                commodity.setCommodityName(resultSet.getString(2));
//                commodity.setPrice(resultSet.getDouble(3));
//                commodity.setAmount(resultSet.getDouble(4));
//                commodityList.add(commodity);
//            }
//            connection.close();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        return commodityList;
    }

    @Override
    public void saveCommodity(CommodityEntity commodity) {

    }

    @Override
    public void deleteCommodity(int commodityId) {

    }
}
