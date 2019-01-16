package Controller;

import Context.systemContext;
import entity.User;
import factory.EJBFactory;
import factory.ServiceFactory;

import java.io.IOException;
import java.sql.*;


import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/login")
public class loginPage extends HttpServlet {
    private DataSource dataSource;
    private ResultSet resultSet;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
//        String username = (String)session.getAttribute("username");
        if (session != null && session.getAttribute(systemContext.USERNAME) != null) {
            res.sendRedirect("/mainPage/user.jsp");
        } else {
            res.sendRedirect("/index.jsp");
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = req.getParameter(systemContext.USERNAME);
        String password = req.getParameter(systemContext.PASSWORD);


        if(req.getParameter(systemContext.VISITOR)!=null){
            username = "visitor";
            password = "visitor";
        }
        ServletContext servletContext = getServletContext();
        int userNum = Integer.parseInt((String) servletContext.getAttribute(systemContext.USERNUM));
        int visitorNum = Integer.parseInt((String)servletContext.getAttribute(systemContext.VISITORNUM)) ;


        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
//        boolean isUser = isUser(username, password);
        boolean isUser = ServiceFactory.getUserService().isUser(user);
//        boolean isUser = EJBFactory.getEJB("unnamed/userBean!dao.userDao");
        if (isUser) {
//            System.out.println(req.getParameter(systemContext.VISITOR));

            session = req.getSession(true);
            session.setAttribute(systemContext.USERNAME, username);
            req.setAttribute(systemContext.USERNAME, username);

            Cookie cookie = new Cookie(systemContext.USERNAME, username);
            cookie.setMaxAge(Integer.MAX_VALUE);
            res.addCookie(cookie);

            if (username.equals("visitor")) {
                visitorNum++;
            } else {
                userNum++;
            }
            servletContext.setAttribute(systemContext.USERNUM, Integer.toString(userNum));
            servletContext.setAttribute(systemContext.VISITORNUM, Integer.toString(visitorNum));
            servletContext.setAttribute(systemContext.TOTALNUM, Integer.toString(userNum+visitorNum));

            session.setAttribute(systemContext.USERNUM, Integer.toString(userNum));
            session.setAttribute(systemContext.VISITORNUM, Integer.toString(visitorNum));
            session.setAttribute(systemContext.TOTALNUM, Integer.toString(userNum+visitorNum));
            session.setAttribute(systemContext.SHOPPINGCAR, "");

            res.sendRedirect(res.encodeRedirectURL("/mainPage/user.jsp"));
        } else {
            res.sendRedirect(res.encodeRedirectURL("/errorPage/error.jsp"));
        }
    }

//    public boolean isUser(String username, String password) {
//        Connection connection = null;
//        PreparedStatement pre = null;
//
//        databaseFactory databaseFactory = new databaseFactory();
//        dataSource = databaseFactory.getConnection(dataSource);
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            String sql = "select username from user where username=? and password=?";
//            pre = connection.prepareStatement(sql);
//            pre.setString(1, username);
//            pre.setString(2, password);
//            resultSet = pre.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
}
