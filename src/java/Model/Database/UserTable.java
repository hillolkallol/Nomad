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
import javax.swing.JOptionPane;

/**
 *
 * @author KD
 */
public class UserTable {
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private PreparedStatement pst2 = null;
    
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
                    String subject = "Passowrd Recovery | Nomad";
                    
                    SecureRandom random = new SecureRandom();
                    String auto_id = new BigInteger(130, random).toString(32);
                    URL url = new URL(request.getRequestURL().toString());
                    String link = "http://" + url.getAuthority() + "/Nomad/recovery?email="+email_address+"&id="+auto_id+"";
                    
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
    
    public String getName(User user){
        con = MySQLConnection.connect();
        String sql = "SELECT first_name FROM User WHERE email_id=?";
        String name ="" ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getEmail_id());
            rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString("first_name");
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public String resetPassword(User userDetails) {
        String message = "";
        String sql = "UPDATE User SET password=? WHERE email_id=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, userDetails.getPassword());
            pst.setString(2, userDetails.getEmail_id());
            //JOptionPane.showMessageDialog(null, userDetails.getPassword() + " " + userDetails.getEmail_address());
            
            if (pst.executeUpdate()!= 0) {
                message = "New Information has been updated!";
                RecoveryTempInfoTable recoveryTempInfoTable = new RecoveryTempInfoTable();
                recoveryTempInfoTable.deleteRaws(userDetails.getEmail_id());
            }
            else{
                message = "There was some problem in updating!";
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }

    public void register_as_rider(User user) {
//        JOptionPane.showMessageDialog(null, user.getFirstName());
//        boolean bool2 = false;
        con = MySQLConnection.connect();
        String sql = "INSERT INTO User(email_id, password, first_name, last_name, address, gender) VALUES (?,?,?,?,?,?)";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, user.getEmail_id());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setString(5, user.getAddress());
            pst.setString(6, user.getGender());
            
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con = MySQLConnection.connect();
        String sql2 = "INSERT INTO Rider(user_id) VALUES (?)";
        
        try {
            pst2 = con.prepareStatement(sql2);
            
            pst2.setInt(1, getID(user));
            
            pst2.execute();
            pst2.close();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return bool2;
    }

    public void register_as_driver(User user2) {
//        boolean bool = false;
        con = MySQLConnection.connect();
        String sql = "INSERT INTO Driver(user_id, license_no, insurance_no, insurance_com) VALUES (?,?,?,?)";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, user2.getUserID());
            pst.setInt(2, user2.getLNo());
            pst.setInt(3, user2.getINo());
            pst.setString(4, user2.getICom());
            
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return bool;
    }
    
    public User userInfo(int id){
        User userDetails = new User();
        
        String sql = "SELECT * FROM User WHERE user_id=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email_address = rs.getString("email_id");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                
                userDetails.setPassword(password);
                userDetails.setFirstName(first_name);
                userDetails.setLastName(last_name);
                userDetails.setEmail_id(email_address);
                userDetails.setGender(gender);
                
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userDetails;
    }

    public String updateUserInfo(User userDetails) {
        String message = "";
        String sql = "UPDATE User SET first_name=?,last_name=?,email_id=?,password=? WHERE user_id=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, userDetails.getFirstName());
            pst.setString(2, userDetails.getLastName());
            pst.setString(3, userDetails.getEmail_id());
            pst.setString(4, userDetails.getPassword());
            pst.setInt(5, userDetails.getUserID());
            
            if (pst.executeUpdate()!= 0) {
                message = "New Information has been updated!";
            }
            else{
                message = "There was some problem in updating!";
            }
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }

    public User driverInfo(int userID) {
        User userDetails = new User();
        
        String sql = "SELECT * FROM Driver WHERE user_id=?";
        con = MySQLConnection.connect();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                int license_no = rs.getInt("license_no");
                int insurance_no = rs.getInt("insurance_no");
                String insurance_com = rs.getString("insurance_com");
                
                userDetails.setLNo(license_no);
                userDetails.setINo(insurance_no);
                userDetails.setICom(insurance_com);
                
            }
            
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userDetails;
    }

}
    

