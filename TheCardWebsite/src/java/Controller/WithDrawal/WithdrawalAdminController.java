/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.WithDrawal;

import DAL.AccountLoginDAO;
import DAL.EmailDAO;
import DAL.GoogleLoginDAO;
import DAL.UserDAO;
import DAL.WithdrawalRequestDAO;
import Model.AccountLogin;
import Model.GoogleLogin;
import Model.Role;
import Model.User;
import Model.WithdrawalRequest;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "WithdrawalAdminController", urlPatterns = {"/withdrawal-admin"})
public class WithdrawalAdminController extends HttpServlet {

    private WithdrawalRequestDAO requestDAO = new WithdrawalRequestDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WithdrawalRequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WithdrawalRequestController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountLogin user = (AccountLogin) session.getAttribute("account");
        GoogleLogin gguser = (GoogleLogin) session.getAttribute("gguser");

        if (user == null && gguser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Role role = (user != null) ? user.getUser().getRole() : gguser.getUser().getRole();
        int role2 = role.getID();

        if (role2 == 2) {
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            default:
                List<WithdrawalRequest> requests = requestDAO.getAll();
                request.setAttribute("requests", requests);
                request.getRequestDispatcher("withdrawal_request_admin.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            WithdrawalRequest requestChange = requestDAO.getById(requestId);
            if (requestChange != null) {
                String action = request.getParameter("action");
                action = action != null ? action : "";
                switch (action) {
                    case "APPROVED":
                        requestDAO.updateStatus(requestChange, action);
                        EmailDAO.sendEmail(requestChange.getUser().getEmail(), "Phản hồi yêu cầu rút tiền | The Card Website",
                                "<div class=\"email-content\" style=\"padding: 20px;\">\n"
                                + "            <p>Xin chào, " + requestChange.getUser().getFirstName() + " " + requestChange.getUser().getLastName() + "</p>\n"
                                + "            <p>Yêu cầu rút tiền của bạn đã được xác nhận.</p>\n"
                                + "            <p>Xin cảm ơn!</p>\n"
                                + "        </div>");
                        break;
                    case "REJECTED":
                        requestDAO.updateStatus(requestChange, action);
                        EmailDAO.sendEmail(requestChange.getUser().getEmail(), "Phản hồi yêu cầu rút tiền | The Card Website",
                                "<div class=\"email-content\" style=\"padding: 20px;\">\n"
                                + "            <p>Xin chào, " + requestChange.getUser().getFirstName() + " " + requestChange.getUser().getLastName() + "</p>\n"
                                + "            <p>Yêu cầu rút tiền của bạn đã được bị từ chối.</p>\n"
                                + "            <p>Xin cảm ơn!</p>\n"
                                + "        </div>");
                        break;
                    default:
                        response.sendRedirect("withdrawal-admin");
                        return;
                }
                request.setAttribute("successMessage", "Thực hiện hành động thành công.");
                doGet(request, response);
            } else {
                request.setAttribute("errorMessage", "Yêu cầu không được tìm thấy.");
                doGet(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình xử lí.");
            doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
