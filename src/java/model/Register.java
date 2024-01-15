package model;

import java.sql.*;

public class Register {

    protected DatabaseConnection newConnection;
    protected Connection connectDB;
    
    private String email, password, password2;
    
    private boolean authenticator  = false; //check same password
    private boolean authenticator2 = false; //check if email is taken
    
    //Account type 
    private static final String ACCOUNT_TYPE = "customer";
    
    //Create acc query
    private static final String CREATEACC_SQL = "INSERT INTO useraccount (email, password, type) VALUES (?, ?, ?)";
    
    //Find acc duplicates query
    private static final String SEARCHACC_SQL = "SELECT count(1) FROM useraccount WHERE email = ?";
    
    //Store [PK].id as [FK] in customer table
    private static final String CUSTOMER_SQL = "INSERT INTO customer(account_id) VALUES (LAST_INSERT_ID())";

    public Register() {
        //default constructor
    }

    public Register(String email, String password, String password2) {
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }

    //------------------get----------------------------//
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public String getType() {
        return ACCOUNT_TYPE;
    }

    public boolean getAuthenticator() {
        return authenticator;
    }
    
    public boolean getAuthenticator2() {
        return authenticator2;
    }

    //------------------set----------------------------//
    private void setAuthenticator(boolean newAuthenticator) {
        this.authenticator = newAuthenticator;
    }
    
    private void setAuthenticator2(boolean newAuthenticator2) {
        this.authenticator2 = newAuthenticator2;
    }

    //-------------------------------------------------//
    
    //Database connection
    public void DBconnect() {
        newConnection = new DatabaseConnection();
        connectDB = newConnection.getConnection();
    }
    
    //Register acc
    public void createAcc() {
        
        DBconnect();

        try{
 
            PreparedStatement pstmt = connectDB.prepareStatement(CREATEACC_SQL);
            
            pstmt.setString(1, getEmail());
            pstmt.setString(2, getPassword());
            pstmt.setString(3, getType());           
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

    //Check if pass == retype pass
    public void verifyPass() {
        
        String pass1 = getPassword();
        String pass2 = getPassword2();

        if (!pass1.equals(pass2)) {
            setAuthenticator(false);
        } else {
            setAuthenticator(true);
        }
    }

    //Find email duplicates
    public void validateEmail() {
        
        DBconnect();

        try{
 
            PreparedStatement pstmt = connectDB.prepareStatement(SEARCHACC_SQL);
            
            pstmt.setString(1, getEmail());
            ResultSet result = pstmt.executeQuery();
            //pstmt.executeUpdate();
            
            while(result.next()){
                if(result.getInt(1) == 1){ //check count(1) returns -> .getInt(1) = columnIndex 1
                    setAuthenticator2(true);              
                }else{
                    setAuthenticator2(false); 
                }
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
