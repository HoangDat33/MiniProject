package Controller.depositmoney;

import Controller.authentication.BaseRequireAuthentication;
import Model.AccountLogin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DisplayResultPayment extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DisplayResultPayment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayResultPayment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
                String username = (String) request.getSession().getAttribute("username");
        String ordercode = (String) request.getSession().getAttribute("ordercode");
        String amount = (String) request.getSession().getAttribute("amount");
        String transactioncode = (String) request.getSession().getAttribute("transactioncode");
        String amountpayment = (String) request.getSession().getAttribute("amountpayment");
        String descriptionpayment = (String) request.getSession().getAttribute("descriptionpayment");
        String bankcode = (String) request.getSession().getAttribute("bankcode");
        String timepayment = (String) request.getSession().getAttribute("timepayment");
        String status = (String) request.getSession().getAttribute("status");

        // In ra giá trị để kiểm tra
        System.out.println("OrderCode: " + ordercode);
        System.out.println("Amount: " + amount);
        System.out.println("AmountPayment: " + amountpayment);
        System.out.println("TransactionCode: " + transactioncode);

        // Đặt thuộc tính cho request
        request.setAttribute("username", username);
        request.setAttribute("transactiontype", "Nạp tiền");
        request.setAttribute("ordercode", ordercode);
        request.setAttribute("description", "Nạp tiền vào ví qua VNPay");
        request.setAttribute("amount", amount);
        request.setAttribute("transactioncode", transactioncode);
        request.setAttribute("amountpayment", amountpayment);
        request.setAttribute("descriptionpayment", descriptionpayment);
        request.setAttribute("bankcode", bankcode);
        request.setAttribute("timepayment", timepayment);
        request.setAttribute("status", status);

        
        // Chuyển tiếp đến JSP
        request.getRequestDispatcher("/paymentbanking.jsp").forward(request, response);
    }

}
