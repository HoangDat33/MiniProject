/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.managemember;

import Controller.authentication.BaseRequireAuthentication;
import DAL.RegisterMemberDAO;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "AccessMember", urlPatterns = {"/accessmember"})
public class AccessMember extends BaseRequireAuthentication {

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        String rmId = (String) req.getParameter("rmId");
        String mID = req.getParameter("mId");
        if (rmId != null) {
            int smid = Integer.parseInt(rmId);
            RegisterMemberDAO rgmdao = new RegisterMemberDAO();
            MemberRegis memberRegis = rgmdao.getMemberRegist(smid);
            req.setAttribute("memberRegis", memberRegis);
            req.getRequestDispatcher("regismeminfo.jsp").forward(req, resp);
        }else if(mID != null){
            int imID = Integer.parseInt(mID);
            RegisterMemberDAO rgmdao = new RegisterMemberDAO();
            MemberRegis memberRegis = rgmdao.getMemberRegistByUID(imID);
            req.setAttribute("memberRegis", memberRegis);
            req.getRequestDispatcher("regismeminfo.jsp").forward(req, resp);
        }
        else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID null");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        String msg = (String) sess.getAttribute("msgresult");
        sess.removeAttribute("msgresult");

        RegisterMemberDAO rgmdao = new RegisterMemberDAO();
        List<MemberRegis> memberRegisList = rgmdao.getListRegisMem();
        Collections.reverse(memberRegisList);
        req.setAttribute("msg", msg);
        req.setAttribute("listMemRegis", memberRegisList);
        req.getRequestDispatcher("regismemberadmin.jsp").forward(req, resp);
    }

}
