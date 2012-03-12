<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<h1>The name <%=session.getAttribute("username")%> is already in use</h1>
	<div>Please enter another name and password.</div>
	<form action="AccountCreationServlet" method="post">
	    <label>User Name: </label>
    	<input type="text" name="username">
    	<br/>
    	<label>Password: </label>
   	 	<input type="password" name="password">
    	<input type="submit" name="Login">
	</form>
</body>
</html>