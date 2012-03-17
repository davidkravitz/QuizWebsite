<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz in Progress</title>
</head>
<body>

	<%
		Quiz quiz = (Quiz) request.getAttribute("currentTakingQuiz");
		List<Question> questions = Quiz.getQuestionsForQuiz(quiz.quizId);

		out.println("<form action=\"SinglePageServlet\" method=\"post\" name=\"quiz\">");
		
		if(quiz.randomized)		
			Collections.shuffle(questions);
		
		int currQ = 1;
		for (Question q : questions) {
			String type = q.getQuestionType();
			out.println("<h3>Question "+currQ+"</h3>");
			if (type.equals("MC")) {
				MCQuestion mc = (MCQuestion) q;
				out.println(mc.getPrompt()+"<br/><br/>");
				
				String[] answers = (String[])mc.getAnswers();
				for(int i=0; i< answers.length; i++) {
					out.println("<input type=\"radio\" value=\""+answers[i]+
							"\" name=\""+mc.getQuestionNumber()+"\">"+answers[i]+"</input>");
					out.println("<br />");
				}
				
			} else if (type.equals("TR")) {
				TRQuestion tr = (TRQuestion) q;
				out.println(tr.getPrompt() + "<br/><br/>");
				out.println("<input type=\"text\" name=\""+tr.getQuestionNumber()+"\"></input>");
			} else if (type.equals("PR")) {
				PRQuestion pr = (PRQuestion) q;
				out.println(pr.getPrompt() + "<br/><br/>");
				out.println("<img src=\""+pr.getImageUrl()+"\" />");
				out.println("<br/><input type=\"text\" name=\""+pr.getQuestionNumber()+"\"></input>");
			} else if (type.equals("FIB")) {
				FIBQuestion fib = (FIBQuestion) q;
				out.println(fib.getPrompt() + "<br/><br/>");
				out.println("<input type=\"text\" name=\""+fib.getQuestionNumber()+"\"></input>");
			}
			
			currQ++;
		}
		out.println("<input type=\"submit\" value=\"Finish\" />");
		out.println("</form>");
	%>

</body>
</html>