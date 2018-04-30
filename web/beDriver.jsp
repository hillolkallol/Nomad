<%-- 
    Document   : registration
    Created on : Mar 22, 2018, 1:46:52 PM
    Author     : KD
--%>

<%@page import="Model.User"%>


<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("login_error_msg");
        if(error == null)
            error = "";
%> 
<html lang="en">
    <%@include file ="Header_Footer/header.jsp" %>

    <body>
        <%@ page session="false" %>
        <div class="login_page_body">
            <div class="sign_in_section ">
                <div class="panel-heading">
                    <!--<img class="logo" src="img/logo.png"><br><br>-->
                    <h2 >Become a Driver</h2></div>
                
                <form method="post" action="beDriver" class="panel-body row">
                    <div class="col-sm-6">
                    <label>License No</label><br>
                    </div>
                    <div class="col-sm-6">
                    <input class="form-control" type="text" placeholder="License No" name="LNO" value="${userDetails.getLNo()!=0 ? userDetails.getLNo(): ""}" required><br>
                    </div>
                    
                    <div class="col-sm-6">
                    <label>Insurance No</label><br>
                    </div>
                    <div class="col-sm-6">
                    <input class="form-control" type="text" placeholder="Insurance No" name="INO" value="${userDetails.getINo() !=0 ? userDetails.getINo(): ""}" required><br>
                    </div>
                    
                    <div class="col-sm-6">
                    <label>Insurance Company</label><br>
                    </div>
                    <div class="col-sm-6">
                    <input class="form-control" type="text" placeholder="Insurance Company" name="ICOM" value="${userDetails.getICom()}" required><br>
                    <label><%= error%></label><br><br>
                    <input class="btn btn-primary" type="submit" value="Become a Driver"><br>
                    </div>
                    
                    
                </form>
            </div>
        </div>
                    <!--<br><br><br><br><br>-->
        <%--<%@include file="footer.jsp" %>--%>
    </body>
</html>
<%@include file ="Header_Footer/footer.jsp" %>
