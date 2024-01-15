
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        //session.invalidate();
        
        //response.sendRedirect("webpages/homepage.jsp");
        //out.close();
        
        String username = (String)session.getAttribute("username");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if (session.getAttribute("token") == null) {
            session.removeAttribute(username);
            response.sendRedirect(request.getContextPath() + "/webpages/homepage.jsp");
            out.close();
        } 
    }
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        if (session.getAttribute("token") == null && username == null) {
            //session.removeAttribute(username);
            response.sendRedirect(request.getContextPath() + "/webpages/homepage.jsp");
        } 
        
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig)
            throws ServletException {
        // We can initialize a filter using the init-params here
        // (which we defined in the deployment descriptor - web.xml)
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
