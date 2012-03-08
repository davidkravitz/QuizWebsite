package model;

public class PRQuestion implements Question {

	public PRQuestion() {
		
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
		return true;
	}
}
