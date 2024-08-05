
package Controller.ManageNews;

import Controller.authentication.BaseRequireAuthentication;
import DAL.CategoriesNewsDAO;
import DAL.NewsDAO;
import Model.AccountLogin;
import Model.CategoriesNews;
import Model.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchNews extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchNews at " + request.getContextPath () + "</h1>");
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
        String keyword = request.getParameter("keyword");
        if(account == null || account.getUser().getRole().getID() != 1){
            response.sendRedirect("login.jsp"); 
        }else{
            NewsDAO newsDao = new NewsDAO();
            CategoriesNewsDAO cateDao = new CategoriesNewsDAO();
            List<News> dataNews = newsDao.searchListNew(keyword); 
            List<CategoriesNews> dataCate = cateDao.getListCategoriesNews();
            request.setAttribute("dataCate", dataCate);
            request.setAttribute("dataNews", dataNews);
            request.setAttribute("key", keyword);
            request.getRequestDispatcher("managenews.jsp").forward(request, response);
        }
    }

}
