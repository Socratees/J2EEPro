package databaseFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

public class databaseFactory {
    public DataSource getConnection(DataSource dataSource){

        try {
            InitialContext jndiContext = new InitialContext();
            dataSource = (DataSource) jndiContext.lookup("java:/mysqlDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
//        Properties properties = new Properties();
//        properties.put(Context.PROVIDER_URL, "jnp:///");
//        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
//        properties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
//        try {
//            jndiContext = new InitialContext(properties);
//            dataSource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/j2ee");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }

        return dataSource;
    }
}
