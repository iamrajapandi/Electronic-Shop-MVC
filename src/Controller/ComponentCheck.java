package Controller;

import java.util.ArrayList;
// import java.util.ArrayList;
import java.util.List;

import Model.C_ModelDAO;
import Model.ComponentsDAO;
import Model.ElectComponentDAO;
import Model.ProjectDAO;

public class ComponentCheck {
    public void CheckComp(String proj) throws Exception {
        ProjectDAO project = ProjectDAO.getInstance();
        int id = project.findid(proj);
        ComponentsDAO comp = ComponentsDAO.getInstance();
        List<Integer> l = comp.findComponents(id);
        C_ModelDAO cmode = C_ModelDAO.getInstance();
        cmode.deletCmode(l);
        comp.DeleteComponent(id);
        project.deletProject(id);

    }
    public void CheckComp(String proj,String newProj) throws Exception {
        ProjectDAO project =  ProjectDAO.getInstance();
        int id = project.findid(proj);
        project.projUpdate(id,newProj);
    }

    public void deleteComp(String compo, String model) throws Exception {
        ComponentsDAO com =  ComponentsDAO.getInstance();
        int id = com.findComponents(compo, model);
        C_ModelDAO cm =  C_ModelDAO.getInstance();
        cm.deletCmode(id);
        com.DeleteComponent(id);
    }
    public List<List<String>> OrderedComponent() throws Exception{
        ComponentsDAO comp=ComponentsDAO.getInstance();
        List<List<String>>l=comp.ViewComponentList();  
        return l;
    }
    public int AvailCom()
    {
        try {
            ComponentsDAO c=ComponentsDAO.getInstance();
            return c.getAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return 1;
    }
    public List<List<String>> SalesComponentList( )
    {
        List<List<String>>list=new ArrayList<>();
        try {
            ElectComponentDAO ec=ElectComponentDAO.getInstance();
            list=ec.ListComp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
}
