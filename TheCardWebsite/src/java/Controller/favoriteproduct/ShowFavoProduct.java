/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.favoriteproduct;

import Controller.authentication.BaseRequireAuthentication;
import DAL.FavoriteProductDAO;
import Model.AccountLogin;
import Model.FavoriteProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Dat
 */
@WebServlet(name = "ShowFavoProduct", urlPatterns = {"/showfavoproduct"})
public class ShowFavoProduct extends BaseRequireAuthentication {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String fpid = req.getParameter("pcid");
        FavoriteProductDAO fddao = new FavoriteProductDAO();
        fddao.removeFP(fpid);
        out.write("{\"message\": \"Đã xóa sản phẩm khỏi danh sách yêu thích!\"}");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        FavoriteProductDAO fddao = new FavoriteProductDAO();
        List<FavoriteProduct> fps = fddao.getListFPByUId(account.getUser().getID());
        req.setAttribute("fps", fps);
        req.getRequestDispatcher("showfavoproduct.jsp").forward(req, resp);
    }

}
