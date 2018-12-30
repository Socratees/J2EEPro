package webListener;

import databaseFactory.databaseFactory;
import Context.systemContext;
import entity.Counter;
import factory.ServiceFactory;
import service.CounterService;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebListener
public class loginListener implements ServletContextListener, ServletContextAttributeListener {
    private DataSource dataSource;
    private ResultSet resultSet;
    int userNum = 0;
    int visitorNum = 0;

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
        List<Counter> counterList = new ArrayList<>();
        counterList = ServiceFactory.getCounterService().getCounterNum();
        if(counterList.size()>0){
            Counter counter = counterList.get(0);
            ServletContext servletContext = sce.getServletContext();
            servletContext.setAttribute(systemContext.USERNUM, Integer.toString(counter.getUserNum()));
            servletContext.setAttribute(systemContext.VISITORNUM, Integer.toString(counter.getVisitorNum()));
            servletContext.setAttribute(systemContext.TOTALNUM, Integer.toString(counter.getUserNum()+counter.getVisitorNum()));
        }
//        Connection connection = null;
//        PreparedStatement pre = null;
//        databaseFactory databaseFactory = new databaseFactory();
//        dataSource = databaseFactory.getConnection(dataSource);
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String sql = "select * from counter";
//            pre = connection.prepareStatement(sql);
//            resultSet = pre.executeQuery();
//
//            if (resultSet.next()) {
//                userNum = resultSet.getInt(2);
//                visitorNum = resultSet.getInt(3);
//            }
//
//            connection.close();
//            ServletContext servletContext = sce.getServletContext();
//            servletContext.setAttribute(systemContext.USERNUM, Integer.toString(userNum));
//            servletContext.setAttribute(systemContext.VISITORNUM, Integer.toString(visitorNum));
//            servletContext.setAttribute(systemContext.TOTALNUM, Integer.toString(userNum + visitorNum));
////            servletContext.setAttribute("dataSource",dataSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    synchronized void addCounter(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        Counter counter = new Counter();
        userNum = Integer.parseInt((String) servletContext.getAttribute(systemContext.USERNUM));
        visitorNum = Integer.parseInt((String) servletContext.getAttribute(systemContext.VISITORNUM));
        counter.setUserNum(userNum);
        counter.setVisitorNum(visitorNum);

        ServiceFactory.getCounterService().addCounterNum(counter);
//        Connection connection = null;
//        PreparedStatement pre = null;
//        databaseFactory databaseFactory = new databaseFactory();
//        dataSource = databaseFactory.getConnection(dataSource);
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String sql = "update counter set userNum=?,visitorNum=? where counterId=1";
//            pre = connection.prepareStatement(sql);
//            pre.setInt(1, userNum);
//            pre.setInt(2, visitorNum);
//            int n = pre.executeUpdate();
//            connection.close();
////            servletContext .setAttribute("dataSource",dataSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
