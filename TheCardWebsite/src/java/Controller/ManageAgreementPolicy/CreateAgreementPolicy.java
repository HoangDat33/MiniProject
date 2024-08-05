package Controller.ManageAgreementPolicy;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "CreateAgreementPolicy", urlPatterns = {"/createagreementpolicy"})
public class CreateAgreementPolicy extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        try (PrintWriter out = response.getWriter()) {
            out.print("<div class='container form-container'>"
                    + "<form action='/TheCardWebsite/successcreate' method='post'>"
                    + "  <div class='form-group'>"
                    + "    <label for='username'>Tên</label>"
                    + "    <input type='text' class='form-control' id='name' name='name' value=''>"
                    + "  </div>"
                    + "  <div class='form-group'>"
                    + "    <label for='message'>Nội dung</label>"
                    + "    <input type='text' class='form-control' id='message' name='message' value=''>"
                    + "  </div>"
                    + "  <div class='form-group'>"
                    + "    <select id='myComboBox' name='selectedOption'>"
                    + "    <option value='option1' selected hidden>Đang hoạt động</option>"
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
