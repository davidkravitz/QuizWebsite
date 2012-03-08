package model;

public class TRQuestion implements Question {
	
	public TRQuestion() {
		
	}

	@Override
	public String getQuestionType() {
		return null;
	}

	@Override
	public String getPrompt() {
		return null;
	}

	public boolean checkAnswer(String userInput) {
		return true;
	}
}
