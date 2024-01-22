package me.osiguraj.Entities;


public class UrlEntity {
    private String originalUrl;
    private String shortUrl = null;
    private Integer redirectType;

    public UrlEntity(String originalUrl, Integer redirectType) {
        this.originalUrl = originalUrl;
        this.redirectType = redirectType;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }
}
