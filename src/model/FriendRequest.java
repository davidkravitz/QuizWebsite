package model;

public class FriendRequest {
	public String username;
	public String recipientName;
	public FriendRequest(String username, String recipientName) {
		this.username = username;
		this.recipientName = recipientName;
	}
	
	public static void sendFriendRequest(String username, String recipientName) {
		
	}
}
