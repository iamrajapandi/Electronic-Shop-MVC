package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class C_ModelDAO {
    private static C_ModelDAO instance;
    PreparedStatement pr;

    private C_ModelDAO() throws Exception {
        Connection con=DbConneticon.getConnection();
        pr =con.prepareStatement("delete from c_model where c_id=?");
    }

    public static C_ModelDAO getInstance() throws Exception {
        if (instance == null)
            instance = new C_ModelDAO();
            return instance;
    }

    public void deletCmode(List<Integer> l) throws Exception {
        for (int i = 0; i < l.size(); i++) {
            pr.setInt(1, l.get(i));
            pr.executeUpdate();

        }
    }

    public void deletCmode(int l) throws Exception {
        pr.setInt(1, l);
        pr.executeUpdate();

    }

}
