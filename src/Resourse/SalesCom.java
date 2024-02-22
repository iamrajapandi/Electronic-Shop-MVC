package Resourse;



public class SalesCom {

    private static int compid=0;
    private static int AvailableStock=0;
    private static int Price=0;
    private static String Component="";
    private static String ComponnetValue="";
    private static String component_Brand="";
    private static String component_ModelNo="";



    public  int getAvailableStock() {
        return AvailableStock;
    }
    public static void setAvailableStock(int availableStock) {
        AvailableStock = availableStock;
    }
    public  int getPrice() {
        return Price;
    }
    public static void setPrice(int price) {
        Price = price;
    }
    public static String getComponnetValue() {
        return ComponnetValue;
    }
    public static void setComponnetValue(String componnetValue) {
        ComponnetValue = componnetValue;
    }
    public  String getComponent_Brand() {
        return component_Brand;
    }
    public static void setComponent_Brand(String component_Brand) {
        SalesCom.component_Brand = component_Brand;
    }
    public  String getComponent_ModelNo() {
        return component_ModelNo;
    }
    public static void setComponent_ModelNo(String component_ModelNo) {
        SalesCom.component_ModelNo = component_ModelNo;
    }
   

    public  int getCompid() {
        return compid;
    }
    public static void setCompid(int compid) {
        SalesCom.compid = compid;
    }
    public  String getComponent() {
        return Component;
    }
    public static void setComponent(String component) {
        Component = component;
    }
   
    
}
