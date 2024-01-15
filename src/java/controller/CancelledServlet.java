/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CancelledServlet", urlPatterns = {"/CancelledServlet"})
public class CancelledServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            request.getRequestDispatcher("WEB-INF/customer/Payment_Failed.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
