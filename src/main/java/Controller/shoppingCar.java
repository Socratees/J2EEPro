package Controller;

import entity.Orders;
import factory.ServiceFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/shoppingCar")
public class shoppingCar extends HttpServlet {
    private DataSource dataSource;
    private ResultSet resultSet;


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

//        Connection connection = null;
//        PreparedStatement pre = null;
//        databaseFactory databaseFactory = new databaseFactory();
//        dataSource = databaseFactory.getConnection(dataSource);
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        String shoppingCar = req.getParameter("shoppingCar");
//        System.out.println(shoppingCar);
        JSONArray array = new JSONArray(shoppingCar);
//        JSONObject object = (JSONObject) array.get(0);
//        System.out.println(object.getInt("commodityId"));

//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(date.getTime());

        HttpSession session = req.getSession(false);
        session.setAttribute("shoppingCar", shoppingCar);
        String username = (String) session.getAttribute("username");
        List<Orders> ordersList = new ArrayList<>();
        double price = 0;
        for (int i = 0; i < array.length(); i++) {
            Orders orders = new Orders();
            JSONObject object = (JSONObject) array.get(i);
            price += object.getDouble("price") * object.getDouble("quantity");
            orders.setUsername(username);
            orders.setCommodityId(object.getInt("commodityId"));
            orders.setQuantity(object.getDouble("quantity"));
            ordersList.add(orders);
        }

        ServiceFactory.getOrdersService().addOrders(ordersList);

//        try{
//            connection.setAutoCommit(false);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            double price = 0;
//            String sql = "insert into orders(username,commodityId,quantity,createTime) values(?,?,?,?)";
//            pre = connection.prepareStatement(sql);
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject object = (JSONObject) array.get(i);
//                price += object.getDouble("price") * object.getDouble("quantity");
////                System.out.println(object);
//                pre.setString(1, username);
//                pre.setInt(2, object.getInt("commodityId"));
//                pre.setDouble(3, object.getDouble("quantity"));
//                pre.setTimestamp(4, timestamp);
//                pre.addBatch();
//            }
//            int[] a = pre.executeBatch();
//            connection.commit();
//            connection.close();
//            PrintWriter printWriter = res.getWriter();
//            if (price >= 100) {
//                printWriter.write("1");
//            } else {
//                printWriter.write("0");
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        PrintWriter printWriter = res.getWriter();
        if (price >= 100) {
            printWriter.write("1");
        } else {
            printWriter.write("0");
        }


    }


}
