package DAL;

import static DAL.DBContext.connection;
import Model.Brand;
import Model.Comment;
import Model.ProductCard;
import Model.ProductCategories;
import Model.Report;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAO extends DBContext {

    public List<Brand> getListBrand() {
        List<Brand> data = new ArrayList<>();
        String sql = "SELECT `brands`.`ID`,\n"
                + "    `brands`.`BrandName`,\n"
                + "    `brands`.`Image`,\n"
                + "    `brands`.`CreatedAt`,\n"
                + "    `brands`.`UpdatedAt`,\n"
                + "    `brands`.`DeletedAt`,\n"
                + "    `brands`.`IsDelete`,\n"
                + "    `brands`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`brands`\n"
                + "WHERE `brands`.`IsDelete` = 0;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            Brand brand = null;
            while (rs.next()) {
                brand = new Brand(rs.getInt("ID"),
                        rs.getString("BrandName"),
                        rs.getString("Image"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(brand);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public List<ProductCategories> getListProductCategory() {
        List<ProductCategories> data = new ArrayList<>();
        String sql = "SELECT `productcategories`.`ID`,\n"
                + "    `productcategories`.`BrandID`,\n"
                + "    `productcategories`.`Name`,\n"
                + "    `productcategories`.`Price`,\n"
                + "    `productcategories`.`Image`,\n"
                + "    `productcategories`.`Quantity`,\n"
                + "    `productcategories`.`Description`,\n"
                + "    `productcategories`.`Discount`,\n"
                + "    `productcategories`.`DiscountFrom`,\n"
                + "    `productcategories`.`DiscountTo`,\n"
                + "    `productcategories`.`CreatedAt`,\n"
                + "    `productcategories`.`UpdatedAt`,\n"
                + "    `productcategories`.`DeletedAt`,\n"
                + "    `productcategories`.`IsDelete`,\n"
                + "    `productcategories`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`productcategories`;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            //st.setString(1, id);
            ResultSet rs = st.executeQuery();
            BrandDAO brandDAO = new BrandDAO();

            ProductCategories product = null;
            while (rs.next()) {
                product = new ProductCategories(rs.getInt("ID"),
                        brandDAO.getBrandById(rs.getInt("BrandID")),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getInt("Quantity"),
                        rs.getString("Description"),
                        rs.getDouble("Discount"),
                        rs.getDate("DiscountFrom"),
                        rs.getDate("DiscountTo"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(product);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }

    public List<ProductCard> getProductCard() {
        List<ProductCard> list = new ArrayList<>();
        String sql = "select * from productcard\n;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ProductCategoriesDAO pcdao = new ProductCategoriesDAO();
            while (rs.next()) {
                list.add(new ProductCard(rs.getInt("ID"), pcdao.getProductCategoriesById(rs.getInt("ProductCategoriesID")),
                        rs.getString("Seri"), rs.getString("Code"), rs.getDate("CreatedAt"),
                        rs.getDate("ExpiredDate"), rs.getDate("UpdatedAt"), rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"), rs.getInt("DeletedBy")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ProductCard> getProductCardDelete() {
        List<ProductCard> list = new ArrayList<>();
        String sql = "select * from productcard where IsDelete = 1;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ProductCategoriesDAO pcdao = new ProductCategoriesDAO();
            while (rs.next()) {
                list.add(new ProductCard(rs.getInt("ID"), pcdao.getProductCategoriesById(rs.getInt("ProductCategoriesID")),
                        rs.getString("Seri"), rs.getString("Code"), rs.getDate("CreatedAt"),
                        rs.getDate("ExpiredDate"), rs.getDate("UpdatedAt"), rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"), rs.getInt("DeletedBy")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<User> getUser() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT `user`.`ID`,\n"
                + "    `user`.`FirstName`,\n"
                + "    `user`.`LastName`,\n"
                + "    `user`.`Email`,\n"
                + "    `user`.`Phone`,\n"
                + "    `user`.`UserBalance`,\n"
                + "    `user`.`RoleID`,\n"
                + "    `user`.`CreatedAt`,\n"
                + "    `user`.`UpdatedAt`,\n"
                + "    `user`.`DeletedAt`,\n"
                + "    `user`.`IsDelete`,\n"
                + "    `user`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`user`;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            RoleDAO r = new RoleDAO();
            while (rs.next()) {
                list.add(new User(rs.getInt("ID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getDouble("UserBalance"),
                        r.getUserRoleById(rs.getInt("RoleID")),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy")));

            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Comment> getListComments() {
        List<Comment> data = new ArrayList<>();
        String sql = "SELECT `comment`.`ID`,\n"
                + "    `comment`.`UserID`,\n"
                + "    `comment`.`ProductCategoriesID`,\n"
                + "    `comment`.`Title`,\n"
                + "    `comment`.`Message`,\n"
                + "    `comment`.`CreatedAt`,\n"
                + "    `comment`.`UpdatedAt`,\n"
                + "    `comment`.`DeletedAt`,\n"
                + "    `comment`.`IsDelete`,\n"
                + "    `comment`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`comment`;\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            UserDAO userDAO = new UserDAO();
            ProductCategoriesDAO pdcateDAO = new ProductCategoriesDAO();

            while (rs.next()) {
                Comment cmt = new Comment(rs.getInt("ID"),
                        userDAO.getUserById(rs.getInt("UserID")),
                        pdcateDAO.getProductCateByID(rs.getString("ProductCategoriesID")),
                        rs.getString("Title"),
                        rs.getString("Message"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                data.add(cmt);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public List<Report> getListReport() {
        List<Report> list = new ArrayList<>();
        ProductCartDAO pc = new ProductCartDAO();
        String sql = "select * from ReportProductCard;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Report(rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getInt("OrderDetailID"),
                        pc.getProductCartByID(rs.getInt("ProductCardID")),
                        rs.getString("ProductCategoriesName"),
                        rs.getString("BrandName"),
                        rs.getString("Status"),
                        rs.getDate("CreatedAt")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public double getRevenueMonthly(int year, int month) {
        double revenue = 0;
        try {
            String sql = "SELECT SUM(TotalAmount) FROM orders WHERE YEAR(createdat) = ? AND MONTH(createdat) = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                revenue = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenue;
    }

    public double getRevenueAnnually(int year) {
        try {
            String sql = "select sum(TotalAmount) from orders WHERE YEAR(createdat) = ? and status = 'Paid';";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getUserMonthly(int month, int year) {
        try {
            String sql = "select count(id) from `user`\n"
                    + "WHERE MONTH(createdat) = ? and YEAR(createdat) = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getUserAnnually(int year) {
        try {
            String sql = "select count(id) from `user`\n"
                    + "WHERE YEAR(createdat) = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        StatisticDAO s = new StatisticDAO();
        List<User> l = s.getUser();
        int count = l.size();
        System.out.println(count);
    }
}
