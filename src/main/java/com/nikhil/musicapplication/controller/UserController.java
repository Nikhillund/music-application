package com.nikhil.musicapplication.controller;

import com.nikhil.musicapplication.constant.Genre;
import com.nikhil.musicapplication.dto.UserDto;
import com.nikhil.musicapplication.model.Album;
import com.nikhil.musicapplication.model.Playlist;
import com.nikhil.musicapplication.model.Song;
import com.nikhil.musicapplication.model.User;
import com.nikhil.musicapplication.service.SongService;
import com.nikhil.musicapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SongService songService;

    @PostMapping("/")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }
    @PostMapping( "/{userId}/follow/{idToFollow}")
    @ResponseStatus(HttpStatus.CREATED)
    public void followUser(@PathVariable Long userId, @PathVariable Long idToFollow) {
        userService.followUser(userId, idToFollow);
    }


    @PostMapping("/{userId}/song/{songId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void likeSong(@PathVariable Long userId, @PathVariable Long songId) {
        userService.likeSong(userId, songId);
    }

    @PostMapping("/{userId}/album/{albumId}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void likeAlbum(@PathVariable Long userId, @PathVariable Long albumId) {
        userService.likeAlbum(userId, albumId);
    }

    @GetMapping("/{userId}/songs")
    public List<Song> getSongs(@RequestParam(required = false) Genre genre,
                               @RequestParam(required = false) String year,
                               @RequestParam(required = false) Album album,
                               @RequestParam(required = false) Boolean liking,
                               @PathVariable Long userId) {
        return userService.getSongs(genre, year, album,liking, userId);
    }

    @GetMapping("/{userId}/albums")
    public List<Album> getAlbums(@RequestParam(required = false) Genre genre,
                                @RequestParam(required = false) String year,
                                 @RequestParam(required = false) Boolean liking,
                                 @PathVariable Long userId) {
        return userService.getAlbums(genre, year, liking,userId);
    }

    @GetMapping("/{userId}/playlists")
    public List<Playlist> getPlaylist(@RequestParam(required = false) Genre genre,
                                      @RequestParam(required = false) String year,
                                      @RequestParam(required = false) Album album,
                                      @PathVariable Long userId) {
        return userService.getPlaylists(genre, year, album, userId);
    }

    @GetMapping("/{userId}/followers")
    Set<User> getFollowers(@PathVariable Long userId) {
        return userService.getFollowers(userId);
    }

}
