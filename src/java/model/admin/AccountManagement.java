package model.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseConnection;

public class AccountManagement {

    protected DatabaseConnection newConnection;
    protected Connection connectDB;

    private int id, lastID;
    private String email, username, password, accType;
    private List<AccountManagement> Account = new ArrayList<>();

    //Show table query
    private static final String LOAD_SQL = "SELECT * from useraccount";
    
    //Add query
    private static final String ADD_SQL = "INSERT INTO useraccount (email, username, password, type) VALUES (?, ?, ?, ?)";
    
    //Edit query 
    private static final String EDIT_SQL = "UPDATE useraccount SET email = ?, username = ?, password = ?, type = ? WHERE id = ?";
    
    //Delete query
    private static final String DELETE_SQL = "DELETE FROM useraccount WHERE id = ?";
    
    //Store [PK].id as [FK] in customer table
    private static final String CUSTOMER_SQL = "INSERT INTO customer(account_id) VALUES (LAST_INSERT_ID())";

    //Delete from useraccount table = customer table as well
    private static final String DEL_CUSTOMER_SQL = "DELETE FROM customer WHERE account_id = ?";
    
    //Search query
    //private String showSQL  = "SELECT * FROM useraccount WHERE username = '" + super.getName() + "';";
    //private String showSQL2 = "SELECT * FROM useraccount WHERE email = '" + super.getName() + "';";
    
    
    public AccountManagement() {
        //default constructor
    }
    
    public AccountManagement(int id) {
        this.id = id;
    }
    
    public AccountManagement( String email, String username, String password, String accType) {
        this.email    = email;
        this.username = username;
        this.password = password;
        this.accType  = accType;
    }
    
    public AccountManagement(int id, String email, String username, String password, String accType) {
        this.id       = id;
        this.email    = email;
        this.username = username;
        this.password = password;
        this.accType  = accType;
    }

    //------------------get----------------------------//

    public List<AccountManagement> getAccountList() {
        return Account;
    }
    
    public int getID(){
        return id;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getAccType(){
        return accType;
    }
    
    public int getLastID(){
        return lastID;
    }
    
    //------------------set----------------------------//
    
    public void setLastID(int newLastID){
        this.lastID = newLastID;
    }

    //-------------------------------------------------//
    //Database connection
    public void DBconnect() {
        newConnection = new DatabaseConnection();
        connectDB = newConnection.getConnection();
    }

    //Load data into Table
    public void showAcc() {
        DBconnect();

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery(LOAD_SQL);

            while (result.next()) {
                int ID       = result.getInt("id");
                String EMAIL = result.getString("email");
                String USER  = result.getString("username");
                String PASS  = result.getString("password");
                String TYPE  = result.getString("type");
                
                AccountManagement exAcc = new AccountManagement(ID, EMAIL, USER, PASS, TYPE);
                Account.add(exAcc);

            }
            stmt.close();
            result.close();

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally { // Close the connection in the finally block to ensure it's always closed
            
            if (newConnection != null) {               
                newConnection.closeConnection(); 
            }
        }
    }
    
    //Search from database using Email/Username
    public void searchAcc() {
        DBconnect();

        /*
        try {
            
            Statement stmt = connectDB.createStatement();
            ResultSet result;

            
            if(checkNameType() == 1){ //email
                result = stmt.executeQuery(showSQL2);
            }else{
                result = stmt.executeQuery(showSQL);
            }
            
            while(result.next()){
                
            } 
            

        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }

    //Add into Database
    public void addAcc() {
        DBconnect();

        try{
 
            PreparedStatement pstmt = connectDB.prepareStatement(ADD_SQL);
            
            pstmt.setString(1, getEmail());
            pstmt.setString(2, getUsername());
            pstmt.setString(3, getPassword());
            pstmt.setString(4, getAccType());           
            pstmt.executeUpdate();
            pstmt.close();
            
            PreparedStatement pstmt2 = connectDB.prepareStatement(CUSTOMER_SQL);
            pstmt2.executeUpdate();
            
            pstmt2.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally { // Close the connection in the finally block to ensure it's always closed
            
            if (newConnection != null) {               
                newConnection.closeConnection(); 
            }
        }
    }

    //Edit from Database
    public void editAcc() {
        DBconnect();

        try{
 
            PreparedStatement pstmt = connectDB.prepareStatement(EDIT_SQL);
            
            pstmt.setString(1, getEmail());
            pstmt.setString(2, getUsername());
            pstmt.setString(3, getPassword());
            pstmt.setString(4, getAccType());           
            pstmt.setString(5, String.valueOf(getID()));
            pstmt.executeUpdate();
            
            pstmt.close();
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally { // Close the connection in the finally block to ensure it's always closed
            
            if (newConnection != null) {               
                newConnection.closeConnection(); 
            }
        }
    }

    //Delete from Database
    public void deleteAcc() {
        DBconnect();

        try{
 
            PreparedStatement pstmt = connectDB.prepareStatement(DELETE_SQL);
            
            pstmt.setString(1, String.valueOf(getID()));
            pstmt.executeUpdate();
            
            pstmt.close();
            
            PreparedStatement pstmt2 = connectDB.prepareStatement(DEL_CUSTOMER_SQL);
            pstmt2.setString(1, String.valueOf(getID()));
            pstmt2.executeUpdate();
            
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
