package Controller;

import java.util.List;
import Model.Account;
import Model.Message;
import Service.MessageService;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    private AccountService  accountService;
    private MessageService messageService;

    public SocialMediaController(){
        
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();

       // app.get("example-endpoint", this::exampleHandler);
        //return app;
        /**
         * register
         */
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/messages", this::postCreateMessageHandler);
        app.get("/messages", this::getAllMessageHandler);
        app.get("/messages/{message_id}", this::getMessageByMsgIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByMsgeIdHandler);
        app.patch("/messages/{message_id}", this::patchMessageUpdateHandler);
        app.get("/accounts/{account_id}/messages", this::getMessageByAccountIdHandler);
        
        return app;
    }

    /**
     * Work ok
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
  //  private void exampleHandler(Context context) {
    //    context.json("sample text");
//  }
private void postRegisterHandler(Context ctx) throws JsonProcessingException{
    
    Account account = ctx.bodyAsClass(Account.class);
    Account reg = accountService.authenticate(account);
    if(reg !=null){
        ctx.json(reg);
        ctx.status(200);
    }else{
        ctx.status(400);
    }
    
}
// Work ok

private void postLoginHandler(Context ctx) throws JsonProcessingException{
    Account account = ctx.bodyAsClass(Account.class);
   Account gt = accountService.userLogin(account);
    if(gt != null){
        ctx.json(gt);
        ctx.status(200);
    }else{
        ctx.status(401);
    }
}
/**
 * work ok
 * *******************************
 * @param ctx
 * @return
 * @throws JsonProcessingException
 */
private Message postCreateMessageHandler(Context ctx) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    Message message = mapper.readValue(ctx.body(), Message.class);
    Message addedMessage = messageService.CreateMessage(message);
    
    if(addedMessage!=null){
       ctx.json(mapper.writeValueAsString(addedMessage));
        ctx.status(200);
    }else{
        ctx.status(400);
    }
     
    return addedMessage;
}
/*
 * Work ok
 * ************************ 
 */
private void getAllMessageHandler(Context ctx)throws JsonProcessingException{
    ctx.json(messageService.getAllMessages()); 
    ctx.status(200);
}

//Work ok 
private Message getMessageByMsgIdHandler(Context ctx)throws JsonProcessingException{
   
    int message_id = Integer.parseInt(ctx.pathParam("message_id"));
   
    // get message back
   Message messageReturned = messageService.getMessageByIdMessage(message_id);
  if (messageReturned != null) {
       ctx.json(messageReturned);
        ctx.status(200);
    }
   
    return null;
    
   
}
/**
 * **************************
 * Delete Message 
 * **************************************
 */
private Message deleteMessageByMsgeIdHandler(Context ctx) throws JsonProcessingException{
    ObjectMapper mapper = new ObjectMapper();
    int mesgId = Integer.parseInt(ctx.pathParam("message_id"));
    
    Message deleM = new Message();
    deleM.setMessage_id(mesgId);
   
    Message deletedMesg = messageService.deleteMessage(deleM);
   
    if(deletedMesg != null){
        ctx.json(mapper.writeValueAsString(deletedMesg));

    }else{
        ctx.status(200);
    } 
  return null;  
}
private void patchMessageUpdateHandler(Context ctx) throws JsonProcessingException {

   ObjectMapper mapper = new ObjectMapper();
   Message message = mapper.readValue(ctx.body(), Message.class);

   int message_id = Integer.parseInt(ctx.pathParam("message_id"));
   Message updateTxMsg = messageService.updateMessageService(message_id, message);
   
   System.out.println(updateTxMsg);

   if(updateTxMsg != null){
    
    ctx.json(mapper.writeValueAsString(updateTxMsg));
    ctx.status(200);
    
   }else{
    ctx.status(400);
   }

}

/*********************************
 * Work ok
 */
private void getMessageByAccountIdHandler(Context ctx)throws JsonProcessingException{
     // get request information if needed
     int account_id = Integer.parseInt(ctx.pathParam("account_id"));
     // call messageService method
     List<Message> msgReturned = messageService.getMessagesFromUserList(account_id);
    // send results to client
     ctx.json(msgReturned);
     ctx.status(200); 
             
}

}
