package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import Resourse.*;

public class OrdersDAO {
    private static OrdersDAO instance;
    PreparedStatement pr, check, checkstatus, date,checkIsNull,checkIsNOTNull;
    PreparedStatement insertOrder, Orderdetails, insertOrderId,insertIntoOrders,GetOrderID;
    PreparedStatement  viewAllOrder,viewAllComponentOrders, AdmincheckIsNull,AdmincheckIsNOTNull,
    AdminviewAllComponentOrders, AdminviewAllOrder;

    private OrdersDAO() throws Exception {
        Connection con = DbConneticon.getConnection();
        pr = con.prepareStatement(
                "select p_id,order_id,customer_id,Order_date,p_name from project join orders using(p_id) where customer_id=? ");
        check = con.prepareStatement("select user_Id from Customer_address where user_Id=?");
        checkstatus = con
                .prepareStatement("select user_Id from Customer_address where order_id=? and isDelivered!='Yes'");
        date = con.prepareStatement("select Order_date from orders where customer_id= ?&&order_id=?");
        insertOrder = con
                .prepareStatement("insert into orders (customer_id,p_id,Quantity,Order_date) values(?,?,?,?)");
        insertIntoOrders = con
                .prepareStatement("insert into orders (customer_id,component_id,Quantity,Order_date) values(?,?,?,?)");
        insertOrderId = con
                .prepareStatement("select order_id from orders where customer_id=?&& p_id=?&&order_id!=?");
                
        GetOrderID = con
                .prepareStatement("select order_id from orders where customer_id=?&&component_id=?&&order_id!=?");
       viewAllOrder=con.prepareStatement("select order_id,name,p_name,Quantity,Order_date,ph_no from project join orders using(p_id) join Customer_address using(order_id) join user using (user_id)  where user_Id=?");
       AdminviewAllOrder=con.prepareStatement("select order_id,name,p_name,Quantity,Order_date,ph_no from project join orders using(p_id) join Customer_address using(order_id) join user using (user_id)  ");
       checkIsNull=con.prepareStatement("select * from orders where p_id Is not NULL and customer_id=?");
       checkIsNOTNull=con.prepareStatement("select * from orders where p_id Is NULL and customer_id=?");
       AdmincheckIsNull=con.prepareStatement("select * from orders where p_id Is not NULL ");
       AdmincheckIsNOTNull=con.prepareStatement("select * from orders where p_id Is NULL");
       viewAllComponentOrders=con.prepareStatement( "select order_id, name, component_values, quantity, order_date,ph_no from orders inner join user on orders.customer_id = user.user_id inner join components_details on orders.component_id = components_details.component_id where user_id=?");
       AdminviewAllComponentOrders=con.prepareStatement("select order_id,name,component_name,Quantity,Order_date,ph_no from  Electronic_Components join orders using(component_id) join Customer_address using(order_id) join user using(user_id)");
    }

    public static OrdersDAO getInstance() throws Exception {
        if (instance == null)
            instance = new OrdersDAO();
        return instance;
    }

