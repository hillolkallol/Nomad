<%-- 
    Document   : login
    Created on : Mar 21, 2018, 11:45:40 PM
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
                    <h2 >Sign In</h2></div>
                <form method="post" action="login_checking" class="panel-body">
                    <input class="form-control" type="text" placeholder="Email Address" name="EMAIL" required><br>
                    <input class="form-control" type="password" placeholder="Password" name="PASSWORD" required><br>
                    <label><%= error%></label><br><br>
                    <input class="btn btn-primary" type="submit" value="Sign In"><br>
                </form>

                <a class="customize_link" href="forget_password">Forget Password?</a>
            </div>
        </div>
                    <!--<br><br><br><br><br>-->
        <%--<%@include file="footer.jsp" %>--%>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>