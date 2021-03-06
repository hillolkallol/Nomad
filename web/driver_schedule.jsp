<%-- 
    Document   : driver_schedule
    Created on : Mar 19, 2018, 9:21:09 PM
    Author     : peshal
--%>
<%@include file ="Header_Footer/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Driver's Schedule</title>
    </head>
    <body>
      
        <div  class="container">
            <h1>Driver's Schedule</h1>
            <div class="row">
                <div class=" col-sm-4 col-sm-offset-4">
                  
                    <form action="schedule" method="POST" >
                        <input type="hidden" name="post_action" value="insert" >
                       <div class="form-group">
                         <label>From</label>
                         <input type="text" class="form-control" id="from" name = "from" placeholder="From Address" size="50" required>
                         </div>
                         <div class="form-group">
                         <label >To</label>
                         <input type="text" class="form-control" id="to" name = "to" placeholder="Destination Address" size="50" required>
                         </div>
                         <div class="form-group">
                         <label>Date</label>
                         <input type="date" class="form-control" id="date" name = "date" required>
                         </div>
                         <div class="form-group">
                         <label>Time</label>
                         <input type="time" class="form-control" id="time" name = "time" required>
                         </div>
                        <div class="form-group">
                            <label>Total Number of Seats</label>
                            <select class="form-control" id="total_Number_of_seats" name="total_seats" required>
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
                            <select class="form-control" id="Number_of_seats_available" name="seats_left" required>
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
                        <input type="submit" class="btn btn-primary"></input>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>