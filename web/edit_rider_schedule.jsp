<%-- 
    Document   : Edit driver_schedule
    Created on : Mar 20, 2018, 9:21:09 PM
    Author     : peshal
--%>
<%@include file ="Header_Footer/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Rider's Schedule</title>
    </head>
    <body>
      
        <div  class="container">
            <h1>Edit Rider's Schedule</h1>
            <div class="row">
                <div class=" col-sm-4 col-sm-offset-4">
                  
                    <form name="drivers_scheduule" method="POST">
                       <div class="form-group">
                         <label>From</label>
                         <input type="text" class="form-control" id="from" name = "from" placeholder="From Address" size="50">
                         </div>
                         <div class="form-group">
                         <label >To</label>
                         <input type="text" class="form-control" id="to" name = "to" placeholder="Destination Address" size="50">
                         </div>
                         <div class="form-group">
                         <label>Date</label>
                         <input type="date" class="form-control" id="date" name = "date">
                         </div>
                         <div class="form-group">
                         <label>Time</label>
                         <input type="time" class="form-control" id="time" name = "time">
                         </div>                                             
                        <button type="button" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>