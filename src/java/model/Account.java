package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.DatabaseConnection;

public class Account {

    protected DatabaseConnection newConnection;
    public Connection connectDB;

    private String name, password, accountType;
    
    public Account() {
        //default constructor
    }

    //------------------get----------------------------//
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getAccountType(){
        return accountType;
    }
    /*
    //------------------set----------------------------//
    
    private void setName(String newName){
        this.name = name;
    }
    
    private void setPassword(String newPassword){
        this.password = newPassword;
    }
    
    private void setAccountType(String newAccountType){
        this.accountType = accountType;
    } */
    
    //-------------------------------------------------//

    public void DBconnect() {
        newConnection = new DatabaseConnection();
        connectDB = newConnection.getConnection();
    }

    //Check email / username for login
    public int checkNameType(){
        
        int type = 0; 
        
        if (getName().contains("@") && getName().contains(".com")) {
            type = 1; //email
        } else {
            type = 2; //username
        }        
        return type;
    } 
}
