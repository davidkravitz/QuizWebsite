package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Achievement {
	public String name;
	private String description;
	private String imageUrl;
	public Achievement(String name, String description, String imageUrl) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
	private void initalizeVariables() {
		String query = "SELECT * FROM " + DBConnection.achievementsTable + " WHERE name = '" + this.name + "'";
		try {
			ResultSet rs = DBConnection.newConnection().executeQuery(query);
			while (rs.next()) {
				this.description = rs.getString("description");
				this.imageUrl = rs.getString("imageUrl");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getDescription() {
		if (this.description.equals("")) {
			this.initalizeVariables();
		}
		return this.description;
	}
	
	public String getImageUrl() {
		if (this.imageUrl.equals("")) {
			this.initalizeVariables();
		}
		return this.imageUrl;
	}
	
	public static void makeAchievement(String name, String description, String imageUrl) {
		String query = "INSERT into " + DBConnection.achievementsTable + " (name, description, imageUrl) VALUES ('" + name + "', '" + description + "', '" + imageUrl + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void awardAchievement(String name, String username) {
		String query = "INSERT into " + DBConnection.userAchievementsTable + " (username, achievementName) VALUES ('" + username + "', '" + name + "')";
		try {
			DBConnection.newConnection().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
