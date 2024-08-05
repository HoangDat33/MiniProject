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

public class ShowRevenueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int year = Integer.parseInt(request.getParameter("year"));
        StatisticDAO s = new StatisticDAO();
        JSONArray revenueData = new JSONArray();

        try {
            for (int month = 1; month <= 12; month++) {
                JSONObject monthData = new JSONObject();
                monthData.put("month", month);
                monthData.put("revenue", s.getRevenueMonthly(year, month));
                revenueData.put(monthData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.print(revenueData.toString());
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
