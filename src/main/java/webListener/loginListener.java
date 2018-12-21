package webListener;

import databaseFactory.databaseFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class loginListener implements ServletContextListener, ServletContextAttributeListener {
    private DataSource dataSource;
    private ResultSet resultSet;
    int userNum = 0;
    int visitorNum = 0;
//    Connection connection = null;
//    PreparedStatement pre = null;
//    public void init() {
//        InitialContext jndiContext = null;
//        Properties properties = new Properties();
//        properties.put(Context.PROVIDER_URL, "jnp:///");
//        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
//        try {
//            jndiContext = new InitialContext(properties);
//            dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/j2ee");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scab) {

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scab) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scab) {
        addCounter(scab);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Connection connection = null;
        PreparedStatement pre = null;
        databaseFactory databaseFactory = new databaseFactory();
        dataSource=databaseFactory.getConnection(dataSource);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql = "select * from counter";
            pre = connection.prepareStatement(sql);
            resultSet = pre.executeQuery();

            if (resultSet.next()) {
                userNum = resultSet.getInt(2);
                visitorNum = resultSet.getInt(3);
            }

            connection.close();
            ServletContext servletContext = sce.getServletContext();
            servletContext.setAttribute("userNum", Integer.toString(userNum));
            servletContext.setAttribute("visitorNum", Integer.toString(visitorNum));
            servletContext.setAttribute("totalNum", Integer.toString(userNum + visitorNum));
//            servletContext.setAttribute("dataSource",dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    synchronized void addCounter(ServletContextAttributeEvent scae){
        ServletContext servletContext = scae.getServletContext();
        userNum = Integer.parseInt((String)servletContext.getAttribute("userNum")) ;
        visitorNum = Integer.parseInt((String) servletContext.getAttribute("visitorNum")) ;
//        Connection connection = null;
//        PreparedStatement pre = null;

//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Connection connection = null;
        PreparedStatement pre = null;
        databaseFactory databaseFactory = new databaseFactory();
        dataSource=databaseFactory.getConnection(dataSource);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql = "update counter set userNum=?,visitorNum=? where counterId=1";
            pre = connection.prepareStatement(sql);
            pre.setInt(1,userNum);
            pre.setInt(2,visitorNum);
            int n = pre.executeUpdate();
            connection.close();
//            servletContext .setAttribute("dataSource",dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContext servletContext = sce.getServletContext();
//        servletContext.removeAttribute("dataSource");
    }
}
