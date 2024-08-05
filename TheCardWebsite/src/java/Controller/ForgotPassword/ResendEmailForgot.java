package Controller.ForgotPassword;


import Controller.authentication.BaseRequireAuthentication;
import DAL.EmailDAO;
import Model.AccountLogin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ResendEmailForgot extends BaseRequireAuthentication {
 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if ("resend".equals(action)) {
            String email = (String) request.getSession().getAttribute("email");
            if (email == null || email.isEmpty()) {
                // Handle case where email is not found in the request
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found in request.");
                return;
            }
            String randomString = EmailDAO.generateRandomString();
            // Send an email
            //String to = email;
            //String tieuDe = "Mã xác thực tài khoản";
            //String noiDung = "Chào bạn, mã OTP xác thực tài khoản của bạn là: " + "<b>" + randomString + "</b>" + ". Mã có giá trị trong 60s.";
            boolean emailSent =  EmailDAO.sendEmail(email, "Mã xác nhận đặt lại mật khẩu" + "", "Chào bạn, mã OTP xác thực tài khoản của bạn là: " + "<b>" + randomString + "</b>" + ". Mã có giá trị trong 60s.");
            HttpSession session = request.getSession();
            if (emailSent) {
                System.out.println("Email sent successfully!");
                // Store the random string in the session to verify later
                request.getSession().setAttribute("otprequest", randomString);
                request.setAttribute("resentSuccess", "Gửi OTP thành công");
            } else {
                System.out.println("Failed to send email.");
            }
        }
        request.getRequestDispatcher("verifyemailrequest.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("resend".equals(action)) {
            String email = (String) request.getSession().getAttribute("email");
            if (email == null || email.isEmpty()) {
                // Handle case where email is not found in the request
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found in request.");
                return;
            }
            String randomString = EmailDAO.generateRandomString();
            // Send an email
            //String to = email;
            //String tieuDe = "Mã xác thực tài khoản";
            //String noiDung = "Chào bạn, mã OTP xác thực tài khoản của bạn là: " + "<b>" + randomString + "</b>" + ". Mã có giá trị trong 60s.";
            boolean emailSent =  EmailDAO.sendEmail(email, "Mã xác nhận đặt lại mật khẩu" + "", "Chào bạn, mã OTP xác thực tài khoản của bạn là: " + "<b>" + randomString + "</b>" + ". Mã có giá trị trong 60s.");
            if (emailSent) {
                System.out.println("Email sent successfully!");
                // Store the random string in the session to verify later
                request.setAttribute("otprequest", randomString);
                request.setAttribute("resentSuccess", "Gửi OTP thành công!");
            } else {
                request.setAttribute("resentSuccess", "Gửi OTP thát bại!");
                System.out.println("Failed to send email.");
            }
        }
        request.getRequestDispatcher("verifyemailrequest.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        //processRequest(req, resp);
    }

}