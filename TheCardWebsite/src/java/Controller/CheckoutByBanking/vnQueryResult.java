package Controller.CheckoutByBanking;
//
//import Controller.CheckoutByBanking.Config;
//import com.google.gson.JsonObject;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.TimeZone;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//public class vnQueryResult extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = resp.getWriter();
//        //Command:querydr
//        String vnp_RequestId = Config.getRandomNumber(8);
//        String vnp_Version = "2.1.0";
//        String vnp_Command = "querydr";
//        String vnp_TmnCode = Config.vnp_TmnCode;
//        //String vnp_TxnRef = req.getParameter("order_id");
//        String vnp_TxnRef = "80367121";
//        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId:" + vnp_TxnRef;
//        //String vnp_TransactionNo = req.getParameter("transactionNo");
//        String vnp_TransactionNo = "14533030";
//        String vnp_TransDate = req.getParameter("trans_date");
//
//        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String vnp_CreateDate = formatter.format(cld.getTime());
//        String vnp_IpAddr = Config.getIpAddress(req);
//
//        JsonObject vnp_Params = new JsonObject();
//        vnp_Params.addProperty("vnp_RequestId", vnp_RequestId);
//        vnp_Params.addProperty("vnp_Version", vnp_Version);
//        vnp_Params.addProperty("vnp_Command", vnp_Command);
//        vnp_Params.addProperty("vnp_TmnCode", vnp_TmnCode);
//        vnp_Params.addProperty("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.addProperty("vnp_OrderInfo", vnp_OrderInfo);
//        //vnp_Params.addProperty("vnp_TransactionNo", vnp_TransactionNo);
//        vnp_Params.addProperty("vnp_TransactionDate", vnp_TransDate);
//        vnp_Params.addProperty("vnp_CreateDate", vnp_CreateDate);
//        vnp_Params.addProperty("vnp_IpAddr", vnp_IpAddr);
//
//        String hash_Data = String.join("|", vnp_RequestId, vnp_Version, vnp_Command, vnp_TmnCode, vnp_TxnRef, vnp_TransDate, vnp_CreateDate, vnp_IpAddr, vnp_OrderInfo);
//        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hash_Data.toString());
//
//        vnp_Params.addProperty("vnp_SecureHash", vnp_SecureHash);
//
//        URL url = new URL(Config.vnp_ApiUrl);
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(vnp_Params.toString());
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("nSending 'POST' request to URL : " + url);
//        System.out.println("Post Data : " + vnp_Params);
//        System.out.println("Response Code : " + responseCode);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String output;
//        StringBuffer response = new StringBuffer();
//        while ((output = in.readLine()) != null) {
//            response.append(output);
//        }
//        in.close();
//        //System.out.println(response.toString());
//        out.print("Response: " + response.toString());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    }
//}
import Controller.CheckoutByBanking.Config;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class vnQueryResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Command: querydr
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String vnp_RequestId = Config.getRandomNumber(8);
        String vnp_Version = "2.1.0";
        String vnp_Command = "querydr";
        String vnp_TmnCode = Config.vnp_TmnCode;
        //String vnp_TxnRef = req.getParameter("order_id"); // Lấy từ yêu cầu
        String vnp_TxnRef = "56023224";
        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId:" + vnp_TxnRef;
        //String vnp_TransDate = req.getParameter("trans_date");
        String vnp_TransDate = "20240726093711";
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

        //System.out.println("Response: " + response.toString());
        out.print("Response: " + response.toString());
        
        //JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
        //String vnp_ResponseCode = jsonResponse.get("vnp_ResponseCode").getAsString();
        //System.out.println("vnp_ResponseCode: " + vnp_ResponseCode);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu không sử dụng phương thức POST, bạn có thể bỏ trống phương thức này
    }
}
