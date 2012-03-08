package model;

import java.util.HashSet;

public class Quiz {
	public HashSet<Question> questions;
	public String category;
	public String name;
	public String createdBy;
	public boolean randomized;
	public boolean multiplePage;
	public boolean immediateCorrection;
	
	public Quiz(String category, String name, String createdBy, boolean randomized, boolean multiplePage, boolean immediateCorrection) {
		this.questions = new HashSet<Question>();
		this.category = category;
		this.createdBy = createdBy;
		this.name = name;
		this.randomized = randomized;
		this.multiplePage = multiplePage;
		this.immediateCorrection = immediateCorrection;
	}
	
	public static Quiz getQuiz(String quizName) {
		return new Quiz("", "", "", true, true, true);
	}
	
	public static Quiz[] getMostRecentlyCreated() {
		
	}
	
	public static Quiz[] getAllQuizzes() {
		
	}
	
	public static Quiz[] getQuizzesBy(String username) {
		
	}
	
	public static User[] getTopScorersFor(String quizName) {
		
	}
	
	public static User[] getQuizTakersFor(String quizName) {
		
	}
	
	public static double getAverageScoreFor(String quizName) {
		
	}
	
	public static void rankQuiz(String quizName, String username, int rating, String review) {
		
	}
	
	public static void recordCompletedQuiz(String quizName, String username, int score, String timeElapsed) {
		
	}
	
	public static void removeQuiz(String quizName) {
		
	}
}
