import java.sql.*;

import java.util.Scanner;

import Controller.AdminCheck;
import Controller.UserCheck;
import Model.*;
import Resourse.*;
import Util.input;
import View.AdminView;
import View.Userview;

public class App extends input {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        try {
            DbConneticon.getConnection();
        } catch (SQLException err) {
            System.out.println("Database connection error");
        }
        System.out.println();
        System.out.printf("                                 Welcome To Electronic Shop");
        System.out.println();
        System.out.printf("****************************************************************************************%n");
        System.out.printf("                          ==> Enter signIn for '1'.......%n");
        System.out.printf("                          ==> Enter signUp for '2'.......%n");

        try {
            String entry = sc.next();
            sc.nextLine();
            if (entry.equals("1")) {
                sign_in();
            } else if (entry.equals("2")) {

                if (sign_up())
                    sign_in();
            } else {
                main(args);
            }

        }

        catch (Exception e) {
            return;
        }

    }

    public static boolean sign_up() throws Exception {

        System.out.println("WELCOME TO SIGNUP");
        System.out.println("Enter Your Name");
        String name = sc.nextLine();
        // UserDAO use=UserDAO.getInstance();
        UserCheck check = new UserCheck();
        if (check.ExistOrNot(name)) {
            System.out.println("UserName is already exist");
            sign_up();

        }

        System.out.println("Enter your Mail");
        String mail = sc.nextLine().toLowerCase();
        System.out.println("Enter your Password");
        String pass = sc.nextLine();
        System.out.println("Enter Your PhoneNumber");
        String ph_no = sc.nextLine();
        System.out.println("Enter your Address");
        String address = sc.nextLine();
        User.setName(name);
        User.setAddress(address);
        User.setPass(pass);
        User.setMail(mail);
        User.setPh(ph_no);
        User u = new User();
        if (check.SIGNUP(u)) {

            System.out.println("Registered Successfully");
            return true;

        }
        return false;

    }

    public static void sign_in() throws Exception {
        System.out.println("WELCOME TO SIGNIN");
        try {

            UserCheck d = new UserCheck();
            AdminCheck ad = new AdminCheck();
            System.out.println("Enter UserName");
            String name = sc.nextLine();
            if (d.ExistOrNot(name)) {
                System.out.println("Enter Password");
                String pass = sc.nextLine();

                if (d.ExistPass(pass)) {
                    User.setName(name);
                    User.setPass(pass);
                    UserDAO dao = UserDAO.getInstance();
                    User user = new User();
                    dao.enter(user);
                    Userview u = new Userview();
                    System.out.printf("                Welcome " + User.getName() + "%n");
                    u.display();

                } else {
                    System.out.println("Invalid password....");
                    sign_in();

                }
            }
            if (ad.ExistOrNot(name)) {
                System.out.println("Enter Password");
                String pass = sc.nextLine();
                if (ad.ExistOrNotPass(pass)) {
                    User.setName(name);
                    User.setPass(pass);

                    System.out.println("Admin Welcome");
                    AdminView admin = new AdminView();
                    admin.display();
                    return;

                } else {
                    System.out.println("Invalid password....");
                    sign_in();
                }

            } else if (!ad.ExistOrNot(name) && !d.ExistOrNot(name)) {
                System.out.println("UserName Not found");
                sign_in();
            }
            return;

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
