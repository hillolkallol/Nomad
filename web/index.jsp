<%-- 
    Document   : index
    Created on : Mar 17, 2018, 6:24:21 PM
    Author     : suraj
--%>
<%@include file ="Header_Footer/header.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
               <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img class="d-block w-100" src="images/img2.png" alt="First slide">
              </div>
              <div class="carousel-item">
                <img class="d-block w-100" src="images/img1.jpeg" alt="Second slide">
              </div>
              <div class="carousel-item">
                <img class="d-block w-100" src="images/img3.jpeg" alt="Third slide">
              </div
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
        
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>