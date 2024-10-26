package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resourse.SalesCom;
import Resourse.orders;

public class ComponentsDAO {
    private static ComponentsDAO instance;
    PreparedStatement proj, DelectComponent, FindComp;
    PreparedStatement findSingleCom, viewComponent;
    PreparedStatement updateStock ,available;

    private ComponentsDAO() throws Exception {
        Connection con = DbConneticon.getConnection();
        updateStock=con.prepareStatement("update components_details set AvailableStock=? where componentValueId=?");
       available=con.prepareStatement("select AvailableStock from components_details where componentValueId=?");
        viewComponent = con.prepareStatement("select * from components join c_model using(c_id) where p_id=?");
        findSingleCom = con.prepareStatement("select c_id from components join c_model where c_name=? and model_no=?");
        FindComp = con.prepareStatement("select c_id from components where p_id= ?");
        DelectComponent = con.prepareStatement("delete from components where p_id=?");
        proj = con.prepareStatement(
                "select  c_id ,c_name ,c_value,quantity,brand_name,organisation,model_no from components join c_model using(c_id) where p_id =?");
    }

    public static ComponentsDAO getInstance() throws Exception {
        if (instance == null)
            instance = new ComponentsDAO();
        return instance;

    }

    public List<List<String>> view() throws Exception {

        orders orders = new orders();
        proj.setInt(1, orders.getViewComponents());
        System.out.println(orders.getViewComponents());
        List<List<String>> l = new ArrayList<>();
        ResultSet rs = proj.executeQuery();
        while (rs.next()) {
            List<String> l1 = new ArrayList<>();
            l1.add(String.valueOf(rs.getInt(1)));
            l1.add(rs.getString(2));
            l1.add(rs.getString(3));
            l1.add(String.valueOf(rs.getString(4)));
            l1.add(rs.getString(5));
            l1.add(rs.getString(6));
            l1.add(rs.getString(7));

            l.add(l1);
        }
        return l;
    }

    public void deleteComp(int id) throws Exception {
        DelectComponent.setInt(1, id);
        DelectComponent.executeUpdate();
    }

    public List<Integer> findComponents(int id) throws Exception {
        List<Integer> l = new ArrayList<>();
        FindComp.setInt(1, id);
        ResultSet rs = FindComp.executeQuery();
        while (rs.next()) {
            l.add(rs.getInt(1));
        }
        return l;

    }

    public void DeleteComponent(List<Integer> l) throws Exception {
        for (int i = 0; i < l.size(); i++) {
            int del = l.get(i);
            DelectComponent.setInt(1, del);
            DelectComponent.executeUpdate("delete from components where c_id=" + l.get(i) + "");
        }

    }

    public int findComponents(String name, String model) throws SQLException {
        findSingleCom.setString(1, name);

        ResultSet rs = findSingleCom.executeQuery();
        return rs.getInt(1);

    }

    public void DeleteComponent(int id) throws Exception {
        DelectComponent.setInt(1, id);
        DelectComponent.executeUpdate("delete from components where c_id=" + id + "");
    }

    public List<List<String>> ViewComponentList() throws SQLException {
        List<List<String>> l1 = new ArrayList<>();
        orders viewList = new orders();
        int tot = 0;
        viewComponent.setInt(1, viewList.getViewComponents());
        ResultSet rs = viewComponent.executeQuery();
        List<String> l = new ArrayList<>();
        while (rs.next()) {
            l = new ArrayList<>();
            l.add(String.valueOf(rs.getInt(1)));
            l.add(String.valueOf(rs.getInt(2)));
            l.add(rs.getString(3));
            l.add(rs.getString(4));
            l.add(String.valueOf(rs.getInt(5)));
            l.add(rs.getString(6));
            l.add(rs.getString(7));
            l.add(rs.getString(8));
            l1.add(l);

        }
        l.add(String.valueOf(tot));
        l1.add(l);

        return l1;

    }
public int getAvailable(){
    SalesCom s=new SalesCom();
    try {
        available.setInt(1, s.getCompid());
        ResultSet rs=available.executeQuery();
        rs.next();
        return rs.getInt(1);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 1;
}
    public void updateStockCompo() {
        SalesCom  s=new SalesCom();
        try {
            updateStock.setInt(1,s.getAvailableStock());
            updateStock.setInt(2,s.getCompid());
            updateStock.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
