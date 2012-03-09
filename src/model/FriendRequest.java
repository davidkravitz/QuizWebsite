package model;

import java.sql.SQLException;

public class FriendRequest {
	public String username;
	public String recipientName;
	public FriendRequest(String username, String recipientName) {
		this.username = username;
		this.recipientName = recipientName;
	}
	
	public static void sendFriendRequest(String username, String recipientName) {
		String query = "INSERT into " + DBConnection.friendRequestTable + " (username, requestedName) VALUES ('" + username + "', '" + recipientName + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void acceptFriendRequest
}
