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
					<%
					out.println("<img src=\"");
					out.println(model.User.getUser((String) request.getParameter("username")).imageUrl); 
					out.println("\" />");
					%>
					</div>
					<div class="profile-info">
						<%
							String currUser = request.getParameter("username");
							User user = User.getUser(currUser);
							out.println("<div class=\"title profile-name\">");
							out.println(user.firstName + " " + user.lastName);
							out.println("</div>");
						
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
									
									out.println("You and " + user.firstName + " are friends.");
								}
							}
						 %>
					</div>
					<%
					if (!currUser.equals(loggedInUser)) {
						User curr = User.getUser(currUser);
						out.println("<form action=\"SendMessageServlet\" method=\"post\" class=\"message-form\">");
						out.println("<textarea name=\"message\">Send " + curr.firstName + " a message.</textarea>");
						out.println("<input type=\"hidden\" name=\"recipientName\" value=\"" + request.getParameter("username") + "\" />"); 
						out.println("<input class=\"button profile-button\" type=\"submit\" value=\"Send\" />");
						out.println("</form>");
					}
					%>
				</div>
				
				<%
				if (currUser.equals(loggedInUser)) {
					out.println("<div class='profile-section'>");
					out.println("<div class=\"title\">Rate these Quizzes</div>");
					
					ArrayList<Quiz> quizzes = Quiz.getUnratedQuizzes(loggedInUser, 10);
					for (Quiz quiz : quizzes) {
							out.println("<div class=\"quiz\">");
							out.println("Quiz: <a href=\"QuizSummaryServlet?quiz=" + quiz.quizId + "\">" + quiz.name + "</a> <br />Rating: ");
							out.println("<form method=\"post\" action=\"RateQuiz\"><select name=\"rating\">");
							out.println("<option value=\"1\">1</option>");
							out.println("<option value=\"2\">2</option>");
							out.println("<option value=\"3\">3</option>");
							out.println("<option value=\"4\">4</option>");
							out.println("<option value=\"5\">5</option>");
							out.println("<option value=\"6\">6</option>");
							out.println("<option value=\"7\">7</option>");
							out.println("<option value=\"8\">8</option>");
							out.println("<option value=\"9\">9</option>");
							out.println("<option value=\"10\">10</option>");
							out.println("</select>");
							out.println("<input type=\"hidden\" name=\"quizId\" value=\"" + quiz.quizId + "\" />");
							out.println("Message: <input type=\"text\" name=\"message\" />");
							out.println("<input type=\"submit\" value=\"Rate!\" /></form>");
							out.println("</div><br />");
					}
					 
					out.println("</div>");
				}
				 %>

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
										out.println("src=\"" + friend.imageUrl + "\"");
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