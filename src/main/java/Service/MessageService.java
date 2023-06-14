package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    
    public MessageDAO  messageDAO;

    /**
     * No-args constructor for MessageService which creates a MessageDAO.
     *
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    /**
     * Constructor for a MessageService when a MessageDAO is provided.
     * @param messageDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
   
 /**
     * Use the messageDAO to retrieve a list of all messages 
     * @return all available messages 
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages(); 
    }

    /***
     * Retrieve message by Message id (work ok)
     */
 public Message getMessageByIdMessage(int message_id){
   
    return messageDAO.MessageByMessageId(message_id);
    
}

    /****
     * insert or add a new message (Work ok)
     * @return 
     */
    public Message CreateMessage(Message message) {
        if(message.getMessage_text().isBlank() || message.getMessage_text().length()>254){
            return null;
        } 
       return messageDAO.CreateMessage(message);
    } 
    /**
 * update  :)
 */
public Message updateMessageService(int message_id, Message message){

      Message existingMessage =  messageDAO.MessageByMessageId(message_id);  
   
       if(existingMessage != null &&  !message.getMessage_text().isBlank() && message.getMessage_text().length()<=255 ){
      
        existingMessage.setPosted_by(message.posted_by);
        existingMessage.setMessage_text(message.message_text);
         existingMessage.setTime_posted_epoch(message.time_posted_epoch);

       }return null;
}

/* *
  retrieve all messages by user list of messages 
 */
public List<Message> getMessagesFromUserList(int account_id) {
    
     List<Message> allMessagesReturned = messageDAO.getMessagesFromUList(account_id);
     List<Message> storeAllMessagesFromUser = new ArrayList<>(); 
      for(Message message : allMessagesReturned){
        if(message.getPosted_by() == account_id) {  
            storeAllMessagesFromUser.add(message);
     
        }        
       }
return storeAllMessagesFromUser;
       
} 

/***
 * Delete 
 */
 public Message deleteMessage(Message message_id){

    Message existingMessage =  messageDAO.MessageByMessageId(message_id.getMessage_id());
    if (existingMessage != null){
    
       messageDAO.deleteMessage(existingMessage);
       return existingMessage;
    } 
    return null;   
  }
   
  
}

