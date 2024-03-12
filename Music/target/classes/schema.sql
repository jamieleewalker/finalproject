DROP TABLE IF EXISTS artist_genre;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS song;
DROP TABLE IF EXISTS artist;

CREATE TABLE artist (
	artist_id int NOT NULL AUTO_INCREMENT,
	first_name varchar(60) NOT NULL,
	last_name varchar(60) NOT NULL,
	PRIMARY KEY (artist_id)
);

CREATE TABLE genre (
	genre_id int NOT NULL AUTO_INCREMENT,
	genre_name varchar(60),
	PRIMARY KEY (genre_id)
);

CREATE TABLE song (
	song_id int NOT NULL AUTO_INCREMENT,
	artist_id int NULL,
	song_title varchar(256) NOT NULL,
	PRIMARY KEY (song_id),
	FOREIGN KEY (artist_id) REFERENCES artist (artist_id) ON DELETE CASCADE
);

CREATE TABLE artist_genre (
	artist_id INT NOT NULL,
	genre_id INT NOT NULL,
	FOREIGN KEY (artist_id) REFERENCES artist (artist_id) ON DELETE CASCADE,
	FOREIGN KEY (genre_id) REFERENCES genre (genre_id) ON DELETE CASCADE,
	UNIQUE KEY (artist_id, genre_id) 
);