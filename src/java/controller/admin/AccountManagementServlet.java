package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//Redirect from dashboard to Account Management page
@WebServlet(name = "AccountManagementServlet", urlPatterns = {"/AccountManagementServlet"})
public class AccountManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            request.getRequestDispatcher("WEB-INF/admin/AdminAccount.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Append data to Table
        String temp = request.getParameter("tableName");
        
        request.setAttribute("tableName", temp);
        request.getRequestDispatcher("WEB-INF/admin/AccountManagement.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
