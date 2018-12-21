package servlets;
import entity.Commodity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashSet;
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

@WebServlet("/getCommodity")
public class getCommodity extends HttpServlet{
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

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        Connection connection = null;
        PreparedStatement pre = null;
        res.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = res.getWriter();
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql = "select * from commodity";
            pre = connection.prepareStatement(sql);
            resultSet = pre.executeQuery();
            JSONArray array = new JSONArray();
            while(resultSet.next()) {
                Commodity commodity = new Commodity();
                commodity.setCommodityId(resultSet.getInt(1));
                commodity.setCommodityName(resultSet.getString(2));
                commodity.setPrice(resultSet.getDouble(3));
                commodity.setAmount(resultSet.getDouble(4));
                JSONObject object = new JSONObject(commodity);
                array.put(object);
            }
            connection.close();

            printWriter.write(array.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
