<%-- 
    Document   : dashboard
    Created on : Mar 18, 2018, 2:50:09 PM
    Author     : suraj
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
        <script src="Assets/geoLocation.js"></script>
        <link rel="stylesheet" type="text/css" href="Assets/reset.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div class="container">
           
            <div class="row">
                
            <div class="col-md-8 col-md-offset-2 col-centered">
                <div class="col-centered">
                    <h2>Ride Offer</h2>
                    <div class="alert alert-dismissible alert-info">

                        <strong>Heads up!</strong> This alert needs your attention.
                        <p class="mb-0">You have been offered a ride</p>
                    </div>
                    <form action ="trip" method = "POST">
                    <input type="hidden" name="rider_id" value="${rider_id}" >
                    <input type="hidden" name="schedule_id" value="${schedule_id}" >
                    <input type="hidden" name="driver_id" value="${driver_id}" >
                    <input type="submit" class="btn btn-success" name = "confirmation" value="Accept"></input>
                    <input type="submit" class="btn btn-danger" name ="confirmation" value="Reject"></input>

                    </form>
                </div>


            </div>
                 
                
            </div>    
        </div>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>