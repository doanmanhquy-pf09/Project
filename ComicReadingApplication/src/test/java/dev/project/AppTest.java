package dev.project;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import dev.project.persistance.User;
import dev.project.bl.UserBL;

public class AppTest {

    /*------------Test Login-----------*/
    /* */
    /* */
    /* */
    private UserBL accountBL = new UserBL();
    private User account = new User();

    @Test
    public void validlogin() {
        account.setUserNameLogin("admin");
        account.setPasswordLogin("admin");
        int result = accountBL.addlogin(account);
        assertTrue(result > 0);
    }

    @Test
    public void LoginWithWrongUsername() {
        account.setUserNameLogin("AMDIN");
        account.setPasswordLogin("admin");
        int result = accountBL.addlogin(account);
        assertFalse(result > 0);
    }

    @Test
    public void LoginWithWrongPassword() {
        account.setUserNameLogin("admin");
        account.setPasswordLogin("AMDIN");
        int result = accountBL.addlogin(account);
        assertFalse(result > 0);
    }

    @Test
    public void usernameIsEmpty() {
        account.setUserNameLogin("");
        String result1 = account.getUserNameLogin();
        int result = accountBL.addlogin(account);
        assertTrue(result1.trim().equals("") && result < 1);
    }

    @Test
    public void usernameIsNotEmpty() {
        account.setUserNameLogin("admin");
        String result1 = account.getUserNameLogin();
        int result = accountBL.addlogin(account);
        assertTrue(result1.trim().equals("admin") && result < 1);
    }

    @Test
    public void passwordIsEmpty() {
        account.setPasswordLogin("");
        String result1 = account.getPasswordLogin();
        int result = accountBL.addlogin(account);
        assertTrue(result1.trim().equals("") && result < 1);
    }

    @Test
    public void passwordIsNotEmpty() {
        account.setPasswordLogin("admin");
        String result1 = account.getPasswordLogin();
        int result = accountBL.addlogin(account);
        assertTrue(result1.trim().equals("admin") && result < 1);
    }

}
