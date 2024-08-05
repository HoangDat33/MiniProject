
package Controller.ForgotPassword;

import DAL.EmailDAO;
import Utils.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ResendEmailForgot2 extends HttpServlet {

    
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResendEmailForgot2</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResendEmailForgot2 at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String userId = request.getParameter("userId");
        
        SendCodeToEmail3 sendcode = new SendCodeToEmail3();
        if ("resend".equals(action)) {
            if (email == null || email.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found in request.");
                return;
            }
            String randomString = SendEmail.generateRandomString();
            String idOtp = SendEmail.generateRandomString();
            boolean emailSent =  EmailDAO.sendEmail(email, "Mã xác nhận đặt lại mật khẩu" + "", "Chào bạn, mã OTP xác thực tài khoản của bạn là: " + "<b>" + randomString + "</b>" + ". Mã có giá trị trong 60s.");

            if (emailSent) {
                System.out.println("Email sent successfully!");
                // Store the random string in the session to verify later
                request.getSession().setAttribute("verifyotp", randomString);
                request.setAttribute("resentSuccess", "Gửi OTP thành công");
                request.setAttribute("idOtp", idOtp);
                sendcode.otpHash.put(idOtp, randomString);
            } else {
                request.setAttribute("resentSuccess", "Gửi OTP thất bại!");
                System.out.println("Failed to send email.");
            }
        }
        request.setAttribute("userId", userId);
        request.setAttribute("email", email);
        request.getRequestDispatcher("verifyemailrequest.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
