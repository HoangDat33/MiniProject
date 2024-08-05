package DAL;

import static DAL.DBContext.connection;
import Model.News;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO extends DBContext {

    public List<News> getListNew() {
        List<News> dataNews = new ArrayList<>();
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`"
                + "WHERE `news`.`IsDelete` = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CategoriesNewsDAO u = new CategoriesNewsDAO();
            while (rs.next()) {
                News news = new News(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ContentFirst"),
                        rs.getString("ContentBody"),
                        rs.getString("ContentEnd"),
                        u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                        rs.getBoolean("Hotnews"),
                        rs.getString("Image"),
                        rs.getString("DescriptionImage"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataNews.add(news);
            }
            return dataNews;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<News> getListNewByID(int Id) {
        List<News> dataNews = new ArrayList<>();
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`"
                + "WHERE `news`.`IsDelete` = 0 AND `news`.`CategoriesID` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            CategoriesNewsDAO u = new CategoriesNewsDAO();
            while (rs.next()) {
                News news = new News(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ContentFirst"),
                        rs.getString("ContentBody"),
                        rs.getString("ContentEnd"),
                        u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                        rs.getBoolean("Hotnews"),
                        rs.getString("Image"),
                        rs.getString("DescriptionImage"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataNews.add(news);
            }
            return dataNews;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<News> getListNewByIDAdmin(int Id) {
        List<News> dataNews = new ArrayList<>();
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`"
                + "WHERE `news`.`CategoriesID` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            CategoriesNewsDAO u = new CategoriesNewsDAO();
            while (rs.next()) {
                News news = new News(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ContentFirst"),
                        rs.getString("ContentBody"),
                        rs.getString("ContentEnd"),
                        u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                        rs.getBoolean("Hotnews"),
                        rs.getString("Image"),
                        rs.getString("DescriptionImage"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataNews.add(news);
            }
            return dataNews;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<News> getListHotNews() {
        List<News> dataNews = new ArrayList<>();
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`\n"
                + "WHERE `news`.`IsDelete` = 0 AND `news`.`Hotnews` = 1\n"
                + "ORDER BY `news`.`ID`\n"
                + "LIMIT 4;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            CategoriesNewsDAO u = new CategoriesNewsDAO();
            while (rs.next()) {
                News news = new News(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ContentFirst"),
                        rs.getString("ContentBody"),
                        rs.getString("ContentEnd"),
                        u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                        rs.getBoolean("Hotnews"),
                        rs.getString("Image"),
                        rs.getString("DescriptionImage"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataNews.add(news);
            }
            return dataNews;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public News getNewsById(int id) {
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`\n"
                + "WHERE `news`.`IsDelete` = 0 AND `news`.`ID` = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            CategoriesNewsDAO u = new CategoriesNewsDAO();
            News news = new News();
            if (rs.next()) {
                news = new News(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("ContentFirst"),
                        rs.getString("ContentBody"),
                        rs.getString("ContentEnd"),
                        u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                        rs.getBoolean("Hotnews"),
                        rs.getString("Image"),
                        rs.getString("DescriptionImage"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
            }
            return news;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<News> getListNewRelatedByID(int CateId, int Id) {
        List<News> dataNews = new ArrayList<>();
        String sql = "SELECT `news`.`ID`,\n"
                + "       `news`.`Title`,\n"
                + "       `news`.`Description`,\n"
                + "       `news`.`ContentFirst`,\n"
                + "       `news`.`ContentBody`,\n"
                + "       `news`.`ContentEnd`,\n"
                + "       `news`.`CategoriesID`,\n"
                + "       `news`.`Hotnews`,\n"
                + "       `news`.`Image`,\n"
                + "       `news`.`DescriptionImage`,\n"
                + "       `news`.`CreatedAt`,\n"
                + "       `news`.`UpdatedAt`,\n"
                + "       `news`.`DeletedAt`,\n"
                + "       `news`.`IsDelete`,\n"
                + "       `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`\n"
                + "WHERE `news`.`IsDelete` = 0 AND `news`.`CategoriesID` = ? AND `news`.`ID` != ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, CateId);
            ps.setInt(2, Id);

            try (ResultSet rs = ps.executeQuery()) {
                CategoriesNewsDAO u = new CategoriesNewsDAO();

                while (rs.next()) {
                    News news = new News(
                            rs.getInt("ID"),
                            rs.getString("Title"),
                            rs.getString("Description"),
                            rs.getString("ContentFirst"),
                            rs.getString("ContentBody"),
                            rs.getString("ContentEnd"),
                            u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                            rs.getBoolean("Hotnews"),
                            rs.getString("Image"),
                            rs.getString("DescriptionImage"),
                            rs.getDate("CreatedAt"),
                            rs.getDate("UpdatedAt"),
                            rs.getDate("DeletedAt"),
                            rs.getBoolean("IsDelete"),
                            rs.getInt("DeletedBy")
                    );
                    dataNews.add(news);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng Logger để ghi log
        }

        return dataNews;
    }

    public void deleteNewsWhenCateDelete(int adminId, int cateId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`news`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`DeletedAt` = NOW(),\n"
                + "`IsDelete` = 1,\n"
                + "`DeletedBy` = ?\n"
                + "WHERE `CategoriesID` = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, adminId);
            ps.setInt(2, cateId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No records found for CategoryID: " + cateId);
            }
        } catch (SQLException e) {
            // Sử dụng logger để ghi lại lỗi thay vì in ra console
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public boolean checkExistNews(String title) {
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`"
                + "Where `news`.`Title` = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            // Sử dụng logger để ghi lại lỗi thay vì in ra console
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return true;
    }

    public boolean checkExistNewsUpdate(String title, int id) {
        String sql = "SELECT `news`.`ID`,\n"
                + "    `news`.`Title`,\n"
                + "    `news`.`Description`,\n"
                + "    `news`.`ContentFirst`,\n"
                + "    `news`.`ContentBody`,\n"
                + "    `news`.`ContentEnd`,\n"
                + "    `news`.`CategoriesID`,\n"
                + "    `news`.`Hotnews`,\n"
                + "    `news`.`Image`,\n"
                + "    `news`.`DescriptionImage`,\n"
                + "    `news`.`CreatedAt`,\n"
                + "    `news`.`UpdatedAt`,\n"
                + "    `news`.`DeletedAt`,\n"
                + "    `news`.`IsDelete`,\n"
                + "    `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`"
                + "Where `news`.`Title` = ? AND `news`.`ID` != ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            // Sử dụng logger để ghi lại lỗi thay vì in ra console
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return true;
    }

    public void restoreNewsWhenCateRestore(int cateId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`news`\n"
                + "SET\n"
                + "`UpdatedAt` = NOW(),\n"
                + "`DeletedAt` = null,\n"
                + "`IsDelete` = 0,\n"
                + "`DeletedBy` = 0\n"
                + "WHERE `CategoriesID` = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cateId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No records found for CategoryID: " + cateId);
            }
        } catch (SQLException e) {
            // Sử dụng logger để ghi lại lỗi thay vì in ra console
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public boolean addNews(News news) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`news` "
                + "(`Title`, `Description`, `ContentFirst`, `ContentBody`, `ContentEnd`, "
                + "`CategoriesID`, `Hotnews`, `Image`, `DescriptionImage`, `CreatedAt`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getDescription());
            ps.setString(3, news.getContentFirst());
            ps.setString(4, news.getContentBody());
            ps.setString(5, news.getContentEnd());
            ps.setInt(6, news.getCategoriesNewsID()); // Đảm bảo lấy đúng ID của Categories
            ps.setBoolean(7, news.isHotnews());
            ps.setString(8, news.getImage());
            ps.setString(9, news.getDescriptionImage());

            int success = ps.executeUpdate(); // Thực hiện lệnh cập nhật
            return success > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            // Bạn có thể ghi log lỗi vào file hoặc hệ thống log ở đây
        }
        return false;
    }

    public void updateNews(News news) {
        String sql = "UPDATE `dbprojectswp391_v1`.`news`\n"
                + "SET\n"
                + "`Title` = ?,\n"
                + "`Description` = ?,\n"
                + "`ContentFirst` = ?,\n"
                + "`ContentBody` = ?,\n"
                + "`ContentEnd` = ?,\n"
                + "`CategoriesID` = ?,\n"
                + "`Hotnews` = ?,\n"
                + "`Image` = ?,\n"
                + "`DescriptionImage` = ?,\n"
                + "`UpdatedAt` = NOW()\n"
                + "WHERE `ID` = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getDescription());
            ps.setString(3, news.getContentFirst());
            ps.setString(4, news.getContentBody());
            ps.setString(5, news.getContentEnd());
            ps.setInt(6, news.getCategoriesNewsID()); // Sửa để lấy đúng ID của Categories
            ps.setBoolean(7, news.isHotnews());
            ps.setString(8, news.getImage());
            ps.setString(9, news.getDescriptionImage());
            ps.setInt(10, news.getID());

            ps.executeUpdate(); // Thực hiện lệnh cập nhật
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public void deleteNews(int id, int adminId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`news` "
                + "SET `UpdatedAt` = NOW(), "
                + "`DeletedAt` = NOW(), "
                + "`IsDelete` = 1, "
                + "`DeletedBy` = ? "
                + "WHERE `ID` = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, adminId);
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate(); // Thực hiện lệnh cập nhật

            // Kiểm tra số lượng hàng bị ảnh hưởng (không bắt buộc nhưng có thể hữu ích)
            if (rowsAffected == 0) {
                System.err.println("No news item found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public void restoreNews(int id) {
        String sql = "UPDATE `dbprojectswp391_v1`.`news` "
                + "SET `UpdatedAt` = NOW(), "
                + "`DeletedAt` = NULL, "
                + "`IsDelete` = 0, "
                + "`DeletedBy` = 0 "
                + "WHERE `ID` = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate(); // Thực hiện lệnh cập nhật

            // Kiểm tra số lượng hàng bị ảnh hưởng (không bắt buộc nhưng có thể hữu ích)
            if (rowsAffected == 0) {
                System.err.println("No news item found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    public List<News> searchListNew(String keyword) {
        List<News> dataNews = new ArrayList<>();
        String sql = "SELECT `news`.`ID`,\n"
                + "       `news`.`Title`,\n"
                + "       `news`.`Description`,\n"
                + "       `news`.`ContentFirst`,\n"
                + "       `news`.`ContentBody`,\n"
                + "       `news`.`ContentEnd`,\n"
                + "       `news`.`CategoriesID`,\n"
                + "       `news`.`Hotnews`,\n"
                + "       `news`.`Image`,\n"
                + "       `news`.`DescriptionImage`,\n"
                + "       `news`.`CreatedAt`,\n"
                + "       `news`.`UpdatedAt`,\n"
                + "       `news`.`DeletedAt`,\n"
                + "       `news`.`IsDelete`,\n"
                + "       `news`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`news`\n"
                + "WHERE `news`.`Title` LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                CategoriesNewsDAO u = new CategoriesNewsDAO();

                while (rs.next()) {
                    News news = new News(
                            rs.getInt("ID"),
                            rs.getString("Title"),
                            rs.getString("Description"),
                            rs.getString("ContentFirst"),
                            rs.getString("ContentBody"),
                            rs.getString("ContentEnd"),
                            u.getCategoriesNewsByID(rs.getInt("CategoriesID")),
                            rs.getBoolean("Hotnews"),
                            rs.getString("Image"),
                            rs.getString("DescriptionImage"),
                            rs.getDate("CreatedAt"),
                            rs.getDate("UpdatedAt"),
                            rs.getDate("DeletedAt"),
                            rs.getBoolean("IsDelete"),
                            rs.getInt("DeletedBy")
                    );
                    dataNews.add(news);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hoặc sử dụng Logger để ghi log
        }

        return dataNews;
    }

    public static void main(String[] args) {
        NewsDAO u = new NewsDAO();
        System.out.println(u.checkExistNews("Khuyến mãi cực sốc mùa hè 2024!"));
        System.out.println(u.checkExistNewsUpdate("Khuyến mãi cực sốc mùa hè 2024!", 1));
        News s = new News(1, "abc", "abc", "abc", "abc", "abc", 1, true, "abc", "abc");
        for (News news : u.searchListNew("c")) {
            System.out.println(news);
        }
    }
}
