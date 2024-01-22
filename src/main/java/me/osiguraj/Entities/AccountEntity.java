package me.osiguraj.Entities;

public class AccountEntity {
    private String accountId;
    private String password;

    public AccountEntity(String accountId, String password){
        this.accountId = accountId;
        this.password = password;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
