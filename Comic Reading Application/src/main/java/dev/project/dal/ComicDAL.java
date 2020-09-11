package main.java.dev.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.dev.project.persistance.Comic;

public class ComicDAL {

    public List<Comic> getCBName(Comic search) {
        List<Comic> lst = new ArrayList<>();
        String sql = "select * from view_comic where name_comic like '%" + search.getNameComic() + "%'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComic(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    public List<Comic> getCBCategory(Comic category) {
        List<Comic> lst = new ArrayList<>();
        String sql = "select * from view_comic where category like '%" + category.getCategory() + "%'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComic(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    public List<Comic> getCBStatus(Comic status) {
        List<Comic> lst = new ArrayList<>();
        String sql = "select * from view_comic where status = '" + status.getStatus() + "'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComic(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    public List<Comic> getCBRank() {
        List<Comic> lst = new ArrayList<>();
        String sql = "SELECT * FROM view_comic ORDER BY likes DESC LIMIT 10";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComic(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    public List<Comic> getComicID(Comic ID) {
        List<Comic> lst = new ArrayList<>();
        String sql = "select * from view_comic where comic_id = '" + ID.getComicID() + "'";
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComic(rs));
            }
        } catch (SQLException e) {
            System.out.println("<!> Error <!>");
            lst = null;
        }
        return lst;
    }

    public int updatelikeComic(Comic updateLike) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement(
                    "UPDATE comic SET likes = '"+updateLike.getLikes()+"' WHERE comic_id = '"+updateLike.getComicID()+"'");) {
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }
    

    private Comic getComic(ResultSet rs) throws SQLException {
        Comic comic = new Comic();
        comic.setNameComic(rs.getString("name_comic"));
        comic.setContent(rs.getString("conten"));
        comic.setStatus(rs.getString("status"));
        comic.setPosting_date(rs.getString("posting_date"));
        comic.setCategory(rs.getString("category"));
        comic.setSource(rs.getString("source"));
        comic.setImage(rs.getString("image"));
        comic.setLikes(rs.getInt("likes"));
        comic.setComicID(rs.getInt("comic_id"));
        return comic;
    }
}