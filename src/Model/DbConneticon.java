package Model;

import java.sql.*;
import Util.Env;

public class DbConneticon {
   private static Connection con=null;

    public static Connection getConnection() throws Exception {
        if (con==null) {
            con = DriverManager.getConnection(Env.url, Env.username, Env.pass);
            
        }
        return con;
        
    }

}
