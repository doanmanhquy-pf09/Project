package dev.project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.project.persistance.Comic;

public class ComicDAL {
    public List<Comic> getAll() {
        String sql = "select * from comic";
        List<Comic> lst = new ArrayList<>();
        try (Connection con = DbUtil.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);) {
            while (rs.next()) {
                lst.add(getComic(rs));
            }
        } catch (SQLException ex) {
            lst = null;

        }
        return lst;
    }

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

    public int insertComic(Comic comic) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement(
                        "INSERT INTO comic (name_comic,content,image,category,source,status,likes) VALUES (?,?,?,?,?,?,?)");) {
            pstm.setString(1, comic.getNameComic());
            pstm.setString(2, comic.getContent());
            pstm.setString(3, comic.getImage());
            pstm.setString(4, comic.getCategory());
            pstm.setString(5, comic.getSource());
            pstm.setString(6, comic.getStatus());
            pstm.setString(7, "0");
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int updateComic(Comic update) {
            try (Connection con = DbUtil.getConnection();
                    PreparedStatement pstm = con.prepareStatement("UPDATE comic SET " + update.getTemporary() + " = '"
                            + update.getTemporary2() + "'WHERE comic_id = '" + update.getComicID() + "'");) {
                return pstm.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("<!> Error <!>");
                return 0;
            }
        }

    public int updatelikeComic(Comic updateLike) {
        try (Connection con = DbUtil.getConnection();
                PreparedStatement pstm = con.prepareStatement("UPDATE comic SET likes = '" + updateLike.getLikes()
                        + "' WHERE comic_id = '" + updateLike.getComicID() + "'");) {
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("<!> Error <!>");
            return 0;
        }
    }

    public int deleteComic(Comic comic) {
        PreparedStatement pstm;
        String sql = "DELETE FROM like_comic WHERE (comic_id = '" + comic.getComicID() + "')";
        String sql1 = "DELETE FROM comment WHERE (comic_id = '" + comic.getComicID() + "')";
        String sql2 = "DELETE FROM comic WHERE (comic_id = '" + comic.getComicID() + "')";
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

    private Comic getComic(ResultSet rs) throws SQLException {
        Comic comic = new Comic();
        comic.setNameComic(rs.getString("name_comic"));
        comic.setContent(rs.getString("content"));
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