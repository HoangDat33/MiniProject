
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

public class ManageNews extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageNews at " + request.getContextPath () + "</h1>");
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
        String id = request.getParameter("newscateId");
        NewsDAO newDao = new NewsDAO();
        CategoriesNewsDAO cateDao = new CategoriesNewsDAO();
        int idCate = 0;
        if(id != null){
            idCate = Integer.parseInt(id);
        }
        if(account == null || account.getUser().getRole().getID()!= 1){
            response.sendRedirect("login.jsp");
        }else{
            List<News> dataNews = newDao.getListNewByIDAdmin(idCate);
            List<CategoriesNews> dataCate = cateDao.getListCategoriesNews();
            if(dataNews != null && dataCate != null){
                request.setAttribute("dataNews", dataNews);
                request.setAttribute("dataCate", dataCate);
                request.getRequestDispatcher("managenews.jsp").forward(request, response);
            }else if(dataCate != null){
                request.setAttribute("dataCate", dataCate);
                request.getRequestDispatcher("managenews.jsp").forward(request, response);
            }else{
                request.setAttribute("notification", "Không có tin tức nào cho thể loại tin tức này!");
                request.getRequestDispatcher("managenews.jsp").forward(request, response);
            }
        }
        
    }

}
