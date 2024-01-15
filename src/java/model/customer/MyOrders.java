
package model.customer;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MyOrders extends MyProfile{

    private int OrderID, packageQuantity, warehouseID; 
    private double totalPrice;
    private String recipientName, recipientPhone, recipientAddress, recipientTown,
                   packageType, packageWeight, offer;
    boolean express, fragile, extraPackage;               
    Blob photo;
    ArrayList<String> addresses = new ArrayList<>();
    
    private static final String PAYMENTMETHOD = "Paypal";
    private static final String DELIVERYSTATUS = "Pending";
    
    //Create order
    private static final String ADD_SQL = "INSERT INTO orders (recipient, r_phone, r_address, township, customer_id, package_type, packageWeight, quantity, express, fragile, extraPackage, contentphoto, price, paymentmethod, delivery_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //Insert Order ID into customer table
    private static final String UPDATE_SQL = "UPDATE customer SET subscription =?, order_id = LAST_INSERT_ID() WHERE customer_id = ?";
    
    //Get addresses for map generation
    private static final String GETADDRESS_SQL = "SELECT r_address FROM orders WHERE delivery_status = 'Pending'";
    
    public MyOrders(){
        //default constructor
        super();
    }
    
    public MyOrders(String recipientName, String recipientPhone, String recipientAddress, String recipientTown, 
                    String email, String packageType, String packageWeight, int packageQuantity, 
                    boolean express, boolean fragile, boolean extraPackage, Blob photo,
                    String offer, double totalPrice)
    {  
        super(email);
        this.recipientName    = recipientName;
        this.recipientPhone   = recipientPhone;
        this.recipientAddress = recipientAddress;
        this.recipientTown    = recipientTown;
        this.packageType      = packageType;
        this.packageWeight    = packageWeight;
        this.packageQuantity  = packageQuantity;
        this.express          = express;
        this.fragile          = fragile;
        this.extraPackage     = extraPackage;
        this.photo            = photo;
        this.offer            = offer;
        this.totalPrice       = totalPrice;
    }
    
    //------------------get----------------------------//
    
    public int getOrderID() {
        return OrderID;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public int getPackageQuantity() {
        return packageQuantity;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getPackageType() {
        return packageType;
    }

    public boolean isExpress() {
        return express;
    }

    public boolean isFragile() {
        return fragile;
    }

    public boolean isExtraPackage() {
        return extraPackage;
    }

    public String getPaymentMethod() {
        return PAYMENTMETHOD;
    }

    public String getDeliveryStatus() {
        return DELIVERYSTATUS;
    }
    
     public String getRecipientTown() {
        return recipientTown;
    }

    public String getOffer() {
        return offer;
    }

    public Blob getPhoto() {
        return photo;
    }
    
    public ArrayList<String> getAddresses(){
        return addresses;
    }
    
    //------------------set----------------------------//
    
    
    
    //-------------------------------------------------//

    //Create an order
    public void createOrder() {

        try{
            getCustomerID();
            DBconnect();
            
            PreparedStatement pstmt = connectDB.prepareStatement(ADD_SQL);
            
            pstmt.setString(1, getRecipientName());
            pstmt.setString(2, getRecipientPhone());
            pstmt.setString(3, getRecipientAddress());         
            pstmt.setString(4, getRecipientTown());    
            pstmt.setInt   (5, super.getCusID());    
            pstmt.setString(6, getPackageType());    
            pstmt.setString(7, getPackageWeight());    
            pstmt.setInt   (8, getPackageQuantity());    
            pstmt.setString(9, String.valueOf(isExpress()));    
            pstmt.setString(10, String.valueOf(isFragile()));    
            pstmt.setString(11, String.valueOf(isExtraPackage()));    
            pstmt.setBlob  (12, getPhoto()); 
            pstmt.setDouble(13, getTotalPrice());    
            pstmt.setString(14, getPaymentMethod());    
            pstmt.setString(15, getDeliveryStatus());  
            System.out.println(ADD_SQL);
            pstmt.executeUpdate();
            

            PreparedStatement pstmt2 = connectDB.prepareStatement(UPDATE_SQL);
            pstmt2.setString(1, getOffer());
            pstmt2.setInt   (2, super.getCusID());
            pstmt2.executeUpdate();
                      
            pstmt.close();
            pstmt2.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally { // Close the connection in the finally block to ensure it's always closed
            
            if (newConnection != null) {               
                newConnection.closeConnection(); 
            }
        }
    }
    
    //get Addresses
    public void LoadDataAddress() {
        
        DBconnect();

        try{
 
            PreparedStatement pstmt = connectDB.prepareStatement(GETADDRESS_SQL);
            ResultSet result = pstmt.executeQuery();

            
            while(result.next()){
                String temp = result.getString("r_address");
                addresses.add(temp);
            } 
            
            pstmt.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally { // Close the connection in the finally block to ensure it's always closed
            
            if (newConnection != null) {               
                newConnection.closeConnection(); 
            }
        }

    } 
     
}
