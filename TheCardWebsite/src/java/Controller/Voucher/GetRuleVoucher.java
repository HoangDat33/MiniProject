package Controller.Voucher;

import DAL.UserDAO;
import DAL.VoucherDAO;
import Model.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetRuleVoucher extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetRuleVoucher</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetRuleVoucher at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idVoucher = request.getParameter("idVoucher");
        VoucherDAO voucherDao = new VoucherDAO();
        int voucherId = 0;
        if (idVoucher != null) {
            voucherId = Integer.parseInt(idVoucher);
        }
        Voucher voucher = voucherDao.getVoucherByID(voucherId);
        UserDAO userDao = new UserDAO();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("<div class=\"header\">\n"
                    + "                            <div class=\"logo\">The Card Shop</div>\n"
                    + "                            <h2 style=\"color: white;\">Giảm " + voucher.getDiscount() + "% Giảm tối đa " + userDao.formatMoney(voucher.getDiscountMax()) + " VNĐ</h2>\n"
                    + "                            <p style=\"color: white;\">Đơn Tối Thiểu " + userDao.formatMoney(voucher.getPricefrom())  + " VNĐ</p>\n"
                    + "                        </div>\n"
                    + "                        <h4>Hạn sử dụng mã</h4>\n"
                    + "                        <p>" + voucher.getFromDate() + " - " +  voucher.getToDate() + "</p>\n"
                    + "                        <h4>Ưu đãi</h3>\n"
                    + "                            <p>Lượt sử dụng có hạn. Nhanh tay kẻo lỡ bạn nhé! Giảm " + voucher.getDiscount() + "% Đơn Tối Thiểu " + userDao.formatMoney(voucher.getPricefrom()) + " VNĐ Giảm tối đa " + userDao.formatMoney(voucher.getDiscountMax()) + " VNĐ</p>\n"
                    + "                            <h4>Áp dụng cho sản phẩm</h4>\n"
                    + "                            <p>Mã áp dụng trên App cho các sản phẩm tham gia chương trình và người dùng nhất định.</p>\n"
                    + "                            <ul>\n"
                    + "                                <li> "+ voucher.getPurchaseOffer() +"</li>\n"
                    + "                            </ul>\n"
                    + "                            <h4>Thanh Toán</h4>\n"
                    + "                            <ul>\n"
                    + "                                <li>- Thanh toán trực tiếp bằng số dư</li>\n"
                    + "                                <li>- Tài khoản ngân hàng đã liên kết</li>\n"
                    + "                                <li>- Thẻ Tín dụng/Ghi nợ</li>\n"
                    + "                            </ul>\n"
                    + "                            <button class=\"button btn-block\" onclick=\"hideRule()\" id=\"agreeButton\">Đồng ý</button>");
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
