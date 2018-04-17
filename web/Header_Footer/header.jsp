<%-- 
    Document   : footer
    Created on : Mar 16, 2018, 5:48:16 PM
    Author     : suraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sess = request.getSession(false);
    if (sess.getAttribute("user") == null) {
        response.sendRedirect("login");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Assets/lux.css">
        <link rel="stylesheet" type="text/css" href="Assets/reset.css">
       
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"  type="text/javascript"></script>
        
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="http://maps.google.com/maps/api/js?key=AIzaSyDaEYikP8pXvbBeGKA609lc93WC6wzV2Js"></script>
        <script src="Assets/geoLocation.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <head>


    <header>
        <div class="jumbotron">
            <div class = "container">
                <nav class="navbar navbar-expand-lg navbar-dark bg-primary navbar_padding">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Nomad</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation" style="">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarColor01">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link" href="${pageContext.request.contextPath}">Home <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Features</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Pricing</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">About</a>
                            </li>
                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="text" placeholder="Search">
                            <!--<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>-->
                        </form>
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item">
                                        <a class="nav-link"  href="logout" ><i class="fa fa-power-off"></i>Log Out</a>
                                </li>
                            </ul>
                        
                    </div>
                </nav>
            </div>
        </div>
    </header>
    
    
</html>


