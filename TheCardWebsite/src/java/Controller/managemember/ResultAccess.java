package Controller.managemember;

import Controller.authentication.BaseRequireAuthentication;
import DAL.EmailDAO;

import DAL.RegisterMemberDAO;
import DAL.UserDAO;
import Model.AccountLogin;
import Model.MemberRegis;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="ResultAccess", urlPatterns={"/resultaccess"})
public class ResultAccess extends BaseRequireAuthentication {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        String choosed = req.getParameter("choosed");
        String idform = req.getParameter("idform");
        int formMemID = Integer.parseInt(idform);
        RegisterMemberDAO rgmdao = new RegisterMemberDAO();
        MemberRegis memberRegis = rgmdao.getMemberRegist(formMemID);
        UserDAO udao = new UserDAO();
                
        if(choosed.equalsIgnoreCase("reject")){
            String msgresult = "Đã từ chối đơn đăng ký!";
            EmailDAO.sendEmail(memberRegis.getUser().getEmail(), "Thông báo đăng ký thành viên", "Đơn đăng ký của bạn đã bị từ chối!");
            HttpSession sess = req.getSession();
            sess.setAttribute("msgresult", msgresult);
            resp.sendRedirect("accessmember");
        }else{
            EmailDAO.sendEmail(memberRegis.getUser().getEmail(), "Thông báo đăng ký thành viên", "Đơn đăng ký của bạn đã được chấp nhận!");
            rgmdao.updateStatus(idform);
            udao.setMember(memberRegis.getUser().getID());
            String msgresult = "Đã chấp nhận đơn đăng ký!";
            HttpSession sess = req.getSession();
            sess.setAttribute("msgresult", msgresult);
            resp.sendRedirect("accessmember");
        }
    }

}