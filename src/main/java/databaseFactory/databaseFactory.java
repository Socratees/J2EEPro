package databaseFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

public class databaseFactory {
    public DataSource getConnection(DataSource dataSource){
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

        return dataSource;
    }
}
