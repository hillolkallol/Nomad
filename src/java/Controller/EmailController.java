/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Database.*;
import Model.User;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author suraj
 */
@WebServlet(name = "EmailController", urlPatterns = {"/email"})
public class EmailController extends HttpServlet {

    ScheduleTable tbl = new ScheduleTable();
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
        HttpSession ses = request.getSession(false);
        User u = new User();
        u = (User) ses.getAttribute("user");
        String id = request.getParameter("schedule_id");
        String email = request.getParameter("email");
        int driver_id = u.getUserID();
        int rider_id = Integer.parseInt(request.getParameter("rider_id"));
        String hash = request.getParameter("hash");
        
        request.setAttribute("rider_id", rider_id);
        request.setAttribute("schedule_id",id);
        request.setAttribute("driver_id", driver_id);
        
        if(!email.equalsIgnoreCase(u.getEmail_id())){
           response.sendRedirect("logout"); 
        }
        else if(email.equalsIgnoreCase(u.getEmail_id())){
            RequestEmail em = new RequestEmail();
            boolean t = false;
            try {
                t = em.checkHash(email,hash);
             }
            catch (SQLException e){
                System.out.println(e);
            }
            System.out.println(t);
            if(t){
              request.getRequestDispatcher("rideAccept.jsp").forward(request, response);  
            }
            else{
                response.sendRedirect("logout"); 
            }
        }
        
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
        int id = Integer.parseInt(request.getParameter("requestID"));
        //get the user whose email is this
        HttpSession ses = request.getSession(false);
        User u = new User();
        u = (User) ses.getAttribute("user");
        int rider_id = u.getUserID();
        String email = "";
        try{
        email = tbl.getScheduleOwnerInfo(id);
        }catch(Exception e){
            System.out.println(e);
        }
        RequestEmail e = new RequestEmail();
//        String hash = "";
        String email_url = "";
        SecureRandom random = new SecureRandom();
        String hash = new BigInteger(130, random).toString(32);
        URL url;
        try{ 
            
            url = new URL(request.getRequestURL().toString());
            email_url = url + "?email=" + email + "&schedule_id="+ id + "&rider_id="+rider_id + "&hash="+hash;
            System.out.println(email_url);
            e.sendRideInvitation(email, id, hash);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        
        response.sendRedirect("dashboard");
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
