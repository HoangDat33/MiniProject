
package Controller.ForgotPassword;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VerifyPasswordOTP2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyPasswordOTP2</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyPasswordOTP2 at " + request.getContextPath () + "</h1>");
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

        HttpSession session = request.getSession();
        //String otpsession = (String) session.getAttribute("otprequest");
        
        String otpUser = request.getParameter("otp").trim();
        String userId = request.getParameter("userId");
        String email = request.getParameter("email");
        String idOtp = request.getParameter("idOtp");
        
        SendCodeToEmail3 sendcode = new SendCodeToEmail3();
        String otp = sendcode.getOtpById(idOtp);
        
        int count = 0;
        if (session.getAttribute("countotp") != null) {
            count = (int) session.getAttribute("countotp");
            if (count == 3) {
                session.removeAttribute("otprequest");
                session.removeAttribute("countotp");
                request.setAttribute("error", "Bạn đã nhập quá 3 lần mã OTP");
                request.getRequestDispatcher("requestchangepass.jsp").forward(request, response);
            } else if (otpUser == null || otpUser.equals("")) {
                count++;
                session.setAttribute("countotp", count);
                request.setAttribute("errorotp", "Vui lòng nhập mã OTP .Bạn còn " + (3 - count) + " lần nhập");
                request.getRequestDispatcher("verifyemailrequest.jsp").forward(request, response);
            } else if (!otpUser.equals(otp)) {
                count++;
                session.setAttribute("countotp", count);
                request.setAttribute("errorotp", "OTP sai .Bạn còn " + (3 - count) + " lần nhập");
                request.getRequestDispatcher("verifyemailrequest.jsp").forward(request, response);
            } else {
                //session.setAttribute("confirmchange", otp);
                request.setAttribute("userId", userId);
                sendcode.removeOtpCode(idOtp);
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("requestchangepass.jsp").forward(request, response);
        }
    }//end post

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
