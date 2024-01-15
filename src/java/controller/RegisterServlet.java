
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


@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
       
        try {     
            request.getRequestDispatcher("webpages/register.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR" + e);
        }
    } */


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String email     = request.getParameter("email");
        String password  = request.getParameter("password");
        String password2 = request.getParameter("password2");

        try {
            model.Register register = new model.Register(email, password, password2);
            
            register.validateEmail();
            register.verifyPass();
            
            if (register.getAuthenticator2() == true) {
                session.invalidate();
                request.setAttribute("errorMessage", "Email has been taken!");
                RequestDispatcher r = request.getRequestDispatcher("webpages/register.jsp");
                r.include(request, response);
            }
            else if (register.getAuthenticator() == false) {
                session.invalidate();
                request.setAttribute("errorMessage", "Password do not match!");
                RequestDispatcher r = request.getRequestDispatcher("webpages/register.jsp");
                r.include(request, response);
            }
            else if (register.getAuthenticator() == true && register.getAuthenticator2() == false){
                register.createAcc();
                response.sendRedirect("webpages/createAcc_success.jsp");
            }
  
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
