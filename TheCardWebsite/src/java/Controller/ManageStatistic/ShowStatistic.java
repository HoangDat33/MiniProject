
package Controller.ManageStatistic;

import Controller.authentication.BaseRequireAuthentication;
import DAL.StatisticDAO;
import Model.AccountLogin;
import Model.Brand;
import Model.Comment;
import Model.ProductCard;
import Model.ProductCategories;
import Model.Report;
import Model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowStatistic extends BaseRequireAuthentication {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        StatisticDAO s = new StatisticDAO();
        List<Brand> listbrand = s.getListBrand();
        int countbrand = listbrand.size();
        List<ProductCategories> listcate = s.getListProductCategory();
        int countcate = listcate.size();
        List<ProductCard> listcard = s.getProductCard();
        int countcard = listcard.size();
        List<ProductCard> listcarddelete = s.getProductCardDelete();
        int countcarddelete = listcarddelete.size();
        int countexit = countcard - countcarddelete;
        List<User> listuser = s.getUser();
        int countuser = listuser.size();
        List<Report> listreport = s.getListReport();
        int countreport = listreport.size();
        List<Comment> listfeedback = s.getListComments();
        int countfeedback = listfeedback.size();
        
        double revenue = s.getRevenueAnnually(2024);
        int user = s.getUserAnnually(2024);
        
        request.setAttribute("user_annual", user);
        request.setAttribute("revenue_annual", revenue);
        request.setAttribute("countbrand", countbrand);
        request.setAttribute("countcate", countcate);
        request.setAttribute("countcard", countcard);
        request.setAttribute("countcdelete", countcarddelete);
        request.setAttribute("countexit", countexit);
        request.setAttribute("countuser", countuser);
        request.setAttribute("countreport", countreport);
        request.setAttribute("countfeedback", countfeedback);
        
        request.getRequestDispatcher("managestatistic.jsp").forward(request, response);
        
        
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, AccountLogin account) throws ServletException, IOException {
        if(account == null ||  account.getUser().getRole().getID() != 1){
            resp.sendRedirect("login.jsp");
        }else{
            processRequest(req, resp);
        }
    }

}
