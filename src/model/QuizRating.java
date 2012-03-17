package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class QuizRating {
	public String username;
	public int quizId;
	public int rating;
	public String review;
	public String dateCreated;
	public int qrId;
	public QuizRating(int qrId, String username, int quizId, int rating, String review, String dateCreated) {
		this.qrId = qrId;
		this.username = username;
		this.quizId = quizId;
		this.rating = rating;
		this.review = review;
		this.dateCreated = dateCreated;
	}
	
	public static void rankQuiz(int quizId, String username, int rating, String review) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.quizRatingTable + " (username, quizId, rating, review, dateCreated) VALUES ('" + username + "', '" + quizId + "', '" + rating + "', '" + review + "', '" + stringDate + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<QuizRating> getQuizRatingsFor(int quizId, int limit) {
		ArrayList<QuizRating> ratings = new ArrayList<QuizRating>();
		String query = "SELECT * FROM " + DBConnection.quizRatingTable + " WHERE quizId = " + quizId + " ORDER BY dateCreated DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				ratings.add(new QuizRating(Integer.valueOf(rs.getString("qrId")), rs.getString("username"), Integer.valueOf(rs.getString("quizId")), Integer.valueOf(rs.getString("rating")), rs.getString("review"), rs.getString("dateCreated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ratings;
	}
}
