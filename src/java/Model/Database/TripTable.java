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

/**
 *
 * @author suraj
 */
public class TripTable {
    private  Connection conn = MySQLConnection.connect();
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    
    public void insertTrip(int rider_id, int schedule_id, int driver_id) throws SQLException{
        String command = "Insert into Trip (is_confirmed, schedule_id, rider_id, driver_id) values (?,?,?,?)";
        preparedStatement = conn.prepareStatement(command);
        preparedStatement.setBoolean(1, true);
        preparedStatement.setInt(2, schedule_id);
        preparedStatement.setInt(3, rider_id);
        preparedStatement.setInt(4, driver_id);
        preparedStatement.executeUpdate();
        
        
        
        
    }
}
