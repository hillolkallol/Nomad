/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database.*;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author suraj
 */
@WebServlet(name = "TripController", urlPatterns = {"/trip"})
public class TripController extends HttpServlet {

 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String confirmation = request.getParameter("confirmation");
        if(confirmation.equalsIgnoreCase("accept")){
            int rider_id = Integer.parseInt(request.getParameter("rider_id"));
            int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
            int driver_id = Integer.parseInt(request.getParameter("driver_id"));
            try{
                TripTable tb = new TripTable();
                tb.insertTrip(rider_id, schedule_id, driver_id);
            }
            catch(Exception e){
                
            }      
        }
        else{
            //do nothing
        }
        
        HttpSession ses = request.getSession(false);
        User u = new User();
        u = (User) ses.getAttribute("user");
        String email_id = u.getEmail_id();
        RequestEmail em = new RequestEmail();
        try {
            em.removeHash(email_id);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        response.sendRedirect("dashboard");
//        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
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
