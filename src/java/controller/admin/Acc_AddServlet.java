
package controller.admin;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Acc_AddServlet", urlPatterns = {"/Acc_AddServlet"})
public class Acc_AddServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String jsonData = request.getParameter("addData");
         
        //Json Parser
        //Download at: https://repo1.maven.org/maven2/com/fasterxml/jackson/core/
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON string into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            // Access individual elements in the JsonNode
            String email    = jsonNode.get("email").asText();
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();
            String accType  = jsonNode.get("accType").asText();
            
            model.admin.AccountManagement acc = new model.admin.AccountManagement(email,username,password,accType);
            acc.addAcc(); 
            
            response.getWriter().write("Data received and processed successfully");
            
        } catch (Exception e) {
            // Handle parsing or processing errors
            response.getWriter().write("Error processing JSON data");
            e.printStackTrace(); // Log the exception for debugging
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
