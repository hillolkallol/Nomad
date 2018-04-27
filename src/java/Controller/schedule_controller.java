/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database.ScheduleTable;
import Model.*;
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
 * @author peshal
 */
@WebServlet(name = "schedule_controller", urlPatterns = {"/schedule"})
public class schedule_controller extends HttpServlet {
    ScheduleTable scTable = new ScheduleTable();
    Schedule schedule = new Schedule();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

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
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("new")){
            request.getRequestDispatcher("driver_schedule.jsp").forward(request, response);
        }
        else{
            String schedule_id = request.getParameter("action");
            int sch_id = Integer.parseInt(schedule_id); 
            try {
                schedule = scTable.getScheduleByID(sch_id);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
             request.setAttribute("schedule", schedule);
            //get Userschedule ma chai 
            //Select * from Scheule where User _id = 

            request.getRequestDispatcher("edit_driver_schedule.jsp").forward(request, response);
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

        String action = request.getParameter("post_action");
        System.out.print(action);

        
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String seats_left = request.getParameter("seats_left");
        String seats_total = request.getParameter("total_seats");
        HttpSession ses = request.getSession(false);
        int u_id = (Integer) ses.getAttribute("user_id");        
        
//        System.out.println(from + to+ date +  time+ seats_left+ seats_total+ u_id+ action);
        ScheduleTable scTable = new ScheduleTable();
        try {
            
            if(action.equalsIgnoreCase("insert")){
                scTable.insertDriverSchedule(from, to, date, time, seats_left, seats_total, u_id);
            }
              if(action.equalsIgnoreCase("delete")){
                  String id = request.getParameter("scheduleID");
                  int sch_id = Integer.parseInt(id);
                  scTable.deleteDriverSchedule(sch_id);
//                scTable.insertDriverSchedule(from, to, date, time, seats_left, seats_total, u_id);
            }
              else if(action.equalsIgnoreCase("edit")){
                 int sch_id = Integer.parseInt(request.getParameter("scheduleID"));
                scTable.updateDriverSchedule(from, to, date, time, seats_left, seats_total, sch_id);
            }
            if (ses != null) {
                if(ses.getAttribute("user") != null){
                    System.out.println("not null");
                    //request.getRequestDispatcher(page).forward(request, response);
                    response.sendRedirect("dashboard");
                }
                else {
                    //out.println("null");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    //response.sendRedirect("login?request="+request+"&response="+response);
                }
            } else {
                System.out.println("session null");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
            
//        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println(ex);
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
