package Controller.depositmoney;

import Controller.authentication.BaseRequireAuthentication;
import DAL.UserDAO;
import Model.AccountLogin;
import Model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DepositAjaxSvl extends BaseRequireAuthentication {

    public static final Map<Integer, Boolean> processingMap = new ConcurrentHashMap<>();

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        AccountLogin account = (AccountLogin) req.getSession().getAttribute("account");
        int userId = account.getUser().getID();

        if (processingMap.getOrDefault(userId, false)) {
            out.print("Bạn đang có một giao dịch chưa hoàn thành, hãy hoàn thành hoặc hủy giao dịch chưa được thực hiện để tiếp tục!");
            return;
        }

        processingMap.put(userId, true);

        long startTime = System.currentTimeMillis();

        try {
            String orderType = "other";
            long amount = Integer.parseInt(req.getParameter("amount")) * 100;
            String bankCode = req.getParameter("bankCode");

            String vnp_TxnRef = DepositConfig.getRandomNumber(8);
            String vnp_IpAddr = req.getRemoteAddr();

            String vnp_TmnCode = DepositConfig.vnp_TmnCode;

            TempTransaction tempTransaction = new TempTransaction(userId, amount);
            TempTransactionStore.addTransaction(vnp_TxnRef, tempTransaction);

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", DepositConfig.vnp_Version);
            vnp_Params.put("vnp_Command", DepositConfig.vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            if (bankCode != null && !bankCode.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bankCode);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang: " + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", DepositConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator<String> itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = itr.next();
                String fieldValue = vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    // Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    // Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = DepositConfig.hmacSHA512(DepositConfig.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = DepositConfig.vnp_PayUrl + "?" + queryUrl;

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken for preprocessing: " + (endTime - startTime) + "ms");

            // Redirect to the payment URL
            out.print(paymentUrl);

            long finalTime = System.currentTimeMillis();
            System.out.println("Time taken for sending request: " + (finalTime - endTime) + "ms");

        } catch (Exception e) {
            out.print("Đã xảy ra lỗi trong quá trình xử lý: " + e.getMessage());
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
