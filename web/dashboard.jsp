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
                 <form action="dashboard" method="GET">
                    
                    Show me rides from
                    <input type="submit" id= "demo" name="city" class="btn btn-outline-primary" >
                </form>
                 </div>
                    <ul class="nav nav-tabs">
          <li class="nav-item">
            <a class="nav-link active show" data-toggle="tab" href="#rides_available">Rides</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#profile">My Schedule</a>
          </li>
        </ul>
                    <div id="myTabContent" class="tab-content">
                    
                        <div class="tab-pane fade active show" id="rides_available">
                            <% ArrayList scheduleList = (ArrayList )  request.getAttribute("scheduleList"); %>
                            <% ArrayList userList = (ArrayList )  request.getAttribute("userScheduleList"); %>
                            <% if (scheduleList.size() > 0) {%>
                            <table class="table table-hover">
                        <thead>
                          <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">From</th>
                            <th scope="col">TO</th>
                            <th scope="col">Seats Left</th>
                          </tr>
                        </thead>
                        <c:forEach items="${scheduleList}" var="schedule">             
                            <tbody>
                              <tr class="table-secondary">
                                    <!--<td>${schedule.getScheduleID()}</td>-->

                                    <td class="col-md-2">${schedule.getDate()}</td>
                                    <td>${schedule.getTime()}</td>
                                    <td>${schedule.getFrom()}</td>
                                    <td>${schedule.getTo()}</td>
                                    <td>${schedule.getSeats_left()}</td>
                                    <!--<td>${schedule.getSeats_total()}</td>-->
                                    <td>
                                    <form action="email" method="POST">
                                        <input type="hidden" name="requestID" value="${schedule.getScheduleID()}" >
                                        <input type="submit" class="btn btn-outline-primary" value="Request" >
                                    </form>
                                    </td> 
                              </tr>
                            </tbody>

                        </c:forEach>
                    </table> 
                            <% } %>
                            <% if (scheduleList.size() == 0) {%>
                             <div class="margin_top alert alert-dismissible alert-primary">
                                
                                 <strong>Oh snap!</strong> <p>No rides available at the moment.Please try again later.</p>
                            </div>
                            <% } %>
                            
                        </div>
                        <div class="tab-pane fade" id="profile">
                            <% if (userList.size() > 0) {%>
                            <table class="table table-hover">
                                      <thead>
                                        <tr>
                                         <th scope="col">Date</th>
                                          <th scope="col">Time</th>
                                          <th scope="col">From</th>
                                          <th scope="col">TO</th>
                                          <th scope="col">Action</th>
                                          <!--<th scope="col">Time</th>-->
                                        </tr>
                                      </thead>
                                      <c:forEach items="${userScheduleList}" var="schedule">             
                                          <tbody>
                                            <tr class="table-secondary">
                                                  <!--<td>${schedule.getScheduleID()}</td>-->
                                                  <td class="col-md-2">${schedule.getDate()}</td>
                                                  <td>${schedule.getTime()}</td>
                                                  <td>${schedule.getFrom()}</td>
                                                  <td>${schedule.getTo()}</td>
                                                  <!--<td>${schedule.getSeats_left()}</td>-->
                                                  <!--<td>${schedule.getSeats_total()}</td>-->
                                                  <td>
                                                      <form action="schedule" method="GET">
                                                            <input type="hidden" name="scheduleID" value="${schedule.getScheduleID()}" >
                                                            <input type="hidden" name="post_action" value="edit" >
                                                            <input type="submit" class="btn btn-outline-primary" value="Edit" >
                                                    </form>    
                                                  </td>
                                                  <td>
                                                      <form action="schedule" method="POST">
                                                          <input type="hidden" name="scheduleID" value="${schedule.getScheduleID()}" >
                                                          <input type="hidden" name="post_action" value="delete" >
                                                          <input type="submit" class="btn btn-outline-primary" value="Delete" >
                                                      </form>
                                                  </td> 
                                            </tr>
                                          </tbody>
>>>>>>> Stashed changes

                                      </c:forEach>
                                  </table> 
                            <% } %>
                            <% if (userList.size() == 0) {%>
                            <div class=" margin_top alert alert-dismissible alert-primary">
                                
                                <strong>Oh snap!</strong> You donot have your schedule posted. Please add your schedule.
                            </div>
                            <% } %>
                        </div>
        </div>
                </div>
                 <div class="col-centered">
                 <form action="schedule" method="GET">
                    <input type="hidden" name="action" value="new" >
                    <input type="submit" class="btn btn-outline-primary" value="Add Schedule" >
                </form>
                 </div>
                
            </div>    
        </div>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>