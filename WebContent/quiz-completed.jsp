<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Completed!</title>
</head>
<body>

<%
	String timeElapsed = (String) request.getAttribute("time");
	String grade = (String) request.getAttribute("grade");
	Quiz completed = (Quiz) request.getSession().getAttribute("currentTakingQuiz");	
	int quizId = completed.quizId;
	System.out.println("Quiz id: "+quizId);
%>
Congratulations on completing <%= completed.name %>! <br/>

Your score was <%= grade %> questions correct. It took you <%= timeElapsed %> seconds. <br/>

Click <a href="QuizSummaryServlet?quiz="+<%= quizId%> >here</a> for a quiz summary. <br/>
Click <a href="index.jsp">here</a> to return home

</body>
</html>