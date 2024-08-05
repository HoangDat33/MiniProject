
package Controller.News;

import static Controller.News.RegisterGetNotification.isValidEmail;
import DAL.CommentNewsDAO;
import DAL.RegisterNotificationDAO;
import Model.CommentNews;
import Model.RegisterNotification;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommentNewsInsert extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentNewsInsert</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentNewsInsert at " + request.getContextPath () + "</h1>");
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
        PrintWriter out = response.getWriter();
        CommentNewsDAO cmd = new CommentNewsDAO();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        String newId = request.getParameter("newId");
        int idNew = Integer.parseInt(newId);
        
        String notification = "";
        if(name.equals(" ") || name.isEmpty()){
            notification = "Tên vui lòng không được bỏ trống!";
            out.print(notification);
        }else if (email.equals("") || email.isEmpty() || !isValidEmail(email)){
            notification = "Email không hợp lệ!";
            out.print(notification);
        }else{
            CommentNews newCommment = new CommentNews(name, email, message,idNew);
            cmd.insertCommentNews(newCommment);
            notification = "Gửi bình luận thành công! Quản trị viên của chúng tôi sẽ gửi thống báo cho bạn qua email!";
            out.print(notification);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
