/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.depositmoney;

import Controller.authentication.BaseRequireAuthentication;
import DAL.OrderDAO;
import DAL.TransactionDAO;
import DAL.UserDAO;
import Model.AccountLogin;
import Model.Order;
import Model.*;
import Model.User;
import Utils.DateTimeHelper;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/**
 *
 * @author Dat
 */
public class DepositProcessing extends BaseRequireAuthentication {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, AccountLogin account)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        int userId = account.getUser().getID();
        DepositAjaxSvl.processingMap.put(userId, false);
        DepositAjaxSvl.processingMap.remove(userId);
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        TempTransaction tempTransaction = TempTransactionStore.getTransaction(vnp_TxnRef);
        if (tempTransaction == null) {
            response.sendRedirect("404_error.jsp");
            return;
        } else {
            // Xóa TempTransaction để giải phóng bộ nhớ
            TempTransactionStore.removeTransaction(vnp_TxnRef);

            // Xử lý các thông tin giao dịch tiếp theo
            String vnp_Amount = request.getParameter("vnp_Amount");
            double amount = Double.parseDouble(vnp_Amount);
            double dividedAmount = amount / 100;
            String dividedAmountStr = String.valueOf(dividedAmount);
            String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");
            String vnp_BankCode = request.getParameter("vnp_BankCode");
            String vnp_PayDate = request.getParameter("vnp_PayDate");
            String vnp_TransactionStatus = request.getParameter("vnp_TransactionStatus");
            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            UserDAO userDao = new UserDAO();
            User userDeposit = userDao.getUserById(account.getUser().getID());
            OrderDAO orderDAO = new OrderDAO();
            TransactionDAO transactionDAO = new TransactionDAO();
            
            Map<String, String> fields = new HashMap<>();
            for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = params.nextElement();
                String fieldValue = request.getParameter(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()), URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                }
            }
            fields.remove("vnp_SecureHashType");
            fields.remove("vnp_SecureHash");
            String calculatedHash = DepositConfig.hashAllFields(fields);
            
            if (calculatedHash.equals(vnp_SecureHash)) {
                String statusMessage;
                if ("00".equals(vnp_TransactionStatus)) {
                    
                    if (checkResult(request, vnp_TxnRef, vnp_PayDate)) {
                        statusMessage = "Success";
                        userDao.updateBalance(userDeposit.getID(), dividedAmountStr);
                        Timestamp createdAt = getCurrentTimestamp();
                        DateTimeHelper dateTimeHelper = new DateTimeHelper();
                        java.sql.Date createTime = dateTimeHelper.convertUtilDateToSqlDate(createdAt);
                        
                        Order orderTransaction = new Order(0, userDeposit.getID(), 1, dividedAmount, "Paid", createTime, null, null, false, 0);
                        int orderIDNew = orderDAO.insertOrder(orderTransaction);
                        Order newestOrder = orderDAO.getOrderByOrderID(orderIDNew);
                        
                        transactionDAO.insertTransaction(userDeposit, newestOrder, dividedAmount, "Deposit", vnp_TxnRef, vnp_BankCode, statusMessage);
                        
                        String name = userDeposit.getFirstName() + userDeposit.getLastName();
                        request.getSession().setAttribute("username", name);
                        request.getSession().setAttribute("transactiontype", "Nạp tiền");
                        request.getSession().setAttribute("ordercode", String.valueOf(orderIDNew));
                        request.getSession().setAttribute("description", "Nạp tiền vào ví qua VNPay");
                        request.getSession().setAttribute("amount", String.valueOf(newestOrder.getTotalAmount()));
                        
                        request.getSession().setAttribute("transactioncode", vnp_TxnRef);
                        request.getSession().setAttribute("amountpayment", String.valueOf(dividedAmount));
                        request.getSession().setAttribute("descriptionpayment", vnp_OrderInfo);
                        request.getSession().setAttribute("bankcode", vnp_BankCode);
                        request.getSession().setAttribute("timepayment", vnp_PayDate);
                        request.getSession().setAttribute("status", statusMessage);
                        
                        DepositAjaxSvl.processingMap.put(userId, false);
                        DepositAjaxSvl.processingMap.remove(userId);
                        response.sendRedirect("resultpayment");
                    } else {
                        DepositAjaxSvl.processingMap.remove(userId);
                        request.getRequestDispatcher("404_error.jsp").forward(request, response);
                    }
                    
                } else {
                    statusMessage = "Failed";
                    DepositAjaxSvl.processingMap.put(userId, false);
                    DepositAjaxSvl.processingMap.remove(userId);
                    response.sendRedirect("depositmoney");
                }
            } else {
                DepositAjaxSvl.processingMap.put(userId, false);
                DepositAjaxSvl.processingMap.remove(userId);
                out.print("Chu ky khong hop le");
            }
        }
    }
    
    private Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }
    
    public boolean checkResult(HttpServletRequest req, String vnp_TxnRef, String vnp_TransDate) throws IOException {
        String vnp_RequestId = Controller.CheckoutByBanking.Config.getRandomNumber(8);
        String vnp_Version = "2.1.0";
        String vnp_Command = "querydr";
        String vnp_TmnCode = Controller.CheckoutByBanking.Config.vnp_TmnCode;
        //String vnp_TxnRef = req.getParameter("order_id"); // Lấy từ yêu cầu
        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId:" + vnp_TxnRef;
        //String vnp_TransDate = req.getParameter("trans_date");
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        
        String vnp_IpAddr = Controller.CheckoutByBanking.Config.getIpAddress(req);
        
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
        String vnp_SecureHash = Controller.CheckoutByBanking.Config.hmacSHA512(Controller.CheckoutByBanking.Config.secretKey, hash_Data.toString());
        
        vnp_Params.addProperty("vnp_SecureHash", vnp_SecureHash);

        // In ra Secure Hash
        System.out.println("vnp_SecureHash: " + vnp_SecureHash);
        
        URL url = new URL(Controller.CheckoutByBanking.Config.vnp_ApiUrl);
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
}
