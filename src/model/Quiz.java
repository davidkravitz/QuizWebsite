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
	public String description;
	public boolean randomized;
	public boolean multiplePage;
	public boolean immediateCorrection;
	public int quizId;
	
	public Quiz(int quizId, String category, String name, String createdBy, String description, String dateCreated, boolean randomized, boolean multiplePage, boolean immediateCorrection) {
		this.questions = new HashSet<Question>();
		this.category = category;
		this.createdBy = createdBy;
		this.description = description;
		this.name = name;
		this.randomized = randomized;
		this.multiplePage = multiplePage;
		this.immediateCorrection = immediateCorrection;
		this.dateCreated = dateCreated;
		this.quizId = quizId;
	}
	
	public static Quiz getQuiz(int quizId) {
		String query = "SELECT * FROM " + DBConnection.quizTable + " WHERE quizId = '" + quizId + "'";
		Quiz quiz = null;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				quiz = new Quiz(Integer.valueOf(rs.getString("quizId")), rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), rs.getString("dateCreated"), rs.getString("randomized").equals("1") ? true : false, rs.getString("multiplePage").equals("1") ? true : false, rs.getString("immediateCorrection").equals("1") ? true : false);
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
				quizzes.add(new Quiz(Integer.valueOf(rs.getString("quizId")), rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), rs.getString("dateCreated"), rs.getString("randomized").equals("1") ? true : false, rs.getString("multiplePage").equals("1") ? true : false, rs.getString("immediateCorrection").equals("1") ? true : false));
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
				quizzes.add(new Quiz(Integer.valueOf(rs.getString("quizId")), rs.getString("category"), rs.getString("name"), rs.getString("createdBy"), rs.getString("description"), rs.getString("dateCreated"), rs.getString("randomized").equals("1") ? true : false, rs.getString("multiplePage").equals("1") ? true : false, rs.getString("immediateCorrection").equals("1") ? true : false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quizzes;
	}
	
	public static void removeQuiz(int quizId) {
		String query = "DELETE FROM " + DBConnection.quizTable + " WHERE quizId = '" + quizId + "'";
		try {
			DBConnection.newConnection().executeUpdate(query);
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
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Question> getQuestionsForQuiz(int quizId) {
		ArrayList<Question> questions = new ArrayList<Question>();
		String query = "SELECT * FROM " + DBConnection.trQuestionTable + " WHERE quizId = '" + quizId + "'";
		try {
			DBConnection db = DBConnection.newConnection();
			ResultSet rs = db.executeQuery(query);
			while (rs.next()) {
				questions.add(new TRQuestion(rs.getString("questionId"), rs.getString("question"), rs.getString("correctAnswer")));
			}
			query = "SELECT * FROM " + DBConnection.fibQuestionTable + " WHERE quizId = '" + quizId + "'";
			rs = db.executeQuery(query);
			while (rs.next()) {
				questions.add(new FIBQuestion(rs.getString("questionId"), rs.getString("question"), rs.getString("correctAnswer")));
			}
			query = "SELECT * FROM " + DBConnection.mcQuestionTable + " WHERE quizId = '" + quizId + "'";
			rs = db.executeQuery(query);
			while (rs.next()) {
				questions.add(new MCQuestion(rs.getString("questionId"), rs.getString("question"), rs.getString("correctAnswer"), rs.getString("icAnswerOne"), rs.getString("icAnswerTwo"), rs.getString("icAnswerThree")));
			}
			query = "SELECT * FROM " + DBConnection.prQuestionTable + " WHERE quizId = '" + quizId + "'";
			rs = db.executeQuery(query);
			while (rs.next()) {
				questions.add(new PRQuestion(rs.getString("questionId"), rs.getString("question"), rs.getString("correctAnswer"), rs.getString("imageUrl")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}
	
	public static ArrayList<Quiz> getPopularQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		
		return quizzes;
	}
	
	public static ArrayList<Quiz> getFriendsRecentQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		return quizzes;
	}
	
	public static ArrayList<Quiz> getRecentQuizzes(int limit) {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		return quizzes;
	}
}
