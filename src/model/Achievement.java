package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Achievement {
	public String name;
	public String description;
	public String imageUrl;
	public String dateCreated;
	public Achievement(String name, String description, String imageUrl, String dateCreated) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.dateCreated = dateCreated;
	}
	
	public static void makeAchievement(String name, String description, String imageUrl) {
		String query = "INSERT into " + DBConnection.achievementsTable + " (name, description, imageUrl) VALUES ('" + name + "', '" + description + "', '" + imageUrl + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void awardAchievement(String name, String username) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String query = "INSERT into " + DBConnection.userAchievementsTable + " (username, achievementName, dateCreated) VALUES ('" + username + "', '" + name + "', '" + stringDate + "')";
		try {
			DBConnection.newConnection().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Achievement> getAchievementsFor(String username) {
		String query = "SELECT achievements.name, achievements.description, achievements.imageUrl, filtered.dateCreated from achievements inner join (select * from userAchievements where username '" + username + "') filtered on achievements.name = filtered.achievementName";
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				achievements.add(new Achievement(rs.getString("name"), rs.getString("description"), rs.getString("imageUrl"), rs.getString("dateCreated")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return achievements;
	}
}
