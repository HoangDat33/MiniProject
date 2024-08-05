
package Controller.ManageNewsCategory;

import DAL.CategoriesNewsDAO;
import DAL.NewsDAO;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestoreCategoriesNews extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RestoreCategoriesNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RestoreCategoriesNews at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idCate = request.getParameter("id");
        int id = 0;
        if (idCate != null) {
            id = Integer.parseInt(idCate);
        }
        CategoriesNewsDAO u = new CategoriesNewsDAO();
        NewsDAO n = new NewsDAO();
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        if (u.getCategoriesNewsByID(id) == null) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Không tìm thấy thể loại tin tức này!");
        }else{
            u.restoreNewCategoriesNews(id);
            n.restoreNewsWhenCateRestore(id);
            jsonResult.addProperty("valid", true);
            jsonResult.addProperty("message", "Khôi phục trạng thái hoạt động của thể loại tin tức thành công!");
        }
        

        // Log kết quả JSON
        System.out.println("JSON Result: " + jsonResult.toString());

        // Gửi JSON đến client
        out.print(jsonResult.toString());
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
