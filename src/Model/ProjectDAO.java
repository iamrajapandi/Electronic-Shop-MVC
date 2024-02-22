package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// import Resourse.User;
import Resourse.orders;

public class ProjectDAO extends DbConneticon {
    private static ProjectDAO instance;
    PreparedStatement proj, projectCheck, projInsert, projId;
    PreparedStatement insertcomponents, insertC_model;
    PreparedStatement availableProject;
    PreparedStatement findComId, updateProject, DeleteProject;
    PreparedStatement UpdateStock;

    private ProjectDAO() throws Exception {
        Connection con=DbConneticon.getConnection();
        projInsert = con.prepareStatement("insert into project(p_name,more_details,AvailableStock,price) values(?,?,?,?)");
        insertcomponents = con
                .prepareStatement("insert into components(p_id,c_name,c_value,quantity,price) values(?,?,?,?,?)");
        insertC_model = con.prepareStatement("insert into c_model values(?,?,?,?)");
        projectCheck = con.prepareStatement("select * from project where p_name=?");
        findComId = con.prepareStatement("select c_id from components where c_name=?");
        updateProject = con.prepareStatement("update project set p_name=? where p_id=?");
        UpdateStock=con.prepareStatement("update project set AvailableStock=? where p_id=?");
        projId = con.prepareStatement("select p_id from project where p_name=?");
        DeleteProject = con.prepareStatement("delete from project where p_id=?");
        proj = con.prepareStatement("Select p_id,p_name,more_details from project where AvailableStock!=0");
        availableProject=con.prepareStatement("select AvailableStock from project where p_id=?");
    }
public static ProjectDAO getInstance() throws Exception{

    if (instance==null) {
        instance=new ProjectDAO();
        
    }
    return instance;
}
    public List<List<String>> project() throws Exception {

        ResultSet rs = proj.executeQuery();
        List<List<String>> l = new ArrayList<>();
        String a = "", b = "", c = "";
        while (rs.next()) {
            List<String> n = new ArrayList<>();
            a = String.valueOf(rs.getInt(1));
            b = rs.getString(2);
            c = rs.getString(3);

            n.add(a);
            n.add(b);
            n.add(c);
            l.add(n);
        }
        return l;
    }
public int Available(orders od) throws Exception{
    availableProject.setInt(1,od.getViewComponents());
    ResultSet rs=availableProject.executeQuery();
    if(rs.next())
    return rs.getInt(1);
    return -1;

}
    public boolean check(String proj) throws Exception {
        projectCheck.setString(1, proj);
        ResultSet rs = projectCheck.executeQuery();
        if (rs.next())
            return true;
        return false;
    }

    public int findid(String proj) throws Exception {
        projId.setString(1, proj);
        ResultSet rs = projId.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);

        }
        return 0;
    }

    public int insert(String proj, String link, int Available,int price) throws Exception {
        projInsert.setString(1, proj);
        projInsert.setString(2, link);
        projInsert.setInt(3, Available);
        projInsert.setInt(4,price);
        projInsert.executeUpdate();
        projId.setString(1, proj);
        ResultSet rs = projId.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);

        }
        return 0;
    }

    public void components(orders o, int pid) throws Exception {
        insertcomponents.setInt(1, pid);
        insertcomponents.setString(2, o.getName());
        insertcomponents.setString(3, o.getValue());
        insertcomponents.setInt(4, o.getQuantity());
        insertcomponents.setInt(5, o.getPrice());
        insertcomponents.executeUpdate();

    }

    public int findComp(String name) throws Exception {

        orders o=new orders();
        findComId.setString(1, o.getName());
        ResultSet rs = findComId.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int projUpdate(int id, String newName) throws Exception {
        updateProject.setString(1, newName);
        updateProject.setInt(2, id);
      int open= updateProject.executeUpdate();
      return open;
     

    }

    public void addModel(orders od) throws Exception {

        insertC_model.setInt(1, od.getC_ID());
        insertC_model.setString(2, od.getBrand());
        insertC_model.setString(3, od.getOrganis());
        insertC_model.setString(4, od.geModel());
        insertC_model.executeUpdate();

    }

    public void deletProject(int id) throws Exception {
        DeleteProject.setInt(1, id);
        DeleteProject.executeUpdate("delete from project where p_id=" + id + "");
    }

    public void UpdateStock() throws Exception{
        UpdateStock.setInt(1, Resourse.project.getAvailable());
        UpdateStock.setInt(2,Resourse.project.getProject_id());
        UpdateStock.executeUpdate();

    }

}