    public boolean CheckYesOrNo(orders os) {
        try {
            checkstatus.setInt(1, os.getL());
            ResultSet rs = checkstatus.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public List<List<String>> orders() throws Exception {
        pr.setInt(1, User.getId());
        List<List<String>> l = new ArrayList<>();
        ResultSet rs = pr.executeQuery();

        while (rs.next()) {
            List<String> l1 = new ArrayList<>();
            l1.add(String.valueOf(rs.getInt(1)));
            l1.add(String.valueOf(rs.getInt(2)));
            l1.add(String.valueOf(rs.getInt(3)));
            l1.add(String.valueOf(rs.getDate(4)));
            l1.add(rs.getString(5));
            l.add(l1);

        }
        return l;
    }

    public boolean CustomerList(User use) throws Exception {

        check.setInt(1, User.getId());
        ResultSet rs = check.executeQuery();
        if (!rs.next())
            return false;
        return true;
    }

    public String Date(orders od) throws Exception {
        date.setInt(1, User.getId());
        date.setInt(2, od.getL());
        ResultSet rs = date.executeQuery();
        String format = "";
        if (rs.next()) {

            format = dateFor(rs.getDate(1));
            return format;
        }
        return "";
    }

    public int insert_order(orders ord, int tot) throws Exception {
        String date = CurrentDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        insertOrder.setInt(1, User.getId());
        insertOrder.setInt(2, ord.getViewComponents());
        insertOrder.setInt(3, tot);
        insertOrder.setDate(4, sqlDate);
        insertOrder.executeUpdate();

        insertOrderId.setInt(1, User.getId());
        insertOrderId.setInt(2, ord.getViewComponents());
        insertOrderId.setInt(3, ord.getL());
        ResultSet rs = insertOrderId.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;

    }
    public int insertIntorder(SalesCom ord, int tot) throws Exception {
        String date = CurrentDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        insertIntoOrders.setInt(1, User.getId());
        insertIntoOrders.setInt(2, ord.getCompid());
        insertIntoOrders.setInt(3, tot);
        insertIntoOrders.setDate(4, sqlDate);
        insertIntoOrders.executeUpdate();

        orders od=new orders();
        GetOrderID.setInt(1, User.getId());
        GetOrderID.setInt(2, ord.getCompid());
        GetOrderID.setInt(3,od.getL());
        ResultSet rs =  GetOrderID.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;

    }

    public String CurrentDate() throws Exception {
        Date date = new Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        String format = dateFor(sqldate);

        return format;
    }

    public List<List<String>> OrderDetails(orders o) throws Exception {
        List<List<String>> l = new ArrayList<>();
        Orderdetails.setInt(1, o.getL());
        ResultSet rs = Orderdetails.executeQuery();
        while (rs.next()) {
            List<String> l1 = new ArrayList<>();
            l1.add(String.valueOf(rs.getInt(1)));
            l1.add(rs.getString(2));
            l1.add(rs.getString(3));
            l.add(l1);
        }
        return l;
    }
    public List<List<String>>  viewAllList() 
    {
        List<List<String>>l=new ArrayList<>();
        try {
            viewAllOrder.setInt(1,User.getId());
            ResultSet rs=viewAllOrder.executeQuery();
            while(rs.next())
            {
                List<String>l1=new ArrayList<>();
                l1.add(String.valueOf(rs.getInt(1)));
                l1.add(rs.getString(2));
                l1.add(rs.getString(3));
                l1.add(String.valueOf(rs.getInt(4)));
                l1.add(String.valueOf(rs.getDate(5)));
                l1.add(rs.getString(6));
                l.add(l1);
            }
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        
        return l;

    }
    public List<List<String>>  AdminviewAllList() 
    {
        List<List<String>>l=new ArrayList<>();
        try {
            ResultSet rs=AdminviewAllOrder.executeQuery();
            while(rs.next())
            {
                List<String>l1=new ArrayList<>();
                l1.add(String.valueOf(rs.getInt(1)));
                l1.add(rs.getString(2));
                l1.add(rs.getString(3));
                l1.add(String.valueOf(rs.getInt(4)));
                l1.add(String.valueOf(rs.getDate(5)));
                l1.add(rs.getString(6));
                l.add(l1);
            }
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        
        return l;

    }
    public List<List<String>>  viewAllComponentOrders() 
    {
        List<List<String>>l=new ArrayList<>();
        try {
            viewAllComponentOrders.setInt(1,User.getId() );
            ResultSet rs=viewAllComponentOrders.executeQuery();
            while(rs.next())
            {
                List<String>l1=new ArrayList<>();
                l1.add(String.valueOf(rs.getInt(1)));
                l1.add(rs.getString(2));
                l1.add(rs.getString(3));
                l1.add(String.valueOf(rs.getInt(4)));
                l1.add(String.valueOf(rs.getDate(5)));
                l1.add(rs.getString(6));
                l.add(l1);
            }
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        
        return l;

    }
    public List<List<String>>  AdminviewAllComponentOrders() 
    {
        List<List<String>>l=new ArrayList<>();
        try {
           
            ResultSet rs=AdminviewAllComponentOrders.executeQuery();
            while(rs.next())
            {
                List<String>l1=new ArrayList<>();
                l1.add(String.valueOf(rs.getInt(1)));
                l1.add(rs.getString(2));
                l1.add(rs.getString(3));
                l1.add(String.valueOf(rs.getInt(4)));
                l1.add(String.valueOf(rs.getDate(5)));
                l1.add(rs.getString(6));
                l.add(l1);
            }
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        
        return l;

    }

    public boolean projectIsNull()
    {
        try {
            checkIsNull.setInt(1,User.getId());
            ResultSet rs=checkIsNull.executeQuery();
            if(rs.next())
            return true;
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return false;
    }
    public boolean projectIsNotNull()
    {
        try {
            checkIsNOTNull.setInt(1,User.getId());
            ResultSet rs=checkIsNOTNull.executeQuery();
            if(rs.next())
            return true;
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return false;
    }
    public boolean AdminprojectIsNull()
    {
        try {
           
            ResultSet rs=AdmincheckIsNull.executeQuery();
            if(rs.next())
            return true;
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return false;
    }
    public boolean AdminprojectIsNotNull()
    {
        try {
           
            ResultSet rs=AdmincheckIsNOTNull.executeQuery();
            if(rs.next())
            return true;
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return false;
    }

    private static String dateFor(Date date2) throws Exception {
        SimpleDateFormat dateFor;
        dateFor = new SimpleDateFormat("yyyy-MM-dd");
        return dateFor.format(date2);

    }

}
