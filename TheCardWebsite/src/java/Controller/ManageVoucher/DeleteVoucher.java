package Controller.ManageVoucher;

import Controller.authentication.BaseRequireAuthentication;
import DAL.VoucherDAO;
import Model.AccountLogin;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteVoucher extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteVoucher at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        String idV = request.getParameter("id");
        int id = Integer.parseInt(idV);
        if (account == null || account.getUser().getRole().getID() != 1) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Vui lòng đăng nhập để thực hiện thao tác!");
        } else {
            VoucherDAO voucherDao = new VoucherDAO();
            if (voucherDao.deleteVoucher(id, account.getUser().getID())) {
                jsonResult.addProperty("valid", true);
                jsonResult.addProperty("message", "Thực hiện xóa voucher thành công!");
            }else{
                jsonResult.addProperty("valid", true);
                jsonResult.addProperty("message", "Thực hiện xóa voucher thất bại!");
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
