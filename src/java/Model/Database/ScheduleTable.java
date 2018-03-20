/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;
import Model.Schedule;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
/**
 *
 * @author suraj
 */
public class ScheduleTable {
    private  Connection conn = MySQLConnection.connect();
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private final String selectStmt = "SELECT * FROM Schedule;";
    private final String selectOne = "SELECT * from Schedule where schedule_id = ?";
    private static final int schedule_id = 0;
    private static final String time = "time";
    private static final String from_location = "from_location";
    private static final String to_destination   = "to_destination";
    private static final String seats_left = "seats_left";
    private static final String seats_total = "seats_total";   
    
    public List<Schedule> getAllSchedule() throws SQLException{
       preparedStatement = conn.prepareStatement(selectStmt);
       resultSet = preparedStatement.executeQuery();
       List<Schedule> schdeleList = new ArrayList<Schedule>();
       while (resultSet.next()) {
        Schedule s = new Schedule();
        s.scheduleID = resultSet.getInt(1);
        s.time = resultSet.getString(2);
        s.from = resultSet.getString(from_location);
        s.to = resultSet.getString(to_destination);
        s.seats_left = resultSet.getInt(seats_left);
        s.seats_total = resultSet.getInt(seats_total);  
        schdeleList.add(s);
       }
        return schdeleList;
    }
    
       public Schedule getScheduleByID(int scheduleID) throws SQLException{
        preparedStatement = conn.prepareStatement(selectOne);
        preparedStatement.setInt(1, scheduleID);
        resultSet = preparedStatement.executeQuery();
        Schedule s = new Schedule();

        while (resultSet.next()) {
            s.scheduleID = resultSet.getInt(1);
            s.time = resultSet.getString(2);
            s.from = resultSet.getString(from_location);
            s.to = resultSet.getString(to_destination);
            s.seats_left = resultSet.getInt(seats_left);
            s.seats_total = resultSet.getInt(seats_total);  
//         schdeleList.add(s);
       }
        return s;
    }
    
}
