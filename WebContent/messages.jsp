<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuizWebsite</title>

<link type="text/css" href="static/css/messages.css" rel="stylesheet"/>

</head>

<link type="text/css" href="static/css/index.css" rel="stylesheet"/>

<body>  
    <div id="wrapper">
        
        <%@include file="header.jsp"%>
        
        <div id="content">
			<div id="left-column">
				<div class="homepage-section">
					<div class="title">Friend Requests</div>
					<%
						String username = (String) request.getSession().getAttribute("username");
						ArrayList<FriendRequest> friendRequests = FriendRequest
								.getFriendRequestsFor(username);
						for (FriendRequest friendRequest : friendRequests) {
							out.println("<div class=\"friend-request\">");
							out.println("<a href=\"user.jsp?username=" + friendRequest.requestingName + "\">");
							out.println(friendRequest.requestingName);
							out.println("</a>");
							out.println("</div>");
							out.println("<form action=\"AcceptFriendRequestServlet\" method=\"post\">");
							out.println("<input type=\"hidden\" name=\"requestingName\" value=\"" + friendRequest.requestingName + "\">");
							out.println("<input class=\"button\" type=\"submit\" value=\"Accept Friend Request\">");
							out.println("</form>");
						}
					%>
				</div>

				<div class="homepage-section">
					<div class="title">Challenges</div>
					<%
						username = (String) request.getSession().getAttribute("username");
						ArrayList<Challenge> challenges = Challenge.getChallengesForUser(
								username, 0);
						for (Challenge challenge : challenges) {
							out.println("<div class=\"challenge\">");
							out.println(challenge.username + " has challenged you to take " + challenge.quizName + ".");
							out.println("<div class=\"challenge-request\">");
							out.println("<a href=\"AcceptChallenge?quiz=" + challenge.quizId + "&cId=" + challenge.challengeId + "\">");
							out.println("Accept Challenge!");
							out.println("</a>");
							out.println("</div>");
							out.println("</div>");
						}
					%>
				</div>
				
				<div class="homepage-section">
					<div class="title">Notes</div>
					<%
						username = (String) request.getSession().getAttribute("username");
						ArrayList<Message> messages = Message.getReceivedMessagesFor(
								username, 0);
						for (Message message : messages) {
							out.println("<div class=\"message\">");
							out.println("<div>");
							out.println("From: " + message.username);
							out.println("<div>");
							out.println("</div>");
							out.println("Message: " + message.message);
							out.println("</div>");
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