/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database.RiderScheduleTable;
import Model.Database.ScheduleTable;
import Model.RiderSchedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "RiderScheduleController", urlPatterns = {"/RiderSchedule"})
public class RiderScheduleController extends HttpServlet {

    RiderSchedule schedule = new RiderSchedule();
    RiderScheduleTable rsTable = new RiderScheduleTable();
    
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
            
            request.getRequestDispatcher("rider_schedule.jsp").forward(request, response);
        }
        else{
            String schedule_id = request.getParameter("action");
            int sch_id = Integer.parseInt(schedule_id); 
            request.setAttribute("schID", sch_id);
            try {
                schedule = rsTable.getScheduleByID(sch_id);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
             request.setAttribute("RiderSchedule", schedule);
            //get Userschedule ma chai 
            //Select * from Scheule where User _id = 

            request.getRequestDispatcher("edit_rider_schedule.jsp").forward(request, response);
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
        HttpSession ses = request.getSession(false);
        int u_id = (Integer) ses.getAttribute("user_id");        
        
                RiderScheduleTable RdTable = new RiderScheduleTable();
        try {
            
            if(action.equalsIgnoreCase("insert")){
                System.out.println("sdadasd");
                RdTable.insertRiderSchedule(from, to, date, time, u_id);
                System.out.println("sdadasd");
//                response.sendRedirect("dashboard");
            }
              if(action.equalsIgnoreCase("delete")){
                  String id = request.getParameter("scheduleID");
                  int sch_id = Integer.parseInt(id);
                  RdTable.deleteRiderSchedule(sch_id);
//                scTable.insertDriverSchedule(from, to, date, time, seats_left, seats_total, u_id);
            }
               else if(action.equalsIgnoreCase("edit")){
                 int sch_id = Integer.parseInt(request.getParameter("scheduleID"));
                 RdTable.updateRiderSchedule(from, to, date, time, sch_id);
            }
//            else{
//               
//                RdTable.updateRiderSchedule(from, to, date, time, u_id);
//            }
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
