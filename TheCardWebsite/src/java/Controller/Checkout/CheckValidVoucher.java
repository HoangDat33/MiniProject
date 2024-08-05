package Controller.Checkout;

import Controller.authentication.BaseRequireAuthentication;
import DAL.BrandDAO;
import DAL.GetVoucherDAO;
import DAL.OrderDAO;
import DAL.VoucherDAO;
import Model.AccountLogin;
import Model.GetVoucher;
import Model.OrderDetails;
import Model.Voucher;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckValidVoucher extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckValidVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckValidVoucher at " + request.getContextPath() + "</h1>");
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
        // Thiết lập loại nội dung là JSON và mã hóa UTF-8
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        OrderDAO u = new OrderDAO();
        BrandDAO brandDao = new BrandDAO();
        GetVoucherDAO v = new GetVoucherDAO();
        String orderIdString = request.getParameter("orderId");
        int orderId = 0;
        if (orderIdString != null) {
            orderId = Integer.parseInt(orderIdString);
        }
        try {
            // Kiểm tra xác thực
            //AccountLogin account = (AccountLogin) request.getSession().getAttribute("account");
            if (account == null) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Vui lòng đăng nhập để sử dụng voucher.");
                out.print(jsonResult.toString());
                return;
            }

            String voucherId = request.getParameter("voucherId");
            String money = request.getParameter("totalMoney");

            // Validate input
            if (voucherId == null || voucherId.isEmpty() || money == null || money.isEmpty()) {
                throw new IllegalArgumentException("voucherId hoặc totalMoney không hợp lệ");
            }

            int idVoucher = Integer.parseInt(voucherId);
            double totalMoney = Double.parseDouble(money);

            // Kiểm tra điều kiện voucher
            VoucherDAO voucherDAO = new VoucherDAO();
            Voucher voucher = voucherDAO.getVoucherByID(idVoucher);
            List<OrderDetails> dataOrderDetail = u.getAllOrderDetailsByOderId(orderId);
            // Kiểm tra điều kiện áp dụng của voucher
            if (totalMoney >= voucher.getPricefrom()) {
                boolean validVoucher = false;
                double eligibleAmount = 0;

                // Tính tổng số tiền của các sản phẩm đủ điều kiện
                for (OrderDetails o : dataOrderDetail) {
                    int brandID = brandDao.getIdBrandByPctID(o.getProductCategories().getId());

                    boolean isEligible = false;

                    // Kiểm tra điều kiện áp dụng của voucher
                    if (voucher.getApplyBrandID() == 0 && voucher.getApplyProductID() == 0) {
                        // Voucher áp dụng cho tất cả sản phẩm
                        isEligible = true;
                    } else if (voucher.getApplyBrandID() != 0 && voucher.getApplyProductID() != 0) {
                        // Voucher áp dụng cho cả brand và sản phẩm cụ thể
                        isEligible = (o.getProductCategories().getId() == voucher.getApplyProductID() && brandID == voucher.getApplyBrandID());
                    } else if (voucher.getApplyBrandID() != 0) {
                        // Voucher áp dụng cho brand cụ thể
                        isEligible = (brandID == voucher.getApplyBrandID());
                    } else if (voucher.getApplyProductID() != 0) {
                        // Voucher áp dụng cho sản phẩm cụ thể
                        isEligible = (o.getProductCategories().getId() == voucher.getApplyProductID());
                    }

                    if (isEligible) {
                        eligibleAmount += o.getAmount();
                        validVoucher = true;
                    }
                }

                // So sánh tổng số tiền đủ điều kiện với giá tối thiểu của voucher
                if (validVoucher && eligibleAmount >= voucher.getPricefrom()) {
                    jsonResult.addProperty("valid", true);
                    jsonResult.addProperty("message", "Voucher hợp lệ!");
                } else if (!validVoucher) {
                    jsonResult.addProperty("valid", false);
                    jsonResult.addProperty("message", "Voucher không áp dụng cho các sản phẩm trong đơn hàng này.");
                } else {
                    jsonResult.addProperty("valid", false);
                    jsonResult.addProperty("message", "Voucher này chỉ áp dụng cho đơn từ " + voucher.getPricefrom() + " VNĐ.");
                }
            } else {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Voucher này chỉ áp dụng cho đơn từ " + voucher.getPricefrom() + " VNĐ.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Đã xảy ra lỗi: " + e.getMessage());
        }

        // Log kết quả JSON
        System.out.println("JSON Result: " + jsonResult.toString());

        // Gửi JSON đến client
        out.print(jsonResult.toString());
        out.flush();
    }

}
