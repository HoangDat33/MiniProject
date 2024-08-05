package DAL;

import Model.CommentNews;
import Model.RegisterNotification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentNewsDAO extends DBContext {

    public void insertCommentNews(CommentNews comment) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`commentnews`"
                + "(`Name`, `Email`, `Message`, `NewsID`, `CreatedAt`) "
                + "VALUES (?, ?, ?, ?, NOW())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, comment.getName());
            ps.setString(2, comment.getEmail());
            ps.setString(3, comment.getMessage());
            ps.setInt(4, comment.getNewsId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<CommentNews> getAllCommentNews() {
        List<CommentNews> data = new ArrayList<>();
        String sql = "SELECT `commentnews`.`ID`,\n"
                + "    `commentnews`.`Name`,\n"
                + "    `commentnews`.`Email`,\n"
                + "    `commentnews`.`Message`,\n"
                + "    `commentnews`.`NewsID`,\n"
                + "    `commentnews`.`CreatedAt`,\n"
                + "    `commentnews`.`UpdatedAt`,\n"
                + "    `commentnews`.`DeletedAt`,\n"
                + "    `commentnews`.`IsDelete`,\n"
                + "    `commentnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`commentnews`;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            NewsDAO newsDao = new NewsDAO();
            while (rs.next()) {
                CommentNews cmNews = new CommentNews(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Message"),
                        newsDao.getNewsById(rs.getInt("NewsID")),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(cmNews);
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
     public List<CommentNews> getAllCommentNewsAdmin(String keyword) {
        List<CommentNews> data = new ArrayList<>();
        String sql = "SELECT `commentnews`.`ID`,\n"
                + "    `commentnews`.`Name`,\n"
                + "    `commentnews`.`Email`,\n"
                + "    `commentnews`.`Message`,\n"
                + "    `commentnews`.`NewsID`,\n"
                + "    `commentnews`.`CreatedAt`,\n"
                + "    `commentnews`.`UpdatedAt`,\n"
                + "    `commentnews`.`DeletedAt`,\n"
                + "    `commentnews`.`IsDelete`,\n"
                + "    `commentnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`commentnews`"
                + "WHERE `commentnews`.`Email` LIKE ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+keyword + "%");
            ResultSet rs = ps.executeQuery();
            NewsDAO newsDao = new NewsDAO();
            while (rs.next()) {
                CommentNews cmNews = new CommentNews(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Message"),
                        newsDao.getNewsById(rs.getInt("NewsID")),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(cmNews);
            }
            return data;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void rejectComment(int id, int adminId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`commentnews`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`DeletedAt` = NOW(),\n"
                + "`IsDelete` = 1,\n"
                + "`DeletedBy` = ?\n"
                + "WHERE `ID` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, adminId);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        CommentNewsDAO u = new CommentNewsDAO();
        u.rejectComment(1, 1);
    }
}
