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
			DBConnection.newConnection().executeUpdate(query);
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
			db.executeUpdate(query1);
			db.executeUpdate(query2);
			db.executeUpdate(query3);
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
				friendRequests.add(new FriendRequest(rs.getString("requestedName"), rs.getString("requestingName"), rs.getString("dateCreated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendRequests;
	}
	
	
	// returns 0 if not friends, returns 1 if request sent to friend, returns 2 if
	// request sent to you, 3 if friends
	public static int getFriendStatus(String user, String friend) {
		String query = "select * from friends where username = '" + user + "' and friendName = '" + friend + "'";
		int numResults = 0;
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				numResults++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (numResults > 0)
			return 3;
		query = "select * from friendRequests where requestedName = '" + friend + "' and requestingName = '" + user + "'";
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				numResults++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (numResults > 0)
			return 1;
		query = "select * from friendRequests where requestedName = '" + user + "' and requestingName = '" + friend + "'";
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				numResults++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (numResults > 0)
			return 2;
		return 0;
	}
}
