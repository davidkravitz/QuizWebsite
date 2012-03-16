package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuizTake {
	public String username;
	public int score;
	public String dateTaken;
	public String timeSpent;
	public int quizId;
	public int qtId;
	
	public QuizTake(int qtId, String username, int quizId, int score, String dateTaken, String timeSpent) {
		this.qtId = qtId;
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
				quizTakes.add(new QuizTake(Integer.valueOf(rs.getString("qtId")), rs.getString("username"), Integer.valueOf(rs.getString("quizId")), Integer.valueOf(rs.getString("score")), rs.getString("dateTaken"), rs.getString("timeSpent")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizTakes;
	}
	
	public static void recordCompletedQuiz(int quizId, String username, int score, String timeElapsed) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.quizTakeTable + " (username, quizId, score, dateTaken, timeSpent) VALUES ('" + username + "', '" + quizId + "', '" + score + "', '" + stringDate + "', '" + timeElapsed + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<QuizTake> getTopScorersFor(int quizId, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE quizId = '" + quizId + "' " + " ORDER BY score DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quizTakes.add(new QuizTake(Integer.valueOf(rs.getString("qtId")), rs.getString("username"), Integer.valueOf(rs.getString("quizId")), Integer.valueOf(rs.getString("score")), rs.getString("dateTaken"), rs.getString("timeSpent")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizTakes;
	}
	
	public static ArrayList<QuizTake> getQuizTakersFor(int quizId, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE quizId = '" + quizId + "' " + " ORDER BY dateTaken DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quizTakes.add(new QuizTake(Integer.valueOf(rs.getString("qtId")), rs.getString("username"), Integer.valueOf(rs.getString("quizId")), Integer.valueOf(rs.getString("score")), rs.getString("dateTaken"), rs.getString("timeSpent")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizTakes;
	}
	
	public static double getAverageScoreFor(int quizId) {
		String query = "SELECT AVG(score) FROM " + DBConnection.quizTakeTable + " WHERE quizId = '" + quizId + "'";
		double averageScore = 0.0;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				averageScore = Double.valueOf(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return averageScore;
	}
	
	public static int getNumTakersFor(String quizName) {
		return 1;
	}
	
	public static ArrayList<Quiz> getPopularQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		
		return quizzes;
	}
	
	public static ArrayList<Quiz> getFriendsRecentQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		return quizzes;
	}
}
