/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database.RequestEmail;
import Model.Database.ScheduleTable;
import Model.Schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author suraj
 */
public class DashboardController extends HttpServlet {
    List<Schedule> scheduleList;
    ScheduleTable scTable = new ScheduleTable();

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
        try {
            
            //all scheduleTable
            scheduleList = scTable.getAllSchedule();
            request.setAttribute("scheduleList", scheduleList);
//            request.setAttribute("mySchedule")
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException, MalformedURLException {
        int id = Integer.parseInt(request.getParameter("requestID"));
        Schedule s;
        try {
            s = scTable.getScheduleByID(id);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        //send to user whose schedule id is id;
        RequestEmail r = new RequestEmail();
        try {
            r.sendRequestEmail(id, request);
        } catch (MessagingException ex) {
           System.out.println(ex);
        }
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
       
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
