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
import javax.servlet.http.HttpSession;

/**
 *
 * @author KD
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                request.getRequestDispatcher("login.jsp").forward(request, response);
                //response.sendRedirect("login?request="+request+"&response="+response);
            }
        } else {
            System.out.println("session null");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
