
package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Acc_LoadServlet", urlPatterns = {"/Acc_LoadServlet"})
public class Acc_LoadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the PrintWriter object to write the JSON response
        PrintWriter out = response.getWriter();

        //Get data Object
        model.admin.AccountManagement acc = new model.admin.AccountManagement();
        acc.showAcc();
        List<model.admin.AccountManagement> dataList = acc.getAccountList();

        // Create a  JSON string 
        StringBuilder jsonBuilder = new StringBuilder("[");

        for (model.admin.AccountManagement obj : dataList) {
            jsonBuilder.append("{");
            jsonBuilder.append("\"id\": ").append(obj.getID()).append(",");
            jsonBuilder.append("\"email\": \"").append(obj.getEmail()).append("\",");
            jsonBuilder.append("\"username\": \"").append(obj.getUsername()).append("\",");
            jsonBuilder.append("\"password\": \"").append(obj.getPassword()).append("\",");
            jsonBuilder.append("\"accType\": \"").append(obj.getAccType()).append("\"");
            jsonBuilder.append("},");

        }
        if (!dataList.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove the trailing comma
        }
        jsonBuilder.append("]");

        // Convert the data to JSON
        String jsonData = jsonBuilder.toString();

        request.getSession().setAttribute("json", jsonData);

        // Set the response type to JSON
        response.setContentType("application/json");

        // Write the JSON data to the response
        out.print(request.getSession().getAttribute("json"));

        // Close the PrintWriter
        out.close();
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
