/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

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
}
