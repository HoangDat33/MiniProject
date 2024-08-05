/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

@WebServlet(name = "ShowAgreementPolicyDetail", urlPatterns = {"/showagreementpolicydetail"})
public class ShowAgreementPolicyDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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

        String Id = request.getParameter("orderId");

        AgreementPolicyDAO adao = new AgreementPolicyDAO();

        AgreementPolicy a = adao.getAgreementPolicyByID(Id);
        NameAgreementPolicy n = adao.getNameAgreementPolicyByID(Id);

        try (PrintWriter out = response.getWriter()) {
            out.print("                            <tr>\n"
                    + "                                <td>" + n.getName() + "</td>\n"
                    + "                                <td>" + a.getMessage() + " VNĐ</td> \n"
                    + "                            </tr>\n");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
