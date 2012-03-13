package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class DBConnection {
	static String account = "ccs108haidao";
	static String password = "bivauche";
	static String server = "mysql-user-master.stanford.edu";
	static String dbName = "c_cs108_haidao";
	static String userTable = "users";
	static String privateUsersTable = "privateUsers";
	static String adminUsersTable = "adminUsers";
	static String userAchievementsTable = "userAchievements";
	static String achievementsTable = "achievements";
	static String friendsTable = "friends";
	static String quizTable = "quizzes";
	static String quizTakeTable = "quizTakes";
	static String quizContentTable = "quizContents";
	static String quizRatingTable = "quizRatings";
	static String trQuestionTable = "trQuestinos";
	static String fibQuestionTable = "fibQuestions";
	static String mcQuestionTable = "mcQuestions";
	static String prQuestionTable = "prQuestions";
	static String friendRequestTable = "friendRequests";
	static String messageTable = "messages";
	static String reportedQuizzesTable = "reportedQuizzes";
	static String announcementTable = "announcements";
	private Connection con;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Used for reading the content of the database, e.g. SELECT. Returns 
	 * the result of the query in a ResultSet.
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	
	/*
	 * User for altering the databases. DROP TABLE, INSERT into TABLE, UPDATE TABLE,
	 * DELETE from TABLE, etc. Returns the number of rows affected by the query.
	 */
	public int executeUpdate(String update) throws SQLException {
		Statement stmt = con.createStatement();
		int numRowsAffected = stmt.executeUpdate(update);
		return numRowsAffected;
	}
	
	public static DBConnection newConnection() {
		return new DBConnection();
	}
	
}
