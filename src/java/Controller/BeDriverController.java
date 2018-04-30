/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database.UserTable;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KD
 */
@WebServlet(name = "BeDriverController", urlPatterns = {"/beDriver"})
public class BeDriverController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(false);
        
        //JOptionPane.showMessageDialog(null, ses.getAttribute("user"));
        UserTable usertable = new UserTable();
        User userDetails = new User();
        User u = (User) ses.getAttribute("user");
        userDetails = usertable.driverInfo(u.getUserID());
        
        request.setAttribute("userDetails", userDetails);
        request.setAttribute("user", u);
        
        if(ses != null){
            request.getRequestDispatcher("beDriver.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("login");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String LNO = request.getParameter("LNO");
        String INO = request.getParameter("INO");
        String ICOM = request.getParameter("ICOM");
        
        UserTable usertable = new UserTable();
        HttpSession ses = request.getSession(false);
        User u = (User) ses.getAttribute("user");
        User user2 = new User();
        user2.setUserID(u.getUserID());
//            JOptionPane.showMessageDialog(null, user2.getUserID());
        user2.setLNo(Integer.parseInt(LNO));
        user2.setINo(Integer.parseInt(INO));
        user2.setICom(ICOM);
        usertable.register_as_driver(user2);
//        JOptionPane.showMessageDialog(null, b);
        
        response.sendRedirect("dashboard");
    }
}
