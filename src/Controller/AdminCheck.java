package Controller;

import Model.AdminDAO;
import Model.OrderAddressDAO;


public class AdminCheck {
     public boolean ExistOrNot(String name) throws Exception {
      AdminDAO u=AdminDAO.getInstance();
        try {
            if (!u.check(name)) {
                return false;
            }
          
        } catch (Exception e) {
            System.out.println(e);

        }
        return true;

    }
     public boolean ExistOrNotPass(String pass) throws Exception {
      AdminDAO u=AdminDAO.getInstance();
        try {
            if (u.checkpass(pass)) {
            return true;
            }
          
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;

    }
public void generateOTP(int otp) throws Exception{
    OrderAddressDAO o=OrderAddressDAO.getInstance();
    o.generateOTP(otp);
}
}
