
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})

public class LoginServlet extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        model.Login login = new model.Login(username, password);
      
        try {
            login.validateLogin();
            
            if (login.getAuthenticator() == true) {
                session.setAttribute("username", username);

                if (login.getUserType().equals("admin")) {
                    request.getRequestDispatcher("WEB-INF/admin/AdminDashboard.jsp").forward(request, response);
                    
                }else if(login.getUserType().equals("customer")){

                    if(login.checkNewcomer()!= null){
                        response.sendRedirect("CustomerDashboardServlet"); //Prompt another Servlet to load data
                        //request.getRequestDispatcher("/CustomerDashboardServlet.java").forward(request, response);
                    }else{
                        //request.getRequestDispatcher("WEB-INF/customer/Newcomer.jsp").forward(request, response);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/Newcomer.jsp");
                        dispatcher.forward(request, response);
                    }

                }else if(login.getUserType().equals("employee")){
                    request.getRequestDispatcher("WEB-INF/employee/EmployeeDashboard.jsp").forward(request, response);
                    //response.sendRedirect("webpages/employee/EmployeeDashboard.jsp");
                }

            } else {
                session.invalidate();
                
                request.setAttribute("errorMessage", "Invalid username or password!");
                //RequestDispatcher r = request.getRequestDispatcher("webpages/login_error.jsp");
                //r.include(request, response);
                request.getRequestDispatcher("webpages/login.jsp").forward(request, response);
                //response.sendRedirect(request.getContextPath() + "/Login_ErrorServlet");
            }
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();     
            out.println("ERROR" + e);
        }
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
