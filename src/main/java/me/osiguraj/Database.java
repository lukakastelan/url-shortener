package me.osiguraj;

import me.osiguraj.Entities.AccountEntity;
import me.osiguraj.Entities.UrlEntity;

import java.util.HashMap;
import java.util.Map;

//Singleton
public class Database {
    public static final Database DATABASE_INSTANCE = new Database();
    private final Map<String, AccountEntity> accounts; //Param accountID,password
    private final Map<String, UrlEntity> urlDatabase; //Param shortUrl, UrlEntity
    private final Map<String, Integer> statistics;
    private final String domain = "http://osiguraj.me/";

    private Database () {
        this.accounts = new HashMap<>();
        this.urlDatabase = new HashMap<>();
        this.statistics = new HashMap<>();
    }

    public Map<String, AccountEntity> getAccounts() {
        return accounts;
    }

    public Map<String, UrlEntity> getUrlDatabase() {
        return urlDatabase;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public void updateStatistics(String url) {
        if(!statistics.containsKey(url)){
            statistics.put(url,1);
        }
        else {
            statistics.put(url,statistics.get(url) + 1);
        }
    }

    public String getDomain(){
        return domain;
    }
}
