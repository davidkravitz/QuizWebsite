<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>  
    <div id="wrapper">
        
        <%@include file="header.jsp"%>
        
        <div id="content">
			<div id="left-column">
				
				<div class="homepage-section">
					<div class="title">Reported Quizzes</div>
					<table>
					<tr>
					<th>Action</th>
					<th>User</th>
					<th>quizId</th>
					<th>Message</th>
					<th>Type</th>
					</tr>
					<%
						int numQuizzes = 5;
						/* ArrayList<Quiz> quizzes = Quiz.getQuizzes(numQuizzes); */
						
						// Stub filled with dummy quizzes
						ArrayList<QuizReport> quizReports = QuizReport.getReportedQuizzes(0);
						
						for (QuizReport quizReport : quizReports) {
							out.println("<tr>");
							out.println("<td>");
							out.println("<form action=\"DeleteQuiz\" method=\"POST\">");
							out.println("<input type=\"hidden\" name=\"quizId\" value=\"" + quizReport.quizId + "\" />");
							out.println("<input type=\"submit\" value=\"Delete quiz\" /></form>");
							out.println("</td>");
							out.println("<td>");
							out.println(quizReport.username);
							out.println("</td>");
							out.println("<td>");
							out.println("<a href=\"" + quizReport.quizId + "\">See Quiz</a>");
							out.println("</td>");
							out.println("<td>");
							out.println(quizReport.message);
							out.println("</td>");
							out.println("<td>");
							out.println(quizReport.type);
							out.println("</td>");
							out.println("</tr>");
						}
					%>
					</table>
				</div>
				
				<div class="add-announcement-section">
					<div class="title">Add Announcement</div>
					
				</div>
				
				<div class="homepage-section">
					<div class="title">Quizzes You've Made</div>
					<%
		/* 				String username = (String) session.getAttribute("username");
						quizzes = Quiz.getQuizzesBy(username, numQuizzes); */
						for (Quiz quiz : quizzes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"" + quiz.hashCode() + "\">" + quiz.name + "</a>");
							out.println("</div>");
						}
					%>
				</div>
				
				<div class="homepage-section">
					<div class="title">Quizzes You've Taken</div>
					<%
		/* 				String username = (String) session.getAttribute("username");
						ArrayList<QuizTake> quizTakes = QuizTake.getTakenQuizzesForUser(username); */
						
						// Stub filled with dummy quizTakes
						ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();
						quizTakes.add(new QuizTake(1, "username", 0, 0, "dataTaken", "timeSpent"));
						quizTakes.add(new QuizTake(1, "username", 0, 0, "dataTaken", "timeSpent"));
						quizTakes.add(new QuizTake(1, "username", 0, 0, "dataTaken", "timeSpent"));
						
						for (QuizTake quizTake : quizTakes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"" + quizTake.quizName + "\">" + quizTake.quizName + "</a>");
							out.println("</div>");
						}
					%>
				</div>
				
				
				<div class="homepage-section">
					<div class="title">Your Friends' Recent Activity</div>
										<%
		/* 				String username = (String) session.getAttribute("username");
						ArrayList<QuizTake> quizTakes = QuizTake.getTakenQuizzesForUser(username); */
						
						// Stub filled with dummy Users
						quizTakes.add(new QuizTake(1, "username", 0, 0, "dataTaken", "timeSpent"));
						String username = "david";
						for (QuizTake quizTake : quizTakes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"user.jsp?username=" + username + "\">" + username + "</a>");
							out.println("</div>");
						}
					%>
				</div>
				
			</div>
			
			<div id="right-column">
				<div class="announcement">
				    <p>Here are your achievements:</p>
				</div>
				<div class="announcement">
				    <p>Here are your messages:</p>
				</div>
			</div>
		</div>
        
        <div id="footer">
            <p>QuizWebsite 2012</p>
        </div>

    </div>
</body>
</html>