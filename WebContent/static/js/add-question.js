$(document).ready(function() {
	
$('#typeSelect').change(function() {
	var value = $('#typeSelect').val();
	$("#addQuestion").empty();
	$("#addAnswer").empty();
	if(value == "MC") {
		$('#addQuestion').html("Question <br/> <input type=\"text\" name=\"question\" size=\"60\"/>")
		$('#addAnswer').append("<input type=\"radio\" name=\"answerChoice\"><input type=\"text\" name=\"answerChoiceA\" width=\"30\" /></input>")
		$('#addAnswer').append("<br/><input type=\"radio\" name=\"answerChoice\"><input type=\"text\" name=\"answerChoiceB\" width=\"30\" /></input>")
		$('#addAnswer').append("<br/><input type=\"radio\" name=\"answerChoice\"><input type=\"text\" name=\"answerChoiceC\" width=\"30\" /></input>")
		$('#addAnswer').append("<br/><input type=\"radio\" name=\"answerChoice\"><input type=\"text\" name=\"answerChoiceD\" width=\"30\" /></input>")
	} else if(value == "QR") {
		$('#addQuestion').html("Question <br/> <input type=\"text\" name=\"question\" size=\"60\"/>")
		$('#addAnswer').html("Answer<br/> <input type=\"text\" name=\"answer\" size=\"30\"/>")
	} else if(value == "PR") {
		$('#addQuestion').html("Question picture link <br/> <input type=\"text\" name=\"question\" size=\"60\"/>")
		$('#addAnswer').html("Answer<br/> <input type=\"text\" name=\"answer\" size=\"30\"/>")
	} else { //FIB
		$('#addQuestion').html("Question with a single '_' char for the blank <br/> <input type=\"text\" name=\"question\" size=\"60\"/>");
		$('#addAnswer').html("Answer <br/> <input type=\"text\" name=\"question\" size=\"30\"/>")
	}
})

})
