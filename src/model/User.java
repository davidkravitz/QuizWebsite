package model;

public class User {
	public String username;
	public String dateJoined;
	public String firstName;
	public String lastName;
	public User(String username, String dateJoined, String firstName, String lastName) {
		this.username = username;
		this.dateJoined = dateJoined;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User[] getFriends() {
		
	}
	
	public Message[] getMessages() {
		
	}
	
	public FriendRequest[] getFriendRequests() {
		
	}
	
	public Achievement[] getAchievements() {
		
	}
	
	public static boolean userExists(String username) {
		
	}
	
	public static User getUser(String username, String password) {
		
	}
	
	public static void createUser(String username, String password) {
		
	}
	
	public static void removeUser(String username) {
		
	}
}
