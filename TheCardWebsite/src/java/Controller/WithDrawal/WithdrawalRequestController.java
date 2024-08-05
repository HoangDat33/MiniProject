
package Controller.WithDrawal;

import DAL.WithdrawalRequestDAO;
import Model.WithdrawalRequest;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import Model.*;
import DAL.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "WithdrawalRequestController", urlPatterns = {"/withdrawal-request"})
public class WithdrawalRequestController extends HttpServlet {

    private WithdrawalRequestDAO requestDAO = new WithdrawalRequestDAO();


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
        AccountLoginDAO ald = new AccountLoginDAO();
        GoogleLoginDAO gld = new GoogleLoginDAO();
        UserDAO userDao = new UserDAO();

        User user = null;
        AccountLogin account = null;
        GoogleLogin gglogin = null;

        HttpSession sess = request.getSession();
        if (request.getAttribute("user") != null) {
            user = (User) request.getAttribute("user");
            account = (AccountLogin) request.getAttribute("account");
        } else if (sess.getAttribute("account") != null) {
            account = (AccountLogin) sess.getAttribute("account");
            user = (User) userDao.getUserById(account.getUser().getID());
        } else if (sess.getAttribute("gguser") != null) {
            gglogin = (GoogleLogin) sess.getAttribute("gguser");
            user = (User) userDao.getUserById(gglogin.getUser().getID());
        } else {
            user = null;
            account = null;
        }
        if (user == null) {
            response.sendRedirect("login.jsp?error=Login");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "request":
                request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
                break;
            default:
                List<WithdrawalRequest> requests = requestDAO.getAllByUser(user.getID());
                request.setAttribute("requests", requests);
                request.getRequestDispatcher("withdrawal_request.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountLoginDAO ald = new AccountLoginDAO();
        GoogleLoginDAO gld = new GoogleLoginDAO();
        UserDAO userDao = new UserDAO();

        User user = null;
        AccountLogin account = null;
        GoogleLogin gglogin = null;

        HttpSession sess = request.getSession();
        if (request.getAttribute("user") != null) {
            user = (User) request.getAttribute("user");
            account = (AccountLogin) request.getAttribute("account");
        } else if (sess.getAttribute("account") != null) {
            account = (AccountLogin) sess.getAttribute("account");
            user = (User) userDao.getUserById(account.getUser().getID());
        } else if (sess.getAttribute("gguser") != null) {
            gglogin = (GoogleLogin) sess.getAttribute("gguser");
            user = (User) userDao.getUserById(gglogin.getUser().getID());
        } else {
            user = null;
            account = null;
        }
        if (user == null) {
            response.sendRedirect("login.jsp?error=Login");
            return;
        }
        int userId = user.getID();
        String accountNumber = null;
        String bankName = null;
        BigDecimal amount = BigDecimal.ZERO;
        String status = "PENDING";
        String desc = "";
        try {
            accountNumber = request.getParameter("account_number");
            bankName = request.getParameter("bank_name");
            amount = new BigDecimal(request.getParameter("amount"));
            desc = request.getParameter("transaction_details");

            if (userId <= 0 || accountNumber == null || accountNumber.isEmpty()
                    || bankName == null || bankName.isEmpty() || amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ.");
            }
            if (Double.parseDouble(amount + "") > user.getBalance()) {
                request.setAttribute("errorMessage", "Số tiền không đủ để thực hiện giao dịch.");
                request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
                return;
            }
            if (!accountNumber.matches("\\d+")) {
                request.setAttribute("errorMessage", "Số tài khoản chỉ được chứa các ký tự số.");
                request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Số tiền phải là một số hợp lệ.");
            request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
            return;
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
            return;
        }
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setUserId(userId);
        withdrawalRequest.setAccountNumber(accountNumber);
        withdrawalRequest.setBankName(bankName);
        withdrawalRequest.setAmount(amount);
        withdrawalRequest.setStatus(status);
        withdrawalRequest.setTransactionDetails(desc);
        try {
            requestDAO.create(withdrawalRequest);
            request.setAttribute("successMessage", "Yêu cầu rút tiền thành công.");
            request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi xử lý yêu cầu.");
            request.getRequestDispatcher("submit_withdrawal_request.jsp").forward(request, response);
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
