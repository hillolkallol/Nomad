/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;
import java.sql.*;
import java.util.*;
import java.sql.Connection;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import javax.websocket.Session;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.mail.Transport;
import javax.mail.internet.*;

/**
 *
 * @author suraj
 */
public class RequestEmail {
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private  Connection conn = MySQLConnection.connect();
    
    public void sendRequestEmail(int id, HttpServletRequest request) throws MalformedURLException, AddressException, MessagingException{
        //String to = email_address;
        //get the email address of the user from here
        String to = "suraz.acharya09@gmail.com";
        String from = "request@nomad.com";
        String host = "localhost";
        String subject = "Ride Requested";
        
//        SecureRandom random = new SecureRandom();
//        String auto_id = new BigInteger(130, random).toString(32);
//        URL url = new URL(request.getRequestURL().toString());
//        String link = "http://" + url.getAuthority() + "/SurveyWebProgramming/recovery?email=suraz.acharya09@gmail.com&id="+auto_id+"";
        
        String body = "You have been requested a ride.";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);  
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);   
       
        String msg = "A link has been sent to your email!Check this out! </br>" + body;
        try{
            System.out.println("before transport");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(body,"text/html");
            message.setText("Hello, this is example of sending email  "); 
            //RecoveryTempInfoTable recoveryTempObj = new RecoveryTempInfoTable();
            //recoveryTempObj.InsertRecoveryTemp("suraz.acharya09@gmail.com", auto_id);
            Transport.send(message);
            System.out.println("after transport");
            msg = "A link has been sent to your email! Check this out!\n\n" + body;
         }catch (MessagingException mex) {
            //msg = "Error: unable to send message....";
         }
        
    }
    
    public void sendRideInvitation(String email, int schedule_id, String hash) throws SQLException{
        String command = "Insert into Invitation (email_id, schedule_id , hash_value) values (?,?,?);";
        preparedStatement = conn.prepareStatement(command);
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, schedule_id);
        preparedStatement.setString(3,hash);
        preparedStatement.executeUpdate();
    }
    
    public boolean checkHash(String email, String hash) throws SQLException{
        String command = "Select email_id from Invitation where hash_value = ?";
        preparedStatement = conn.prepareStatement(command);
        preparedStatement.setString(1, hash);
        resultSet = preparedStatement.executeQuery();
        String em="";
        while(resultSet.next()){
             em = resultSet.getString("email_id");
        }
        if(em.equalsIgnoreCase(email)){
            return true;
        }
        else{
            return false;
        }      
    }
    
    public void removeHash(String email_id) throws SQLException{
        String command = "Delete from Invitation where email_id = ?";
        preparedStatement = conn.prepareStatement(command);
        preparedStatement.setString(1, email_id);
        preparedStatement.executeUpdate();
     
        
    }
}
