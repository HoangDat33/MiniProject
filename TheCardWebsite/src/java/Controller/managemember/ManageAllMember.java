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
import java.util.List;

@WebServlet(name="ManageAllMember", urlPatterns={"/manageallmember"})
public class ManageAllMember extends BaseRequireAuthentication {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        String mID = req.getParameter("mId");
        UserDAO udao = new UserDAO();
        udao.deleteMember(mID);
        List<User> members = udao.getListmember();
        String msg = "Xóa khỏi danh sách thành viên thành công!";
        req.setAttribute("msgCustom", msg);
        req.setAttribute("members", members);
        req.getRequestDispatcher("managemember.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        UserDAO udao = new UserDAO();
        List<User> members = udao.getListmember();
        req.setAttribute("members", members);
        req.getRequestDispatcher("managemember.jsp").forward(req, resp);
    }

}
