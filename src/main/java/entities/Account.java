package entities;

import com.epam.commons.DataClass;

public class Account extends DataClass<Account> {
    public String login;
    public String password;

    public static final String DEMO_ROOT_LOGIN = "email@gmail.com";
    public static final String DEMO_ROOT_PASSWORD = "password";

    public static Account DEMO_ACCOUNT = new Account().set(u -> {
                u.login = DEMO_ROOT_LOGIN;
                u.password = DEMO_ROOT_PASSWORD;
    });

}
