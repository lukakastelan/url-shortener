package me.osiguraj.Services;

import me.osiguraj.Controllers.Controller;
import me.osiguraj.Database;
import me.osiguraj.Entities.UrlEntity;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

public class UrlService {
    private static final Database database = Database.DATABASE_INSTANCE;
    private static final Map<String, UrlEntity> urlDatabase = database.getUrlDatabase();
    private static final String domain = database.getDomain();

    public UrlEntity registerURL(String accountId, String password, UrlEntity urlEntity){
       String shortUrl = generateShortUrl();
       urlEntity.setShortUrl(shortUrl);

        urlDatabase.put(accountId, urlEntity);

        return urlEntity;
    }

    private String generateShortUrl(){
        BigInteger randomValue = new BigInteger(36, new SecureRandom());
        StringBuilder shortUrlSlug = new StringBuilder();

        while (randomValue.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = randomValue.divideAndRemainder(BigInteger.valueOf(Controller.CHARACTERS.length()));
            int index = divRem[1].intValue();
            shortUrlSlug.insert(0, Controller.CHARACTERS.charAt(index));
            randomValue = divRem[0];
        }

        return domain + shortUrlSlug;
    }
}
