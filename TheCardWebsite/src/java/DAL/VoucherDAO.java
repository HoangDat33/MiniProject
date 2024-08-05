package DAL;

import static DAL.DBContext.connection;
import Model.Voucher;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoucherDAO extends DBContext {

    public List<Voucher> getListVoucher() {
        List<Voucher> dataVoucher = new ArrayList<>();
        String sql = "SELECT `voucher`.`ID`,\n"
                + "    `voucher`.`Title`,\n"
                + "    `voucher`.`PurchaseOffer`,\n"
                + "    `voucher`.`ApplyDescription`,\n"
                + "    `voucher`.`Image`,\n"
                + "    `voucher`.`ApplyBrandID`,\n"
                + "    `voucher`.`ApplyProductID`,\n"
                + "    `voucher`.`FromDate`,\n"
                + "    `voucher`.`ToDate`,\n"
                + "    `voucher`.`PriceFrom`,\n"
                + "    `voucher`.`Discount`,\n"
                + "    `voucher`.`DiscountMax`,\n"
                + "    `voucher`.`Quantity`,\n"
                + "    `voucher`.`CreatedAt`,\n"
                + "    `voucher`.`UpdatedAt`,\n"
                + "    `voucher`.`DeletedAt`,\n"
                + "    `voucher`.`IsDelete`,\n"
                + "    `voucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`voucher`"
                + "WHERE `voucher`.`IsDelete` = 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("PurchaseOffer"),
                        rs.getString("ApplyDescription"),
                        rs.getString("Image"),
                        rs.getInt("ApplyBrandID"),
                        rs.getInt("ApplyProductID"),
                        rs.getDate("FromDate"),
                        rs.getDate("ToDate"),
                        rs.getDouble("PriceFrom"),
                        rs.getDouble("Discount"),
                        rs.getDouble("DiscountMax"),
                        rs.getInt("Quantity"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataVoucher.add(voucher);
            }
            return dataVoucher;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Voucher> getListVoucherAdmin() {
        List<Voucher> dataVoucher = new ArrayList<>();
        String sql = "SELECT `voucher`.`ID`,\n"
                + "    `voucher`.`Title`,\n"
                + "    `voucher`.`PurchaseOffer`,\n"
                + "    `voucher`.`ApplyDescription`,\n"
                + "    `voucher`.`Image`,\n"
                + "    `voucher`.`ApplyBrandID`,\n"
                + "    `voucher`.`ApplyProductID`,\n"
                + "    `voucher`.`FromDate`,\n"
                + "    `voucher`.`ToDate`,\n"
                + "    `voucher`.`PriceFrom`,\n"
                + "    `voucher`.`Discount`,\n"
                + "    `voucher`.`DiscountMax`,\n"
                + "    `voucher`.`Quantity`,\n"
                + "    `voucher`.`CreatedAt`,\n"
                + "    `voucher`.`UpdatedAt`,\n"
                + "    `voucher`.`DeletedAt`,\n"
                + "    `voucher`.`IsDelete`,\n"
                + "    `voucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`voucher`";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("PurchaseOffer"),
                        rs.getString("ApplyDescription"),
                        rs.getString("Image"),
                        rs.getInt("ApplyBrandID"),
                        rs.getInt("ApplyProductID"),
                        rs.getDate("FromDate"),
                        rs.getDate("ToDate"),
                        rs.getDouble("PriceFrom"),
                        rs.getDouble("Discount"),
                        rs.getDouble("DiscountMax"),
                        rs.getInt("Quantity"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataVoucher.add(voucher);
            }
            return dataVoucher;
        } catch (Exception e) {
        }
        return null;
    }

    public Voucher getVoucherByID(int voucherId) {
        String sql = "SELECT `voucher`.`ID`,\n"
                + "    `voucher`.`Title`,\n"
                + "    `voucher`.`PurchaseOffer`,\n"
                + "    `voucher`.`ApplyDescription`,\n"
                + "    `voucher`.`Image`,\n"
                + "    `voucher`.`ApplyBrandID`,\n"
                + "    `voucher`.`ApplyProductID`,\n"
                + "    `voucher`.`FromDate`,\n"
                + "    `voucher`.`ToDate`,\n"
                + "    `voucher`.`PriceFrom`,\n"
                + "    `voucher`.`Discount`,\n"
                + "    `voucher`.`DiscountMax`,\n"
                + "    `voucher`.`Quantity`,\n"
                + "    `voucher`.`CreatedAt`,\n"
                + "    `voucher`.`UpdatedAt`,\n"
                + "    `voucher`.`DeletedAt`,\n"
                + "    `voucher`.`IsDelete`,\n"
                + "    `voucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`voucher`"
                + "WHERE `voucher`.`IsDelete` = 0 AND `voucher`.`ID` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, voucherId);
            ResultSet rs = st.executeQuery();
            Voucher voucher = new Voucher();
            while (rs.next()) {
                voucher = new Voucher(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("PurchaseOffer"),
                        rs.getString("ApplyDescription"),
                        rs.getString("Image"),
                        rs.getInt("ApplyBrandID"),
                        rs.getInt("ApplyProductID"),
                        rs.getDate("FromDate"),
                        rs.getDate("ToDate"),
                        rs.getDouble("PriceFrom"),
                        rs.getDouble("Discount"),
                        rs.getDouble("DiscountMax"),
                        rs.getInt("Quantity"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
            }
            return voucher;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean checkExistVoucher(String title) {
        String sql = "SELECT `voucher`.`ID`,\n"
                + "    `voucher`.`Title`,\n"
                + "    `voucher`.`PurchaseOffer`,\n"
                + "    `voucher`.`ApplyDescription`,\n"
                + "    `voucher`.`Image`,\n"
                + "    `voucher`.`ApplyBrandID`,\n"
                + "    `voucher`.`ApplyProductID`,\n"
                + "    `voucher`.`FromDate`,\n"
                + "    `voucher`.`ToDate`,\n"
                + "    `voucher`.`PriceFrom`,\n"
                + "    `voucher`.`Discount`,\n"
                + "    `voucher`.`DiscountMax`,\n"
                + "    `voucher`.`Quantity`,\n"
                + "    `voucher`.`CreatedAt`,\n"
                + "    `voucher`.`UpdatedAt`,\n"
                + "    `voucher`.`DeletedAt`,\n"
                + "    `voucher`.`IsDelete`,\n"
                + "    `voucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`voucher`"
                + "WHERE `voucher`.`Title` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean checkExistVoucherUpdate(String title, int id) {
        String sql = "SELECT `ID` "
                + "FROM `dbprojectswp391_v1`.`voucher` "
                + "WHERE `Title` = ? AND `ID` != ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, title);
            st.setInt(2, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public void updateQuantityVoucher(int voucherId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`voucher` "
                + "SET `Quantity` = `Quantity` - 1 "
                + "WHERE `ID` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, voucherId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean addVoucher2(Voucher vou) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`voucher` "
                + "(`Title`, `PurchaseOffer`, `ApplyDescription`, `Image`, `ApplyBrandID`, `ApplyProductID`, `FromDate`, `ToDate`, `PriceFrom`, `Discount`, `DiscountMax`, `Quantity`, `CreatedAt`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, vou.getTitle());
            pstmt.setString(2, vou.getPurchaseOffer());
            pstmt.setString(3, vou.getApplyDescription());
            pstmt.setString(4, vou.getImage());
            pstmt.setInt(5, vou.getApplyBrandID());
            pstmt.setInt(6, vou.getApplyProductID());
            pstmt.setDate(7, new java.sql.Date(vou.getFromDate().getTime()));
            pstmt.setDate(8, new java.sql.Date(vou.getToDate().getTime()));
            pstmt.setDouble(9, vou.getPricefrom());
            pstmt.setDouble(10, vou.getDiscount());
            pstmt.setDouble(11, vou.getDiscountMax());
            pstmt.setInt(12, vou.getQuantity());

            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows);
            return affectedRows == 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addVoucher3(String title, String purchase, String applydesc, String image, int brandid, int productid, String FromDate, String Todate, double pricef, double discount, double discountmax, int quantity) {
        String sql = "INSERT INTO `dbprojectswp391_v1`.`voucher` "
                + "(`Title`, `PurchaseOffer`, `ApplyDescription`, `Image`, `ApplyBrandID`, `ApplyProductID`, `FromDate`, `ToDate`, `PriceFrom`, `Discount`, `DiscountMax`, `Quantity`, `CreatedAt`) "
                + "VALUES (?, ?, ?, ?, ?, ?, COALESCE(?, FromDate), COALESCE(?, ToDate), ?, ?, ?, ?, NOW())";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, purchase);
            pstmt.setString(3, applydesc);
            pstmt.setString(4, image);
            pstmt.setInt(5, brandid);
            pstmt.setInt(6, productid);
            pstmt.setString(7, FromDate);
            pstmt.setString(8, Todate);
            pstmt.setDouble(9, pricef);
            pstmt.setDouble(10, discount);
            pstmt.setDouble(11, discountmax);
            pstmt.setInt(12, quantity);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVoucher(int id, String title, String purchase, String applydesc, String image, int brandid, int productid, String FromDate, String Todate, double pricef, double discount, double discountmax, int quantity) {
        String sql = "UPDATE `dbprojectswp391_v1`.`voucher`\n"
                + "SET\n"
                + "`Title` = ?,\n"
                + "`PurchaseOffer` = ?,\n"
                + "`ApplyDescription` = ?,\n"
                + "`Image` = ?,\n"
                + "`ApplyBrandID` = ?,\n"
                + "`ApplyProductID` = ?,\n"
                + "`FromDate` = COALESCE(?, FromDate),\n"
                + "`ToDate` = COALESCE(?, ToDate),\n"
                + "`PriceFrom` = ?,\n"
                + "`Discount` = ?,\n"
                + "`DiscountMax` = ?,\n"
                + "`Quantity` = ?,\n"
                + "`UpdatedAt` = NOW()\n"
                + "WHERE `ID` = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, purchase);
            pstmt.setString(3, applydesc);
            pstmt.setString(4, image);
            pstmt.setInt(5, brandid);
            pstmt.setInt(6, productid);
            pstmt.setString(7, FromDate);
            pstmt.setString(8, Todate);
            pstmt.setDouble(9, pricef);
            pstmt.setDouble(10, discount);
            pstmt.setDouble(11, discountmax);
            pstmt.setInt(12, quantity);
            pstmt.setInt(13, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVoucher(int id, int adminId) {
        String sql = "UPDATE `dbprojectswp391_v1`.`voucher` "
                + "SET "
                + "`UpdatedAt` = NOW(), "
                + "`DeletedAt` = NOW(), "
                + "`IsDelete` = 1, "
                + "`DeletedBy` = ? "
                + "WHERE `ID` = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Thiết lập các tham số
            pstmt.setInt(1, adminId);
            pstmt.setInt(2, id);

            // Thực thi câu lệnh
            int affectedRows = pstmt.executeUpdate();

            // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng
            return affectedRows > 0;
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean restoreVoucher(int id) {
        String sql = "UPDATE `dbprojectswp391_v1`.`voucher` "
                + "SET "
                + "`UpdatedAt` = NOW(), "
                + "`DeletedAt` = NULL, "
                + "`IsDelete` = 0, "
                + "`DeletedBy` = 0 "
                + "WHERE `ID` = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // Thiết lập tham số cho câu lệnh SQL
            pstmt.setInt(1, id);

            // Thực thi câu lệnh và lấy số lượng dòng bị ảnh hưởng
            int affectedRows = pstmt.executeUpdate();

            // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng
            return affectedRows > 0;
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Voucher> searchVoucher(String keyword) {
        List<Voucher> dataVoucher = new ArrayList<>();
        String sql = "SELECT `voucher`.`ID`,\n"
                + "    `voucher`.`Title`,\n"
                + "    `voucher`.`PurchaseOffer`,\n"
                + "    `voucher`.`ApplyDescription`,\n"
                + "    `voucher`.`Image`,\n"
                + "    `voucher`.`ApplyBrandID`,\n"
                + "    `voucher`.`ApplyProductID`,\n"
                + "    `voucher`.`FromDate`,\n"
                + "    `voucher`.`ToDate`,\n"
                + "    `voucher`.`PriceFrom`,\n"
                + "    `voucher`.`Discount`,\n"
                + "    `voucher`.`DiscountMax`,\n"
                + "    `voucher`.`Quantity`,\n"
                + "    `voucher`.`CreatedAt`,\n"
                + "    `voucher`.`UpdatedAt`,\n"
                + "    `voucher`.`DeletedAt`,\n"
                + "    `voucher`.`IsDelete`,\n"
                + "    `voucher`.`DeletedBy`\n"
                + "FROM `dbprojectswp391_v1`.`voucher`"
                + "WHERE `voucher`.`Title` LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher(rs.getInt("ID"),
                        rs.getString("Title"),
                        rs.getString("PurchaseOffer"),
                        rs.getString("ApplyDescription"),
                        rs.getString("Image"),
                        rs.getInt("ApplyBrandID"),
                        rs.getInt("ApplyProductID"),
                        rs.getDate("FromDate"),
                        rs.getDate("ToDate"),
                        rs.getDouble("PriceFrom"),
                        rs.getDouble("Discount"),
                        rs.getDouble("DiscountMax"),
                        rs.getInt("Quantity"),
                        rs.getDate("CreatedAt"),
                        rs.getDate("UpdatedAt"),
                        rs.getDate("DeletedAt"),
                        rs.getBoolean("IsDelete"),
                        rs.getInt("DeletedBy"));
                dataVoucher.add(voucher);
            }
            return dataVoucher;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        VoucherDAO u = new VoucherDAO();
        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
        Voucher v = new Voucher("A", "B", "C", "viettel_logo.jpg", 1, 1, sqlDate, sqlDate, 1000, 10, 10, 10);
        //Voucher v1 = new Voucher();
        System.out.println(u.addVoucher2(v));
    }
}
