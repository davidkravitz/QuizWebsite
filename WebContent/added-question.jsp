<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Quiz curr = (Quiz) request.getSession().getAttribute("currCreationQuiz");
	%>
	<h3>Current Quiz:	<%=curr.name%></h3>
		
		Successfully added question. <br/>
		<a href="add-question.jsp">Add Another</a>
		<form name="done" action="AddQuestionServlet" method="get">
			<input type="submit" value="Done" />
		</form>
</body>
</html>