package Resourse;



public class project {
    private static int project_id=0;
    private static int price=0;
    private static int Available=0;
    private static String ISAvailabe="";
    public static int getPrice() {
        return price;
    }
    public static void setPrice(int price) {
        if(price<0)
        {
            System.out.println("Price should not be Negative");
            return;
        }
        project.price = price;
    }

    public static String getISAvailabe() {
        return ISAvailabe;
    }
    public static void setISAvailabe(String iSAvailabe) {
        if(iSAvailabe.equals("Yes")||iSAvailabe.equals("No"))
        {
            ISAvailabe = iSAvailabe;

        }
        else{
            System.out.println("Only Yes or No type...");
            return;
        }
    }
    public static void setProject_id(int project_id) {
        project.project_id = project_id;
    }
    public static int getProject_id() {
        return project_id;
    }
    public static int getAvailable() {
        return Available;
    }
    public static void setAvailable(int available) {
        Available = available;
    }
}
