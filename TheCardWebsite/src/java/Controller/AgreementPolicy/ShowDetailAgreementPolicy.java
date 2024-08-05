package Controller.AgreementPolicy;

import DAL.AgreementPolicyDAO;
import Model.AgreementPolicy;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;


@WebServlet(name = "ShowDetailAgreementPolicy", urlPatterns = {"/showdetailagreementpolicy"})
public class ShowDetailAgreementPolicy extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String id = request.getParameter("id");
        
        AgreementPolicyDAO d = new AgreementPolicyDAO();
        
        AgreementPolicy a = d.getAgreementPolicyByID(id);
        String detail = a.getMessage();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("detail", detail);
        response.getWriter().write(jsonResponse.toString());
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
