package factory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.Naming;
import java.util.Properties;

public class EJBFactory {
    public static Object getEJB(String jndipath){
        try {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
            properties.put("jboss.naming.client.ejb.context", true);
            final Context context = new InitialContext(properties);
//            InitialContext initialContext = new InitialContext(properties);
            Object O = context.lookup(jndipath);
            System.out.print(O.toString());
            return O;
        }catch (NamingException e){
            e.printStackTrace();
        }
        return null;
    }
}
