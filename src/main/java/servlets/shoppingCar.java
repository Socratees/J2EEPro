package servlets;

import entity.Commodity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/shoppingCar")
public class shoppingCar extends HttpServlet {
    private DataSource dataSource;
    private ResultSet resultSet;

    public void init() {
        InitialContext jndiContext = null;
        Properties properties = new Properties();
        properties.put(Context.PROVIDER_URL, "jnp:///");
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        try {
            jndiContext = new InitialContext(properties);
            dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/j2ee");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Connection connection = null;
        PreparedStatement pre = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String shoppingCar = req.getParameter("shoppingCar");
//        System.out.println(shoppingCar);
        JSONArray array = new JSONArray(shoppingCar);
//        JSONObject object = (JSONObject) array.get(0);
//        System.out.println(object.getInt("commodityId"));

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
//        System.out.println(timestamp);

        HttpSession session = req.getSession(false);
        session.setAttribute("shoppingCar", shoppingCar);
        String username = (String) session.getAttribute("username");

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            double price = 0;
            String sql = "insert into orders(username,commodityId,quantity,createTime) values(?,?,?,?)";
            pre = connection.prepareStatement(sql);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                price += object.getDouble("price") * object.getDouble("quantity");
//                System.out.println(object);
                pre.setString(1, username);
                pre.setInt(2, object.getInt("commodityId"));
                pre.setDouble(3, object.getDouble("quantity"));
                pre.setTimestamp(4, timestamp);
                pre.addBatch();
            }
            int[] a = pre.executeBatch();
            connection.commit();
            connection.close();
            PrintWriter printWriter = res.getWriter();
            if (price >= 100) {
                printWriter.write("1");
            } else {
                printWriter.write("0");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
