package dev.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.project.persistance.Like;

public class LikeDAL {
    public int checkLike(Like like) {
        try (Connection con = DbUtil.getConnection(); Statement stm = con.createStatement();) {
            ResultSet rs = stm.executeQuery("select * from like_comic where customer_id = '" + like.getCustomerID()
                    + "' and comic_id = '" + like.getComicID() + "'");
            while (rs.next()) {
                like.setLikeID(rs.getInt("like_id"));
            }
            return like.getLikeID();
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int unlike(Like unlike) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("DELETE FROM like_comic WHERE customer_id = '"
                        + unlike.getCustomerID() + "' and comic_id = '" + unlike.getComicID() + "'")) {
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int addlike(Like addlike) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con
                        .prepareStatement("INSERT INTO like_comic (customer_id, comic_id) VALUES ('"+addlike.getCustomerID()+"','"+addlike.getComicID()+"')");) {
            // pstm.setInt(1, addlike.getCustomerID());
            // pstm.setInt(2, addlike.getComicID());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }
}