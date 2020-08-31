package main.java.dev.project.bl;

import java.util.List;

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
    public List<User> getInfoUser(User ID) {
        return userDAL.getInfo(ID);
    }
    public boolean deleteAcc(User user) {
        return userDAL.deleteAcc(user) > 0;
    }
    public boolean updateAcc(User user) {
        return userDAL.updateUser(user) > 0;
    }
    
}