/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database.RequestEmail;
import Model.Database.RiderScheduleTable;
import Model.Database.ScheduleTable;
import Model.RiderSchedule;
import Model.Schedule;
import Model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author suraj
 */
public class DashboardController extends HttpServlet {
    List<Schedule> scheduleList;
    
    
    ScheduleTable scTable = new ScheduleTable();
    RiderScheduleTable rsd = new RiderScheduleTable();

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
            
            HttpSession ses = request.getSession(false);
            User u = new User();
            u = (User) ses.getAttribute("user");
            int u_id = (Integer) ses.getAttribute("user_id");
//            System.out.println(ses.getAttribute("user_id"));
            String from = request.getParameter("city");           
            scheduleList = scTable.getAllSchedule(u_id, from);
            
            request.setAttribute("isDriver", u.getIsDriver());
            request.setAttribute("scheduleList", scheduleList);
            
            if(u.getIsDriver()){
                System.out.println("sdsd");
                List<Schedule> userScheduleList;
                userScheduleList = scTable.getDriverSchedule(u_id);
               
                request.setAttribute("userScheduleList", userScheduleList);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            }
            else{
                System.out.println("rideer");
//                userScheduleList = scTable.getDriverSchedule(u_id);
//                request.setAttribute("userScheduleList", userScheduleList);
                List <RiderSchedule> riderScheduleList;
                riderScheduleList = rsd.getRiderSchedule(u_id);
                 for (int i = 0; i < riderScheduleList.size(); i++){
                    System.out.println(riderScheduleList.get(i));
                }
//                System.out.println(userScheduleList.size());
                
                request.setAttribute("riderScheduleList", riderScheduleList);
                request.getRequestDispatcher("userDashboard.jsp").forward(request, response);
            }
//            request.setAttribute("mySchedule")
            
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
