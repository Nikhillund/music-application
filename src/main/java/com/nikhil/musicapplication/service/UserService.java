package com.nikhil.musicapplication.service;

import com.nikhil.musicapplication.constant.Genre;
import com.nikhil.musicapplication.dto.UserDto;
import com.nikhil.musicapplication.exception.AlbumNotFoundException;
import com.nikhil.musicapplication.exception.SongNotFoundException;
import com.nikhil.musicapplication.exception.UserNotFoundException;
import com.nikhil.musicapplication.model.Album;
import com.nikhil.musicapplication.model.Playlist;
import com.nikhil.musicapplication.model.Song;
import com.nikhil.musicapplication.model.User;
import com.nikhil.musicapplication.repository.AlbumRepository;
import com.nikhil.musicapplication.repository.PlaylistRepository;
import com.nikhil.musicapplication.repository.SongRepository;
import com.nikhil.musicapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    private final PlaylistRepository playlistRepository;
    public void followUser(Long userId, Long idToFollow) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Our user not found"));

        User userToFollow = userRepository.findById(idToFollow)
                .orElseThrow(() -> new UserNotFoundException("User to follow not found"));

        userToFollow.getFollowers().add(user);
        userRepository.save(userToFollow);
    }

    public void likeSong(Long userId, Long songId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("Song not found by id"));
        //if already liked will remove from liked song
        if(user.getLikedSongs().contains(song)) {
            user.getLikedSongs().remove(song);
            song.getLikeByUsers().remove(user);
        }
        else {
            user.getLikedSongs().add(song);
            song.getLikeByUsers().add(user);
        }

        userRepository.save(user);
    }

    public void likeAlbum(Long userId, Long albumId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException("Album not found by id"));
//if already liked will remove from liked album
        if(user.getLikedAlbums().contains(album)) {
            user.getLikedAlbums().remove(album);
            album.getLikeByUsers().remove(user);
        }
        else {
            user.getLikedAlbums().add(album);
            album.getLikeByUsers().add(user);
        }

        userRepository.save(user);
    }

    public void createUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phNumber(userDto.getPhNumber())
                .age(userDto.getAge())
                .gender(userDto.getGender())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);
    }

    public List<Song> getSongs(Genre genre, String year, Album album, Boolean liking, Long userId) {
        List<Song> songs = songRepository.findAll();

        if (genre != null) {
            songs = songs.stream()
                    .filter(song -> song.getGenre().equals(genre))
                    .toList();
        }

        if (year != null) {
            songs = songs.stream()
                    .filter(song -> song.getReleaseYear().equals(year))
                    .toList();
        }

        if (album != null) {
            songs = album.getSongs().stream().toList();
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (liking != null) {
            songs = songs.stream().filter(song -> song.getLikeByUsers().contains(user)).toList();
        }

        /*for(Song song : songs) {
            song.setLikeByUsers(Collections.emptySet());
        }*/

        return songs;
    }

    public List<Album> getAlbums(Genre genre, String year, Boolean liking, Long userId) {
        List<Album> albums = albumRepository.findAll();
        if (genre != null) {
            albums = albums.stream()
                    .filter(album -> album.getGenre().equals(genre))
                    .toList();
        }

        if(year != null) {
            albums = albums.stream()
                    .filter(album -> album.getSongs().stream()
                            .findFirst().get().getReleaseYear().equals(year))
                    .toList();
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (liking != null) {
            albums = albums.stream().filter(album1 -> album1.getLikeByUsers().contains(user)).toList();
        }
        /*for(Album album : albums) {
            album.setLikeByUsers(Collections.emptySet());
        }*/
        return albums;
    }

    public List<Playlist> getPlaylists(Genre genre, String year, Album album, Long userId) {
        List<Playlist> playlists = playlistRepository.findAll();
        if (genre != null) {
            playlists = playlists.stream()
                    .filter(playlist -> playlist.getSongs().stream().anyMatch(song -> song.getGenre().equals(genre)))
                    .toList();
        }

        if (year != null) {
            playlists = playlists.stream()
                    .filter(playlist -> playlist.getSongs().stream().anyMatch(song -> song.getReleaseYear().equals(year)))
                    .toList();
        }
        if (album != null) {
            playlists = playlists.stream().filter(playlist ->playlist.getAlbums().contains(album)).toList();
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        playlists = playlists.stream().filter(playlist -> playlist.getOwner().equals(user)).toList();

        /*for(Playlist playlist : playlists) {
            playlist.getAlbums().forEach(album1 -> album1.setLikeByUsers(Collections.emptySet()));
            playlist.getSongs().forEach(song -> song.setLikeByUsers(Collections.emptySet()));
            playlist.getOwner().setLikedAlbums(Collections.emptySet());
            playlist.getOwner().setLikedSongs(Collections.emptySet());
        }*/
        return playlists;
    }

    public Set<User> getFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return user.getFollowers();
    }
}
