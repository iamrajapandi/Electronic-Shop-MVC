package Resourse;


public class orders {

    private static int viewComponent=0;
    private static int id=0;
    private static int order_id=0;
    private static int c_id=0;
    private static int quant=0;
    private static int price=0;
    private static int Available=0;
    private static int OTP=0;
    private static int quantity=0;
    private static String value="";
    private static String name="";
    private static String brand="";
    private static String organistation="";
    private static String model="";
    
    public  int getOTP() {
        return OTP;
    }

    public static void setOTP(int oTP) {
        OTP = oTP;
    }

    public  int getL() {
        
      
        return order_id;
    }

    public static void setL(int l) {
        
        orders.order_id = l;
    }
    public static void setViewComponents(int Component) {
        viewComponent = Component;
    }

    public  int getViewComponents() {
        return viewComponent;
    }

    public void SetComponentsList(int id, int c_id, int quant, int price, String value, String name, String brand,
            String organisation, String model, int Available) {
        orders.id = id;
        orders.c_id = c_id;
        orders.price = price;
        orders.quant = quant;
        orders.brand = brand;
        orders.model = model;
        orders.name = name;
        orders.organistation = organisation;
        orders.value = value;
        orders.Available = Available;

    }

    public  int getId() {
        return id;
    }

    public  int getAvailable() {
        return Available;
    }

    public  int getC_ID() {
        return c_id;
    }

    public  int getPrice() {
        return price;
    }

    public  int getQuantity() {
        return quant;
    }

    public  int getQuantityItem() {
        return quantity;
    }

    public  String getName() {
        return name;
    }

    public  String getValue() {
        return value;
    }

    public String getBrand() {
        return brand;
    }

    public  String geModel() {
        return model;
    }

    public String getOrganis() {
        return organistation;
    }
}
