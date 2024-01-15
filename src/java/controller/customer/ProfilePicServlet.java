package controller.customer;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProfilePicServlet", urlPatterns = {"/ProfilePicServlet"})
public class ProfilePicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            String email = (String) session.getAttribute("username");
            model.customer.MyProfile myProfile = new model.customer.MyProfile(email);
            myProfile.getAccID();
            myProfile.SearchDetail();

            InputStream avatar = myProfile.getProfilePic();

            response.setContentType("image/png");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = avatar.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            avatar.close();

        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("ERROR" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
