package Controller.ManageVoucher;

import Controller.authentication.BaseRequireAuthentication;
import DAL.VoucherDAO;
import Model.AccountLogin;
import Model.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ManageVoucher extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageVoucher at " + request.getContextPath() + "</h1>");
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
        VoucherDAO v = new VoucherDAO();
        List<Voucher> dataVoucher = v.getListVoucherAdmin();
        if (account == null || account.getUser().getRole().getID() != 1) {
            response.sendRedirect("login.jsp");
        } else {
            if (dataVoucher.size() != 0 && !dataVoucher.isEmpty()) {
                request.setAttribute("dataVoucher", dataVoucher);
                request.getRequestDispatcher("managevoucher.jsp").forward(request, response);
            }
        }

    }

}
