$(document).ready(function() {

	$('#addQuestion').html("Question <br/> <input type=\"text\" name=\"question\" size=\"60\"/>")
	$("#addAnswer").append("Add answer choices, and select the correct one: <br/>")
	$('#addAnswer').append("<input type=\"radio\" value=\"A\" name=\"answerChoice\" checked><input type=\"text\" name=\"A\" width=\"30\" /></input>")
	$('#addAnswer').append("<br/><input type=\"radio\" value=\"B\" name=\"answerChoice\"><input type=\"text\" name=\"B\" width=\"30\" /></input>")
	$('#addAnswer').append("<br/><input type=\"radio\" value=\"C\" name=\"answerChoice\"><input type=\"text\" name=\"C\" width=\"30\" /></input>")
	
	$('#typeSelect').change(function() {
		var value = $('#typeSelect').val();
		$("#addQuestion").empty();
		$("#addAnswer").empty();
		if(value == "MC") {
			$('#addQuestion').html("Question <br/> <input type=\"text\" name=\"question\" size=\"60\"/>")
			$("#addAnswer").append("Add answer choices, and select the correct one: <br/>")
			$('#addAnswer').append("<input type=\"radio\" value=\"A\" name=\"answerChoice\" checked><input type=\"text\" name=\"A\" width=\"30\" /></input>")
			$('#addAnswer').append("<br/><input type=\"radio\" value=\"B\" name=\"answerChoice\"><input type=\"text\" name=\"B\" width=\"30\" /></input>")
			$('#addAnswer').append("<br/><input type=\"radio\" value=\"C\" name=\"answerChoice\"><input type=\"text\" name=\"C\" width=\"30\" /></input>")
		} else if(value == "QR") {
			$('#addQuestion').html("Question <br/> <input type=\"text\" name=\"question\" size=\"60\"/>")
			$('#addAnswer').html("Answer<br/> <input type=\"text\" name=\"answer\" size=\"30\"/>")
		} else if(value == "PR") {
			$('#addQuestion').html("Question <br/> <input type=\"text\" name=\"question\" size=\"60\" />")
			$('#addQuestion').html("Question picture link <br/> <input type=\"text\" name=\"picture\" size=\"60\"/>")
			$('#addAnswer').html("Answer<br/> <input type=\"text\" name=\"answer\" size=\"30\"/>")
		} else { //FIB
			$('#addQuestion').html("Question with a single '_' char for the blank <br/> <input type=\"text\" name=\"question\" size=\"60\"/>");
			$('#addAnswer').html("Answer <br/> <input type=\"text\" name=\"question\" size=\"30\"/>")
		}
	})

})
