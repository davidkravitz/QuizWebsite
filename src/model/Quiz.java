package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class Quiz {
	public HashSet<Question> questions;
	public String category;
	public String name;
	public String createdBy;
	public boolean randomized;
	public boolean multiplePage;
	public boolean immediateCorrection;
	
	public Quiz(String category, String name, String createdBy, String description, boolean randomized, boolean multiplePage, boolean immediateCorrection) {
		this.questions = new HashSet<Question>();
		this.category = category;
		this.createdBy = createdBy;
		this.name = name;
		this.randomized = randomized;
		this.multiplePage = multiplePage;
		this.immediateCorrection = immediateCorrection;
	}
	
	public static Quiz getQuiz(String quizName) {
		String query = "SELECT TOP 1 * FROM " + DBConnection.quizTable + " WHERE quizName = '" + quizName + "'";
		Quiz quiz = null;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quiz = new Quiz(rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), Boolean.valueOf(rs.getString("randomized")), Boolean.valueOf(rs.getString("multiplePage")), Boolean.valueOf(rs.getString("immediateCorrection")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quiz;
	}
	
	public static ArrayList<Quiz> getMostRecentlyCreated() {
		
	}
	
	public static ArrayList<Quiz> getAllQuizzes() {
		
	}
	
	public static ArrayList<Quiz> getQuizzesBy(String username) {
		
	}
	
	public static ArrayList<User> getTopScorersFor(String quizName) {
		
	}
	
	public static ArrayList<User> getQuizTakersFor(String quizName) {
		
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
