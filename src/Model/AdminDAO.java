package Model;

import java.sql.*;

import Resourse.User;

public class AdminDAO {
    private static AdminDAO instance;
    PreparedStatement check, checkpass;

    private AdminDAO() throws Exception {
        Connection con = DbConneticon.getConnection();
        check = con.prepareStatement("Select name from user where name= ? and Role='Admin'");
        checkpass = con.prepareStatement("Select pass from user where pass= ? and name=? and Role='Admin'");
    }
    public static AdminDAO getInstance() 
    {
        if(instance==null)
            try {
                instance =new AdminDAO();
            } catch (Exception e) {
        
                e.printStackTrace();
            }
        return instance;
    }

    public boolean check(String name) throws Exception {

        check.setString(1, name);
        ResultSet rs = check.executeQuery();
        if (rs.next()) {
            User.setName(name);
            return true;
        }
        return false;

    }

    public boolean checkpass(String name) throws Exception {

        checkpass.setString(1, name);
        checkpass.setString(2, User.getName());
        ResultSet rs = checkpass.executeQuery();
        if (rs.next())
            return true;
        return false;

    }

}
