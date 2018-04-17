/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;

/**
 *
 * @author suraj
 */
public class Schedule {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AAA2DB41-12AF-70D9-6C0A-5FE82CEB762D]
    // </editor-fold> 
    public int scheduleID;
    
    public String date;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C2163A8E-A97A-F692-AC8E-CD7FE5D2ED18]
    // </editor-fold> 
    public String time;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7CE9721F-B8FA-31A7-1313-D69577552683]
    // </editor-fold> 
    public String from;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DA1F4333-6B5D-F94E-1E5F-3CF5C18DC3DE]
    // </editor-fold> 
    public String to;
    public int seats_left;
    public int seats_total;

    public String getFrom(){
        return from;
    }
    public String to(){
        return to;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getSeats_left() {
        return seats_left;
    }

    public void setSeats_left(int seats_left) {
        this.seats_left = seats_left;
    }

    public int getSeats_total() {
        return seats_total;
    }

    public void setSeats_total(int seats_total) {
        this.seats_total = seats_total;
    }
    
}
