USE c_cs108_haidao;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS privateUsers;
DROP TABLE IF EXISTS adminUsers;
DROP TABLE IF EXISTS userAchievements;
DROP TABLE IF EXISTS achievements;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS quizTakes;
DROP TABLE IF EXISTS quizContents;
DROP TABLE IF EXISTS trQuestions;
DROP TABLE IF EXISTS fibQuestions;
DROP TABLE IF EXISTS mcQuestions;
DROP TABLE IF EXISTS prQuestions;
DROP TABLE IF EXISTS friendRequests;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS reportedQuizzes;
DROP TABLE IF EXISTS announcements;

CREATE TABLE users (
	firstName VARCHAR(64),
	lastName VARCHAR(64),
	username VARCHAR(64),
	dateJoined DATETIME,
	encryptedPassword VARCHAR(255),
	profileImage VARCHAR(255)
);

CREATE TABLE privateUsers (
	username VARCHAR(64)
);

CREATE TABLE adminUsers (
	username VARCHAR(64)
);

CREATE TABLE userAchievements (
	username VARCHAR(64),
	achievementName VARCHAR(64)
);

CREATE TABLE achievements (
	name VARCHAR(64),
	description TEXT,
	imageName VARCHAR(255)
);

CREATE TABLE friends (
	username VARCHAR(64),
	friendName VARCHAR(64)
);

CREATE TABLE quizzes (
	name VARCHAR(100),
	category VARCHAR(64),
	description TEXT,
	createdBy VARCHAR(64)
);

CREATE TABLE quizTakes(
	username VARCHAR(64),
	quizName VARCHAR(100),
	score BIGINT,
	dateTaken DATETIME,
	timeSpent TIME
);

CREATE TABLE quizRatings (
	username VARCHAR(64),
	quizName VARCHAR(100),
	rating BIGINT,
	review TEXT
);

CREATE TABLE quizContents(
	quizName VARCHAR(100),
	questionId VARCHAR(32)
);

CREATE TABLE trQuestions(
	questionId VARCHAR(32),
	question TEXT,
	correctAnswer VARCHAR(255),
	quizId BIGINT
);

CREATE TABLE fibQuestions(
	questionId VARCHAR(32),
	question TEXT,
	correctAnswer VARCHAR(255),
	quizId BIGINT
);

CREATE TABLE mcQuestions(
	questionId VARCHAR(32),
	question TEXT,
	correctAnswer VARCHAR(255),
	incorrectAnswerOne VARCHAR(255),
	incorrectAnswerTwo VARCHAR(255),
	incorrectAnswerThre VARCHAR(255),
	quizId BIGINT
);

CREATE TABLE prQuestions(
	questionId VARCHAR(32),
	question TEXT,
	imageUrl VARCHAR(255),
	correctAnswer VARCHAR(255),
	quizId BIGINT
);

CREATE TABLE friendRequests(
	username VARCHAR(64),
	requestedName VARCHAR(64)
);

CREATE TABLE messages(
	username VARCHAR(64),
	recipientName VARCHAR(64),
	message TEXT,
	title VARCHAR(255)
);

CREATE TABLE reportedQuizzes(
	username VARCHAR(64),
	quiz VARCHAR(64),
	message TEXT,
	type VARCHAR(255)
);

CREATE TABLE announcements(
	type VARCHAR(255),
	message TEXT,
	title VARCHAR(255),
	created DATETIME
);

INSERT INTO achievements VALUES
	("Unicorn", "You've taken your first quiz!", ""),
	("Terminator", "You've taken ten quizzes!", "")
