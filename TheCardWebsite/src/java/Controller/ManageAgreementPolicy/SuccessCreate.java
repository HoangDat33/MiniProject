package Controller.ManageAgreementPolicy;

import DAL.AgreementPolicyDAO;
import Model.AgreementPolicy;
import Model.NameAgreementPolicy;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "SuccessCreate", urlPatterns = {"/successcreate"})
public class SuccessCreate extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name").trim();
        String message = request.getParameter("message").trim();
        String status = request.getParameter("selectedOption");
        String statusrefer;
        if(status.equals("Đang hoạt động")){
            statusrefer = "Active";
        }else{
            statusrefer = "Inactive";
        }
        
        if(name.equals("") || name.isEmpty() || message.equals("") || message.isEmpty()){
            request.setAttribute("message", "Điền đầy đủ thông tin");
            request.getRequestDispatcher("displayagreementpolicy").forward(request, response);
        }
        
        AgreementPolicyDAO adao = new AgreementPolicyDAO();
        List<NameAgreementPolicy> list = adao.showAgreementPolicy();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(name)){
                request.setAttribute("message", "Tên trùng lặp");
                request.getRequestDispatcher("displayagreementpolicy").forward(request, response);
            }
        }
        
        adao.createNameAgreementPolicy(name, statusrefer);
        NameAgreementPolicy nap = adao.getAgreementPolicyByName(name);
        adao.createAgreementPolicy(nap.getId(), message);
        
        request.setAttribute("message", "Thêm mới thành công");
        request.getRequestDispatcher("displayagreementpolicy").forward(request, response);
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
    }// </editor-fold>

}
