package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.ComponentsDAO;
import Model.OrderAddressDAO;
import Model.OrdersDAO;
import Resourse.SalesCom;
// import Resourse.SalesCom;
import Resourse.orders;

public class ordercheck {
    private static ordercheck instance;

    public ordercheck getInstance() throws Exception {
        if (instance == null)
            instance = new ordercheck();
        return instance;
    }

    public static List<Integer> l = new ArrayList<>();

    public void check() throws Exception {

        OrderAddressDAO address = OrderAddressDAO.getInstance();
        orders od = new orders();
        int id = address.orderId(od);
        orders.setL(id);
        address.setAddress(od);
    }

    public List<List<String>> Orders() throws Exception {
        OrdersDAO od = OrdersDAO.getInstance();
        orders list = new orders();
        List<List<String>> l = od.OrderDetails(list);
        return l;

    }

    public void insertOrder(int tot) throws Exception {

        orders it = new orders();
        OrdersDAO od = OrdersDAO.getInstance();
        OrderAddressDAO ad = OrderAddressDAO.getInstance();
        int id = od.insert_order(it, tot);

        orders.setL(id);
        ad.setAddress(it);
    }

    public boolean checkOrderOrNot(orders order) throws Exception {
        OrdersDAO od = OrdersDAO.getInstance();
        if (od.CheckYesOrNo(order))
            return true;
        return false;
    }

    public List<List<String>> RetriewOrdersAll() throws Exception {
       OrdersDAO addres=OrdersDAO.getInstance();
        List<List<String>> data = addres.viewAllList();

        return data;

    }
    public List<List<String>> AdminRetriewOrdersAll() throws Exception {
       OrdersDAO addres=OrdersDAO.getInstance();
        List<List<String>> data = addres.AdminviewAllList();

        return data;

    }
    public List<List<String>> RetriewCompOrder() throws Exception {
       OrdersDAO addres=OrdersDAO.getInstance();
        List<List<String>> data = addres.viewAllComponentOrders();

        return data;

    }
    public List<List<String>> AdminRetriewCompOrder() throws Exception {
       OrdersDAO addres=OrdersDAO.getInstance();
        List<List<String>> data = addres.AdminviewAllComponentOrders();

        return data;

    }

    public String Date(orders od) throws Exception {
        OrdersDAO ad = OrdersDAO.getInstance();

        return ad.Date(od);
    }

    public String CurrentDate(orders od) throws Exception {
        OrdersDAO ad = OrdersDAO.getInstance();
        return ad.CurrentDate();
    }
    public boolean ProjectIsNull()
    {
        
        try {
            OrdersDAO ad = OrdersDAO.getInstance();
            return ad.projectIsNull();
        
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    public boolean ProjectIsNotNull()
    {
        
        try {
            OrdersDAO ad = OrdersDAO.getInstance();
            return ad.projectIsNotNull();
        
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    public boolean AdminProjectIsNull()
    {
        
        try {
            OrdersDAO ad = OrdersDAO.getInstance();
            return ad.AdminprojectIsNull();
        
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    public boolean AdminProjectIsNotNull()
    {
        
        try {
            OrdersDAO ad = OrdersDAO.getInstance();
            return ad.AdminprojectIsNotNull();
        
        } catch (Exception e) {
           e.printStackTrace();
        }
        return false;
    }
    public void InsertOrderComp(SalesCom od,int tot) 
    {
        try {
            OrderAddressDAO addres=OrderAddressDAO.getInstance();
            OrdersDAO ad = OrdersDAO.getInstance();
           int id=ad.insertIntorder(od, tot);
           orders.setL(id);
           addres.setAddress(od);

        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }

    public void updateStockComponent()
    {
        try {
            ComponentsDAO cs=ComponentsDAO.getInstance();
            cs.updateStockCompo();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    public int BillCheck() throws Exception {
        OrderAddressDAO add = OrderAddressDAO.getInstance();
        return add.FindBill();

    }

}
