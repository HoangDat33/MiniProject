package Controller.CheckoutByBanking;

import Controller.authentication.BaseRequireAuthentication;
import DAL.OrderDAO;
import DAL.ProductCardDAO;
import DAL.ProductCategoriesDAO;
import DAL.TransactionDAO;
import DAL.UserDAO;
import Model.AccountLogin;
import Model.GoogleLogin;
import Model.Order;
import Model.OrderDetails;
import Model.ProductCard;
import Model.Transaction;
import Model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mysql.cj.xdevapi.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
//
//public class CheckoutBankingReturn extends BaseRequireAuthentication {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CheckoutBankingReturn</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CheckoutBankingReturn at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    } 
//
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//    private OrderDAO orderDAO = new OrderDAO();
//    private UserDAO userDAO = new UserDAO();
//    private ProductCardDAO pdcDAO = new ProductCardDAO();
//    private ProductCategoriesDAO productCateDAO = new ProductCategoriesDAO();
//    private static ConcurrentLinkedQueue<Order> orderQueue = new ConcurrentLinkedQueue<>();
//    private static boolean processing = false;
//    private CheckoutByBanking checkoutbankingGet = new CheckoutByBanking();
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            TransactionDAO transDao = new TransactionDAO();
//            account = (AccountLogin) request.getSession().getAttribute("account");
//            GoogleLogin gglogin = (GoogleLogin) request.getSession().getAttribute("gguser");
//            int userId = account.getUser().getID();
//            if (account == null && gglogin == null) {
//                out.print("<h5>Vui lòng đăng nhập để tiếp tục <a href=\"login.jsp\">Click</a></h5>");
//                return;
//            }
//
//            User user = getUserFromSession(account, gglogin);
//            if (user == null) {
//                out.print("<h5>Vui lòng đăng nhập để tiếp tục <a href=\"login.jsp\">Click</a></h5>");
//                return;
//            }
//
//            Map<String, String> vnpParams = getVnpParams(request);
//            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
//
//            if (!isValidSignature(vnpParams, vnp_SecureHash)) {
//                out.print("Invalid signature");
//                return;
//            }
//
//            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
//            String vnp_TxnRef = request.getParameter("vnp_TxnRef");
//            String vnp_Amount = request.getParameter("vnp_Amount");
//            String vnp_BankCode = request.getParameter("vnp_BankCode");
//            String vnp_PayDate = request.getParameter("vnp_PayDate");
//
//            if ("00".equals(vnp_TransactionStatus)) {
//                //processOrder(vnp_Amount, user, vnp_TxnRef, vnp_BankCode, transDao, out, request, response);
//                while (!orderQueue.isEmpty()) {
//                    Order order = orderQueue.poll();
//                    //Thread.sleep(2000);
//
//                    Map<Integer, Integer> productCategoryCount = new HashMap<>();
//                    List<OrderDetails> listOrderTest = orderDAO.getAllOrderDetailsByOderId(order.getId());
//                    for (OrderDetails od : listOrderTest) {
//                        int productCategoriesId = od.getProductCategories().getId();
//                        productCategoryCount.put(productCategoriesId, productCategoryCount.getOrDefault(productCategoriesId, 0) + 1);
//                    }
//
//                    double totalPayment = Double.parseDouble(vnp_Amount);
//                    if ("Unpaid".equals(order.getStatus())) {
//                        orderDAO.updateStatusOrder(order.getId());
//                    }
//
//                    List<OrderDetails> listDetail = orderDAO.getAllOrderDetailsByOderId(order.getId());
//                    for (OrderDetails od : listDetail) {
//                        int productCategoriesId = od.getProductCategories().getId();
//                        ProductCard pdc = pdcDAO.getProductCardbyIDQuantity2(productCategoriesId, 1);
//
//                        orderDAO.insertProductCartOrderDetail(pdc.getID(), od.getID());
//                        pdcDAO.updateIsDeleteProductCard(pdc.getID());
//
//                        productCateDAO.updateQuantityAfterCheckout(productCategoriesId, productCategoryCount.get(productCategoriesId));
//                    }
//
//                    Transaction trans = new Transaction(user.getID(), order.getId(), totalPayment, "Payment", vnp_TxnRef, vnp_BankCode, "Success");
//                    transDao.addTransitionAfterCheckoutByBanking(trans);
//
//                    request.setAttribute("username", user.getFirstName() + " " + user.getLastName());
//                    request.setAttribute("transactiontype", "Thanh toán");
//                    request.setAttribute("ordercode", order.getId());
//                    request.setAttribute("description", "Thanh toán hóa đơn mua thẻ");
//                    request.setAttribute("amount", formatMoney(totalPayment / 100));
//
//                    request.setAttribute("username", user.getFirstName() + user.getLastName());
//                    request.setAttribute("transactiontype", "Thanh toán");
//                    request.setAttribute("transactioncode", vnp_TxnRef);
//                    double vnpAmount = Double.parseDouble(vnp_Amount);
//                    request.setAttribute("amountpayment", formatMoney(vnpAmount / 100));
//                    request.setAttribute("descriptionpayment", "Thanh toán hóa đơn mua hàng bằng VNPAY");
//                    request.setAttribute("bankcode", vnp_BankCode);
//
//                    String inputDateFormat = "yyyyMMddHHmmss";
//                    SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
//                    Date date = inputFormat.parse(vnp_PayDate);
//                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String formattedDate = outputFormat.format(date);
//                    request.setAttribute("timepayment", formattedDate);
//                    request.setAttribute("status", "Thành công");
//                    
//                    //mapProcessing.remove(account.getUser().getID());
//                    request.getRequestDispatcher("paymentbanking.jsp").forward(request, response);  
//                }
//            } else {
//                response.sendRedirect("order");
//                
//            }
//            //mapProcessing.put(userId, false);
//            checkoutbankingGet.mapProcessing.remove(userId);
//        } catch (Exception ex) {
//            System.out.println("Error: " + ex);
//        }
//    }//end do get
//    
//    
//    private User getUserFromSession(AccountLogin account, GoogleLogin gglogin) {
//        if (account != null) {
//            return userDAO.getUserById(account.getUser().getID());
//        } else if (gglogin != null) {
//            return userDAO.getUserById(gglogin.getUser().getID());
//        }
//        return null;
//    }
//    
//    private String formatMoney(double money) {
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        return currencyFormat.format(money);
//    }
//    
//    private boolean isValidSignature(Map<String, String> fields, String vnp_SecureHash) {
//        String calculatedHash = Config.hashAllFields(fields);
//        return calculatedHash.equals(vnp_SecureHash);
//    }
//    
//    private Map<String, String> getVnpParams(HttpServletRequest request) throws UnsupportedEncodingException {
//        Map<String, String> fields = new HashMap<>();
//        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
//            String fieldName = params.nextElement();
//            String fieldValue = request.getParameter(fieldName);
//            if (fieldValue != null && !fieldValue.isEmpty()) {
//                fields.put(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()),
//                        URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
//            }
//        }
//        fields.remove("vnp_SecureHashType");
//        fields.remove("vnp_SecureHash");
//        return fields;
//    }
//
//}//end class
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class CheckoutBankingReturn extends BaseRequireAuthentication {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10); // Sử dụng pool với 10 luồng
    private OrderDAO orderDAO = new OrderDAO();
    private UserDAO userDAO = new UserDAO();
    private ProductCardDAO pdcDAO = new ProductCardDAO();
    private ProductCategoriesDAO productCateDAO = new ProductCategoriesDAO();
    private CheckoutByBanking checkoutbankingGet = new CheckoutByBanking();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            TransactionDAO transDao = new TransactionDAO();
            account = (AccountLogin) request.getSession().getAttribute("account");
            GoogleLogin gglogin = (GoogleLogin) request.getSession().getAttribute("gguser");
            int userId = account.getUser().getID();

            if (account == null && gglogin == null) {
                out.print("<h5>Vui lòng đăng nhập để tiếp tục <a href=\"login.jsp\">Click</a></h5>");
                return;
            }

            User user = getUserFromSession(account, gglogin);
            if (user == null) {
                out.print("<h5>Vui lòng đăng nhập để tiếp tục <a href=\"login.jsp\">Click</a></h5>");
                return;
            }

            Map<String, String> vnpParams = getVnpParams(request);
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");

            if (!isValidSignature(vnpParams, vnp_SecureHash)) {
                out.print("Invalid signature");
                return;
            }

            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
            String vnp_TxnRef = request.getParameter("vnp_TxnRef");
            String vnp_Amount = request.getParameter("vnp_Amount");
            String vnp_BankCode = request.getParameter("vnp_BankCode");
            String vnp_PayDate = request.getParameter("vnp_PayDate");

            if ("00".equals(vnp_TransactionStatus)) {
                //đi tới vn pay check lại giao dịch tồn tại
                if (checkResult(request, vnp_TxnRef, vnp_PayDate)) {
                    Order order = checkoutbankingGet.orderQueue.poll();
                    if (order != null) {
                        //CompletableFuture.runAsync(() -> {
                        try {
                            processOrder(order, vnp_Amount, user, vnp_TxnRef, vnp_BankCode, transDao);
                            synchronized (request) {
                                setRequestAttributes(request, user, vnp_Amount, vnp_TxnRef, vnp_BankCode, vnp_PayDate, order.getId());
                                checkoutbankingGet.mapProcessing.remove(userId);
                                request.getRequestDispatcher("paymentbanking.jsp").forward(request, response);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //}, executor);
                    } else {
                        checkoutbankingGet.mapProcessing.remove(userId);
                        response.sendRedirect("order");
                    }
                }else{
                    response.sendRedirect("404_error.jsp");
                }
            } else {
                response.sendRedirect("order");
            }
            checkoutbankingGet.mapProcessing.remove(userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void processOrder(Order order, String vnp_Amount, User user, String vnp_TxnRef, String vnp_BankCode, TransactionDAO transDao) {
        try {
            Map<Integer, Integer> productCategoryCount = new HashMap<>();
            List<OrderDetails> listOrderTest = orderDAO.getAllOrderDetailsByOderId(order.getId());
            for (OrderDetails od : listOrderTest) {
                int productCategoriesId = od.getProductCategories().getId();
                productCategoryCount.put(productCategoriesId, productCategoryCount.getOrDefault(productCategoriesId, 0) + 1);
            }

            double totalPayment = Double.parseDouble(vnp_Amount);
            if ("Unpaid".equals(order.getStatus())) {
                orderDAO.updateStatusOrder(order.getId());
            }

            List<OrderDetails> listDetail = orderDAO.getAllOrderDetailsByOderId(order.getId());
            for (OrderDetails od : listDetail) {
                int productCategoriesId = od.getProductCategories().getId();
                ProductCard pdc = pdcDAO.getProductCardbyIDQuantity2(productCategoriesId, 1);

                orderDAO.insertProductCartOrderDetail(pdc.getID(), od.getID());
                pdcDAO.updateIsDeleteProductCard(pdc.getID());

                productCateDAO.updateQuantityAfterCheckout(productCategoriesId, productCategoryCount.get(productCategoriesId));
            }

            Transaction trans = new Transaction(user.getID(), order.getId(), totalPayment / 100, "Payment", vnp_TxnRef, vnp_BankCode, "Success");
            transDao.addTransitionAfterCheckoutByBanking(trans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRequestAttributes(HttpServletRequest request, User user, String vnp_Amount, String vnp_TxnRef, String vnp_BankCode, String vnp_PayDate, int orderId) throws Exception {
        request.setAttribute("username", user.getFirstName() + " " + user.getLastName());
        request.setAttribute("transactiontype", "Thanh toán");
        request.setAttribute("ordercode", orderId);
        request.setAttribute("description", "Thanh toán hóa đơn mua thẻ");
        request.setAttribute("amount", vnp_Amount);

        request.setAttribute("username", user.getFirstName() + user.getLastName());
        request.setAttribute("transactiontype", "Thanh toán");
        request.setAttribute("transactioncode", vnp_TxnRef);
        double vnpAmount = Double.parseDouble(vnp_Amount);
        request.setAttribute("amountpayment", formatMoney(vnpAmount / 100));
        request.setAttribute("descriptionpayment", "Thanh toán hóa đơn mua hàng bằng VNPAY");
        request.setAttribute("bankcode", vnp_BankCode);

        String inputDateFormat = "yyyyMMddHHmmss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
        Date date = inputFormat.parse(vnp_PayDate);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = outputFormat.format(date);
        request.setAttribute("timepayment", formattedDate);
        request.setAttribute("status", "Thành công");
    }

    private User getUserFromSession(AccountLogin account, GoogleLogin gglogin) {
        if (account != null) {
            return userDAO.getUserById(account.getUser().getID());
        } else if (gglogin != null) {
            return userDAO.getUserById(gglogin.getUser().getID());
        }
        return null;
    }

    private String formatMoney(double money) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(money);
    }

    private boolean isValidSignature(Map<String, String> fields, String vnp_SecureHash) {
        String calculatedHash = Config.hashAllFields(fields);
        return calculatedHash.equals(vnp_SecureHash);
    }

    private Map<String, String> getVnpParams(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                fields.put(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()),
                        URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
            }
        }
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");
        return fields;
    }

    public boolean checkResult(HttpServletRequest req, String vnp_TxnRef, String vnp_TransDate) throws IOException {
        String vnp_RequestId = Config.getRandomNumber(8);
        String vnp_Version = "2.1.0";
        String vnp_Command = "querydr";
        String vnp_TmnCode = Config.vnp_TmnCode;
        //String vnp_TxnRef = req.getParameter("order_id"); // Lấy từ yêu cầu
        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId:" + vnp_TxnRef;
        //String vnp_TransDate = req.getParameter("trans_date");
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        String vnp_IpAddr = Config.getIpAddress(req);

        JsonObject vnp_Params = new JsonObject();
        vnp_Params.addProperty("vnp_RequestId", vnp_RequestId);
        vnp_Params.addProperty("vnp_Version", vnp_Version);
        vnp_Params.addProperty("vnp_Command", vnp_Command);
        vnp_Params.addProperty("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.addProperty("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.addProperty("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.addProperty("vnp_TransactionDate", vnp_TransDate);
        vnp_Params.addProperty("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.addProperty("vnp_IpAddr", vnp_IpAddr);

        // In ra tất cả các tham số trước khi tạo Secure Hash
        System.out.println("vnp_RequestId: " + vnp_RequestId);
        System.out.println("vnp_Version: " + vnp_Version);
        System.out.println("vnp_Command: " + vnp_Command);
        System.out.println("vnp_TmnCode: " + vnp_TmnCode);
        System.out.println("vnp_TxnRef: " + vnp_TxnRef);
        System.out.println("vnp_OrderInfo: " + vnp_OrderInfo);
        System.out.println("vnp_TransactionDate: " + vnp_TransDate);
        System.out.println("vnp_CreateDate: " + vnp_CreateDate);
        System.out.println("vnp_IpAddr: " + vnp_IpAddr);

        String hash_Data = String.join("|", vnp_RequestId, vnp_Version, vnp_Command, vnp_TmnCode, vnp_TxnRef, vnp_TransDate, vnp_CreateDate, vnp_IpAddr, vnp_OrderInfo);
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hash_Data.toString());

        vnp_Params.addProperty("vnp_SecureHash", vnp_SecureHash);

        // In ra Secure Hash
        System.out.println("vnp_SecureHash: " + vnp_SecureHash);

        URL url = new URL(Config.vnp_ApiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(vnp_Params.toString());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader in;
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String output;
        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        String responseString = response.toString();
        System.out.println("Response: " + responseString);

        // Phân tích phản hồi JSON và lấy vnp_ResponseCode
        String vnp_ResponseCode = "";
        String vnp_TxnRefReturn = "";
        String vnp_TransactionDate = "";
        try {
            JSONObject jsonResponse = new JSONObject(responseString);
            vnp_ResponseCode = jsonResponse.getString("vnp_ResponseCode");
            vnp_TxnRefReturn = jsonResponse.getString("vnp_TxnRef");
            vnp_TransactionDate = jsonResponse.getString("vnp_PayDate");
        } catch (Exception e) {
            System.err.println("Failed to parse JSON response: " + e.getMessage());
        }
        if (vnp_ResponseCode.equals("00") && vnp_TxnRefReturn.equals(vnp_TxnRef) && vnp_TransactionDate.equals(vnp_TransDate)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
