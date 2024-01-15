package controller.customer;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name = "NewcomerServlet", urlPatterns = {"/NewcomerServlet"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class NewcomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String email    = (String) session.getAttribute("username");
        String username = request.getParameter("username");
        String realname = request.getParameter("realname");
        String phone    = request.getParameter("phone");
        String address  = request.getParameter("address");

        InputStream avatar = null;
        //System.out.println("GET:" + email + username+realname+phone+address);
        try {
            Part filePart = request.getPart("avatar"); //Get avatar pic

            if (filePart != null) {
                avatar = filePart.getInputStream();
            }else{
                request.setAttribute("errorMessage", "Invalid Picture!");
                request.getRequestDispatcher("customer/Newcomer.jsp").forward(request, response);
            }
            
            model.customer.MyProfile profile = new model.customer.MyProfile(email, username, realname, phone, address, avatar);
            profile.updateProfile();
            
            //request.getRequestDispatcher("WEB-INF/customer/CustomerDashboard.jsp").forward(request, response);
            response.sendRedirect("CustomerDashboardServlet"); //Prompt another Servlet to load data
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (avatar != null) {
                avatar.close();
            }
        }
    }
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
