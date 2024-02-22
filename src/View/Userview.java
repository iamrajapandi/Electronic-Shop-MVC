package View;

import java.util.List;

import Controller.ComponentCheck;
import Controller.ElectCompCheck;
import Controller.ProjectCheck;
import Controller.ordercheck;
import Model.ComponentsDAO;
import Model.OrderAddressDAO;
import Model.ProjectDAO;
// import Model.OrdersDAO;
import Model.UserDAO;
import Resourse.*;
import Util.input;

public class Userview extends input {

        public void display() throws Exception {

                ordercheck od = new ordercheck();
                orders ord = new orders();
                if (od.BillCheck() != 0) {
                        System.out.println("    OTP is " + ord.getOTP());
                        System.out.println();
                }
                System.out.println("    1.To display the components");
                System.out.println("   2.To Display the project...");
                System.out.println("   3.Change Address!");
                System.out.println("   4.View Orders------>>>>>");
                System.out.println("   5.Delivered------>>>>>");
                System.out.println("   6.Exit------>>>>>");
                UserDAO u = UserDAO.getInstance();
                User sent = new User();
                u.enter(sent);
                int n = sc.nextInt();
                sc.nextLine();
                if (n == 1) {
                        UserViewcomponentsList();

                }
                if (n == 2) {
                        project();

                } else if (n == 3) {
                        change();

                } else if (n == 4) {
                        Vieworders();

                } else if (n == 5) {
                        ConfirmAddress();

                } else {

                        return;
                }
        }

        public static void order() throws Exception {

                sc.nextLine();
                System.out.println("Please Confirm your orders");
                System.out.println("1..Add more");
                String more = sc.nextLine();
                orders ord = new orders();
                ordercheck check = new ordercheck();
                ProjectCheck pr = new ProjectCheck();

                pr.AvailableStock(ord);
                System.out.println("           Available Stock ===>  " + project.getAvailable());
                int tot = 1;
                if (more.equals("1")) {
                        System.out.println("Enter the total Projects");
                        do {
                                tot = sc.nextInt();

                        } while (project.getAvailable() < tot);

                }
                check.insertOrder(tot);
                orders us = new orders();
                project stock = new project();
                project.setProject_id(us.getViewComponents());

                int existStock = project.getAvailable();
                if ((existStock - tot) < 0) {
                        System.out.println("      Only " + project.getAvailable() + " is availaable.....");
                        order();
                        return;
                }
                ProjectCheck c = new ProjectCheck();
                project.setAvailable(existStock - tot);
                c.updateStock(stock);

                System.out.println("press enter for okk");
                System.out.println(" Enter '1' for edit addres");
                System.out.println(" Enter '2' for order Cancel");
                String enter = sc.nextLine();
                OrderAddressDAO ad = OrderAddressDAO.getInstance();
                if (enter.equals("2")) {
                        ad.deleteAddres(ord);
                        System.out.println("<<<<<<<<<<----Order Cancelled---->>>>>>>>>>>>>");
                } else if (enter.equals("1")) {
                        String newAddress = sc.nextLine();
                        ad.update(newAddress);
                } else {
                        System.out.println("Ordered Successfully...");
                        OrderviewFull();
                        return;
                }

        }

        public static void BuyComponents() {

                SalesCom od = new SalesCom();
                ElectCompCheck checkPrice = new ElectCompCheck();
                checkPrice.FindId(od);
                ordercheck oc = new ordercheck();
                sc.nextLine();
                System.out.println("Enter the total Quantity");
                int tot = sc.nextInt();
                sc.nextLine();
                oc.InsertOrderComp(od, tot);
                System.out.println("1..Add More");
                String more = sc.nextLine();
                if (more.equals("")) {
                        System.out.println("Ordered Successfully");
                        OrderviewFull();

                } else {
                        UserViewcomponentsList();

                }

        }

        public static void project() throws Exception {
                ProjectDAO proj = ProjectDAO.getInstance();
                List<List<String>> l1 = proj.project();
                System.out.printf("-------------------------------------------------------%n");
                System.out.printf("| %-3s |    %-20s  | %-17s |%n", "p_id", "p_name", "Details");
                for (int i = 0; i < l1.size(); i++) {
                        System.out.printf("-------------------------------------------------------%n");

                        System.out.printf("| %-3s  | %-24s | %-17s |%n", l1.get(i).get(0), l1.get(i).get(1),
                                        l1.get(i).get(2));

                }
                System.out.printf("-------------------------------------------------------%n");
                // ComponentsView view=new ComponentsView();
                Components_View();
        }

