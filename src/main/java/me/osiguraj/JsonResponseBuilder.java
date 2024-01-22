package me.osiguraj;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import me.osiguraj.Entities.UrlEntity;

public class JsonResponseBuilder {

    public JsonObject RegisterAccountJsonBuilder(boolean success, Object password) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("success", success);

        if (success) {
            builder.add("description", "Your account is opened.");
            builder.add("password", password.toString());
        }
        else {
            builder.add("description", "An account with that ID already exists.");
        }

        return builder.build();
    }

    public JsonObject RegisterUrlJsonBuilder(UrlEntity urlEntity) {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (urlEntity.getShortUrl() != null) {
            builder.add("shortUrl", urlEntity.getShortUrl());
        }
        else {
            builder.add("description", "Invalid authorization or account ID");
        }

        return builder.build();
    }
}
