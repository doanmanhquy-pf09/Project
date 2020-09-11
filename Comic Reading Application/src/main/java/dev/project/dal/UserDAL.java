package main.java.dev.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.dev.project.persistance.User;

public class UserDAL {
    
    public int addLogin(User login) {
        try (Connection con = DbUtil.getConnection(); Statement stm = con.createStatement();) {
            ResultSet rs = stm.executeQuery("select * from admin where userName = '" + login.getUserNameLogin()
                    + "' and password = '" + login.getUserNameLogin() + "'");
            while (rs.next()) {
                login.setID(rs.getInt("admin_id"));
            }
            if (login.getID() == 0) {
                rs = stm.executeQuery("select * from customer where userName = '" + login.getUserNameLogin()
                        + "' and password = '" + login.getUserNameLogin() + "'");
                while (rs.next()) {
                    login.setID(rs.getInt("customer_id"));
                }
            }
            return login.getID();
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int creAcc(User cre) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con
                        .prepareStatement("INSERT INTO customer (name,email,userName,password) VALUES (?,?,?,?)");) {
            pstm.setString(1, cre.getEmailCre());
            pstm.setString(2, cre.getUserNameCre());
            pstm.setString(3, cre.getPasswordCre());
            pstm.setString(4, cre.getYourNameCre());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int deleteAcc(User user) {
        PreparedStatement pstm;
        String sql = "DELETE FROM like_comic WHERE (customer_id = '" + user.getID() + "')";
        String sql1 = "DELETE FROM comment WHERE (customer_id = '" + user.getID() + "')";
        String sql2 = "DELETE FROM customer WHERE (customer_id = '" + user.getID() + "')";
        try (Connection con = DbUtil.getConnection()) {
            pstm = con.prepareStatement(sql);
            pstm.executeUpdate(sql);
            pstm = con.prepareStatement(sql1);
            pstm.executeUpdate(sql1);
            pstm = con.prepareStatement(sql2);
            pstm.executeUpdate(sql2);
            return 1;
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public List<User> getInfo(User ID) {
        List<User> lst = new ArrayList<>();
        String sql = "SELECT * FROM customer where customer_id = '" + ID.getID() + "'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getUser(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    private User getUser(final ResultSet rs) throws SQLException {
        User user = new User();
        user.setYourName(rs.getString("name"));
        user.setUserName(rs.getString("userName"));
        user.setDateOfBirth(rs.getString("date_of_birth"));
        user.setGender(rs.getString("gender"));
        user.setAddress(rs.getString("address"));
        user.setEmail(rs.getString("email"));
        user.setID(rs.getInt("customer_id"));
        return user;
    }

    public int updateUser(User update) {
        System.out.println(update.getID());
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("UPDATE customer SET " + update.getTemporary() + " = '"
                        + update.getTemporary2() + "'WHERE customer_id = '" + update.getID() + "'");) {
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }
}