package Model;

import java.sql.*;
// import java.util.*;

import Resourse.User;

public class UserDAO  {
    private static UserDAO instance;
    PreparedStatement pst, check, id,change,proj,checkpass;

    private UserDAO() throws Exception {
        Connection con=DbConneticon.getConnection();
        pst =con.prepareStatement("insert into user(name,mail,pass,ph_no,address,Role) values(?,?,?,?,?,?)");
        check =con.prepareStatement("Select name from user where name= ? and Role='User'");
        checkpass =con.prepareStatement("Select pass from user where name= ? and pass=? and Role like('User')");
        id = con.prepareStatement("select user_id,address from user where name = ?");
      
        change=con.prepareStatement("update user set address =? where address=?");
    }
public static UserDAO getInstance() throws Exception{
    if(instance==null)
    instance=new UserDAO();
    return instance;
}
    public boolean signUp(User use) throws Exception {
        pst.setString(1, User.getName());
        pst.setString(2, User.getMail());
        pst.setString(3, User.getPass());
        pst.setString(4, User.getPh());
        pst.setString(5, User.getAddress());
        pst.setString(6, "User");
        int c = pst.executeUpdate();
        if (c == 1)
            return true;

        return false;
    }

    // public boolean check(String name,String pass) throws Exception {

    //     check.setString(1, name);
    //     ResultSet rs = check.executeQuery();
    //     while (rs.next())
    //     {
    //         String one=rs.getString(1);
    //         String two=rs.getString(2);
    //         if(one.equals(name)&&two.equals(pass))
    //         return true;

    //     }
    //     return false;
    // }
    public boolean check(String name) throws Exception {

        check.setString(1, name);
        ResultSet rs = check.executeQuery();
        if(rs.next())
        {
            User.setName(name);

            return true;
        }

    
        return false;
    }
    public boolean checkpass(String pass) throws Exception {

        checkpass.setString(2, pass);
        checkpass.setString(1, User.getName());
        ResultSet rs = checkpass.executeQuery();
        if(rs.next())
        {
            User.setName(pass);
            return true;

        }

        return false;
    }

    public void enter(User sent) throws Exception {

        id.setString(1, User.getName());
        ResultSet rs = id.executeQuery();
        while (rs.next()) {
            User.setId(rs.getInt(1));
            User.setAddress(rs.getString(2));
        }
    }
    public void changeAddress(String address) throws Exception
    {
        change.setString(1,address);
        change.setString(2,User.getAddress());
        change.executeUpdate();
        User.setAddress(address);
      
    }
    
}
