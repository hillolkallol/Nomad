/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Database.UserTable;

/**
 *
 * @author KD
 */
@WebServlet(name = "ForgetPassword", urlPatterns = {"/forget_password"})
public class ForgetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HttpSession ses = request.getSession(false);
        
        //JOptionPane.showMessageDialog(null, ses.getAttribute("user"));
//        UserTable usertable = new UserTable();
//        User userDetails = new User();
//        User u = (User) ses.getAttribute("user");
//        userDetails = usertable.userInfo(u.getUsername());
//        request.setAttribute("userDetails", userDetails);
        
        
        request.getRequestDispatcher("forget_password.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String email_or_username = request.getParameter("email_or_username");
        
        UserTable usertable = new UserTable();
        String s = "";
        s = usertable.passRecovery(email_or_username, request);
        request.setAttribute("pass_recovery", s);
        doGet(request, response);
    }
}
