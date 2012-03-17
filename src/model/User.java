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
	public String dateJoined;
	public String firstName;
	public String lastName;
	public String imageUrl;
	public User(String username, String dateJoined, String firstName, String lastName, String imageUrl) {
		this.username = username;
		this.dateJoined = dateJoined;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
	}
	
	public static ArrayList<User> getFriendsFor(String username, int limit) {
		String query = "select users.username, users.firstName, users.lastName, users.dateJoined, users.imageUrl from users inner join (select * from friends where username = '" + username + "') filtered on users.username = filtered.friendName";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<User> friends = new ArrayList<User>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				friends.add(new User(rs.getString("username"), rs.getString("dateJoined"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("imageUrl")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
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
	
	public static User getUser(String username) {
		String query = "SELECT * FROM " + DBConnection.userTable + " WHERE username = '" + username + "'";
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
		System.out.println("Creating new user");
		String encryptedPw = createHash(password);
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.userTable + " (firstName, lastName, username, dateJoined, encryptedPassword, imageUrl) VALUES ('" + firstName + "', '" + lastName + "', '" + username + "', '" + stringDate + "', '" + encryptedPw + "', '" + imageUrl + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeUser(String username) {
		String query = "DELETE FROM " + DBConnection.userTable + " WHERE name = '" + username + "'";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<User> getSimilarUsernames(String username, int limit) {
		String query = "SELECT * FROM " + DBConnection.userTable + " WHERE username LIKE '%" + username + "%' " + " ORDER BY dateJoined DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<User> users = new ArrayList<User>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				users.add(new User(rs.getString("username"), rs.getString("dateJoined"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("imageUrl")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static boolean isAdminUser(String username) {
		String query = "select * from adminUsers where username = '" + username + "'";
		int resultSize = 0;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				resultSize++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSize > 0 ? true : false;
	}
}
