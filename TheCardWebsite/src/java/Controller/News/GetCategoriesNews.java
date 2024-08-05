
package Controller.News;

import DAL.NewsDAO;
import Model.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCategoriesNews extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetCategoriesNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetCategoriesNews at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String idCate = request.getParameter("id");
        int id = 0; 
        if(idCate != null){
            id = Integer.parseInt(idCate);
        }
        NewsDAO newsDao = new NewsDAO();
        List<News> dataNews = newsDao.getListNewByID(id);
        if(dataNews != null){
            request.setAttribute("dataNews", dataNews);
            request.setAttribute("id", id);
        }else{
            request.setAttribute("notification", "Hiện tại không có tin tức cho thể loại này!");
        }
        request.getRequestDispatcher("news.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
