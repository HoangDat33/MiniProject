/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.comment;

import Controller.authentication.BaseRequireAuthentication;
import DAL.CommentDAO;
import DAL.ProductCategoriesDAO;
import Model.AccountLogin;
import Model.Comment;
import Model.GoogleLogin;
import Model.ProductCategories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class CommentServlet extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountLogin accountLogin = (AccountLogin) session.getAttribute("account");
        GoogleLogin googleLogin = (GoogleLogin) session.getAttribute("gguser");
        if (accountLogin == null && googleLogin == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        int userId = 0;
        if(accountLogin != null){
            userId = accountLogin.getUser().getID();
        }else if(googleLogin != null){
            userId = googleLogin.getUser().getID();
        }
        
        String pid = request.getParameter("pid");
        int PCateID = Integer.parseInt(pid);
        ProductCategoriesDAO PCateDAO = new ProductCategoriesDAO();
        ProductCategories pc = PCateDAO.getProductCateByID(pid);
        String productCateName = pc.getName();
        String brandName = pc.getBrand().getName();
        
        
        String title = "";
        if(accountLogin != null){
            title = accountLogin.getUser().getFirstName() + " " + accountLogin.getUser().getLastName() + " comment!";
        }else if(googleLogin != null){
            if(googleLogin.getUser().getFirstName() != null && googleLogin.getUser().getLastName() != null){
                title = googleLogin.getUser().getFirstName() + " " + googleLogin.getUser().getLastName() + " comment!";
            }else if(googleLogin.getUser().getFirstName() != null && googleLogin.getUser().getLastName() == null){
                title = googleLogin.getUser().getFirstName() + " comment!";
            }else if(googleLogin.getUser().getFirstName() == null && googleLogin.getUser().getLastName() != null){
                title = googleLogin.getUser().getLastName() + " comment!";
            }
        }
        
        String msg = request.getParameter("comment");

        try {
            CommentDAO cmtDAO = new CommentDAO();
            cmtDAO.insertComment(PCateID, userId, title, msg,productCateName, brandName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("productdetail?id=" + pid);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
