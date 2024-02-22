package Controller;

import Model.ProjectDAO;
import Resourse.orders;
import Resourse.project;

public class ProjectCheck {
    public boolean ExistOrNot(String project) throws Exception {
        ProjectDAO proj = ProjectDAO.getInstance();
        if (proj.check(project))
            return true;
        return false;
    }

    public int ExistComponents(orders o, int cid) throws Exception {
        ProjectDAO proj = ProjectDAO.getInstance();
        int c_id = proj.findComp(o.getName());
        return c_id;
    }
    public void AvailableStock(orders od)throws Exception{
          ProjectDAO pr=ProjectDAO.getInstance();
          int Availablecount=pr.Available(od);
          project.setAvailable(Availablecount);
        }
        public boolean updateStock(project proj) throws Exception{
            ProjectDAO pr=ProjectDAO.getInstance();
            pr.UpdateStock();
            return true;
            
    }
}
