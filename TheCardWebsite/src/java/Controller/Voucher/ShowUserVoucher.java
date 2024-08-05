package Controller.Voucher;

import Controller.authentication.BaseRequireAuthentication;
import DAL.GetVoucherDAO;
import Model.AccountLogin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUserVoucher extends BaseRequireAuthentication {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowUserVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowUserVoucher at " + request.getContextPath() + "</h1>");
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
        GetVoucherDAO getVoucher = new GetVoucherDAO();
        if (account == null) {
            response.sendRedirect("login.jsp");
        }
        
        List<Model.GetVoucher> get = getVoucher.getListVoucherByUserId(account.getUser().getID());
        if (get.size() != 0 && !get.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                for (Model.GetVoucher g : get) {
                    out.print("<div class=\"col-md-12 voucher-section\">\n"
                            + "                                    <div class=\"voucher-card d-flex align-items-center mb-4\">\n"
                            + "                                        <div class=\"voucher-image mr-5\">\n"
                            + "                                            <img src=\"images/logo/" + g.getVoucher().getImage() + "\" alt=\"Voucher\" style=\"width: 200px;\">\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"voucher-info\">\n"
                            + "                                            <div class=\"voucher-header\">\n"
                            + "                                                <h4 class=\"mb-0\">" + g.getVoucher().getTitle() + "</h4>\n"
                            + "                                            </div>\n"
                            + "                                            <div class=\"voucher-discount\">Giảm " + g.getVoucher().getDiscount() + " %</div>\n"
                            + "                                            <div class=\"voucher-condition\">Giảm tối đa " + g.getVoucher().getDiscountMax() + " VNĐ | Đơn Tối Thiểu " + g.getVoucher().getPricefrom() + " VNĐ | Số lượng: " + g.getVoucher().getQuantity() + "</div>\n"
                            + "                                            <!--<button onclick=\"saveVoucher(" + g.getVoucher().getId() + ")\" class=\"voucher-button mt-2\">Lưu</button>-->\n"
                            + "                                            <a href=\"#\" class=\"voucher-link d-block mt-1\" onclick=\"getRule(" + g.getVoucher().getId() + ")\">Điều kiện</a>\n"
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                </div>");
                    
                }
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.print("<div class=\"col-md-12\">\n"
                        + "                                    <div class=\"voucher-header\">\n"
                        + "                                        <h4 class=\"mb-0\">Hiện tại bạn chưa có voucher giảm giá nào!</h4>\n"
                        + "                                    </div>\n"
                        + "                                </div>");
            }
        }
    }
    
}
