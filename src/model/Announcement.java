package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Announcement {
	public String type;
	public String message;
	public String title;
	public String created;
	public Announcement(String type, String message, String title, String created) {
		this.type = type;
		this.message = message;
		this.title = title;
		this.created = created;
	}
	
	public static ArrayList<Announcement> getAnnouncements(int limit) {
		String query = "SELECT * FROM " + DBConnection.announcementTable + " ORDER BY created DESC";
		if (limit > 0) {
			query += " LIMIT " + limit;
		}
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				announcements.add(new Announcement(rs.getString("type"), rs.getString("message"), rs.getString("title"), rs.getString("created")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return announcements;
	}
	
	public static void makeAnnouncement(String message, String title, String type) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.announcementTable + " (type, message, title, created) VALUES ('" + type + "', '" + message + "', '" + title + "', '" + stringDate + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
