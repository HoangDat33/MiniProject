package Controller.AgreementPolicy;

import DAL.AgreementPolicyDAO;
import Model.NameAgreementPolicy;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "ShowAgreementPolicy", urlPatterns = {"/showagreementpolicy"})
public class ShowAgreementPolicy extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        AgreementPolicyDAO d = new AgreementPolicyDAO();
        List<NameAgreementPolicy> list = d.showAgreementPolicy();
        request.setAttribute("list", list);
        request.getRequestDispatcher("agreementpolicy.jsp").forward(request, response);
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
