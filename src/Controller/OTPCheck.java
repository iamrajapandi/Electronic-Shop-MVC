package Controller;

import Model.OrderAddressDAO;
import Resourse.orders;

public class OTPCheck {
    public boolean verify(orders od) throws Exception{
        OrderAddressDAO ord=OrderAddressDAO.getInstance();
       if(ord.VerifyOTP(od))
        return true;
        return false;
    }
}
