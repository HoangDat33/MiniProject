
package Controller.Voucher;

import DAL.VoucherDAO;
import Model.Voucher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class GetVoucher extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetVoucher</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetVoucher at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        VoucherDAO vcDao = new VoucherDAO();
        List<Voucher> dataVoucher = vcDao.getListVoucher();
        if(dataVoucher != null && dataVoucher.size() != 0){
            request.setAttribute("dataVoucher", dataVoucher);
            request.getRequestDispatcher("voucher.jsp").forward(request, response);
        }else{
            request.setAttribute("notification", "Hiện tại hệ thống chữa có phiếu giảm giá, vui lòng quay trở lại sau!");
            request.getRequestDispatcher("voucher.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
