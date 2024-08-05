package Controller.ManageAgreementPolicy;

import DAL.AgreementPolicyDAO;
import Model.AgreementPolicy;
import Model.NameAgreementPolicy;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateAgreementPolicy", urlPatterns = {"/updateagreementpolicy"})
public class UpdateAgreementPolicy extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String ID = request.getParameter("userID");

        AgreementPolicyDAO d = new AgreementPolicyDAO();

        AgreementPolicy a = d.getAgreementPolicyByID(ID);
        NameAgreementPolicy n = d.getNameAgreementPolicyByID(ID);
        String status = "";
        if (n.getStatus().equals("Active")) {
            status += "Đang hoạt động";
        } else {
            status += "Không hoạt động";
        }

        try (PrintWriter out = response.getWriter()) {
            out.print("<div class='container form-container'>"
                    + "<form action='/TheCardWebsite/successupdate' method='post'>"
                    + "  <div class='form-group'>"
                    + "    <label for='id'>ID</label>"
                    + "    <input type='text' class='form-control' id='id' name='id' value='" + n.getId() + "' readonly>"
                    + "  </div>"
                    + "  <div class='form-group'>"
                    + "    <label for='username'>Tên</label>"
                    + "    <input type='text' class='form-control' id='username' name='username' value='" + n.getName() + "'>"
                    + "  </div>"
                    + "  <div class='form-group'>"
                    + "    <label for='message'>Nội dung</label>"
                    + "<textarea class='form-control' id='message' name='message'>" + a.getMessage() + "</textarea>"
                    + "  </div>"
                    + "  <div class='form-group'>"
                    + "    <select id='myComboBox' name='selectedOption'>"
                    + "    <option value='option1' selected hidden>" + status + "</option>"
                    + "    <option value='option2'>Đang hoạt động</option>"
                    + "    <option value='option3'>Dừng hoạt động</option>"
                    + "    </select>"
                    + "  </div>"
                    + "  <button type='submit' class='btn btn-primary'>Cập nhật</button>"
                    + "</form>"
                    + "</div");
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
