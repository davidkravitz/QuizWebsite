<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuizWebsite</title>

<link type="text/css" href="static/css/search.css" rel="stylesheet"/>

</head>

<link type="text/css" href="static/css/index.css" rel="stylesheet"/>

<body>  
    <div id="wrapper">
        
        <%@include file="header.jsp"%>
        
        <div id="content">
			<div id="left-column">
			
				<div class="homepage-section">
					<div class="title">Quizzes</div>
					<%
						
						String searchQuery = request.getParameter("query");
						ArrayList<Quiz> quizzes = Quiz.getSimilarQuizName(searchQuery, 0);
						for (Quiz quiz : quizzes) {
							out.println("<div class=\"search-result\">");
							out.println("<a href=\"quiz-summary.jsp?quiz=" + quiz.quizId + "\">");
							out.println(quiz.name);
							out.println("</a>");
							out.println("</div>");
						}
					%>
				</div>

				<div class="homepage-section">
					<div class="title">Users</div>
					<%
						searchQuery = request.getParameter("query");
						ArrayList<User> users = User.getSimilarUsernames(searchQuery, 0);
						for (User user : users) {
							out.println("<div class=\"search-result\">");
							out.println("<a href=\"user.jsp?username=" + user.username + "\">");
							out.println(user.firstName + " " + user.lastName);
							out.println("</a>");
							out.println("</div>");
						}
					%>
				</div>
			</div>
			
			<div id="right-column">
			</div>
		</div>
        
        <div id="footer">
            <p>QuizWebsite 2012</p>
        </div>

    </div>
</body>

</html>