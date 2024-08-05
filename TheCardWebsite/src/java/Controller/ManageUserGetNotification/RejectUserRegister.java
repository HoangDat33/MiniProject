package Controller.ManageUserGetNotification;

import Controller.authentication.BaseRequireAuthentication;
import DAL.RegisterNotificationDAO;
import Model.AccountLogin;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RejectUserRegister extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RejectUserRegister</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RejectUserRegister at " + request.getContextPath () + "</h1>");
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
        int idU = Integer.parseInt(id);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();
        if (account == null || account.getUser().getRole().getID() != 1) {
            response.sendRedirect("login.jsp");
        } else {
            RegisterNotificationDAO rnDao = new RegisterNotificationDAO();
            rnDao.deleteRegister(idU, account.getUser().getID());
            jsonResult.addProperty("valid", true);
            jsonResult.addProperty("message", "Cập nhật từ chối đăng kí thông báo thành công!");
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
