/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.User;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author KD
 */
public class UserTable {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public boolean login(User user){
        con = MySQLConnection.connect();
        String sql = "SELECT email_id FROM User WHERE email_id=? AND password=?";
        boolean bool = false;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getEmail_id());
            pst.setString(2, user.getPassword());
            
            rs = pst.executeQuery();
            if (rs.next()) {
                String email_id = rs.getString("email_id");
                if (email_id.equals(user.getEmail_id())) {
                    bool = true;
                }
                else {
                    bool = false;
                }
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }
    
    public String passRecovery(String email_or_username, HttpServletRequest request) throws AddressException, MessagingException, MalformedURLException {
        String msg = "";
        
        String sql = "SELECT email_id FROM User WHERE email_id=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email_or_username);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                String email_address = rs.getString("email_id");
                if(email_address != null){
                    String to = email_address;
                    String from = "care@nomad.com";
                    String host = "localhost";
                    String subject = "Passowrd Recovery | Survey";
                    
                    SecureRandom random = new SecureRandom();
                    String auto_id = new BigInteger(130, random).toString(32);
                    URL url = new URL(request.getRequestURL().toString());
                    String link = "http://" + url.getAuthority() + "/SurveyWebProgramming/recovery?email="+email_address+"&id="+auto_id+"";
                    
                    String body = "Click the link below to reset your passwrod.."
                            + "<form method=\"post\" action=\"recovery\">"
                            + "<input type=\"text\" name=\"email\" value="+email_address+" hidden>"
                            + "<input type=\"text\" name=\"id\" value="+auto_id+" hidden>"
                            + "<input type=\"submit\" value=\"Click Here\">"
                            + "</form> ";
                    
                    Properties properties = System.getProperties();
                    properties.setProperty("mail.smtp.host", host);
                    Session mailSession = Session.getDefaultInstance(properties);
                    msg = "A link has been sent to your email! Check this out! </br>" + body;

                    try{
                       MimeMessage message = new MimeMessage(mailSession);
                       message.setFrom(new InternetAddress(from));
                       message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                       message.setSubject(subject);
                       message.setContent(body,"text/html");
                       RecoveryTempInfoTable recoveryTempObj = new RecoveryTempInfoTable();
                       recoveryTempObj.InsertRecoveryTemp(email_address, auto_id);
                       Transport.send(message);
                       msg = "A link has been sent to your email! Check this out!\n\n" + body;
                    }catch (MessagingException mex) {
                       //msg = "Error: unable to send message....";
                    }
                }
            }
            else{
                msg = "Username/Email not found!";
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msg;
    }
    
       
    public int getID(User user){
        con = MySQLConnection.connect();
        String sql = "SELECT user_id FROM User WHERE email_id=?";
        int id=0 ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getEmail_id());
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("user_id");
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
    

