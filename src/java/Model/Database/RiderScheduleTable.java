/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import Model.RiderSchedule;
import Model.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peshal
 */
public class RiderScheduleTable {
     private  Connection conn = MySQLConnection.connect();
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
//    String ex= Se;ect * from, shechudel where user_id = ? and schedule_id = ?
    private final String selectStmt = "SELECT * FROM RiderSchedule where user_id == ?;";
    private final String selectRiderSchedule = "SELECT * FROM RiderSchedule where user_id = ?;";
    private final String selectOne = "SELECT * from RiderSchedule where schedule_id = ?;";
    private final String deleteStmt = "Delete from RiderSchedule where schedule_id = ?;";
    private final String insertStmt = "Insert into RiderSchedule (from_location, to_destination, date, time, user_id) values (?,?,?,?,?);";
    private final String updateStmt = "Update RiderSchedule set from_location = ?, to_destination = ? , date = ?, time =? where schedule_id = ?;";

    private static final int schedule_id = 0;
    private static final String time = "time";
    private static final String from_location = "from_location";
    private static final String to_destination   = "to_destination";  

    
    public List<Schedule> getAllSchedule(int userID, String from) throws SQLException{
        
        if(from != null){
            String Stmt = "SELECT * FROM RiderSchedule where user_id = ? and from_location = ?;";
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
            schdeleList.add(s);
       }
        return schdeleList;
    }
    
    public List<RiderSchedule> getRiderSchedule(int userID) throws SQLException{
       preparedStatement = conn.prepareStatement(selectRiderSchedule);
       preparedStatement.setInt(1, userID);
       resultSet = preparedStatement.executeQuery();
       List<RiderSchedule> riderSchdeleList = new ArrayList<RiderSchedule>();
       while (resultSet.next()) {
            RiderSchedule s = new RiderSchedule();
            s.scheduleID = resultSet.getInt(1);
            s.date = resultSet.getString(2);
            s.time = resultSet.getString(3);
            s.from = resultSet.getString(from_location);
            s.to = resultSet.getString(to_destination); 
            riderSchdeleList.add(s);
       }
        return riderSchdeleList;
    }
    
        public RiderSchedule getScheduleByID(int scheduleID) throws SQLException{
            preparedStatement = conn.prepareStatement(selectOne);
            preparedStatement.setInt(1, scheduleID);
            resultSet = preparedStatement.executeQuery();
            RiderSchedule rs = new RiderSchedule();

            while (resultSet.next()) {
                rs.scheduleID = resultSet.getInt(1);
                rs.time = resultSet.getString(time);
                rs.from = resultSet.getString(from_location);
                rs.to = resultSet.getString(to_destination);
    //            s.schdeleList.add(s);
    //            s.date = resultSet.getString(date);

           }
            return rs;
    }
    
       public void insertRiderSchedule(String from, String to, String date, String time, int userID) throws SQLException{
           preparedStatement = conn.prepareStatement(insertStmt);
           preparedStatement.setString(1, from);
           preparedStatement.setString(2,to);
           preparedStatement.setString(3, date);
           preparedStatement.setString(4,time);
     
           preparedStatement.setInt(5,userID);
           preparedStatement.executeUpdate();     
           
       }
       
        public void updateRiderSchedule(String from, String to, String date, String time, int scheduleID) throws SQLException{
           preparedStatement = conn.prepareStatement(updateStmt);
           preparedStatement.setString(1, from);
           preparedStatement.setString(2,to);
           preparedStatement.setString(3, date);
           preparedStatement.setString(4,time);
           preparedStatement.setInt(5,scheduleID);
           preparedStatement.executeUpdate();     
           
       }
        public void deleteRiderSchedule(int scheduleID) throws SQLException{
           preparedStatement = conn.prepareStatement(deleteStmt);
           preparedStatement.setInt(1, scheduleID);
          
           preparedStatement.executeUpdate();     
           
       }
    
}
