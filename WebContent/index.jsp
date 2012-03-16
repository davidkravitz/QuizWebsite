<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuizWebsite</title>
</head>

<link type="text/css" href="static/css/index.css" rel="stylesheet"/>

<body>  
    <div id="wrapper">
        
        <%@include file="header.jsp"%>
        
        <div id="content">
			<div id="left-column">
			
				<div class="announcement">
				    <div class="title">Announcements</div>
				    <%
				    	ArrayList<Announcement>  announcements = Announcement.getAnnouncements(0);
				    	for (Announcement announcement : announcements) {
				    		out.println("<div class=\"announcement\">");
				    		out.println("<div class=\"title\">");
				    		out.println(announcement.title);
				    		out.println("</div>");
				    		out.println("<div class=\"message\">");
				    		out.println(announcement.message);
				    		out.println("</div></div>");
				    	}
				     %>
				</div>
				
				<div class="homepage-section">
					<div class="title">Popular Quizzes</div>
					<%
						int numQuizzes = 5;
						/* ArrayList<Quiz> quizzes = Quiz.getPopularQuizzes(numQuizzes); */
						
						// Stub filled with dummy quizzes
						ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
						/*quizzes.add(new Quiz("category", "World War II", "createdBy", "description", "dateCreated", false, false, false));
						quizzes.add(new Quiz("category", "Algebra I", "createdBy", "description", "dateCreated", false, false, false));
						quizzes.add(new Quiz("category", "Political Philosophy", "createdBy", "description", "dateCreated", false, false, false));
						*/
						for (Quiz quiz : quizzes) {
							out.println("<div class=\"challenge-request\">");
							out.println("<a href=\"quiz-summary.jsp?quiz=" + quiz.quizId + "\">");
							out.println(quiz.name);
							out.println("</a>");
							out.println("</div>");
						}
					%>
				</div>
				
				<div class="homepage-section">
					<div class="title">Recently Created Quizzes</div>
					<%
						/* ArrayList<Quiz> quizzes = Quiz.getQuizzes(numQuizzes); */
						for (Quiz quiz : quizzes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"" + quiz.hashCode() + "\">" + quiz.name + "</a>");
							out.println("</div>");
						}
					%>
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
						//quizTakes.add(new QuizTake("quizName", "username", 0, "dataTaken", "timeSpent"));
						//quizTakes.add(new QuizTake("quizName", "username", 0, "dataTaken", "timeSpent"));
						//quizTakes.add(new QuizTake("quizName", "username", 0, "dataTaken", "timeSpent"));

						
						for (QuizTake quizTake : quizTakes) {
							out.println("<div class=\"quiz\">");
							//out.println("<a href=\"" + quizTake.quizName + "\">" + quizTake.quizName + "</a>");
							out.println("</div>");
						}
					%>
				</div>
				
				
				<div class="homepage-section">
					<div class="title">Quizzes Your Friends Recently Took</div>
										<%
		/* 				String username = (String) session.getAttribute("username");
						ArrayList<QuizTake> quizTakes = QuizTake.getFriendsRecentQuizzes(username); */
						
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
				
				<div class="homepage-section">
					<div class="title">Quizzes Your Friends Recently Created</div>
										<%
		/* 				String username = (String) session.getAttribute("username");
						ArrayList<QuizTake> quizTakes = QuizTake.getFriendsRecentQuizzes(username); */
						
						// Stub filled with dummy Users
						quizTakes.add(new QuizTake(1, "username", 0, 0, "dataTaken", "timeSpent"));
						username = "david";
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
