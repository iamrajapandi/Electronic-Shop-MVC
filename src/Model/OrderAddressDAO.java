package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
// import java.util.Random;
import java.util.Random;

import Resourse.SalesCom;
import Resourse.User;
import Resourse.orders;
import Resourse.project;

public class OrderAddressDAO {
    private static OrderAddressDAO instance;
    PreparedStatement id, addrressAdd;
    PreparedStatement delete, deleteNext;
    PreparedStatement update, otp, RetriewAll, Retriew;
    PreparedStatement BillNo, VerifyOTP, OrderFinish;
    PreparedStatement isDeliverCheck;

    private OrderAddressDAO() throws Exception {
        Connection con = DbConneticon.getConnection();
        addrressAdd = con.prepareStatement(
                "insert into Customer_address(order_id,user_Id,address,isDelivered) values(?,?,?,?) ");
        otp = con.prepareStatement(
                "update Customer_address set Bill_no= ? where user_Id=? and order_id=?");
        id = con.prepareStatement("Select order_id from orders where customer_id=? and p_id= ?");
        delete = con.prepareStatement("delete from Customer_address where order_id=?");
        deleteNext = con.prepareStatement("delete from orders where order_id=?");
        update = con.prepareStatement("update Customer_address set address= ? where address= ? ");
        Retriew = con.prepareStatement(
                "select order_id,p_name,Quantity,Order_date from project join orders using(p_id) join Customer_address using(order_id) where order_id=?andcustomer_id =?");
        RetriewAll = con.prepareStatement(
                "select order_id,p_name,Quantity,Order_date from project join orders using(p_id) join Customer_address using(order_id) where customer_id =?; ");
        BillNo = con.prepareStatement("Select Bill_no from Customer_address where user_Id=?");
        VerifyOTP = con.prepareStatement("select Bill_no from  Customer_address where user_Id=? and Bill_no=?");
        OrderFinish = con.prepareStatement("update Customer_address set isDelivered =?,Bill_no=? where user_Id=?");
        isDeliverCheck = con
                .prepareStatement("select isDelivered from customer_address where isDelivered not like('Yes')");
    }

    public static OrderAddressDAO getInstance() throws Exception {
        if (instance == null)
            instance = new OrderAddressDAO();
        return instance;

    }

    public boolean deliverCheck() {
        try {
            ResultSet rs = isDeliverCheck.executeQuery();
            if (rs.next())
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public int orderId(orders od) throws Exception {
        id.setInt(2, od.getViewComponents());
        id.setInt(1, od.getId());
        ResultSet rs = id.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return 1;
    }

    public int FindBill() throws Exception {
        BillNo.setInt(1, User.getId());
        ResultSet rs = BillNo.executeQuery();
        if (rs.next()) {
            orders.setOTP(rs.getInt(1));
            return rs.getInt(1);
        }
        return 0;
    }

    public void generateOTP(int orderId,int billid) throws Exception {
        Random random = new Random();
        int randomDigit = random.nextInt(1000);
        otp.setInt(1, randomDigit);
        otp.setInt(2, orderId);
        otp.setInt(3,billid);
        otp.executeUpdate();
    }

    public void setAddress(orders od) throws Exception {

        int o = od.getL();
        addrressAdd.setInt(1, o);
        addrressAdd.setInt(2, User.getId());
        addrressAdd.setString(3, User.getAddress());
        addrressAdd.setString(4, "NO");
        addrressAdd.executeUpdate();
    }

    public void setAddress(SalesCom od) throws Exception {

        orders ob = new orders();
        int o = ob.getL();
        addrressAdd.setInt(1, o);
        addrressAdd.setInt(2, User.getId());
        addrressAdd.setString(3, User.getAddress());
        addrressAdd.setString(4, "NO");
        addrressAdd.executeUpdate();
    }
    // public void setCompOrderAddress(orders od) throws Exception {

    // int o = od.getL();
    // addrressAdd.setInt(1, o);
    // addrressAdd.setInt(2, User.getId());
    // addrressAdd.setString(3, User.getAddress());
    // addrressAdd.setString(4, "NO");
    // addrressAdd.executeUpdate();
    // }

    public int deleteAddres(orders o) throws Exception {
        delete.setInt(1, o.getL());
        int del = delete.executeUpdate();
        deleteNext.setInt(1, o.getL());
        int del1 = deleteNext.executeUpdate();
        return del + del1;

    }

    public void update(String DeliverAdd) throws Exception {
        update.setString(1, DeliverAdd);
        update.setString(2, User.getAddress());
        update.executeUpdate();
    }

    public List<List<String>> RetriewAll() throws Exception {
        RetriewAll.setInt(1, User.getId());
        ResultSet rs = RetriewAll.executeQuery();
        List<List<String>> retriew = new ArrayList<>();
        while (rs.next()) {
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(rs.getInt(1)));
            l.add(rs.getString(2));
            l.add(String.valueOf(rs.getInt(3)));
            l.add(String.valueOf(rs.getDate(4)));
            retriew.add(l);

        }
        return retriew;
    }

    public boolean VerifyOTP(orders od) throws Exception {
        VerifyOTP.setInt(1, User.getId());
        VerifyOTP.setInt(2, od.getOTP());
        ResultSet rs = VerifyOTP.executeQuery();
        if (rs.next()) {
            rs.getInt(1);
            System.out.println(rs.getInt(1));
            OrderFinish.setString(1, project.getISAvailabe());
            OrderFinish.setInt(2, 0);
            OrderFinish.setInt(3, User.getId());
            OrderFinish.executeUpdate();

            return true;
        }
        return false;

    }
}
