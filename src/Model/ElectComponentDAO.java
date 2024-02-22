package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resourse.SalesCom;
// import Resourse.orders;

public class ElectComponentDAO {
    private static ElectComponentDAO instance;
    PreparedStatement pr, pr2, Userpr;
    PreparedStatement insert, insertComponents_Details, inserIntoOrders;
    PreparedStatement selectId, selectAvailable, FindPrice;
    PreparedStatement UpdateStock, UpdatePrice;

    private ElectComponentDAO() {
        try {
            Connection con = DbConneticon.getConnection();
            pr = con.prepareStatement("select * from Electronic_Components ");
            Userpr = con.prepareStatement(
                    "select componentValueId , Component_values , AvailableStock , Price  from   Components_Details where component_id=? and  AvailableStock!=0 ");
            pr2 = con.prepareStatement(
                    "select componentValueId , Component_values , AvailableStock , Price  from   Components_Details where component_id=?");
            insertComponents_Details = con.prepareStatement(
                    "insert into Components_Details(component_id,Component_values,component_Brand,component_ModelNo,AvailableStock,Price) values(?,?,?,?,?,?)");
            insert = con.prepareStatement("insert into Electronic_Components(component_name) values(?)");
            selectId = con.prepareStatement("select component_id from Electronic_Components where component_name=?");
            selectAvailable = con.prepareStatement("select AvailableStock from Electronic_Components where value_id=?");
            UpdateStock = con
                    .prepareStatement("update Components_Details set availableStock=? where componentValueId=?");
            UpdatePrice = con.prepareStatement("update Components_Details set Price=? where componentValueId=?");
            inserIntoOrders = con
                    .prepareStatement("insert into orders (customer_id,Comp_id,Quantity,Order_date) values(?,?,?,?)");
            FindPrice = con.prepareStatement("Select Price from Components_Details where componentValueId=?");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static ElectComponentDAO getInstance() {
        if (instance == null)
            instance = new ElectComponentDAO();
        return instance;

    }

    public int AvailbleStock() {
        try {
            SalesCom od=new SalesCom();
            selectAvailable.setInt(1, od.getCompid());
            ResultSet rs = selectAvailable.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return 0;
    }

    public void UpdatePrice() {
        try {
            SalesCom od=new SalesCom();
            UpdatePrice.setInt(1, od.getPrice());
            UpdatePrice.setInt(2, od.getCompid());
            UpdatePrice.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void UpdateStock() {
        try {
            SalesCom sc=new SalesCom();
            UpdateStock.setInt(1,sc.getAvailableStock());
            UpdateStock.setInt(2, sc.getCompid());
            UpdateStock.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<List<String>> ListComp() {
        List<List<String>> l = new ArrayList<>();
        try {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                List<String> ad = new ArrayList<>();
                ad.add(String.valueOf(rs.getInt(1)));
                ad.add(rs.getString(2));
                l.add(ad);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return l;

    }

    public List<List<String>> ViewDetailed() {
        List<List<String>> l = new ArrayList<>();
        try {
            SalesCom od=new SalesCom();
            pr2.setInt(1, od.getCompid());
            ResultSet rs = pr2.executeQuery();
            while (rs.next()) {
                List<String> ad = new ArrayList<>();
                ad.add(String.valueOf(rs.getInt(1)));
                ad.add(rs.getString(2));
                ad.add(String.valueOf(rs.getInt(3)));
                ad.add(String.valueOf(rs.getInt(4)));

                l.add(ad);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return l;

    }

    public List<List<String>> UserViewDetailed() {
        List<List<String>> l = new ArrayList<>();
        try {
            SalesCom od=new SalesCom();
            Userpr.setInt(1,od.getCompid());
            ResultSet rs = Userpr.executeQuery();
            while (rs.next()) {
                List<String> ad = new ArrayList<>();
                ad.add(String.valueOf(rs.getInt(1)));
                ad.add(rs.getString(2));
                ad.add(String.valueOf(rs.getInt(3)));
                ad.add(String.valueOf(rs.getInt(4)));

                l.add(ad);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return l;

    }

    public boolean insertComponents() {
        try {
            SalesCom od=new SalesCom();
            insert.setString(1, od.getComponent());
            insert.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return true;
    }

    public boolean seletId() {

        try {
            SalesCom od=new SalesCom();
            selectId.setString(1,od.getComponent());
            ResultSet rs = selectId.executeQuery();
            if (rs.next()) {
                SalesCom.setCompid(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertComponentsDetails(SalesCom sc) {
        try {
            insertComponents_Details.setInt(1,sc.getCompid());
            insertComponents_Details.setString(2, SalesCom.getComponnetValue());
            insertComponents_Details.setString(3, sc.getComponent_Brand());
            insertComponents_Details.setString(4, sc.getComponent_ModelNo());
            insertComponents_Details.setInt(5, sc.getAvailableStock());
            insertComponents_Details.setInt(6, sc.getPrice());
            insertComponents_Details.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public int FindCompPrice(  SalesCom od ) {
   
        try {
            FindPrice.setInt(1,od.getCompid());
            ResultSet rs = FindPrice.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return 0;
    }
}
