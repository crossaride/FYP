package model.customer;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseConnection;

public class MyProfile {

    protected DatabaseConnection newConnection;
    protected Connection connectDB;

    private String email, username, realName, phone, address;
    private int id, cusID;
    private double balance;
    private List<MyProfile> Profile = new ArrayList<>();

    private InputStream profilePic = null;

    //Get ID
    private static final String GETID_SQL = "SELECT id FROM useraccount WHERE email = ?";

    //Get CustomerID
    private static final String GETCUSID_SQL = "SELECT customer_id FROM customer WHERE account_id = ?";
    
    //Update 'username'
    private static final String UPDATE_USERNAME_SQL = "UPDATE useraccount SET username = ? WHERE id = ?";

    //Update Profile
    private static final String UPDATE_SQL = "UPDATE customer SET username = ?, realname = ?, phone = ?, address = ?, profilepic = ? WHERE account_id = ?";

    //Update balance
    private static final String EDIT_BALANCE_SQL = "UPDATE customer SET balance = ? WHERE id = ?";

    //Load table
    private static final String SEARCH_SQL = "SELECT * from customer WHERE account_id = ?";

    //Get Profile Pic
     private static final String PROFILE_SQL = "SELECT profilepic FROM customer WHERE account_id = ?";
    
    
    public MyProfile() {
        //default constructor
    }

    public MyProfile(String email) {
        this.email = email;
    }

    public MyProfile(String realName, String phone, String address, double balance) {
        this.realName = realName;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    public MyProfile(String email, String username, String realName, String phone, String address, InputStream profilePic) {
        this.email = email;
        this.username = username;
        this.realName = realName;
        this.phone = phone;
        this.address = address;
        this.profilePic = profilePic;
    }

    //------------------get----------------------------//
    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRealName() {
        return realName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public InputStream getProfilePic() {
        return profilePic;
    }

    public int getID() {
        return id;
    }
    
    public int getCusID(){
        return cusID;
    }

    //------------------set----------------------------//
    public void setID(int newID) {
        this.id = newID;
    }
    
    public void setCusID(int newCusID) {
        this.cusID = newCusID;
    }

    public void setRealName(String newRealName) {
        this.realName = newRealName;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfilePic(InputStream profilePic) {
        this.profilePic = profilePic;
    }
 

    //-------------------------------------------------//
    
    //Database connection
    public void DBconnect() {
        newConnection = new DatabaseConnection();
        connectDB = newConnection.getConnection();
    }

    //Update Profile
    public void updateProfile() {
 
        try {
            //get ID from useraccount
            getAccID();
            
            DBconnect(); // getAccID() closes connection with finally, remember to put AFTER
            
            //Update into useraccount table
            PreparedStatement pstmt = connectDB.prepareStatement(UPDATE_USERNAME_SQL);

            pstmt.setString(1, getUsername());
            pstmt.setInt(2, getID());
            pstmt.executeUpdate();
            pstmt.close();

            //Update customer table with condition id = ?
            PreparedStatement pstmt2 = connectDB.prepareStatement(UPDATE_SQL);

            pstmt2.setString(1, getUsername());
            pstmt2.setString(2, getRealName());
            pstmt2.setString(3, getPhone());
            pstmt2.setString(4, getAddress());
            pstmt2.setBlob  (5, getProfilePic());
            pstmt2.setInt   (6, getID());
            pstmt2.executeUpdate();
 
            pstmt2.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally { // Close the connection in the finally block to ensure it's always closed

            if (newConnection != null) {
                newConnection.closeConnection();
            }
        }
    }

    //Get Account ID using email
    public void getAccID() {
        DBconnect();

        try {

            int ID = 0;

            PreparedStatement pstmt = connectDB.prepareStatement(GETID_SQL);
            pstmt.setString(1, getEmail());
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                ID = result.getInt(1);
                setID(ID);
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally { // Close the connection in the finally block to ensure it's always closed

            if (newConnection != null) {
                newConnection.closeConnection();
            }
        }
    }

    //Load data into text-field
    public void SearchDetail() {
        DBconnect();

        try {
            PreparedStatement pstmt = connectDB.prepareStatement(SEARCH_SQL);
            pstmt.setInt(1, getID());
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                
                String realName    = result.getString("realname");
                String Phone       = result.getString("phone");
                String Address     = result.getString("address");
                double Balance     = result.getDouble("wallet");
                String userName    = result.getString("username");
            //ALternative
                //InputStream avatar = result.getBinaryStream("profilepic");
                
                Blob blob = result.getBlob("profilepic");  
                InputStream avatar = blob.getBinaryStream();  
                       
                setRealName(realName);
                setPhone(Phone);
                setAddress(Address);
                setBalance(Balance);
                setUsername(userName);
                setProfilePic(avatar);
                
                //MyProfile exProf = new MyProfile(realName, Phone, Address, Balance);
                //Profile.add(exProf);
            }
            pstmt.close();
            result.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally { // Close the connection in the finally block to ensure it's always closed

            if (newConnection != null) {
                newConnection.closeConnection();
            }
        }
    }
    
    //Search Customer ID
    public void getCustomerID() {
        
        try {
            getAccID();
            
            DBconnect();

            PreparedStatement pstmt = connectDB.prepareStatement(GETCUSID_SQL);
            pstmt.setInt(1, getID());
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                
                int temp = result.getInt("customer_id");
                setCusID(temp);
            
            }
            pstmt.close();
            result.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally { // Close the connection in the finally block to ensure it's always closed

            if (newConnection != null) {
                newConnection.closeConnection();
            }
        }
    }

}
