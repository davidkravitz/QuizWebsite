<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<link type="text/css" href="static/css/index.css" rel="stylesheet"/>
<link type="text/css" href="static/css/user.css" rel="stylesheet"/>
</head>
<body>
	<div id="wrapper">

		<%@include file="header.jsp"%>

		<div id="content">
			<div id="left-column">

				<div class="profile-header">
					<div class="profile-image">
						<img
							src="http://animal.discovery.com/mammals/cheetah/pictures/cheetah-picture.jpg">
					</div>
					<div class="profile-info">
						<div class="title profile-name"><%=request.getParameter("username")%></div>
						<%
							String currUser = request.getParameter("username");
							if (!currUser.equals(loggedInUser)) {
								int status = FriendRequest.getFriendStatus(loggedInUser, currUser);
								if (status == 0) {
									out.println("<form action=\"FriendRequestServlet\" method=\"post\">");
									out.println("<input type=\"hidden\" name=\"friendName\"");
									out.println("value=\"" + currUser + "\">");
									out.println("<input class=\"button profile-button\" type=\"submit\" value=\"Add Friend\">");
									out.println("</form>");
								} else if (status == 1) {
									out.println("Friend Request Sent.");
								} else if (status == 2) {
									out.println("<form action=\"AcceptFriendRequest\" method=\"post\">");
									out.println("<input type=\"hidden\" name=\"friendName\"");
									out.println("value=\"" + currUser + "\">");
									out.println("<input class=\"button profile-button\" type=\"submit\" value=\"Accept Friend Request\">");
									out.println("</form>");
								} else {
									out.println("Friends");
								}
							}
						 %>
					</div>
					<%
					if (!currUser.equals(loggedInUser)) {
						out.println("<form action=\"SendMessageServlet\" method=\"post\" class=\"message-form\">");
						out.println("<textarea name=\"message\">Send " + request.getParameter("username") + " a message.</textarea>");
						out.println("<input type=\"hidden\" name=\"recipientName\" value=\"" + request.getParameter("username") + "\" />"); 
						out.println("<input class=\"button profile-button\" type=\"submit\" value=\"Send\" />");
						out.println("</form>");
					}
					%>
				</div>

				<div class="profile-section">
					<div class="title">Quizzes Made</div>
					<%
						String username = request.getParameter("username");
						/* ArrayList<Quiz> quizzes = Quiz.getQuizzesBy(username, 0); */

						// Stub filled with dummy quizzes
						ArrayList<Quiz> quizzes = Quiz.getQuizzesBy(username, 0);
						for (Quiz quiz : quizzes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"QuizSummaryServlet?quiz=" + quiz.quizId + "\">" + quiz.name
									+ "</a>");
							out.println("</div>");
						}
					%>
				</div>
				
				<div class="profile-section">
					<div class="title">Quizzes Taken</div>
					<table border="1">
					<tr>
					<th>Quiz Name</th>
					<th>Score</th>
					<th>Time Spent</th>
					<th>Date</th>
					</tr>
					<%
						username = request.getParameter("username");
						/* ArrayList<QuizTake> quizTakes = QuizTakes.getTakenQuizzesForUser(username); */

						ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();

						for (QuizTake quizTake : quizTakes) {
							out.println("<tr class=\"quiz\">");
							out.println("<td><a href=\"QuizSummaryServlet?quiz=" + quizTake.quizId + "\">" + quizTake.quizName + "</a></td>");
							out.println("<td>");
							out.println(quizTake.score);
							out.println("</td>");
							out.println("<td>");
							out.println(quizTake.timeSpent);
							out.println("</td>");
							out.println("<td>");
							out.println(quizTake.dateTaken);
							out.println("</td>");
							out.println("</tr>");
						}
					%>
					</table>
				</div>

			</div>

			<div id="right-column">
			
				<div class="profile-sidebar">
					<div class="profile-sidebar-item">
								<%
									username = request.getParameter("username");
									ArrayList<User> friends = User.getFriendsFor(username, 0);
									int numFriends = friends.size();
									out.println("<div class=\"photo-list-title\">Friends with " + numFriends + " People</div>");
									out.println("<table class=\"photo-list\"><tbody>");
									int numFriendsPerRow = 8;
									User friend;
									for (int i = 0; i < numFriends; i++) {
										if (i % numFriendsPerRow == 0) {
											out.println("<tr>");
										}
										friend = friends.get(i);
										out.println("<td>");
										out.println("<a href=\"user.jsp?username=" + friend.username + "\">");
										out.println("<img class=\"profile-mini-image mini-image-with-tooltip\"");
										out.println("src=\"http://animal.discovery.com/mammals/cheetah/pictures/cheetah-picture.jpg\"");
										out.println("title=\"" + friend.firstName + " " + friend.lastName + "\" style=\"cursor: pointer; \">");
										out.println("</a>");
										out.println("</td>");
										if (i % numFriendsPerRow == 0) {
											out.println("<tr>");
										}
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
				
			</div>

		<div id="footer">
			<p>QuizWebsite 2012</p>
		</div>

	</div>
</body>
</html>