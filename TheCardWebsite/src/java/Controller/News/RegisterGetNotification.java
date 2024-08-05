
package Controller.News;

import DAL.RegisterNotificationDAO;
import Model.RegisterNotification;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterGetNotification extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterGetNotification</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterGetNotification at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 
    
     private static final String EMAIL_PATTERN =
        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RegisterNotificationDAO rnDao = new RegisterNotificationDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        
        String notification = "";
        if(name.equals(" ") || name.isEmpty()){
            notification = "Tên vui lòng không được bỏ trống!";
            //request.setAttribute("notification", notification);
            out.print(notification);
        }else if (email.equals("") || email.isEmpty() || !isValidEmail(email)){
            notification = "Email không hợp lệ!";
            //request.setAttribute("notification", notification);
            out.print(notification);
        }else if(!rnDao.getEmailRegistered(email)){
            notification = "Email này đã được đăng kí nhận thông báo!";
            //request.setAttribute("notification", notification);
            out.print(notification);
        }else{
            RegisterNotification newRegister = new RegisterNotification(name, email, message);
            rnDao.insertRegisterNotification(newRegister);
            notification = "Đăng kí nhận thông báo thành công! Chúng tôi sẽ gửi thông báo cho bạn khi có tin tức mới!";
            //request.setAttribute("notification", notification);
            out.print(notification);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
