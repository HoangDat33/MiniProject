package Controller.ManageAgreementPolicy;

import DAL.AgreementPolicyDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteAgreementPolicy", urlPatterns = {"/deleteagreementpolicy"})
public class DeleteAgreementPolicy extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id  = request.getParameter("id");
        AgreementPolicyDAO adao = new AgreementPolicyDAO();
        adao.deleteAgreementPolicy(id);
        adao.deleteNameAgreementPolicy(id);
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
