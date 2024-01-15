package model;

import java.sql.*;

public class Login extends Account {

    private String name, password, type, username;
    private boolean authenticator = false; //verify login info 

    //Login with Email
    private static final String EMAIL_SQL = "SELECT count(1) FROM useraccount WHERE email = ? && password = ?";

    //Login with Username
    private static final String USERNAME_SQL = "SELECT count(1) FROM useraccount WHERE username = ? && password = ?";

    //Get Account Type
    private static final String EMAIL_SQL2 = "SELECT type FROM useraccount WHERE email = ? && password = ?";
    private static final String USERNAME_SQL2 = "SELECT type FROM useraccount WHERE username = ? && password = ?";
   
    //Get Username (newcomer prompt)
    private static final String EMAIL_SQL3 = "SELECT username FROM useraccount WHERE email = ? && password = ?";
    private static final String USERNAME_SQL3 = "SELECT username FROM useraccount WHERE username = ? && password = ?";
    
    public Login() {
        //default constructor
        super();
    }

    public Login(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

    //------------------get----------------------------//
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public boolean getAuthenticator() {
        return authenticator;
    }
    
    public String getUsername() {
        return username;
    }

    //------------------set----------------------------//
    
    private void setType(String newType) {
        this.type = newType;
    }
    
    private void setUsername(String newUsername) {
        this.username = newUsername;
    }

    //-------------------------------------------------//
    public void validateLogin() {

        super.DBconnect();

        try {

            PreparedStatement pstmt = null;
            ResultSet result;
            int temp = super.checkNameType();

            if (temp == 1) {
                pstmt = connectDB.prepareStatement(EMAIL_SQL);
                pstmt.setString(1, getName());
                pstmt.setString(2, getPassword());
            } else if (temp == 2) {
                pstmt = connectDB.prepareStatement(USERNAME_SQL);
                pstmt.setString(1, getName());
                pstmt.setString(2, getPassword());
            } else {
                result = null;
                System.err.println("Error: Unable to identifty username or Email.");
            }

            result = pstmt.executeQuery();

            while (result.next()) {
                if (result.getInt(1) == 1) { //check count(1) returns -> .getInt(1) = columnIndex 1
                    System.out.println("Login Successfully");
                    authenticator = true;

                } else {
                    System.err.println("Username or Password is incorrect!");
                    authenticator = false;
                }
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

    //Check Admin/Customer/Employee
    public String getUserType() {

        super.DBconnect();

        try {

            PreparedStatement pstmt = null;
            ResultSet result;
            int temp = super.checkNameType();

            if (temp == 1) {
                pstmt = connectDB.prepareStatement(EMAIL_SQL2);
                pstmt.setString(1, getName());
                pstmt.setString(2, getPassword());
            } else if (temp == 2) {
                pstmt = connectDB.prepareStatement(USERNAME_SQL2);
                pstmt.setString(1, getName());
                pstmt.setString(2, getPassword());
            } else {
                result = null;
                System.err.println("Error: Unable to identifty Account Type.");
            }

            result = pstmt.executeQuery();

           while (result.next()) {
                setType(result.getString(1).trim());
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
        return getType();         
    } 
    
    public String checkNewcomer(){
        
        super.DBconnect();
        
        String temp2 = ""; //return 
        
        try {

            PreparedStatement pstmt = null;
            ResultSet result;
            int temp = super.checkNameType();

            if (temp == 1) {
                pstmt = connectDB.prepareStatement(EMAIL_SQL3);
                pstmt.setString(1, getName());
                pstmt.setString(2, getPassword());
                result = pstmt.executeQuery();
            } else if (temp == 2) {
                pstmt = connectDB.prepareStatement(USERNAME_SQL3);
                pstmt.setString(1, getName());
                pstmt.setString(2, getPassword());
            } else {
                result = null;
                System.err.println("Error: Unable to verify newcomer account.");
            }

            result = pstmt.executeQuery();

            while (result.next()) {                
                String s = result.getString(1);
                setUsername(s);
            } 
                      
            temp2 = getUsername();
            
            pstmt.close();
            result.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally { // Close the connection in the finally block to ensure it's always closed

            if (newConnection != null) {
                newConnection.closeConnection();
            }
        }
            
        return temp2;
    }

}
