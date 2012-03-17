<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Quiz</title>
</head>
<body>

First, enter some basic data about the quiz
<form name="quizProperties" action="CreateQuizServlet" method="post">
    Quiz name: <input type="text" name="quizName" /><br />
    Description: <br/><textarea name="description" rows="10" cols="30" ></textarea><br />
    <input type="checkbox" name="randomize" />Randomize questions<br />
    <input type = "checkbox" name="immediate" />Immediate feedback<br/>
    Page Style: <br />
    <input type="radio" name="page" value="single" checked />Single<br/>
    <input type="radio" name="page" value="multiple" />Multiple<br/>
    Category:<select name="category">
    	<%
    		String[] categories = {"Math", "English", "Art", "History", "Science", "General", "Music", "Other" };
    		for(String category : categories) {
    			out.println("<option value=\""+category+"\">"+category+"</option>");
    		}
    	%>
    </select>
    <br/><input type="submit" value="Continue" />
</form>

</body>
</html>