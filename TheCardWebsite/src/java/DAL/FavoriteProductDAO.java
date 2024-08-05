/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Controller.favoriteproduct.FavoriteProductSvl;
import static DAL.DBContext.connection;
import Model.FavoriteProduct;
import Model.*;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dat
 */
public class FavoriteProductDAO extends DBContext {

    public void insertNewFavorite(int userId, String productCateID) {
        try {
            String sql = "INSERT INTO `dbprojectswp391_v1`.`favoriteproduct` (UserID, ProductCategoriesID, CreatedAt, IsDelete) VALUES (?, ?, NOW(), 0)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setString(2, productCateID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        FavoriteProductDAO d = new FavoriteProductDAO();
        d.removeFP("2");
    }

    public List<FavoriteProduct> getListFPByUId(int id) {
        List<FavoriteProduct> favoriteProduct = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbprojectswp391_v1.favoriteproduct where UserID = ? and IsDelete = 0;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserDAO udao = new UserDAO();
                User user = udao.getUserById(rs.getInt("UserID"));
                ProductCategoriesDAO pcdao = new ProductCategoriesDAO();
                ProductCategories pcate = pcdao.getProductCategoriesByID(rs.getInt("ProductCategoriesID"));
                FavoriteProduct fd = new FavoriteProduct(rs.getInt("ID"), user, pcate,
                        rs.getDate("CreatedAt"), rs.getDate("UpdatedAt"), rs.getDate("DeletedAt"), rs.getBoolean("IsDelete"), rs.getInt("DeletedBy"));
                favoriteProduct.add(fd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterMemberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return favoriteProduct;
    }

    public void removeFP(String fpid) {
        try {
            String sql = "UPDATE `dbprojectswp391_v1`.`favoriteproduct`\n"
                    + "SET\n"
                    + "`UpdatedAt` = NOW(),\n"
                    + "`DeletedAt` = NOW(),\n"
                    + "`IsDelete` = 1\n"
                    + "WHERE `ID` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, fpid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriteProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
