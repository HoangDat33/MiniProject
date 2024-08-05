package Controller.ManageNews;

import Controller.authentication.BaseRequireAuthentication;
import DAL.CategoriesNewsDAO;
import DAL.NewsDAO;
import DAL.RegisterNotificationDAO;

import Model.AccountLogin;
import Model.News;
import Model.RegisterNotification;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class AddNewNews extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewNews at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String desc = request.getParameter("desc");
        String firstContent = request.getParameter("firstContent");
        String bodyContent = request.getParameter("bodyContent");
        String endContent = request.getParameter("endContent");
        String categoriesSelect = request.getParameter("categoriesSelect");
        String image = request.getParameter("image");
        String imagedesc = request.getParameter("imagedesc");
        String hotnews = request.getParameter("hotnews");
        int categoriesId = 0;
        if (categoriesSelect != null) {
            categoriesId = Integer.parseInt(categoriesSelect);
        }
        int hotN = 0;
        if (hotnews != null) {
            hotN = Integer.parseInt(hotnews);
        }
        int idNews = 0;
        if (id != null) {
            idNews = Integer.parseInt(id);
        }

        boolean hot = (hotN == 1);

        NewsDAO newDao = new NewsDAO();
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        try {
            if (title == null || title.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Tiêu đề không được để trống!");
            } else if (desc == null || desc.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Mô tả không được để trống!");
            } else if (firstContent == null || firstContent.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Nội dung đầu không được để trống!");
            } else if (bodyContent == null || bodyContent.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Nội dung giữa không được để trống!");
            } else if (endContent == null || endContent.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Nội dung cuối không được để trống!");
            } else if (categoriesSelect == null || categoriesSelect.trim().isEmpty() || categoriesId == 0) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Lựa chọn thể loại không được để trống!");
            } else if (image == null || image.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Hình ảnh không được để trống!");
            } else if (imagedesc == null || imagedesc.trim().isEmpty()) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Mô tả hình ảnh không được để trống!");
            } else if (!newDao.checkExistNewsUpdate(title, idNews)) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Tiêu đề đã tồn tại!");
            } else {
                News news = new News(title, desc, firstContent, bodyContent, endContent, categoriesId, hot, image, imagedesc);
                if (newDao.addNews(news)) {
                    RegisterNotificationDAO rgDao = new RegisterNotificationDAO();
                    List<RegisterNotification> dataUser = rgDao.getSendRegisterNotification();
                    if (!dataUser.isEmpty()) {
                        for (RegisterNotification r : dataUser) {
                            RequestQueue.addRequest(r);
                        }
                    }
                    jsonResult.addProperty("valid", true);
                    jsonResult.addProperty("message", "Thêm mới tin tức thành công!");
                } else {
                    jsonResult.addProperty("valid", false);
                    jsonResult.addProperty("message", "Thêm mới tin tức thất bại!");
                }
            }

            // Log kết quả JSON
            System.out.println("JSON Result: " + jsonResult.toString());

            // Gửi JSON đến client
            out.print(jsonResult.toString());
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Đã xảy ra lỗi khi xử lý yêu cầu.");
            out.print(jsonResult.toString());
            out.flush();
        } finally {
            out.close(); // Đảm bảo rằng PrintWriter được đóng đúng cách
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
