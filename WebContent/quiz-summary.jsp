<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	Quiz quiz = (Quiz) request.getAttribute("quiz");	
	User user = (User) request.getSession().getAttribute("user");
	if (user == null)
		user = User.getUser((String) request.getSession().getAttribute("username"));
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Summary: <%= quiz.name %></title>
</head>
<body>

<h1>Quiz Summary: <%= quiz.name %></h1>

<div id="quizInfo">
	<h3>Description</h3>
	<% 
		out.println(quiz.description);
	 %>
	 
	<h3>Creator</h3>
	<%
		out.println("<a href=\"user-info.jsp?name="+quiz.createdBy+"\">"+quiz.createdBy+"</a>");
	%>
</div>

<div id="userOnQuiz">
	<h3>Past History</h3>
	<%
		List<QuizTake> quizTakes = QuizTake.getTakenQuizzesForUser(user.username);
		
		List<QuizTake> takesForThisQuiz = new ArrayList<QuizTake>();
		for(QuizTake quizTake : quizTakes) {
			if(quizTake.quizId == quiz.quizId) {
				takesForThisQuiz.add(quizTake);
			}
		}
		
		//dummy data
		//takesForThisQuiz.add(new QuizTake(1, user.username, quiz.quizId, 50, "Today", "10 sec"));

		
		if(takesForThisQuiz.isEmpty()) {
			out.println("You have not taken this quiz yet");
		} else {
			out.println("<table> <tr> <th>Date</th> <th>Score</th> <th>Time Elapsed</th> </tr>");
			
			for(QuizTake curr: takesForThisQuiz) {
				out.println("<tr>");
				out.println("<td>"+curr.dateTaken+"</td>");
				out.println("<td>"+curr.score+"</td>");
				out.println("<td>"+curr.timeSpent+"</td>");
				out.println("</tr>");
			}
			
			out.println("</table>");
		}
	%>
</div>

<div id="otherUsersOnQuiz">
	<div id="allTime">
		<h3>High Scorers (All Time)</h3>
		<%
			List<QuizTake> allTimeBest = QuizTake.getTopScorersFor(quiz.quizId, 10);
			if(allTimeBest.isEmpty()) {
				out.println("No one has taken this quiz yet");
			} else {
				out.println("<ol>");
				for(QuizTake curr: allTimeBest) {
					out.println("<li>"+curr.username+" with score "+curr.score+"</li>");
				}
				out.println("</ol>");
			}
		%>
		
	</div>
	
	<div id="today">
		<h3>High Scorers (Today)</h3>
	</div>
	
	<div id="ratings">
		<h3>Ratings</h3>
		<table border="1">
			<tr>
			<th>Rating</th>
			<th>Message</th>
			<th>Username</th>
			<th>Date</th>
			</tr>
		<%
			ArrayList<QuizRating> ratings = QuizRating.getQuizRatingsFor(quiz.quizId, 10);
			for (QuizRating rating : ratings) {
				out.println("<tr class=\"rating\">");
				out.println("<td>");
				out.println(rating.rating);
				out.println("</td>");
				out.println("<td>");
				out.println(rating.review);
				out.println("</td>");
				out.println("<td>");
				out.println(rating.username);
				out.println("</td>");
				out.println("<td>");
				out.println(rating.dateCreated);
				out.println("</td>");
				out.println("</tr>");
			}
		
		 %>
		 </table>
	</div>
	
	<div id="recent">
	</div>
	
	<div id="summary">
		<h3>Summary</h3>
		<%
			//double avgScore = Quiz.getAverageScoreFor(quiz.quizId);
			//int numUsers = QuizTake.getQuizTakersFor(quiz.quizId, 0);
		
			//out.println("Average Score: "+avgScore);
			//out.println("Number of users: "+numUsers);
		%>		
	</div>
</div>

<div id="challengeFriend">
<h3>Challenge</h3>
<form action="ChallengeFriend" method="post">
Friend's username:&nbsp;&nbsp;&nbsp;<input type="text" name="friendName" /> &nbsp;
<input type="hidden" name="quizId" value="<%= quiz.quizId %>" />
<input type="hidden" name="quizName" value="<%= quiz.name %>" />
<input type="submit" value="Challenge!" />
</form>
</div>

<br/>

<div id="takeQuizLink">
	<%
	out.println("<a href=\"take-quiz.jsp?quiz="+quiz.quizId+"\">");
	out.println("Take the quiz </a>");
	%>
</div>

<br/>
<div id="reportQuiz">
<h3>Report Quiz</h3>
	<form action="ReportQuiz" method="post">
	Type:
	<select name="type">
	<option value="Bad quiz">Bad quiz</option>
	<option value="Inappropriate">Inappropriate</option>
	</select>
	<br/>
	Description:<br/>
	<textarea name="description" rows="5" cols="30"></textarea><br/>
	<input type="hidden" name="quizId" value="<%= quiz.quizId %>" />
	<input type="submit" value="Report Quiz" />
	</form>
</div>

</body>
</html>