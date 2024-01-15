
package controller.customer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CustomerDashboardServlet", urlPatterns = {"/CustomerDashboardServlet"})
public class CustomerDashboardServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            String email = (String) session.getAttribute("username");
            model.customer.MyProfile myProfile = new model.customer.MyProfile(email);
            myProfile.getAccID();
            myProfile.SearchDetail();
            
            String realName    = myProfile.getRealName();
            String phone       = myProfile.getPhone();
            String address     = myProfile.getAddress();
            double balance     = myProfile.getBalance();
            String userName    = myProfile.getUsername();
            //InputStream avatar = myProfile.getProfilePic();
            
            //System.out.println(realName+phone+address+balance);
            session.setAttribute("myName", realName);
            session.setAttribute("myPhone", phone);
            session.setAttribute("myAddress", address);
            session.setAttribute("myWallet", balance);
            session.setAttribute("myUserName", userName);
            
            
            //session.setAttribute("myAvatar", buffer);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/CustomerDashboard.jsp");
            dispatcher.forward(request, response);
            //request.getRequestDispatcher("WEB-INF/customer/RequestDelivery.jsp").forward(request, response);
        
            //request.getRequestDispatcher("WEB-INF/customer/CustomerDashboard.jsp").forward(request, response);
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
