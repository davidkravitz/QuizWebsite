<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript" src="static/js/add-question.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Question</title>
</head>

<body>
	
	<%
		Quiz curr = (Quiz) request.getSession().getAttribute("currCreationQuiz");
	%>
	<h3>
		Current Quiz:	<%=curr.name%></h3>
	<div id="questionCreation">
		<form name="questionSubmit">
			Select question type: <br/>
			<select id="typeSelect" name="questionType">
				<option value="MC">Multiple Choice</option>
				<option value="QR">Question Response</option>
				<option value="PR">Picture Response</option>
				<option value="FIB">Fill in the Blank</option>
			</select>

			<div id="addQuestion">
			</div>

			<div id="addAnswer">
			</div>
			
			<input type="submit" value="Add Question">
		</form>
	</div>

	<div id="answer"></div>

</body>
</html>