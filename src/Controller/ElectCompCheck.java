package Controller;

import java.util.List;

import Model.ElectComponentDAO;
import Resourse.SalesCom;
// import Resourse.orders;

public class ElectCompCheck {
    public boolean ExixtsOrNotComp()
    {
        ElectComponentDAO sc=ElectComponentDAO.getInstance();
        sc.ListComp();
        return true;
    }
    public boolean InsertComp()
    {
        ElectComponentDAO sc=ElectComponentDAO.getInstance();
        if(!sc.seletId())
        {
            if(sc.insertComponents())
            {

                return true;
            }

        }
        return false;
    }
    public void insertComponentsDetails(SalesCom sc)
    {
        ElectComponentDAO ec=ElectComponentDAO.getInstance();
        ec.insertComponentsDetails(sc);
    }
    public  List<List<String>> ViewDetailed() 
    {
        ElectComponentDAO ec=ElectComponentDAO.getInstance();
        List<List<String>>l=ec.ViewDetailed();
        return l;
        

    }
    public  List<List<String>> UserViewDetailed() 
    {
        ElectComponentDAO ec=ElectComponentDAO.getInstance();
        List<List<String>>l=ec.UserViewDetailed();
        return l;
    
    }
    public void UpateStock() 
    {
        ElectComponentDAO ec=ElectComponentDAO.getInstance();
        ec.UpdateStock();
    }
    public void UpdatePrice() 
    {
        ElectComponentDAO ec=ElectComponentDAO.getInstance();
        ec.UpdatePrice();
    }
    public void FindId(SalesCom od) 
    {
        ElectComponentDAO ec=ElectComponentDAO.getInstance();
       int price= ec.FindCompPrice(od);
       SalesCom.setPrice(price);
    }
    
}
