/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.registermember;

import Controller.authentication.BaseRequireAuthentication;
import DAL.RegisterMemberDAO;
import DAL.UserDAO;
import Model.*;
import Model.MemberRegis;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "RegisterMember", urlPatterns = {"/registermember"})
public class RegisterMember extends BaseRequireAuthentication {

    @Override
    public String getServletInfo() {
        return "Short description";
    }

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
    resp.setContentType("application/json;charset=UTF-8");
    PrintWriter out = resp.getWriter();
    String email1 = req.getParameter("email1");
    String email2 = req.getParameter("email2");
    String msg = req.getParameter("msg");

    if(msg == ""){
        msg = "Tôi đồng ý với toàn bộ chính sách của trang web!";
    }
    boolean existEmail = checkExistsEmail(email1) || checkExistsEmail(email2);
    User thisUser = new User();
    RegisterMemberDAO rmbDao = new RegisterMemberDAO();

    if (existEmail) {
        out.write("{\"message\": \"Email bạn nhập đã được sử dụng để đăng ký thành viên!\"}");
    } else {
        thisUser = account.getUser();
        MemberRegis memberRegis = new MemberRegis(thisUser, email1, email2, msg);
        boolean success = rmbDao.insertRegisMember(memberRegis);
        if (success) {
            out.write("{\"message\": \"Đã gửi thành công, hãy chờ hệ thống xét duyệt!\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Đã xảy ra lỗi, vui lòng thử lại sau.\"}");
        }
    }
    out.flush();
    out.close();
}


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        req.getRequestDispatcher("registermember.jsp").forward(req, resp);
    }

    private boolean checkExistsEmail(String email) {
        boolean result = true;
        RegisterMemberDAO rgmdao = new RegisterMemberDAO();
        result = rgmdao.checkExistEmail(email);
        return result;
    }

}