        public void change() throws Exception {
                System.out.println("     Enter the new Address");
                String add = sc.nextLine();
                UserDAO u = UserDAO.getInstance();
                u.changeAddress(add);
                System.out.println("     Successfully Changed");

        }

        public void Vieworders() throws Exception {
                        ordercheck o = new ordercheck();
                        try {
                            if (o.ProjectIsNull()) {
                                List<List<String>> l = o.RetriewOrdersAll();
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
                
                            if (!o.ProjectIsNotNull()) {
                                List<List<String>> l = o.RetriewCompOrder();
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

        public static void ConfirmAddress() throws Exception {
                ordercheck check = new ordercheck();
                List<List<String>> l = check.Orders();
                System.out.printf("----------------------------------------------%n");
                System.out.printf("| %-5s |  %-10s |    %-14s |%n", "order_id", "UserId", "Address");
                for (int i = 0; i < l.size(); i++) {
                        System.out.printf("----------------------------------------------%n");
                        System.out.printf("|    %-5s | %-12s | %-15s |%n", l.get(i).get(0), l.get(i).get(1),
                                        l.get(i).get(2));

                }
                System.out.printf("----------------------------------------------%n");
        }

        public void OrderComponents() throws Exception {
                ComponentCheck orderComp = new ComponentCheck();
                List<List<String>> l = orderComp.OrderedComponent();
                System.out.printf(
                                "--------------------------------------------------------------------------------------------------%n");
                System.out.printf(
                                "|  %-4s |  %-8s | %-8s | %6s| %-6s | %-6s | %-5s | %-10s |%n",
                                "c_id", "c_name", "c_value", "Quantity", "price",
                                "Brand", "Organisation",
                                "Model");
                for (int i = 0; i < l.size(); i++) {
                        System.out.printf(
                                        "--------------------------------------------------------------------------------------------------%n");
                        System.out.printf(
                                        "|   %-4s | %-8s | %-8s | %6s| %-6s | %-6s | %-5s | %-10s |%n",
                                        l.get(i).get(0), l.get(i).get(1), l.get(i).get(2), l.get(i).get(3),
                                        l.get(i).get(4),
                                        l.get(i).get(5), l.get(i).get(6), l.get(i).get(7));
                }

        }

        public static void OrderviewFull() {
                ordercheck o = new ordercheck();
                try {
                        if (o.ProjectIsNull()) {
                                List<List<String>> l = o.RetriewOrdersAll();
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
                                                        "|  %-6s |  %-10s | %-25s | %6s| %-6s | %-6s |%n",
                                                        l.get(i).get(0), l.get(i).get(1), l.get(i).get(2),
                                                        l.get(i).get(3), l.get(i).get(4),
                                                        l.get(i).get(5));
                                }

                                System.out.printf(
                                                "---------------------------------------------------------------------------------------%n");
                        }

                        if (o.ProjectIsNotNull()) {
                                List<List<String>> l = o.RetriewCompOrder();
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
                                                        "|  %-6s |  %-10s | %-25s | %6s| %-6s | %-6s |%n",
                                                        l.get(i).get(0), l.get(i).get(1), l.get(i).get(2),
                                                        l.get(i).get(3), l.get(i).get(4),
                                                        l.get(i).get(5));
                                }

                                System.out.printf(
                                                "---------------------------------------------------------------------------------------%n");
                        }

                } catch (Exception e) {

                        System.out.print(e);
                }

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
                System.out.println("1.Buy");
                System.out.println("2.Back");
                int select = sc.nextInt();
                switch (select) {
                        case 1: {
                                order();
                                break;
                        }
                        default: {
                                project();
                                break;
                        }

                }

        }

        public static void UserViewcomponentsList() {
                sc.nextLine();
                ComponentCheck com = new ComponentCheck();
                ElectCompCheck ec = new ElectCompCheck();
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

                List<List<String>> list = ec.UserViewDetailed();
                // orders od=new orders();

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
                System.out.println("       1..Buy the Component");
                System.out.println("          2..Go Back");
                int buy = sc.nextInt();
                if (buy == 1) {
                        System.out.println("Enter the Component Id");
                        int compid = sc.nextInt();
                        sc.nextLine();
                        SalesCom.setCompid(compid);
                        BuyComponents();
                } else {
                        UserViewcomponentsList();
                }

        }

}