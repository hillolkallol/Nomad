<%-- 
    Document   : Edit driver_schedule
    Created on : Mar 19, 2018, 9:21:09 PM
    Author     : peshal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Schedule"%>
<%@page import="Model.Database.ScheduleTable"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file ="Header_Footer/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Driveasdr's Schedule</title>
    </head>
    <body>
      
        <div  class="container">
            <h1>Edit Driver's Schedule</h1>
            <div class="row">
                <div class=" col-sm-4 col-sm-offset-4">
                    <form action = "schedule" method="POST">
                       <div class="form-group">
                         <label>From</label>
                         <input type="text" class="form-control" id="from" name = "from" placeholder="From Address" size="50" value = ${schedule.getFrom()}>
                         </div>
                         <div class="form-group">
                         <label >To</label>
                         <input type="text" class="form-control" id="to" name = "to" placeholder="Destination Address" size="50" value="${schedule.getTo()}">
                         </div>
                         <div class="form-group">
                         <label>Date</label>
                         <input type="date" class="form-control" id="date" name = "date" required value="${schedule.getDate()}">
                         </div>
                         <div class="form-group">
                         <label>Time</label>
                         <input type="time" class="form-control" id="time" name = "time" value="${schedule.getTime()}">
                         </div>
                        <div class="form-group">
                            <label>Total Number of Seats</label>
                            <select class="form-control" id="total_Number_of_seats" name="total_seats" value="${schedule.getSeats_total()}">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                                <option>7</option>
                                <option>8</option>
                                <option>9</option>
                                <option>10</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Number of Seats Available</label>
                            <select class="form-control" id="Number_of_seats_available" name="seats_left" value="${schedule.getSeats_left()}">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                                <option>7</option>
                                <option>8</option>
                                <option>9</option>
                                <option>10</option>
                            </select>
                        </div>
                                <input type="hidden" name="post_action" value="update" >
                        <input type="Submit" class="btn btn-primary"></input>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>