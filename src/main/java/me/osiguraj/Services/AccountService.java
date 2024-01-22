package me.osiguraj.Services;

import me.osiguraj.Controllers.Controller;
import me.osiguraj.Database;
import me.osiguraj.Entities.AccountEntity;

import java.security.SecureRandom;
import java.util.Map;


public class AccountService {
    private static final Database database = Database.DATABASE_INSTANCE;
    private static final Map<String, AccountEntity> accounts = database.getAccounts();

    public boolean registerAccount(String accountId){
        if(!isValidAccountId(accountId) || accounts.containsKey(accountId)){
            return false;
        }

        String password = generatePassword();
        AccountEntity newAccount = new AccountEntity(accountId,password);
        accounts.put(accountId,newAccount);
        return true;
    }

    private boolean isValidAccountId(String accountId){
        if(accountId == null || accountId.isEmpty()){
            return false;
        }
        return true;
    }

    private String generatePassword() {

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder(8);

        for (int i=0; i<8; i++) {
            int randomIndex = secureRandom.nextInt(Controller.CHARACTERS.length());
            password.append(Controller.CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }

    public boolean authorization(String accountId, String password) {
        if(accountId == null || password == null){
            return false;
        }
        if(accountId.isEmpty() || password.isEmpty()){
            return false;
        }
        else if(accounts.containsKey(accountId)){
            AccountEntity account = accounts.get(accountId);
            String accountPassword = account.getPassword();
            if(accountPassword.equals(password)){
                return true;
            }
            return false;
        }
        return false;
    }
}
