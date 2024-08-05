
package Controller.ManageNews;

import Controller.authentication.BaseRequireAuthentication;
import DAL.CategoriesNewsDAO;
import DAL.NewsDAO;
import Model.AccountLogin;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestoreNews extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RestoreNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RestoreNews at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        String idNews = request.getParameter("id");
        int id = 0;
        if (idNews != null) {
            id = Integer.parseInt(idNews);
        }

        CategoriesNewsDAO u = new CategoriesNewsDAO();
        NewsDAO n = new NewsDAO();
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        if (n.getNewsById(id) == null) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Không tìm thấy tin tức này!");
        } else {
            if (account == null && account.getUser().getRole().getID() != 1) {
                response.sendRedirect("login.jsp");
            } else {
                n.restoreNews(id);
                jsonResult.addProperty("valid", true);
                jsonResult.addProperty("message", "Bật trạng thái hoạt động của tin tức thành công!");
            }
        }
        System.out.println("JSON Result: " + jsonResult.toString());

        // Gửi JSON đến client
        out.print(jsonResult.toString());
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
