package Controller.ManageVoucher;

import Controller.authentication.BaseRequireAuthentication;
import DAL.VoucherDAO;
import Model.AccountLogin;
import Model.Voucher;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class AddNewVoucher extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewVoucher</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewVoucher at " + request.getContextPath() + "</h1>");
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
        String desc = request.getParameter("desc");
        String purchase = request.getParameter("purchase");
        String image = request.getParameter("image");
        String brandapply = request.getParameter("brandapply");
        String productapply = request.getParameter("productapply");
        String fromdate = request.getParameter("fromdate");
        String todate = request.getParameter("todate");
        String minprice = request.getParameter("minprice");
        String discount = request.getParameter("discount");
        String maxdiscount = request.getParameter("maxdiscount");
        String quantity = request.getParameter("quantity");

        VoucherDAO voucherDao = new VoucherDAO();

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        JsonObject jsonResult = new JsonObject();

        boolean isValid = validateData(title, desc, purchase, image, brandapply, productapply, fromdate, todate, minprice, discount, maxdiscount, quantity);

        if (title == null || title.trim().isEmpty()) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Tiêu đề không được để trống!");
        } else if (desc == null || desc.trim().isEmpty()) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Mô tả không được để trống!");
        } else if (purchase == null || purchase.trim().isEmpty()) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Điều kiện áp dụng không được để trống!");
        } else if (image == null || image.trim().isEmpty()) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Hình ảnh không được để trống!");
        } else if (fromdate == null || fromdate.trim().isEmpty()) {
            jsonResult.addProperty("valid", false);
            jsonResult.addProperty("message", "Ngày bắt đầu hiệu lực mã giảm giá không được để trống!");
        } else {
            try {
                LocalDate startDate = LocalDate.parse(fromdate);
                if (todate != null && !todate.trim().isEmpty()) {
                    LocalDate endDate = LocalDate.parse(todate);
                    if (endDate.isBefore(startDate)) {
                        jsonResult.addProperty("valid", false);
                        jsonResult.addProperty("message", "Ngày kết thúc hiệu lực mã giảm giá phải lớn hơn hoặc bằng ngày bắt đầu!");
                    } else if (!voucherDao.checkExistVoucher(title)) {
                        jsonResult.addProperty("valid", false);
                        jsonResult.addProperty("message", "Tiêu đề mã giảm giá đã tồn tại!");
                    } else {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date utilFromDate = sdf.parse(fromdate);
                            java.util.Date utilToDate = sdf.parse(todate);

                            try {
                                Date discountFromDate = sdf.parse(fromdate);
                                Date discountToDate = sdf.parse(todate);

                                // Get the start of today
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.HOUR_OF_DAY, 0);
                                cal.set(Calendar.MINUTE, 0);
                                cal.set(Calendar.SECOND, 0);
                                cal.set(Calendar.MILLISECOND, 0);
                                Date todayStart = cal.getTime();

                                if (discountFromDate.before(todayStart)) {
                                    jsonResult.addProperty("valid", false);
                                    jsonResult.addProperty("message", "Ngày bắt đầu khuyến mãi phải từ hiện tại!");
                                } else if (discountToDate.before(todayStart)) {
                                    jsonResult.addProperty("valid", false);
                                    jsonResult.addProperty("message", "Ngày két thúc khuyến mãi không thể là ngày trong quá khứ!");
                                } else if (discountToDate.before(discountFromDate)) {
                                    jsonResult.addProperty("valid", false);
                                    jsonResult.addProperty("message", "Ngày kết thúc phải sau ngày bắt đầu");
                                }
                            } catch (ParseException e) {
                                jsonResult.addProperty("valid", false);
                                jsonResult.addProperty("message", "Ngày khuyến mãi không hợp lệ!");
                            }
                            int brandId = Integer.parseInt(brandapply);
                            int productId = Integer.parseInt(productapply);
                            double priceFrom = Double.parseDouble(minprice);
                            double discounts = Double.parseDouble(discount);
                            double discountMax = Double.parseDouble(maxdiscount);
                            int quantitys = Integer.parseInt(quantity);

                            Voucher v = new Voucher(title, purchase, desc, image,
                                    brandId, productId, utilFromDate, utilToDate,
                                    priceFrom, discounts, discountMax, quantitys);
                           

                            if (voucherDao.addVoucher3(title, purchase, desc, image, brandId, productId, fromdate, todate, priceFrom, discounts, discountMax, quantitys)) {
                                jsonResult.addProperty("valid", true);
                                jsonResult.addProperty("message", "Thêm mới voucher thành công!");
                            } else {
                                jsonResult.addProperty("valid", false);
                                jsonResult.addProperty("message", "Thêm mới voucher thất bại!");
                            }
                        } catch (ParseException | NumberFormatException e) {
                            jsonResult.addProperty("valid", false);
                            jsonResult.addProperty("message", "Lỗi khi xử lý dữ liệu: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                } else {
                    jsonResult.addProperty("valid", false);
                    jsonResult.addProperty("message", "Ngày kết thúc hiệu lực mã giảm giá không được để trống!");
                }
            } catch (DateTimeParseException e) {
                jsonResult.addProperty("valid", false);
                jsonResult.addProperty("message", "Ngày bắt đầu hoặc ngày kết thúc không đúng định dạng!");
            }
        }

        System.out.println("JSON Result: " + jsonResult.toString());

        // Gửi JSON đến client
        out.print(jsonResult.toString());
        out.flush();
    }

    private boolean validateData(String title, String desc, String purchase, String image, String brandapply, String productapply, String fromdate, String todate, String minprice, String discount, String maxdiscount, String quantity) {
        boolean isValid = true;

        // Kiểm tra trường dữ liệu không được để trống
        if (title == null || title.trim().isEmpty()) {
            isValid = false;
        }
        if (desc == null || desc.trim().isEmpty()) {
            isValid = false;
        }
        if (purchase == null || purchase.trim().isEmpty()) {
            isValid = false;
        }
        if (image == null || image.trim().isEmpty()) {
            isValid = false;
        }
        if (brandapply == null || brandapply.trim().isEmpty()) {
            isValid = false;
        }
        if (productapply == null || productapply.trim().isEmpty()) {
            isValid = false;
        }

        // Kiểm tra ngày tháng
        try {
            // Giả sử định dạng ngày là YYYY-MM-DD
            java.time.LocalDate startDate = java.time.LocalDate.parse(fromdate);
            java.time.LocalDate endDate = java.time.LocalDate.parse(todate);
            if (startDate.isAfter(endDate)) {
                isValid = false;
            }
        } catch (Exception e) {
            isValid = false;
        }

        // Kiểm tra số tiền
        try {
            if (Double.parseDouble(minprice) < 0) {
                isValid = false;
            }
            if (Double.parseDouble(discount) < 0) {
                isValid = false;
            }
            if (Double.parseDouble(maxdiscount) < 0) {
                isValid = false;
            }
            if (Integer.parseInt(quantity) < 0) {
                isValid = false;
            }
        } catch (NumberFormatException e) {
            isValid = false;
        }

        return isValid;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {

    }

}
