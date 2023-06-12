import Controller.SocialMediaController;
//import DAO.MessageDAO;
//import Model.Account;
//import Model.Message;
import io.javalin.Javalin;


/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        

       // System.out.println(msg.getMessageByMessageId(1));

       // System.out.println("****************************");
        /*
         * System.out.println("****************************");
        Account accot = new Account("testuser1", "password");
       System.out.println(accot);

        Message msg = new Message(1, "test message 1", 1669947792);
        System.out.println(msg);


        Message msg1 = new Message(2, "test message 2", 1669947793);
       // Message msg1 = new Message(3, "test message 3", 1669947794);
        System.out.println(msg1);
        // msg.getAllMessages();
        System.out.println("++++++++++++++++++++++");
        msg.getMessage_id();
        msg1.getMessage_id();
        
        
        //msg1.deleteMessage();        
    
   
         * 
         */
  
    }

}
