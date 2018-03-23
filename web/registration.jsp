<%-- 
    Document   : registration
    Created on : Mar 22, 2018, 1:46:52 PM
    Author     : KD
--%>

<%@include file ="Header_Footer/header_before_login.jsp" %>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("login_error_msg");
        if(error == null)
            error = "";
%> 
<html lang="en">
    <head>
        
    </head>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section ">
                <div class="panel-heading">
                    <!--<img class="logo" src="img/logo.png"><br><br>-->
                    <h2 >Sign Up</h2></div>
                <form method="post" action="login_checking" class="panel-body">
                    <input class="form-control" type="text" placeholder="First Name" name="FNAME" required><br>
                    <input class="form-control" type="text" placeholder="Last Name" name="LNAME" required><br>
                    <input class="form-control" type="text" placeholder="Email Address" name="EMAIL" required><br>
                    <input class="form-control" type="password" placeholder="password" name="PASSWORD" required><br>
                    <input class="form-control" type="text" placeholder="Address" name="ADDRESS" required><br>
                    <label >Gender: </label>
                    <input type="radio" name="gender" value="1" style="display: inline" checked> Male
                    <input type="radio" name="gender" style="display: inline" value="2"> Female<br>
                    
                    <input type="radio" name="group1" value="1" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                    <label >Register as a driver too </label><br>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <input class="form-control require-if-active" type="text" placeholder="License No" name="LNO" ><br>
                            <input class="form-control require-if-active" type="text" placeholder="Insurance No" name="INO" ><br>
                            <input class="form-control require-if-active" type="text" placeholder="Insurance Company" name="ICOM" ><br>
                        </div>
                    </div>
                    
                    <label><%= error%></label><br><br>
                    <input class="btn btn-primary" type="submit" value="Sign In"><br>
                </form>
            </div>
        </div>
                    <!--<br><br><br><br><br>-->
        <%--<%@include file="footer.jsp" %>--%>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>
