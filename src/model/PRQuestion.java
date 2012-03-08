package model;

public class PRQuestion extends Question {
	private String prompt;
	private String correctAnswer;
	private String imageUrl;

	public PRQuestion(String imageUrl, String correctAnswer, String prompt, String questionId) {
		this.questionId = questionId;
		this.prompt = prompt;
		this.correctAnswer = correctAnswer;
		this.imageUrl = imageUrl;
	}

	@Override
	public String getPrompt() {
		return prompt;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public boolean checkAnswer() {
		return true;
	}
}
