package DAO;

import Util.ConnectionUtil;

import Model.Account;
import java.sql.*;



public class AccountDAO{

  
   // int idGenerator = 0;
        
        public Account findByUserName(String username){
        Connection connection = ConnectionUtil.getConnection();
     
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

public Account findByID(int account_id){
    Connection connection = ConnectionUtil.getConnection();
    try{
        String sql = "SELECT * FROM account WHERE account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account_id);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
           
            Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
                return account;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }

 return null;
}

    /***
     * 
     * Insert new account
     */
    
     public Account createAccount(Account account){
        
        Connection connection = ConnectionUtil.getConnection();
        try {
         
            String sql = "INSERT INTO account (username, password) VALUES (?,?);";
           
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            int rowsInserted = preparedStatement.executeUpdate();
          if(rowsInserted>0){
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                int idGenerator = (int) keys.getInt(1);
                return new Account(idGenerator, account.getUsername(), account.getPassword());
          }
           
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
     return account;
     }

    }

