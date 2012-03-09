package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FriendRequest {
	public String requestedName;
	public String requestingName;
	public String dateCreated;
	public FriendRequest(String requestedName, String requestingName, String dateCreated) {
		this.requestedName = requestedName;
		this.requestingName = requestingName;
		this.dateCreated = dateCreated;
	}
	
	public static void sendFriendRequest(String requestedName, String requestingName) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.friendRequestTable + " (requestedName, requestingName, dateCreated) VALUES ('" + requestedName + "', '" + requestingName + "', '" + stringDate + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void acceptFriendRequest(String requestedName, String requestingName) {
		String query1 = "DELETE FROM " + DBConnection.friendRequestTable + " WHERE requestedName = '" + requestedName + "' and requestingName = '" + requestingName + "'";
		String query2 = "INSERT into " + DBConnection.friendsTable + " (username, friendName) VALUES ('" + requestedName + "', '" + requestingName + "')";
		String query3 = "INSERT into " + DBConnection.friendsTable + " (username, friendName) VALUES ('" + requestingName + "', '" + requestedName + "')";
		try {
			DBConnection db = DBConnection.newConnection();
			db.executeQuery(query1);
			db.executeQuery(query2);
			db.executeQuery(query3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<FriendRequest> getFriendRequestsFor(String username) {
		String query = "SELECT * FROM " + DBConnection.friendRequestTable + " WHERE requestedName = '" + username + "' ORDER BY dateCreated DESC";
		ArrayList<FriendRequest> friendRequests = new ArrayList<FriendRequest>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				friendRequests.add(new FriendRequest(rs.getString("username"), rs.getString("requestedName"), rs.getString("dateCreated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendRequests;
	}
}
