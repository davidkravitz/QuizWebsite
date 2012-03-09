package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Quiz {
	public HashSet<Question> questions;
	public String category;
	public String name;
	public String createdBy;
	public String dateCreated;
	public boolean randomized;
	public boolean multiplePage;
	public boolean immediateCorrection;
	
	public Quiz(String category, String name, String createdBy, String description, String dateCreated, boolean randomized, boolean multiplePage, boolean immediateCorrection) {
		this.questions = new HashSet<Question>();
		this.category = category;
		this.createdBy = createdBy;
		this.name = name;
		this.randomized = randomized;
		this.multiplePage = multiplePage;
		this.immediateCorrection = immediateCorrection;
		this.dateCreated = dateCreated;
	}
	
	public static Quiz getQuiz(String quizName) {
		String query = "SELECT TOP 1 * FROM " + DBConnection.quizTable + " WHERE quizName = '" + quizName + "'";
		Quiz quiz = null;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quiz = new Quiz(rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), rs.getString("dateCreated"), Boolean.valueOf(rs.getString("randomized")), Boolean.valueOf(rs.getString("multiplePage")), Boolean.valueOf(rs.getString("immediateCorrection")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quiz;
	}
	
	public static ArrayList<Quiz> getQuizzes(int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTable + " ORDER BY dateCreated DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quizzes.add(new Quiz(rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), rs.getString("dateCreated"), Boolean.valueOf(rs.getString("randomized")), Boolean.valueOf(rs.getString("multiplePage")), Boolean.valueOf(rs.getString("immediateCorrection"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
	
	public static ArrayList<Quiz> getQuizzesBy(String username, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTable + " WHERE createdBy = '" + username + "' " + " ORDER BY dateCreated DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quizzes.add(new Quiz(rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), rs.getString("dateCreated"), Boolean.valueOf(rs.getString("randomized")), Boolean.valueOf(rs.getString("multiplePage")), Boolean.valueOf(rs.getString("immediateCorrection"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
	
	public static ArrayList<User> getTopScorersFor(String quizName, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE quizName = '" + quizName + "' " + " ORDER BY score DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<User> users = new ArrayList<User>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				users.add(new User(rs.getString("username"), "", "", "", ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static ArrayList<User> getQuizTakersFor(String quizName, int limit) {
		String query = "SELECT * FROM " + DBConnection.quizTakeTable + " WHERE quizName = '" + quizName + "' " + " ORDER BY dateTaken DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<User> users = new ArrayList<User>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				users.add(new User(rs.getString("username"), "", "", "", ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static double getAverageScoreFor(String quizName) {
		String query = "SELECT AVG(score) FROM " + DBConnection.quizTakeTable + " WHERE quizName = '" + quizName + "'";
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
	
	public static void rankQuiz(String quizName, String username, int rating, String review) {
		String query = "INSERT into " + DBConnection.quizRatingTable + " (username, quizName, rating, review) VALUES ('" + username + "', '" + quizName + "', '" + rating + "', '" + review + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void recordCompletedQuiz(String quizName, String username, int score, String timeElapsed) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.quizTakeTable + " (username, quizName, score, dateTaken, timeSpent) VALUES ('" + username + "', '" + quizName + "', '" + score + "', '" + stringDate + "', '" + timeElapsed + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeQuiz(String quizName) {
		String query = "DELETE FROM " + DBConnection.quizTable + " WHERE name = '" + quizName + "'";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createQuiz(String quizName, String category, String description, String createdBy, boolean randomized, boolean multiplePage, boolean immediateCorrection) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.quizTable + " (name, category, description, createdBy, dateCreated, randomized, multiplePage, immediateCorrection) VALUES ('" + quizName + "', '" + category + "', '" + description + "', '" + createdBy + "', '" + stringDate + "', '" + (randomized ? 1 : 0) + "', '" + (multiplePage ? 1 : 0) + "', '" + (immediateCorrection ? 1 : 0) + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Question> getQuestionsForQuiz(String quizName) {
		ArrayList<Question> questions = new ArrayList<Question>();
		return questions;
	}
}
