package controller.admin;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 *
 * @author kenne
 */
@WebServlet(name = "Acc_EditServlet", urlPatterns = {"/Acc_EditServlet"})
public class Acc_EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String jsonData = request.getParameter("editData"); 

        //Json Parser
        //Download at: https://repo1.maven.org/maven2/com/fasterxml/jackson/core/
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON string into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            // Access individual elements in the JsonNode
            String id       = jsonNode.get("id").asText();
            String email    = jsonNode.get("email").asText();
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();
            String accType  = jsonNode.get("accType").asText();
            
            System.out.println("\nid: " + id + "\nemail: " + email + "\nusername: "+ username +
                               "\npassword: " + password + "\naccType: " + accType);

            model.admin.AccountManagement acc = new model.admin.AccountManagement(Integer.parseInt(id),email,username,password,accType);
            acc.editAcc(); 
            
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
