package dev.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.project.persistance.Comment;

public class CommentDAL {
    public List<Comment> getCommentCID(Comment ID) {
        List<Comment> lst = new ArrayList<>();
        String sql = "SELECT * FROM view_comment where comic_id = '" + ID.getComicID() + "'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComment(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    public int addComment(Comment addC) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con
                        .prepareStatement("INSERT INTO comment (content,comic_id,customer_id) VALUES (?,?,?)");) {
            pstm.setString(1, addC.getContent());
            pstm.setInt(2, addC.getComicID());
            pstm.setInt(3, addC.getCustomerID());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    private Comment getComment(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setContent(rs.getString("content"));
        comment.setNameUser(rs.getString("name"));
        return comment;
    }
}