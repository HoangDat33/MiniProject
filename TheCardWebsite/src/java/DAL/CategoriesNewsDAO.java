package DAL;

import static DAL.DBContext.connection;
import Model.CategoriesNews;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesNewsDAO extends DBContext {

    public CategoriesNews getCategoriesNewsByID(int id) {
        String sql = "SELECT `categoriesnews`.`ID`,\n"
                + "    `categoriesnews`.`Title`,\n"
                + "    `categoriesnews`.`CreatedAt`,\n"
                + "    `categoriesnews`.`UpdatedAt`,\n"
                + "    `categoriesnews`.`DeletedAt`,\n"
                + "    `categoriesnews`.`IsDelete`,\n"
                + "    `categoriesnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`categoriesnews`"
                + "WHERE `categoriesnews`.`ID` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            CategoriesNews ctn = new CategoriesNews();
            if (rs.next()) {
                ctn = new CategoriesNews(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
            }
            return ctn;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<CategoriesNews> getListCategoriesNews() {
        List<CategoriesNews> dataCate = new ArrayList<>();
        String sql = "SELECT `categoriesnews`.`ID`,\n"
                + "    `categoriesnews`.`Title`,\n"
                + "    `categoriesnews`.`CreatedAt`,\n"
                + "    `categoriesnews`.`UpdatedAt`,\n"
                + "    `categoriesnews`.`DeletedAt`,\n"
                + "    `categoriesnews`.`IsDelete`,\n"
                + "    `categoriesnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`categoriesnews`"
                + "WHERE `categoriesnews`.`IsDelete` = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CategoriesNews ctn = new CategoriesNews();
            while (rs.next()) {
                ctn = new CategoriesNews(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataCate.add(ctn);
            }
            return dataCate;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<CategoriesNews> getListCategoriesNewsAdmin() {
        List<CategoriesNews> dataCate = new ArrayList<>();
        String sql = "SELECT `categoriesnews`.`ID`,\n"
                + "    `categoriesnews`.`Title`,\n"
                + "    `categoriesnews`.`CreatedAt`,\n"
                + "    `categoriesnews`.`UpdatedAt`,\n"
                + "    `categoriesnews`.`DeletedAt`,\n"
                + "    `categoriesnews`.`IsDelete`,\n"
                + "    `categoriesnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`categoriesnews`";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CategoriesNews ctn = new CategoriesNews();
            while (rs.next()) {
                ctn = new CategoriesNews(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataCate.add(ctn);
            }
            return dataCate;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<CategoriesNews> searchListCategoriesNews(String keyword) {
        List<CategoriesNews> dataCate = new ArrayList<>();
        String sql = "SELECT `categoriesnews`.`ID`,\n"
                + "    `categoriesnews`.`Title`,\n"
                + "    `categoriesnews`.`CreatedAt`,\n"
                + "    `categoriesnews`.`UpdatedAt`,\n"
                + "    `categoriesnews`.`DeletedAt`,\n"
                + "    `categoriesnews`.`IsDelete`,\n"
                + "    `categoriesnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`categoriesnews`"
                + "WHERE `categoriesnews`.`IsDelete` = 0 AND `categoriesnews`.`Title` LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            CategoriesNews ctn = new CategoriesNews();
            while (rs.next()) {
                ctn = new CategoriesNews(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataCate.add(ctn);
            }
            return dataCate;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void addNewCategoriesNews(String title) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`categoriesnews` (Title, CreatedAt) VALUES (?, NOW())";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateNewCategoriesNews(String title, int id) {
        String sql = "UPDATE `dbprojectswp391_v1`.`categoriesnews`\n"
                + "SET\n"
                + "`Title` = ?,\n"
                + "`UpdatedAt` = NOW()\n"
                + "WHERE `ID` = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteNewCategoriesNews(int adminId, int id) {
        String sql = "UPDATE `dbprojectswp391_v1`.`categoriesnews`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`DeletedAt` = NOW(),\n"
                + "`IsDelete` = 1,\n"
                + "`DeletedBy` = ?\n"
                + "WHERE `ID` = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, adminId);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void restoreNewCategoriesNews(int id) {
        String sql = "UPDATE `dbprojectswp391_v1`.`categoriesnews`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`DeletedAt` = NULL,\n"
                + "`IsDelete` = 0,\n"
                + "`DeletedBy` = 0\n"
                + "WHERE `ID` = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            // Sử dụng logger để ghi lại lỗi thay vì in ra console
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public boolean checkExistTitle(String keyword) {
        List<CategoriesNews> dataCate = new ArrayList<>();
        String sql = "SELECT `categoriesnews`.`ID`,\n"
                + "    `categoriesnews`.`Title`,\n"
                + "    `categoriesnews`.`CreatedAt`,\n"
                + "    `categoriesnews`.`UpdatedAt`,\n"
                + "    `categoriesnews`.`DeletedAt`,\n"
                + "    `categoriesnews`.`IsDelete`,\n"
                + "    `categoriesnews`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`categoriesnews`"
                + "WHERE `categoriesnews`.`IsDelete` = 0 AND `categoriesnews`.`Title` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, keyword);
            ResultSet rs = ps.executeQuery();
            CategoriesNews ctn = new CategoriesNews();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public static void main(String[] args) {
        CategoriesNewsDAO u = new CategoriesNewsDAO();
        //System.out.println(u.getCategoriesNewsByID(1));
        //System.out.println(u.getListCategoriesNews());
        // System.out.println(u.searchListCategoriesNews("khuyến mãi"));
//     /   u.addNewCategoriesNews("ABCD");
        // u.updateNewCategoriesNews("Hello", 11);
        //System.out.println(u.checkExistTitle("ABCDF"));
        //u.deleteNewCategoriesNews(1, 11);
//        for (CategoriesNews c : u.getListCategoriesNewsAdmin()) {
//            System.out.println(c);
//        }

            u.restoreNewCategoriesNews(11);
    }
}
