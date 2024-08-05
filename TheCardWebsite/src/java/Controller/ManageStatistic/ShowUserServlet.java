package Controller.ManageStatistic;

import DAL.StatisticDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShowUserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowUserServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowUserServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONArray userData = new JSONArray();

        int year = Integer.parseInt(request.getParameter("year"));
        
        StatisticDAO s = new StatisticDAO();

        try {
            for (int month = 1; month <= 12; month++) {
                JSONObject monthData = new JSONObject();
                monthData.put("month", month);
                monthData.put("user", s.getUserMonthly(month,year));
                userData.put(monthData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print(userData.toString());
        out.flush();
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