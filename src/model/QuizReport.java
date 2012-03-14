package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizReport {
	public String username;
	public String quizName;
	public String message;
	public String type;
	public String dateCreated;
	public QuizReport(String username, String quizName, String message, String type, String dateCreated) {
		this.username = username;
		this.quizName = quizName;
		this.message = message;
		this.type = type;
		this.dateCreated = dateCreated;
	}
	
	public static void reportQuiz(String username, String quizName, String message, String type, String dateCreated) {
		String query = "INSERT into " + DBConnection.reportedQuizzesTable + " (username, quiz, message, type, dateCreated) VALUES ('" + username + "', '" + quizName + "', '" + message + "', '" + type + "', '" + dateCreated + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<QuizReport> getReportedQuizzes(int limit) {
		ArrayList<QuizReport> reportedQuizzes = new ArrayList<QuizReport>();
		String query = "SELECT * FROM " + DBConnection.reportedQuizzesTable + " ORDER BY dateCreated DESC";
		if (limit > 0) 
			query += " LIMIT " + limit;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				reportedQuizzes.add(new QuizReport(rs.getString("username"), rs.getString("quiz"), rs.getString("message"), rs.getString("type"), rs.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reportedQuizzes;
	}
}
