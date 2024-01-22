package me.osiguraj.Controllers;

import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.osiguraj.Database;
import me.osiguraj.Entities.UrlEntity;
import me.osiguraj.Services.AccountService;
import me.osiguraj.Entities.AccountEntity;
import me.osiguraj.JsonResponseBuilder;
import me.osiguraj.Services.UrlService;

import java.util.Map;


@Path ("/api")
public class Controller {

    private static final Database database = Database.DATABASE_INSTANCE;

    public static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final AccountService accountService = new AccountService();
    private static final UrlService urlService = new UrlService();

    private static final JsonResponseBuilder jsonResponseBuilder = new JsonResponseBuilder();


    @POST
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerAccount(AccountEntity accountEntity) {
        String accountId = accountEntity.getAccountId();
        boolean success = accountService.registerAccount(accountId);
        Object password = accountEntity.getPassword();
        JsonObject jsonResponse = jsonResponseBuilder.RegisterAccountJsonBuilder(success,password);
        if(success) {
            System.out.println(jsonResponse);
            return Response.status(Response.Status.CREATED).entity(jsonResponse.toString()).build();
        }
        else {
            return Response.status(Response.Status.CONFLICT).entity(jsonResponse.toString()).build();
        }

    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUrl(@HeaderParam("accountId") String accountId, @HeaderParam("password") String password, UrlEntity urlEntity) {
        JsonObject jsonResponse;
        if(accountService.authorization(accountId, password) == false) {
            jsonResponse = jsonResponseBuilder.RegisterUrlJsonBuilder(urlEntity);
            return Response.status(Response.Status.UNAUTHORIZED).entity(jsonResponse.toString()).build();
        }
        else {
            urlEntity = urlService.registerURL(accountId, password, urlEntity);
            jsonResponse = jsonResponseBuilder.RegisterUrlJsonBuilder(urlEntity);
            database.updateStatistics(urlEntity.getOriginalUrl());

            if(urlEntity.getRedirectType() != null &&  urlEntity.getRedirectType() == 301)
            {
                return Response.status(Response.Status.MOVED_PERMANENTLY).entity(jsonResponse.toString()).build();
            }
            else
            {
                return Response.status(Response.Status.FOUND).entity(jsonResponse.toString()).build();
            }
        }
    }

    @GET
    @Path("/statistic/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics(@HeaderParam("accountId") String accountIdHeader, @HeaderParam("password") String password, @PathParam("accountId") String accountIdPath) {
        if (accountService.authorization(accountIdHeader,password) == false) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            Map<String, Integer> statistics = database.getStatistics();
            return Response.ok().entity(statistics).build();
        }
    }
}
