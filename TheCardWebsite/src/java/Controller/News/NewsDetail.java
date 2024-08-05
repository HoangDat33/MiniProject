
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

public class NewsDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewsDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsDetail at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idNews = request.getParameter("Id");
        int id = 0;
        if(idNews != null){
            id = Integer.parseInt(idNews);
        }
        NewsDAO newDao = new NewsDAO();
        News news = newDao.getNewsById(id);
        List<News> newsData = newDao.getListNewRelatedByID(news.getNews().getID(), id);
        if(news != null){
            request.setAttribute("news", news);
            request.setAttribute("newsData", newsData);
            request.getRequestDispatcher("newsdetail.jsp").forward(request, response);
        }else{
            response.sendRedirect("404_error.jsp");
        }
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
