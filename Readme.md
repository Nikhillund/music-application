**Playlist**
- create playlist
- sample api - http://localhost:port/user/1/playlist
  - playload -
  {
    "name":"Playlist 4",
    "songIds":[1],
    "albumIds":[1],
    "ownerId":1,
    "visibility":"PUBLIC"
    }
- edit playlist
  - sample api  - http://localhost:port/user/1/playlist/3 
  - sample payload - {
    "name":"Playlist 4",
    "removeSongIds":[2]
    }
- delete playlist
  - sample api - http://localhost:port/user/1/playlist/2
- Follow user
  - sample api : http://localhost:port/user/2/follow/1
- Like song 
  - sample api : http://localhost:port/user/1/song/2/like
- user lists songs, album, his/her playlist with filter on genre, album, liking, year
  - api: http://localhost:port/user/1/songs or http://localhost:65303/user/1/songs?genre=ROCK
  - api:http://localhost:port/user/1/albums
  - api: http://localhost:port/user/1/playlists
- **query for initial data**
- INSERT INTO musicapplication.artist (id, create_at, updated_at, likes, name, artist_rank) VALUES (1, '2023-06-25 12:47:14', '2023-06-25 12:47:17', 0, 'Adele', 1);

INSERT INTO musicapplication.song (id, create_at, updated_at, genre, name, release_year) VALUES (1, '2023-06-25 12:04:49.558000', '2023-06-25 12:04:49.558000', 0,  'Thinking Out Loud', '2016');
INSERT INTO musicapplication.song (id, create_at, updated_at, genre, name, release_year) VALUES (2, '2023-06-25 12:06:41.914000', '2023-06-25 12:06:41.914000', 4,  'Uptown Funk', '2020');




INSERT INTO musicapplication.user (id, create_at, updated_at, age, email, gender, name, password, ph_number, username) VALUES (2, '2023-06-25 12:42:53', '2023-06-25 12:43:00', 20, 'nik@fm.com', 0, 'Nikhil', 'temp', '82388892', 'nixengg');

INSERT INTO musicapplication.album (id, create_at, updated_at, genre, name, artist_id) VALUES (1, '2023-06-25 19:05:27', null, 2, 'No', 1);
INSERT INTO musicapplication.user (id, create_at, updated_at, age, email, gender, name, password, ph_number, username) VALUES (1, '2023-06-25 12:42:53', '2023-06-25 12:43:00', 20, 'nik@fm.com', 0, 'Nikhil', 'temp', '82388892', 'nixengg');

