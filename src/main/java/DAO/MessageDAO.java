package DAO;

import Util.ConnectionUtil;
import Model.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.azul.crs.client.Result;

public class MessageDAO{

    /**
     * select all messages 
     * @return
     */
   
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message";
            //String sql = "SELECT * FROM message INNER JOIN account WHERE account.account_id = message.posted_by";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
               
              Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                        messages.add(message);
                 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    /**
     * Retrieve a message from an expecific message_id number
     */
    public Message getMessageByMessageId(int message_id){
        
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
         
            //write preparedStatement's setInt method here.
           preparedStatement.setInt(1, message_id);

           ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                  
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                    
                  return message;
                  
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }
    /***
     * retrieve all message for user
     * @param message 
     * @return
     */
    public List<Message> getMessagesFromUList(int account_id){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE posted_by = ?";
         
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                        messages.add(message);                   
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    /**
     * create new message
     */
     public Message CreateMessage(Message message){

          Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO message VALUES (default, ?,?,?);"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
           //if there is a key to return
            if(rs.next()){
                return new Message(rs.getInt(1), 
                message.getPosted_by(), 
                message.getMessage_text(),
                 message.getTime_posted_epoch());
             }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
         
    
    
     }
//++++++++++++++++++end create message

    /**
     * delete a message given message id
     */
    public Message deleteMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
       
        try {
            //Write SQL logic here
            String sql = "DELETE FROM message WHERE message_id = ?";
            //preparedStatement 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,  message.getMessage_id());
            preparedStatement.executeUpdate();
        
          return message;
           
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}
/***
 * update message text
 * @return 
 */

 public void updateMessage(int id, Message message){
    Connection connection = ConnectionUtil.getConnection();
    try {
        //Write SQL logic here
     
     String sql = "UPDATE message SET posted_by = ?, message_text = ?, time_posted_epoch = ? WHERE message_id = ?;";
    
     PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //write PreparedStatement setString and setInt methods here.
      
       preparedStatement.setInt(1, message.getPosted_by());
       preparedStatement.setString(2, message.getMessage_text());
       preparedStatement.setLong(3, message.getTime_posted_epoch());
       preparedStatement.setInt(4, id);
       
       preparedStatement.executeUpdate();
   
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
   
}


}
