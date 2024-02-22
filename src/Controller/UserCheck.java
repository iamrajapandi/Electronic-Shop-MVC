package Controller;

import Model.OrdersDAO;
import Model.UserDAO;
import Resourse.User;

public class UserCheck {
    public static String Username="";
    public boolean ExistOrNot(String name) throws Exception {
        UserDAO u = UserDAO.getInstance();
        Username=name;
       
        try {
            if (u.check(name)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;

    }
    public boolean ExistPass(String pass) throws Exception{
        UserDAO u = UserDAO.getInstance();
       
        try {
            if (u.checkpass(pass)) {
               
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
       
        return false;
    }
public boolean SIGNUP(User u) throws Exception{
    UserDAO signup=UserDAO.getInstance();
    if(signup.signUp(u))
    return true;
    return false;
}
    public boolean OrderCheck() throws Exception {
        OrdersDAO order = OrdersDAO.getInstance();
        User u = new User();
        try {
            if (order.CustomerList(u)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
