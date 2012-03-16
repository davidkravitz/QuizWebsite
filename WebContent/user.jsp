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
						ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
						quizzes.add(new Quiz(0, "category", "World War II", "createdBy",
								"description", "dateCreated", false, false, false));
						quizzes.add(new Quiz(1, "category", "Algebra I", "createdBy",
								"description", "dateCreated", false, false, false));
						quizzes.add(new Quiz(2, "category", "Political Philosophy",
								"createdBy", "description", "dateCreated", false, false,
								false));

						for (Quiz quiz : quizzes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"" + quiz.hashCode() + "\">" + quiz.name
									+ "</a>");
							out.println("</div>");
						}
					%>
				</div>
				
				<div class="profile-section">
					<div class="title">Quizzes Taken</div>
					<%
						username = request.getParameter("username");
						/* ArrayList<QuizTake> quizTakes = QuizTakes.getTakenQuizzesForUser(username); */

						// Stub filled with dummy quizzes
						quizzes = new ArrayList<Quiz>();
						quizzes.add(new Quiz(0, "category", "World War II", "createdBy",
								"description", "dateCreated", false, false, false));
						quizzes.add(new Quiz(1, "category", "Algebra I", "createdBy",
								"description", "dateCreated", false, false, false));
						quizzes.add(new Quiz(2, "category", "Political Philosophy",
								"createdBy", "description", "dateCreated", false, false,
								false));

						for (Quiz quiz : quizzes) {
							out.println("<div class=\"quiz\">");
							out.println("<a href=\"" + quiz.hashCode() + "\">" + quiz.name
									+ "</a>");
							out.println("</div>");
						}
					%>
				</div>

			</div>

			<div id="right-column">
			
				<div class="profile-sidebar">
					<div class="profile-sidebar-item">
						<div class="photo-list-title">Friends with 20 People</div>
						<table class="photo-list">
							<tbody>
								<%
									username = request.getParameter("username");
									ArrayList<User> friends = User.getFriendsFor(username, 0);
									int numFriends = friends.size();
									numFriends = 20;
									int numFriendsPerRow = 8;
									for (int i = 0; i < numFriends; i++) {
										if (i % numFriendsPerRow == 0) {
											out.println("<tr>");
										}
										out.println("<td></td");
										out.println("<a href=\"/user/3\">");
										out.println("<img class=\"profile-mini-image mini-image-with-tooltip\"");
										out.println("src=\"http://animal.discovery.com/mammals/cheetah/pictures/cheetah-picture.jpg\"");
										out.println("title=\"David Kravitz\" style=\"cursor: pointer; \">");
										out.println("</a>");
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