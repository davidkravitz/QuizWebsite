package model;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
	public String username;
	private String dateJoined;
	private String firstName;
	private String lastName;
	private String imageUrl;
	public User(String username, String dateJoined, String firstName, String lastName, String imageUrl) {
		this.username = username;
		this.dateJoined = dateJoined;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
	}
	
	private void initializeVariables() {
		String query = "SELECT * FROM " + DBConnection.userTable + " WHERE username = '" + this.username + "'";
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				this.dateJoined = rs.getString("dateJoined");
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.imageUrl = rs.getString("imageUrl");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getDateJoined() {
		if (this.dateJoined.equals("")) {
			this.initializeVariables();
		}
		return this.dateJoined;
	}
	
	public String getImageUrl() {
		if (this.imageUrl.equals("")) {
			this.initializeVariables();
		}
		return this.imageUrl;
	}
	
	public String getFirstName() {
		if (this.firstName.equals("")) {
			this.initializeVariables();
		}
		return this.firstName;
	}
	
	public String getLastName() {
		if (this.lastName.equals("")) {
			this.initializeVariables();
		}
		return this.lastName;
	}
	
	public static ArrayList<User> getFriendsFor(String username, int limit) {
		String query = "SELECT * FROM " + DBConnection.friendsTable + " WHERE username = '" + username + "'";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<User> friends = new ArrayList<User>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				friends.add(new User(rs.getString("friendName"), "", "", "", ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}
	
	public static ArrayList<Message> getSentMessagesFor(String username, int limit) {
		String query = "SELECT * FROM " + DBConnection.messageTable + " WHERE username = '" + username + "'";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				messages.add(new Message(rs.getString("username"), rs.getString("recipientName"), rs.getString("message"), rs.getString("text")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public static ArrayList<Message> getReceivedMessagesFor(String username, int limit) {
		String query = "SELECT * FROM " + DBConnection.friendsTable + " WHERE recipientName = '" + username + "'";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				messages.add(new Message(rs.getString("username"), rs.getString("recipientName"), rs.getString("message"), rs.getString("text")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public static ArrayList<FriendRequest> getFriendRequestsFor(String username) {
		String query = "SELECT * FROM " + DBConnection.friendRequestTable + " WHERE requestedName = '" + username + "'";
		ArrayList<FriendRequest> friendRequests = new ArrayList<FriendRequest>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				friendRequests.add(new FriendRequest(rs.getString("username"), rs.getString("requestedName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendRequests;
	}
	
	public static ArrayList<Achievement> getAchievementsFor(String username) {
		String query = "SELECT * FROM " + DBConnection.userAchievementsTable + " WHERE username = '" + username + "'";
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				achievements.add(new Achievement(rs.getString("achievementName"), "", ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achievements;
	}
	
	public static boolean userExists(String username) {
		String query = "SELECT * FROM " + DBConnection.userTable + " WHERE username = '" + username + "'";
		int numRows = 0;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				numRows++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (numRows > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static User getUser(String username, String password) {
		String encryptedPw = createHash(password);
		String query = "SELECT * FROM " + DBConnection.userTable + " WHERE username = '" + username + "' AND encryptedPassword = '" + encryptedPw + "'";
		User user = null;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				user = new User(rs.getString("username"), rs.getString("dateJoined"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("imageUrl"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean correctPassword(String username, String password) {
		String encryptedPw = createHash(password);
		String query = "SELECT * FROM " + DBConnection.userTable + " WHERE username = '" + username + "' AND encryptedPassword = '" + encryptedPw + "'";
		int numRows = 0;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				numRows++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (numRows > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String createHash(String word) {
		String hashedWord = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");;
			synchronized(md) {
				md.reset();
				md.update(word.getBytes());
			}
			byte[] inputDigest = md.digest();
			hashedWord = hexToString(inputDigest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hashedWord;
	}
	
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	public static void createUser(String username, String password, String firstName, String lastName, String imageUrl) {
		String encryptedPw = createHash(password);
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.userTable + " (firstName, lastName, username, dateJoined, encryptedPassword, imageUrl) VALUES ('" + firstName + "', '" + lastName + "', '" + username + "', '" + stringDate + "', '" + encryptedPw + "', '" + imageUrl + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeUser(String username) {
		String query = "DELETE FROM " + DBConnection.userTable + " WHERE name = '" + username + "'";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
