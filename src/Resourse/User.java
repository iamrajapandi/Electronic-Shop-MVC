package Resourse;

public class User {
    private static String name = "";
    private static String address = "";
    private static String mail= "";
    private static String password = "";
    private static int user_id=0;
    private static String ph_no="";
    private static int tot_Item=0;
    private static int totalAmount=0;

    public static void setName(String Name) {
        name = Name;

    }

    public static String getName() {
        return name;
    }
    public static void setMail(String Mail) {
       mail=Mail;

    }

    public static String getMail() {
        return mail;
    }

    public static void setPass(String pass) {
        password = pass;
    }

    public static String getPass() {
        return password;
    }

    public static void setAddress(String Address) {
        address = Address;
    }

    public static String getAddress() {
        return address;
    }

    public static void setId(int id) {
       user_id= id;
    }

    public static int getId() {
        return user_id;
    }
    public static void setPh(String id) {
       ph_no= id;
    }

    public static String getPh() {
        return ph_no;
    }
    public static void settotItem(int item) {
      tot_Item= item;
    }

    public static int gettotItem() {
        return tot_Item;
    }
    public static void setAmount(int amount) {
      totalAmount= amount;
    }

    public static int getAmount() {
        return totalAmount;
    }


}
