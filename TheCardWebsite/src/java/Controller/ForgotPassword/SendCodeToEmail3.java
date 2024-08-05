
package Controller.ForgotPassword;

import Controller.Captcha;
import DAL.EmailDAO;
import DAL.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendCodeToEmail3 extends HttpServlet {

    public static ConcurrentHashMap<String, String> otpHash = new ConcurrentHashMap<String, String>();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public String getOtpById(String idOtp) {
        String otp = otpHash.get(idOtp);
        return otp;
    }
    
     public static void removeOtpCode(String idOtp) {
        otpHash.remove(idOtp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendCodeToEmail2</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendCodeToEmail2 at " + request.getContextPath () + "</h1>");
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
        String email = request.getParameter("email");
        String captcha = request.getParameter("captcha");
        
        HttpSession session = request.getSession();
        //String captchasession = (String) session.getAttribute("captcha");
        Captcha classCaptcha = new Captcha();
        String idCaptcha = (String) request.getParameter("idCaptcha"); //lấy từ thẻ input hidden xuống !!
        String codeCaptcha = classCaptcha.getCaptchaById(idCaptcha);
        // check rỗng
        if (email == null || email.equals("") || captcha == null || captcha.equals("")) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            request.getRequestDispatcher("requestchangepass.jsp").forward(request, response);
        }

        UserDAO dao = new UserDAO();
        User useremail = dao.getUserbyEmail(email);
        
        
        int count = 0;
        session.setAttribute("countotp", count);
        // check email tồn tại
        if (useremail == null) {
            request.setAttribute("error", "Email không tồn tại");
            request.getRequestDispatcher("requestchangepass.jsp").forward(request, response);
        } else if(!captcha.equals(codeCaptcha)){
            request.setAttribute("error", "Sai mã captcha");
            request.getRequestDispatcher("requestchangepass.jsp").forward(request, response);
        }else{
            int userid = useremail.getID();
            String otprequest = EmailDAO.generateRandomString();
            String otpId = EmailDAO.generateRandomString();    
            //scheduler.schedule(() -> session.removeAttribute());  
            //scheduler.schedule(() -> session.removeAttribute("otprequest"), 60, TimeUnit.SECONDS);
            
            classCaptcha.removeCaptchaCode(idCaptcha);
            otpHash.put(otpId, otprequest);
            
            request.setAttribute("userId", userid);
            request.setAttribute("email", email);
            request.setAttribute("idOtp", otpId);
            
            EmailDAO.sendEmail(email, "Mã xác nhận đặt lại mật khẩu" + "", "Chào bạn, mã OTP xác thực tài khoản của bạn là: " + "<b>" + otprequest + "</b>" + ". Mã có giá trị trong 60s.");
            request.getRequestDispatcher("verifyemailrequest.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
