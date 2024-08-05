/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.favoriteproduct;

import Controller.authentication.BaseRequireAuthentication;
import DAL.FavoriteProductDAO;
import DAL.UserDAO;
import Model.AccountLogin;
import Model.User;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dat
 */
@WebServlet(name="FavoriteProduct", urlPatterns={"/favoriteproduct"})
public class FavoriteProductSvl extends BaseRequireAuthentication {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();
        
        String producCateID = request.getParameter("id");
        if(account == null ){
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Bạn chưa đăng nhập, vui lòng đăng nhập để tiếp tục!");
        }else{
            FavoriteProductDAO favoriteProductDAO = new FavoriteProductDAO();
            favoriteProductDAO.insertNewFavorite(account.getUser().getID(),producCateID);
            jsonResult.addProperty("valid", true);
            jsonResult.addProperty("message", "Thêm sản phẩm yêu thích thành công!");
        }
        System.out.println("JSON Result: " + jsonResult.toString());

        // Gửi JSON đến client
        out.print(jsonResult.toString());
        out.flush();
        
    }

}
