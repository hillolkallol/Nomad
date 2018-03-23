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
        String sql = "SELECT email_id FROM user WHERE email_id=? AND password=?";
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
}
