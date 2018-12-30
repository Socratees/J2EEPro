package Controller;
import entity.Commodity;
import factory.ServiceFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/getCommodity")
public class getCommodity extends HttpServlet{
    private DataSource dataSource;
    private ResultSet resultSet;


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        res.setContentType("application/json; charset=utf-8");//返回jsonArray前需设置contentType
        List<Commodity> commodityList = ServiceFactory.getCommodityService().getAllCommodity();
        JSONArray array = new JSONArray();
        for(int i=0;i<commodityList.size();i++){
            JSONObject object = new JSONObject(commodityList.get(i));
            array.put(object);
        }
        PrintWriter printWriter = res.getWriter();
        printWriter.write(array.toString());

//        Connection connection = null;
//        PreparedStatement pre = null;
//        PrintWriter printWriter = res.getWriter();
//
//        databaseFactory databaseFactory = new databaseFactory();
//        dataSource = databaseFactory.getConnection(dataSource);
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            String sql = "select * from commodity";
//            pre = connection.prepareStatement(sql);
//            resultSet = pre.executeQuery();
//            JSONArray array = new JSONArray();
//            while(resultSet.next()) {
//                Commodity commodity = new Commodity();
//                commodity.setCommodityId(resultSet.getInt(1));
//                commodity.setCommodityName(resultSet.getString(2));
//                commodity.setPrice(resultSet.getDouble(3));
//                commodity.setAmount(resultSet.getDouble(4));
//                JSONObject object = new JSONObject(commodity);
//                array.put(object);
//            }
//            connection.close();
//
//            printWriter.write(array.toString());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }
}
