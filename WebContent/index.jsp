<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuizWebsite</title>
</head>

<body>  
    <div class="container">
        
        <div id="header">
    		<div id="userpanel">
                Hello, <b><%=(String) session.getAttribute("username")%></b>.
                <a href="extras">Extras</a>
                <span style="color: #ccc;">-</span>
                <a href="profile.jsp">Account</a>
                <span style="color: #ccc;">-</span>
                <a href="logout.jsp">Log out</a>    
            </div>
        
            <h1 id="logo">
                <a href="index.jsp" class="logo">QuizWebsite</a>
            </h1>

            <div style="font-size: 14px; margin-top: 8px;">A simple website to create, take, and share quizzes.</div>
        </div>
        
        <%-- <%@include file="header.html"%> --%>
        
        

        <div id="footer">
            <p>2012</p>
        </div>

    </div>
</body>




</body>
</html>