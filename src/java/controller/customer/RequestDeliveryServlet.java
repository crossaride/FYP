/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.commons.codec.binary.Base64;

@WebServlet(name = "RequestDeliveryServlet", urlPatterns = {"/RequestDeliveryServlet"})
public class RequestDeliveryServlet extends HttpServlet {

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

            String realName = myProfile.getRealName();
            String phone = myProfile.getPhone();
            String address = myProfile.getAddress();
            double balance = myProfile.getBalance();
            // System.out.println(realName+phone+address+balance);
            session.setAttribute("myName", realName);
            session.setAttribute("myPhone", phone);
            session.setAttribute("myAddress", address);
            session.setAttribute("myWallet", balance);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/RequestDelivery.jsp");
            dispatcher.forward(request, response);
            //request.getRequestDispatcher("WEB-INF/customer/RequestDelivery.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("ERROR" + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
            String jsonData = request.getParameter("requestformData");

            //Json Parser
            //Download at: https://repo1.maven.org/maven2/com/fasterxml/jackson/core/
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            String email = (String) session.getAttribute("username");

            String rname = jsonNode.get("rname").asText();
            String rphone = jsonNode.get("rphone").asText();
            String raddress = jsonNode.get("raddress").asText();
            String rtown = jsonNode.get("rtown").asText();
            String name = jsonNode.get("name").asText();
            String phone = jsonNode.get("phone").asText();
            String address = jsonNode.get("address").asText();
            String ptype = jsonNode.get("ptype").asText();
            String pweight = jsonNode.get("pweight").asText();
            int pquantity = Integer.parseInt(jsonNode.get("pquantity").asText());
            boolean express = Boolean.parseBoolean(jsonNode.get("express").asText());
            boolean fragile = Boolean.parseBoolean(jsonNode.get("fragile").asText());
            boolean exPackage = Boolean.parseBoolean(jsonNode.get("expackage").asText());
            String offer = jsonNode.get("offer").asText();
            String photo = jsonNode.get("dataURL").asText();
            double totalPrice = Double.parseDouble(jsonNode.get("amount").asText());
            
            //convert Base64 String (photo) into Blob
            byte[] decodedByte = Base64.decodeBase64(photo);
            Blob newPhoto = new SerialBlob(decodedByte);

            model.customer.MyOrders ord = new  model.customer.MyOrders(rname, rphone, raddress, rtown, email, ptype, pweight, pquantity, express, fragile, exPackage, newPhoto, offer ,totalPrice);
            ord.createOrder();
            //response.sendRedirect("CompletedServlet");
            //out.println(rname + rphone + raddress + rtown + name + phone + address + ptype + pweight + pquantity + express + fragile + exPackage + offer + photo + totalPrice);
            //System.out.println(rname + rphone + raddress + rtown + name + phone + address + ptype + pweight + pquantity + express + fragile + exPackage + offer + photo + totalPrice);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/Payment_Success.jsp");
            dispatcher.forward(request, response);
            //RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/customer/RequestDelivery.jsp");
            //dispatcher.forward(request, response);
            //request.getRequestDispatcher("WEB-INF/customer/RequestDelivery.jsp").forward(request, response);
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
