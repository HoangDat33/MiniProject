
package Controller.ManageCommentNews;

import Controller.authentication.BaseRequireAuthentication;
import DAL.CommentNewsDAO;
import Model.AccountLogin;
import Model.CommentNews;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageCommentNews extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageCommentNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageCommentNews at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        if(account == null || account.getUser().getRole().getID() != 1){
            response.sendRedirect("login.jsp");
        }else{
            CommentNewsDAO cmDao = new CommentNewsDAO();
            List<CommentNews> dataCMNews = cmDao.getAllCommentNews();
            request.setAttribute("dataCMNews", dataCMNews);
            request.getRequestDispatcher("managecommentnew.jsp").forward(request, response);
        }
    }

}
