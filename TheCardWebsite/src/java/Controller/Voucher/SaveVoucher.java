
package Controller.Voucher;

import Controller.authentication.BaseRequireAuthentication;
import DAL.GetVoucherDAO;
import DAL.VoucherDAO;
import Model.AccountLogin;
import Model.GoogleLogin;
import Model.User;
import Model.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveVoucher extends BaseRequireAuthentication {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveVoucher</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveVoucher at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, AccountLogin account) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        GoogleLogin gglogin = (GoogleLogin) request.getSession().getAttribute("gguser");
        User user = new User();
        
        String voucherId = request.getParameter("idVoucher");
        int idVoucher = 0;
        if(voucherId != null){
            idVoucher = Integer.parseInt(voucherId);
        }
        if(account == null && gglogin == null){
            response.sendRedirect("login.jsp");
        }else if(account != null){
            user = account.getUser();
        }else if(gglogin != null){
            user = gglogin.getUser();
        }
        GetVoucherDAO getDao = new GetVoucherDAO();
        VoucherDAO voucherDao = new VoucherDAO();
        if(!getDao.checkExistVoucher(user.getID(), idVoucher)){
            out.print("Bạn đã lưu voucher này! Không thể lưu 2 voucher cùng loại!");
        }else{
            getDao.insertVoucher(idVoucher, user.getID());
            voucherDao.updateQuantityVoucher(idVoucher);
            out.print("Lưu vourcher thành công! Bạn có thể xem tại mục voucher!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
    }

}
