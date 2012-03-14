package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public static void recordCompletedQuiz(String quizName, String username, int score, String timeElapsed) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.quizTakeTable + " (username, quizName, score, dateTaken, timeSpent) VALUES ('" + username + "', '" + quizName + "', '" + score + "', '" + stringDate + "', '" + timeElapsed + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<QuizTake> getTopScorersFor(String quizName, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE quizName = '" + quizName + "' " + " ORDER BY score DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();
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
	
	public static ArrayList<QuizTake> getQuizTakersFor(String quizName, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE quizName = '" + quizName + "' " + " ORDER BY dateTaken DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<QuizTake> quizTakes = new ArrayList<QuizTake>();
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
