/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.managemember;

import Controller.authentication.BaseRequireAuthentication;
import DAL.UserDAO;
import Model.AccountLogin;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dat
 */
@WebServlet(name = "ValidEmail", urlPatterns = {"/validemail"})
public class ValidEmail extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Lấy các tham số từ yêu cầu
            String emailRegis = request.getParameter("e0");
            String email1 = request.getParameter("e1");
            String email2 = request.getParameter("e2");

            UserDAO udao = new UserDAO();

            // Lấy thông tin người dùng từ cơ sở dữ liệu
            User userregis = udao.getUserbyEmail2(emailRegis);
            User user1 = udao.getUserbyEmail2(email1);
            User user2 = udao.getUserbyEmail2(email2);

            if (userregis == null) {
                out.print("<p class='error-message'>Người dùng yêu cầu trở thành thành viên không tồn tại.</p>");
                return;
            }
            if (userregis == null) {
                out.print("<div class='highlight'>Người dùng yêu cầu trở thành thành viên không tồn tại.</div>");
                return;
            }

            if (user1 == null && user2 != null) {
                out.print("<p>Người dùng yêu cầu trở thành thành viên khỏi tạo tài khoản vào ngày <span class='created-at'>" + userregis.getCreatedAt()
                        + "</span>.</p>"
                        + "<div class='email-info'><p>Email 1 không được tìm thấy trên hệ thống.</p></div>"
                        + "<div class='email-info'><p>Email 2 của người dùng <span>" + user2.getFirstName() + " " + user2.getLastName()
                        + "</span> được khỏi tạo vào ngày <span class='created-at'>" + user2.getCreatedAt() + "</span>.</p></div></div>");
            } else if (user2 == null && user1 != null) {
                out.print("<p>Người dùng yêu cầu trở thành thành viên khỏi tạo tài khoản vào ngày <span class='created-at'>" + userregis.getCreatedAt()
                        + "</span>.</p>"
                        + "<div class='email-info'><p>Email 2 không được tìm thấy trên hệ thống.</p></div>"
                        + "<div class='email-info'><p>Email 1 của người dùng <span>" + user1.getFirstName() + " " + user1.getLastName()
                        + "</span> được khỏi tạo vào ngày <span class='created-at'>" + user1.getCreatedAt() + "</span>.</p></div></div>");
            } else if (user1 == null && user2 == null) {
                out.print("<div class='highlight'>Người dùng yêu cầu trở thành thành viên khỏi tạo tài khoản vào ngày "
                        + "<span class='created-at'>" + userregis.getCreatedAt() + "</span>. Cả Email 1 và Email 2 không được tìm thấy trên hệ thống!</div>");
            } else {
                out.print("<p>Người dùng yêu cầu trở thành thành viên khỏi tạo tài khoản vào ngày <span class='created-at'>" + userregis.getCreatedAt()
                        + "</span>.</p>"
                        + "<div class='email-info'><p>Email 1 của người dùng <span>" + user1.getFirstName() + " " + user1.getLastName()
                        + "</span> được khỏi tạo vào ngày <span class='created-at'>" + user1.getCreatedAt() + "</span>.</p></div>"
                        + "<div class='email-info'><p>Email 2 của người dùng <span>" + user2.getFirstName() + " " + user2.getLastName()
                        + "</span> được khỏi tạo vào ngày <span class='created-at'>" + user2.getCreatedAt() + "</span>.</p></div></div>");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi xử lý yêu cầu: " + e.getMessage());
        } finally {
            out.close(); // Đảm bảo đóng PrintWriter
        }
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
