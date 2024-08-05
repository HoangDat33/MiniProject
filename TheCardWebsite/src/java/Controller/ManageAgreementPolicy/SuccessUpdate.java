package Controller.ManageAgreementPolicy;

import DAL.AgreementPolicyDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "SuccessUpdate", urlPatterns = {"/successupdate"})
public class SuccessUpdate extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String message = request.getParameter("message");
        String status = request.getParameter("selectedOption");
        String statusrefer;
        if(status.equals("Đang hoạt động")){
            statusrefer = "Active";
        }else{
            statusrefer = "Inactive";
        }
        AgreementPolicyDAO adao = new AgreementPolicyDAO();
        adao.updateNameAgreementPolicy(id, username, statusrefer);
        adao.updateAgreementPolicy(id, message);
        
        response.sendRedirect("displayagreementpolicy");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
