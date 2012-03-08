package model;

public class Message {
	public String username;
	public String recipientName;
	public String message;
	public String title;
	public Message(String username, String recipientName, String message, String title) {
		this.username = username;
		this.recipientName = recipientName;
		this.message = message;
		this.title = title;
	}
	
	public static void sendMessage(String username, String recipientName, String message, String title) {
		
	}
}
