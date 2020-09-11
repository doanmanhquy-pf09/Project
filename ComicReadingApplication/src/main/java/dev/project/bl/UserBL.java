package dev.project.bl;

import java.util.List;

import dev.project.dal.UserDAL;
import dev.project.persistance.User;

public class UserBL {
    private UserDAL userDAL = new UserDAL();

    public int addlogin(User login) {
        return userDAL.addLogin(login);
    }

    // admin
    public boolean updateAdmin(User user) {
        return userDAL.updateAdmin(user) > 0;
    }

    public List<User> getIdCustomer(User ID) {
        return userDAL.getIdCustomer(ID);
    }

    public List<User> getAllUser() {
        return userDAL.getAll();
    }

    public List<User> getCustomerByUserName(User search) {
        return userDAL.getCBName(search);
    }
    // customer
    public boolean creAcc(User cre) {
        return userDAL.creAcc(cre) > 0;
    }

    public boolean deleteAcc(User user) {
        return userDAL.deleteAcc(user) > 0;
    }

    public boolean updateAcc(User user) {
        return userDAL.updateCustomer(user) > 0;
    }

    public boolean updatePass(User user) {
        return userDAL.updatePassword(user) > 0;
    }

    public List<User> getInfoUser(User ID) {
        return userDAL.getInfo(ID);
    }
    
}