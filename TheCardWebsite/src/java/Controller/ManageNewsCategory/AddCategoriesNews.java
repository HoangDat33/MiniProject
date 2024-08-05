package Controller.ManageNewsCategory;

import Controller.authentication.BaseRequireAuthentication;
import DAL.CategoriesNewsDAO;
import Model.AccountLogin;
import Model.CategoriesNews;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCategoriesNews extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCategoriesNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCategoriesNews at " + request.getContextPath() + "</h1>");
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
        String title = request.getParameter("title");
        CategoriesNewsDAO u = new CategoriesNewsDAO();

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        if (title.trim().isEmpty()) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Không được để trống Tiêu đề!");
        } else {
            if (u.checkExistTitle(title.trim())) {
                u.addNewCategoriesNews(title.trim());
                jsonResult.addProperty("valid", true);
                jsonResult.addProperty("message", "Thêm mới thể loại tin tức thành công!");
            }else{
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Tiêu đề đã tồn tại!");
            }

        }

        // Log kết quả JSON
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
