<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
<body>  
    <div id="wrapper">
        
        <%@include file="header.jsp"%>
        
        <div id="content">
			<div id="left-column">
				
				<div class="homepage-section">
					<div class="title">Reported Quizzes</div>
					<table border="1">
					<tr>
					<th>Action</th>
					<th>User</th>
					<th>quizId</th>
					<th>Message</th>
					<th>Type</th>
					<th>Date</th>
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
							out.println("<a href=\"QuizSummaryServlet?quiz=" + quizReport.quizId + "\">See Quiz</a>");
							out.println("</td>");
							out.println("<td>");
							out.println(quizReport.message);
							out.println("</td>");
							out.println("<td>");
							out.println(quizReport.type);
							out.println("</td>");
							out.println("<td>");
							out.println(quizReport.dateCreated);
							out.println("</td>");
							out.println("</tr>");
						}
					%>
					</table>
				</div>
				
				<br />
				
				<div class="add-announcement-section">
					<div class="title">Add Announcement</div>
					<form action="MakeAnnouncement" method="POST">
					<textarea rows="10" cols="30" name="announcement" ></textarea>
					<input type="submit" value="Add announcement" />
					</form>
				</div>
				
				
        <div id="footer">
            <p>QuizWebsite 2012</p>
        </div>

    </div>
</body>
</html>