package main.java.dev.project.bl;

import main.java.dev.project.dal.UserDAL;
import main.java.dev.project.persistance.User;

public class UserBL {
    private UserDAL userDAL = new UserDAL();

    public int addlogin(User login) {
        return userDAL.addLogin(login);
    }

    public boolean creAcc(User cre) {
        return userDAL.creAcc(cre) > 0;
    }
    
}