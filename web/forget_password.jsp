<%--
    Document   : forget_password.jsp
    Created on : Mar 26, 2018, 12:49:53 PM
    Author     : KD
--%>

<%@include file ="Header_Footer/header_before_login.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String message = " ";
    if((String) request.getAttribute("pass_recovery") != null)
        message = (String) request.getAttribute("pass_recovery");  
%> 
<!DOCTYPE html>
<html lang="en">
    <head>
    </head>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section">
               <div class="panel-heading"> 
                   <!--<img class="logo" src="img/logo.png"><br><br>-->
                    <h2 >Forget Password?</h2></div>
                <p>A password recovery link will be sent to your Email address after submitting!</p><br>
                <form method="post" action="forget_password" class="panel-body">
                    <input class="form-control" placeholder="username/email address" type="text" name="email_or_username" required><br>
                    <input class="btn btn-primary" type="submit" value="Recover"><br>
                </form>
                <label><%= message %></label><br>
                <a class="customize_link" href="login">Back to home?</a>
            </div>
        </div>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>