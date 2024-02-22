package View;

import java.util.List;

import Controller.AdminCheck;
import Controller.ComponentCheck;
import Controller.ElectCompCheck;
import Controller.OTPCheck;
import Controller.ProjectCheck;
import Controller.ordercheck;
import Model.ComponentsDAO;
import Model.ProjectDAO;
import Resourse.SalesCom;
import Resourse.orders;
import Resourse.project;
import Util.input;

public class AdminView extends input {
    public static int Available = 0;

    public void display() throws Exception {
        System.out.println();
        System.out.printf("****************************************************************************************%n");
        System.out.printf("****************************************************************************************%n");
        System.out.printf("                             Enter 1 for insert Components:%n");
        System.out.printf("                             Enter 2 for insert:%n");
        System.out.printf("                             3 for view: %n");
        System.out.printf("                             4 for delete: %n");
        System.out.printf("                             5 for view orders: %n");
        System.out.printf("                             6 for Update Project: %n");
        System.out.printf("                             7 for Update Available Stock: %n");
        System.out.printf("                             8 Sent OTP: %n");
        System.out.printf("                             9 check OTP: %n");
        System.out.printf("                             10 Back: %n");

        int select = sc.nextInt();
        if (select == 1) {
            componentsList();
        } else if (select == 2) {
            insertProj();

        } else if (select == 3) {
            projectView();

        } else if (select == 4) {
            delete();

        } else if (select == 5) {
            OrderviewFull();

        } else if (select == 6) {
            updateProject();

        } else if (select == 7) {
            Stock();

        } else if (select == 8) {
            OTP();

        } else if (select == 8) {
            CheckOTP();
        } else {
            return;

        }
    }
    public static void componentsList() {
        sc.nextLine();
        ComponentCheck com = new ComponentCheck();
        SalesCom sc1 = new SalesCom();
        ElectCompCheck ec = new ElectCompCheck();
        System.out.println("Enter the Electronic Component");
        String comp = sc.nextLine();
        SalesCom.setComponent(comp);
        System.out.println(sc1.getComponent());
        if (!ec.InsertComp()) {
            ec.InsertComp();

        }
        int condition = 0;
        do {
            System.out.println("Enter the Components Value");
            String comValue = sc.nextLine();
            SalesCom.setComponnetValue(comValue);
            System.out.println("Enter the Component Brand");
            String ComBrand = sc.nextLine();
            SalesCom.setComponent_Brand(ComBrand);
            System.out.println("Enter the Model Number");
            String ComModel = sc.nextLine();
            SalesCom.setComponent_ModelNo(ComModel);
            System.out.println("Enter the AvailableStock");
            int AvailStock = sc.nextInt();
            SalesCom.setAvailableStock(AvailStock);
            System.out.println("Enter the Price");
            int price = sc.nextInt();
            SalesCom.setPrice(price);
            ec.insertComponentsDetails(sc1);
            System.out.println("          1..ADD MORE");
            System.out.println("          2.Exit");
            condition = sc.nextInt();
            sc.nextLine();

        } while (condition == 1);

        List<List<String>> l = com.SalesComponentList();
        System.out.printf(
                "---------------------------%n");
        System.out.printf("| %-4s|  %-15s |%n", "c_id", "Component Name");

        for (int i = 0; i < l.size(); i++) {
            System.out.printf(
                    "---------------------------%n");
            System.out.printf("| %-4s |  %-16s |%n", l.get(i).get(0), l.get(i).get(1));
        }
        System.out.printf(
                "---------------------------%n");
        System.out.println();
        System.out.println("            Enter the Id For List of Components ");
        int CompId = sc.nextInt();
        SalesCom.setCompid(CompId);

        List<List<String>> list = ec.ViewDetailed();

        System.out.printf(
                "----------------------------------------------------------%n");
        System.out.printf("| %-4s |  %-15s | %-10s | %-6s |%n", "value_id", "Component Value", "AvailableStock",
                "Price");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf(
                    "----------------------------------------------------------%n");
            System.out.printf("| %-6s  |  %-15s |   %-14s | %-6s | %n", list.get(i).get(0),
                    list.get(i).get(1),
                    list.get(i).get(2), list.get(i).get(3));

        }
        System.out.printf(
                "----------------------------------------------------------%n");
    }
    public void insertProj() throws Exception {
        sc.nextLine();

        System.out.println("Enter the project name");
        String proj = sc.nextLine().toLowerCase();
        ProjectCheck pr = new ProjectCheck();
        if (pr.ExistOrNot(proj)) {
            System.out.println("Already Exist..");
            insertProj();
        } else {
            System.out.println("Enter Details_link");
            String link = sc.nextLine();
            System.out.println("Enter the Available stock");
            Available = sc.nextInt();
            System.out.println("Enter the Price");
            int price = sc.nextInt();
            sc.nextLine();
            ProjectDAO d = ProjectDAO.getInstance();
            int id = d.insert(proj, link, Available, price);
            addComponents(proj, id, link);
        }
    }

    public void projectView() throws Exception {
        ProjectDAO proj = ProjectDAO.getInstance();
        List<List<String>> l1 = proj.project();
        System.out.printf("-------------------------------------------------------%n");
        System.out.printf("| %-3s |    %-20s  | %-17s |%n", "p_id", "p_name", "Details");
        for (int i = 0; i < l1.size(); i++) {
            System.out.printf("-------------------------------------------------------%n");

            System.out.printf("| %-3s  | %-24s | %-17s |%n", l1.get(i).get(0), l1.get(i).get(1), l1.get(i).get(2));

        }
        System.out.printf("-------------------------------------------------------%n");
        System.out.println();
        System.out.printf("              1... View Components ");
        System.out.printf("              2... Back ");
        String choice = sc.nextLine();
        if (choice.equals("1")) {

            Components_View();
        }

        else {
            return;
        }
    }
    public void Stock() throws Exception {
        System.out.println("   Enter the Project Id");
        try {
            int id = sc.nextInt();
            project.setProject_id(id);
            System.out.println("Enter the update stock");
            int u_stock = sc.nextInt();
            project.setAvailable(u_stock);
            project ob = new project();
            ProjectCheck pr = new ProjectCheck();
            pr.updateStock(ob);

        } catch (Exception e) {
            System.out.println(e);
            return;

        }

    }

    public static void OrderviewFull() {
        ordercheck o = new ordercheck();
        try {
            if (o.AdminProjectIsNull()) {
                List<List<String>> l = o.AdminRetriewOrdersAll();
                System.out.printf(
                        "---------------------------------------------------------------------------------------%n");
                System.out.printf(
                        "|  %-5s |  %-10s | %-23s | %6s| %-6s | %-6s |%n",
                        "Order_id", "name", "p_name", "Quantity", "OrderDate",
                        "PhoneNumber");
                for (int i = 0; i < l.size(); i++) {
                    System.out.printf(
                            "---------------------------------------------------------------------------------------%n");

                    System.out.printf(
                            "|  %-6s |  %-10s | %-25s | %6s| %-6s | %-6s |%n", l.get(i).get(0), l.get(i).get(1),
                            l.get(i).get(2), l.get(i).get(3), l.get(i).get(4),
                            l.get(i).get(5));
                }

                System.out.printf(
                        "---------------------------------------------------------------------------------------%n");
            }

            if (o.AdminProjectIsNotNull()) {
                List<List<String>> l = o.AdminRetriewCompOrder();
                System.out.printf(
                        "---------------------------------------------------------------------------------------%n");
                System.out.printf(
                        "|  %-5s |  %-10s | %-23s | %6s| %-6s | %-6s |%n",
                        "Order_id", "name", "p_name", "Quantity", "OrderDate",
                        "PhoneNumber");
                for (int i = 0; i < l.size(); i++) {
                    System.out.printf(
                            "---------------------------------------------------------------------------------------%n");

                    System.out.printf(
                            "|  %-6s |  %-10s | %-25s | %6s| %-6s | %-6s |%n", l.get(i).get(0), l.get(i).get(1),
                            l.get(i).get(2), l.get(i).get(3), l.get(i).get(4),
                            l.get(i).get(5));
                }

                System.out.printf(
                        "---------------------------------------------------------------------------------------%n");
            }

        } catch (Exception e) {

            System.out.print(e);
        }

    }

    
    public void CheckOTP() throws Exception {
        System.out.println("   Enter the ORDER_ID");
        int or_id = sc.nextInt();
        System.out.println("   Enter the OTP From User");
        int OTP = sc.nextInt();
        orders.setOTP(OTP);
        orders.setL(or_id);
        project.setISAvailabe("Yes");
        OTPCheck ot = new OTPCheck();
        orders od = new orders();
        ot.verify(od);

    }

    public void OTP() throws Exception {
        System.out.println("Enter the ORDER_ID");
        int or_id = sc.nextInt();
        AdminCheck ad = new AdminCheck();
        ad.generateOTP(or_id);

    }

  

    public void addComponents(String proj, int id, String link) throws Exception {

        ProjectCheck pr = new ProjectCheck();
        ProjectDAO d = ProjectDAO.getInstance();
        System.out.println("Enter the Component Name");
        String cname = sc.nextLine();
        System.out.println("Enter the Values");
        String value = sc.nextLine();
        System.out.println("Enter the Quantity");
        int quant = sc.nextInt();
        System.out.println("Enter the price");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the Brand Name");
        String brand = sc.nextLine();
        System.out.println("Enter the ModeNumber");
        String model = sc.nextLine();
        System.out.println("Enter the Manufacture from");
        String organisation = sc.nextLine();

        orders o = new orders();
        o.SetComponentsList(id, 0, quant, price, value, cname, brand, organisation, model, Available);
        d.components(o, id);
        int cid = pr.ExistComponents(o, id);
        o.SetComponentsList(id, cid, quant, price, value, cname, brand, organisation, model, Available);

        d.addModel(o);
        System.out.println("Successfully Added.....");
        System.out.println("1..Add more ");
        System.out.println("2..Leave it...");
        int more = sc.nextInt();
        sc.nextLine();
        if (more == 1) {
            addComponents(proj, id, link);
        } else {
            System.out.println("Successfully added.....");
            return;
        }
    }

    public void updateProject() throws Exception {
        sc.nextLine();
        System.out.println("Enter the old project Name");
        String proj = sc.nextLine();
        System.out.println("Enter the new project Name");
        String projEdit = sc.nextLine().toLowerCase();
        ComponentCheck com = new ComponentCheck();
        com.CheckComp(proj, projEdit);
    }


    public static void Components_View() throws Exception {
        System.out.printf(
                "                  Enter the project id Number for view the Components                      %n");

        int com = sc.nextInt();
        sc.nextLine();
        orders.setViewComponents(com);
        ComponentsDAO c = ComponentsDAO.getInstance();
        List<List<String>> l = c.view();
        System.out.printf(
                "---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-4s|  %-15s | %-8s|   %-10s | %-8s | %8s | %-13s | %-15s |%n", "c_id",
                "c_name", "c_value", "Quantity", "price", "Brand_name", "Organisation", "ModeNumber");
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(
                    "---------------------------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-4s |  %-15s | %-8s|   %-10s | %-8s | %10s | %-13s | %-15s |%n",
                    l.get(i).get(0),
                    l.get(i).get(1), l.get(i).get(2), l.get(i).get(3), l.get(i).get(4),
                    l.get(i).get(5),
                    l.get(i).get(6), l.get(i).get(7));
        }
        System.out.printf(
                "---------------------------------------------------------------------------------------------------------------%n");
        // System.out.println("1.Buy");
        // System.out.println("2.Back");

    }

    public void delete() throws Exception {

        System.out.println("1...Delete the project");
        System.out.println("2..Delete the compontents");
        int del = sc.nextInt();
        sc.nextLine();
        switch (del) {
            case 1:
                deleteProject();
                break;

            case 2:
                deleteComponent();
                break;

            default:
                delete();
                break;
        }
    }

    public void deleteProject() throws Exception {
        System.out.println("Enter the project Name");
        String proj = sc.nextLine();
        ComponentCheck check = new ComponentCheck();
        check.CheckComp(proj);
    }

    public void deleteComponent() throws Exception {
        sc.nextLine();
        System.out.println("Enter Component Name");
        String comp = sc.nextLine();
        System.out.println("Enter the model number");
        String model = sc.nextLine();
        ComponentCheck check = new ComponentCheck();
        check.deleteComp(comp, model);

    }

}
