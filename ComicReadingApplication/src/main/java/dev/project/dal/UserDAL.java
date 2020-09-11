package dev.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.project.persistance.User;

public class UserDAL {
    public List<User> getAll() {
        String sql = "select * from customer";
        List<User> lst = new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getUser(rs));
            }
        } catch (SQLException ex) {
            System.out.println("<!> Not available <!>");
            lst = null;
        }
        return lst;
    }

    public List<User> getCBName(User search) {
        List<User> lst = new ArrayList<>();
        String sql = "select * from customer where userName = '" + search.getUserName() + "'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getUser(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Not available <!>");
            lst = null;
        }
        return lst;
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
            System.out.println("<!> Not available <!>");
            lst = null;
        }
        return lst;
    }

    public int addLogin(User login) {
        try (Connection con = DbUtil.getConnection(); Statement stm = con.createStatement();) {
            ResultSet rs = stm.executeQuery("select * from admin where userName = '" + login.getUserNameLogin()
                    + "' and password = (md5('"+login.getPasswordLogin()+"'))");
            while (rs.next()) {
                login.setID(rs.getInt("admin_id"));
            }
            if (login.getID() == 0) {
                rs = stm.executeQuery("select * from customer where userName = '" + login.getUserNameLogin()
                        + "' and password = (md5('"+login.getPasswordLogin()+"'))");
                while (rs.next()) {
                    login.setID(rs.getInt("customer_id"));
                }
            }
            return login.getID();
        } catch (SQLException e) {
            System.out.println("<!> Not available <!>");
            return 0;
        }
    }

    public int creAcc(User cre) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con
                        .prepareStatement("INSERT INTO customer (name,email,userName,password) VALUES (?,?,?,(md5('"+cre.getPassword()+"')))");) {
            pstm.setString(1, cre.getYourName());
            pstm.setString(2, cre.getEmail());
            pstm.setString(3, cre.getUserName());
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

    public int updateCustomer(User update) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("UPDATE customer SET " + update.getTemporary() + " = '"
                        + update.getTemporary2() + "'WHERE customer_id = '" + update.getID() + "'");) {
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int updateAdmin(User update) {
        PreparedStatement pstm;
        String sql = "UPDATE admin SET userName = '"+update.getUserName()+"',password = (md5('"+update.getPassword()+"')) WHERE admin_id = '"+update.getID()+"';";
        String sql1 = "UPDATE customer SET userName = '"+update.getUserName()+"',password = (md5('"+update.getPassword()+"')) WHERE customer_id = '"+update.getID()+"';";
        try (Connection con = DbUtil.getConnection()) {
            pstm = con.prepareStatement(sql);
            pstm.executeUpdate(sql);
            pstm = con.prepareStatement(sql1);
            pstm.executeUpdate(sql1);
            return 1;
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int updatePassword(User update) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("UPDATE customer SET password = (md5('"+update.getTemporary2()+"')) WHERE customer_id = '" + update.getID() + "'");) {
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int insertUser(User user) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement(
                        "INSERT INTO customer (name,date_of_birth,gender,address,email,userName,password) VALUES (?,?,?,?,?,?,(md5('"+user.getPassword()+"')))");) {
            pstm.setString(1, user.getYourName());
            pstm.setString(2, user.getDateOfBirth());
            pstm.setString(3, user.getGender());
            pstm.setString(4, user.getAddress());
            pstm.setString(5, user.getEmail());
            pstm.setString(6, user.getUserName());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public List<User> getIdCustomer(User ID) {
        List<User> lst = new ArrayList<>();
        String sql = "select * from customer where customer_id = '" + ID.getID() + "'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getUser(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Not available <!>");
            lst = null;
        }
        return lst;
    }

    private User getUser(final ResultSet rs) throws SQLException {
        User user = new User();
        user.setYourName(rs.getString("name"));
        user.setDateOfBirth(rs.getString("date_of_birth"));
        user.setGender(rs.getString("gender"));
        user.setAddress(rs.getString("address"));
        user.setEmail(rs.getString("email"));
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setID(rs.getInt("customer_id"));
        return user;
    }
}
