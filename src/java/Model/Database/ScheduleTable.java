/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;
import Model.Schedule;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
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
//    String ex= Se;ect * from, shechudel where user_id = ? and schedule_id = ?
    private final String selectStmt = "SELECT * FROM Schedule where user_id != ?;";
    private final String selectScheduleUser = "SELECT * FROM Schedule where user_id = ?";
    private final String selectOne = "SELECT * from Schedule where schedule_id = ?";
    private final String deleteStmt = "Delete from Schedule where schedule_id = ?";
    private final String insertStmt = "Insert into Schedule (from_location, to_destination, date, time, seats_left, seats_total, user_id) values (?,?,?,?,?,?,?)";
    private final String updateStmt = "Update Schedule set from_location = ?, to_destination = ? , date = ?, time =?, seats_left=?, seats_total=? where user_id = ?";

    private static final int schedule_id = 0;
    private static final String time = "time";
    private static final String from_location = "from_location";
    private static final String to_destination   = "to_destination";
    private static final String seats_left = "seats_left";
    private static final String seats_total = "seats_total";  
//    private static final String date = "date";
    
    public List<Schedule> getAllSchedule(int userID, String from) throws SQLException{
        
        if(from != null){
            String Stmt = "SELECT * FROM Schedule where user_id != ? and from_location = ?;";
            preparedStatement = conn.prepareStatement(Stmt);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, from.trim());
        }
        else{
            preparedStatement = conn.prepareStatement(selectStmt);
            preparedStatement.setInt(1, userID);
        }
       resultSet = preparedStatement.executeQuery();

       List<Schedule> schdeleList = new ArrayList<Schedule>();
       while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            Schedule s = new Schedule();
            s.scheduleID = resultSet.getInt(1);
            s.date = resultSet.getString(2);
            s.time = resultSet.getString(3);
            s.from = resultSet.getString(from_location);
            s.to = resultSet.getString(to_destination);
            s.seats_left = resultSet.getInt(seats_left);
            s.seats_total = resultSet.getInt(seats_total);  
            schdeleList.add(s);
       }
        return schdeleList;
    }
    
    public List<Schedule> getUserSchedule(int userID) throws SQLException{
       preparedStatement = conn.prepareStatement(selectScheduleUser);
       preparedStatement.setInt(1, userID);
       resultSet = preparedStatement.executeQuery();
       List<Schedule> schdeleList = new ArrayList<Schedule>();
       while (resultSet.next()) {
        Schedule s = new Schedule();
        s.scheduleID = resultSet.getInt(1);
        s.date = resultSet.getString(2);
        s.time = resultSet.getString(3);
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
            s.time = resultSet.getString(time);
            s.from = resultSet.getString(from_location);
            s.to = resultSet.getString(to_destination);
            s.seats_left = resultSet.getInt(seats_left);
            s.seats_total = resultSet.getInt(seats_total);  
//            s.schdeleList.add(s);
//            s.date = resultSet.getString(date);
            
       }
        return s;
    }
    
       public void insertDriverSchedule(String from, String to, String date, String time, String seats_left, String total_seats, int userID) throws SQLException{
           preparedStatement = conn.prepareStatement(insertStmt);
           preparedStatement.setString(1, from);
           preparedStatement.setString(2,to);
           preparedStatement.setString(3, date);
           preparedStatement.setString(4,time);
           preparedStatement.setInt(5,Integer.parseInt(seats_left));
           preparedStatement.setInt(6,Integer.parseInt(total_seats));
           preparedStatement.setInt(7,userID);
           preparedStatement.executeUpdate();     
           
       }
       
        public void updateDriverSchedule(String from, String to, String date, String time, String seats_left, String total_seats, int userID) throws SQLException{
           preparedStatement = conn.prepareStatement(updateStmt);
           preparedStatement.setString(1, from);
           preparedStatement.setString(2,to);
           preparedStatement.setString(3, date);
           preparedStatement.setString(4,time);
           preparedStatement.setInt(5,Integer.parseInt(seats_left));
           preparedStatement.setInt(6,Integer.parseInt(total_seats));
           preparedStatement.setInt(7,userID);
           preparedStatement.executeUpdate();     
           
       }
        public void deleteDriverSchedule(int scheduleID) throws SQLException{
           preparedStatement = conn.prepareStatement(deleteStmt);
           preparedStatement.setInt(1, scheduleID);
          
           preparedStatement.executeUpdate();     
           
       }
}
