package model;

public class QuizTake {
	public String quizName;
	public String username;
	public int score;
	public String dateTaken;
	public String timeSpent;
	
	public QuizTake(String quizName, String username, int score, String dateTaken, String timeSpent) {
		this.quizName = quizName;
		this.username = username;
		this.score = score;
		this.dateTaken = dateTaken;
		this.timeSpent = timeSpent;
	}
}
