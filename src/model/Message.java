package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Message {
	public String username;
	public String recipientName;
	public String message;
	public String title;
	public String dateCreated;
	public Message(String username, String recipientName, String message, String title, String dateCreated) {
		this.username = username;
		this.recipientName = recipientName;
		this.message = message;
		this.title = title;
		this.dateCreated = dateCreated;
	}
	
	public static void sendMessage(String username, String recipientName, String message, String title) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.messageTable + " (username, recipientName, message, title, dateCreated) VALUES ('" + username + "', '" + recipientName + "', '" + message + "', '" + title + "', '" + stringDate + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				messages.add(new Message(rs.getString("username"), rs.getString("recipientName"), rs.getString("message"), rs.getString("text"), rs.getString("dateCreated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public static ArrayList<Message> getReceivedMessagesFor(String username, int limit) {
		String query = "SELECT * FROM " + DBConnection.messageTable + " WHERE recipientName = '" + username + "'";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				messages.add(new Message(rs.getString("username"), rs.getString("recipientName"), rs.getString("message"), rs.getString("text"), rs.getString("dateCreated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
}
