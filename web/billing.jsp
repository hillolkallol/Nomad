<%-- 
    Document   : billing
    Created on : Apr 29, 2018
    Author     : PR
--%>

<%@include file ="Header_Footer/header.jsp" %>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">
        <body>
            <div class="container">
                <h1>Billings History</h1>
                <p>price/mile = $0.15</p>
                <br />
                <h4>Your Role: Rider</h4>
                <table class="table">
                  <thead class="thead-light">
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Date</th>
                      <th scope="col">Departure Time</th>
                      <th scope="col">Arrival Time</th>
                      <th scope="col">Departure Location</th>
                      <th scope="col">Arrival Location</th>
                      <th scope="col">Trip Distance (miles)</th>
                      <th scope="col">Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th scope="row">2</th>
                      <td>2018-04-28</td>
                      <td>14:01</td>
                      <td>14:42</td>
                      <td>Belen</td>
                      <td>Socorro</td>
                      <td>43.4</td>
                        <td>-$6.51</td>
                    </tr>
                    <tr>
                      <th scope="row">1</th>
                      <td>2018-04-27</td>
                      <td>14:05</td>
                      <td>15:13</td>
                      <td>Albuquerque</td>
                      <td>Socorro</td>
                      <td>76.3</td>
                        <td>-$11.45</td>
                    </tr>
                  </tbody>
                </table>
                <br />
                <h4>Your Role: Driver</h4>
                <table class="table">
                  <thead class="thead-light">
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Date</th>
                      <th scope="col">Departure Time</th>
                      <th scope="col">Arrival Time</th>
                      <th scope="col">Departure Location</th>
                      <th scope="col">Arrival Location</th>
                      <th scope="col">Trip Distance (miles)</th>
                      <th scope="col">Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <th scope="row">1</th>
                      <td>2018-02-02</td>
                      <td>14:01</td>
                      <td>16:25</td>
                      <td>White Sands</td>
                      <td>Socorro</td>
                      <td>148</td>
                        <td>+$22.20</td>
                    </tr>
                  </tbody>
                </table>
            </div>
        </body>
        <%@include file ="Header_Footer/footer.jsp" %>
</html>
