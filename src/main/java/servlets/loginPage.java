package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

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

@WebServlet("/login")
public class loginPage extends HttpServlet {
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

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
//        String username = (String)session.getAttribute("username");
        if (session != null && session.getAttribute("username") != null) {
            res.sendRedirect("/mainPage/user.jsp");
        } else {
            res.sendRedirect("/index.jsp");
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        if(req.getParameter("visitor")!=null){
            username = "visitor";
            password = "visitor";
        }
        ServletContext servletContext = getServletContext();
        int userNum = Integer.parseInt((String) servletContext.getAttribute("userNum"));
        int visitorNum = Integer.parseInt((String)servletContext.getAttribute("visitorNum")) ;

        boolean isUser = isUser(username, password);
        if (isUser) {
            System.out.println(req.getParameter("visitor"));

            session = req.getSession(true);
            session.setAttribute("username", username);
            req.setAttribute("username", username);

            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(Integer.MAX_VALUE);
            res.addCookie(cookie);

            if (username.equals("visitor")) {
                visitorNum++;
            } else {
                userNum++;
            }
            servletContext.setAttribute("userNum", Integer.toString(userNum));
            servletContext.setAttribute("visitorNum", Integer.toString(visitorNum));
            servletContext.setAttribute("totalNum", Integer.toString(userNum+visitorNum));

            session.setAttribute("userNum", Integer.toString(userNum));
            session.setAttribute("visitorNum", Integer.toString(visitorNum));
            session.setAttribute("totalNum", Integer.toString(userNum+visitorNum));
            session.setAttribute("shoppingCar", "");

            res.sendRedirect(res.encodeRedirectURL("/mainPage/user.jsp"));
        } else {
            res.sendRedirect(res.encodeRedirectURL("/errorPage/error.jsp"));
        }
    }

    public boolean isUser(String username, String password) {
        Connection connection = null;
        PreparedStatement pre = null;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            String sql = "select username from user where username=? and password=?";
            pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            resultSet = pre.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
