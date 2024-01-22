package me.osiguraj;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import me.osiguraj.Entities.AccountEntity;

import java.util.Map;

@ApplicationScoped
public class Test {

    private static final Database database = Database.DATABASE_INSTANCE;
    private static final Map<String, AccountEntity> accounts = database.getAccounts();

    void onStart(@Observes StartupEvent event) {
        AccountEntity testAccount = new AccountEntity("Luka Kastelan","123");
        accounts.put("Luka Kastelan", testAccount);
        System.out.println();
        System.out.println("AccountId: Luka Kastelan");
        System.out.println("Password: " + testAccount.getPassword());
    }
}