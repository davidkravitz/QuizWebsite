package model;

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
	
	public static Announcement[] getAnnouncements(int limit) {
		
	}
	
	public static void makeAnnouncement(String message, String title, String type) {
		Announcement announcement = new Announcement(type, message, title, type);
	}
}
