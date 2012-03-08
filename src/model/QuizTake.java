package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public static ArrayList<QuizTake> getTakenQuizzesForUser(String username) {
		ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE username = '" + username + "' ORDER BY dateTaken";
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quizTakes.add(new QuizTake(rs.getString("quizName"), rs.getString("username"), Integer.valueOf(rs.getString("score")), rs.getString("dateTaken"), rs.getString("timeSpent")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizTakes;
	}
}
