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
import javax.swing.JOptionPane;

/**
 *
 * @author KD
 */
@WebServlet(name = "Registration", urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out = response.getWriter();
        request.setAttribute("login_error_msg", "");
        HttpSession ses = request.getSession(false);
        if (ses != null) {
            if(ses.getAttribute("user") != null){
                System.out.println("not null");
                //request.getRequestDispatcher(page).forward(request, response);
                response.sendRedirect("dashboard");
            }
            else {
                //out.println("null");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                //response.sendRedirect("login?request="+request+"&response="+response);
            }
        } else {
            System.out.println("session null");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String FNAME = request.getParameter("FNAME");
        String LNAME = request.getParameter("LNAME");
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String ADDRESS = request.getParameter("ADDRESS");
        String gender = request.getParameter("gender");
        
        String group1 = request.getParameter("group1");
        
        String LNO = request.getParameter("LNO");
        String INO = request.getParameter("INO");
        String ICOM = request.getParameter("ICOM");
        
        User user = new User();
        user.setFirstName(FNAME);
        user.setLastName(LNAME);
        user.setEmail_id(email);
        user.setPassword(password);
        user.setAddress(ADDRESS);
        user.setGender(gender);
        
//        JOptionPane.showMessageDialog(null, FNAME);
//        JOptionPane.showMessageDialog(null, user.getFirstName());
        
        UserTable usertable = new UserTable();
        usertable.register_as_rider(user);
        
//        JOptionPane.showMessageDialog(null, b);
//        JOptionPane.showMessageDialog(null, group1);
        if(group1.equals("abc")){
//            JOptionPane.showMessageDialog(null, "inside");
            int id = usertable.getID(user);
            User user2 = new User();
            user2.setUserID(id);
//            JOptionPane.showMessageDialog(null, user2.getUserID());
            user2.setLNo(Integer.parseInt(LNO));
            user2.setINo(Integer.parseInt(INO));
            user2.setICom(ICOM);
            usertable.register_as_driver(user2);
        }
//        JOptionPane.showMessageDialog(null, b);
        
        response.sendRedirect("login");

    }

}
