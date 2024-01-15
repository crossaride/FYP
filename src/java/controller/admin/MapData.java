package controller.admin;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Get Address from Model
@WebServlet(name = "MapData", urlPatterns = {"/MapData"})
public class MapData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the PrintWriter object to write the JSON response
        PrintWriter out = response.getWriter();
        model.customer.MyOrders orders = new model.customer.MyOrders();
        orders.LoadDataAddress();
        
        ArrayList<String> sample = new ArrayList<>();
        sample = orders.getAddresses();
        String[] addresses = new String[sample.size()];
        
        for(int x=0; x<sample.size(); x++){
            addresses[x] = sample.get(x);
        }
        
        //String address1 = "17 & 19, Jalan Selatan 3\n" + "TMN PERINDUSTRIAN RINGAN PULAI\n" + "81300 Skudai\n" + "Johor, Malaysia";
        //String address2 = "Paradigm Mall, Johor";
        //String address3 = "27, Persiaran Skudai 8\n" + "Taman Perusahaan\n" + "81300 Skudai\n" + "Johor, Malaysia";

        //String[] addresses = {address1, address2, address3};
        String json = new Gson().toJson(addresses);
        response.setContentType("application/json");
        out.print(json);
        out.flush();   
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
