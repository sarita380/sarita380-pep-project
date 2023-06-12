package Service;
import Model.Account;

//import java.util.Optional;

import DAO.AccountDAO;

//import java.util.List;




public class AccountService {
    private AccountDAO accountDAO;


    
    /**
     * no-args constructor for creating a new AccountService with a new AccountDAO.
     * 
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

   
   //public void createAccount(Account account) {
     //  Account acct = new Account(account.getAccount_id(),account.getUsername(), account.getPassword());
    
       //accountDAO.createAccount(acct);
   // }

    /**
     * Authentication for registering a new account
     * @param account
     * @return
     */
    public Account authenticate(Account account) {
       
       if(account.getUsername().isBlank() ||  account.getPassword().length()<4){
            return null;
       }
      Account existingAccount = accountDAO.findByUserName(account.getUsername());
     if(existingAccount != null && existingAccount.getUsername().equals(account.getUsername())){
        
        return null;
       }  
      
       Account AccountToCreate = accountDAO.createAccount(account);
       if(AccountToCreate != null){
        return AccountToCreate; 
       }
       
       return null;
    }
/***
 * user login 
 */
public Account userLogin(Account account){
    Account existingAccount = accountDAO.findByUserName(account.getUsername()); 
    if(existingAccount!= null && existingAccount.getPassword().equals(account.getPassword())){
        return existingAccount;
    }
 return null;
}

}
