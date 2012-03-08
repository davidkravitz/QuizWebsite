package model;

public class MCQuestion implements Question {
	
	public MCQuestion() {
		
	}

	@Override
	public String getQuestionType() {
		return null;
	}

	@Override
	public String getPrompt() {
		return null;
	}

	public boolean checkAnswer() {
		return false;
	}
}
