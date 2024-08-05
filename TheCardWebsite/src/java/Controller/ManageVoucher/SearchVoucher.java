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
import java.util.List;

public class SearchVoucher extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchVoucher at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        if (account == null || account.getUser().getRole().getID() != 1) {
            response.sendRedirect("login.jsp");
        } else {
            VoucherDAO voucherDao = new VoucherDAO();
            String keyword = request.getParameter("keyword");
            List<Voucher> getList = voucherDao.searchVoucher(keyword);
            request.setAttribute("key", keyword);
            request.setAttribute("dataVoucher", getList);
            request.getRequestDispatcher("managevoucher.jsp").forward(request, response);
        }
    }

}
