package com.nikhil.musicapplication.controller;

import com.nikhil.musicapplication.dto.PlayListEditDto;
import com.nikhil.musicapplication.dto.PlaylistDto;
import com.nikhil.musicapplication.model.Playlist;
import com.nikhil.musicapplication.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping( "/playlist")
    @ResponseStatus(HttpStatus.CREATED)
    void createPlaylist(@RequestBody PlaylistDto playlistDto) {
        playlistService.createNewPlaylist(playlistDto);
    }

    @GetMapping("/playlist/{playId}")
    Playlist getPlaylist(@PathVariable Long playId) {
        return playlistService.getPlaylist(playId);
    }

    @PatchMapping("/playlist/{playId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void editPlaylist(@PathVariable Long playId, @RequestBody PlayListEditDto playListEditDto) {
        playlistService.editPlaylist(playId, playListEditDto);
    }

    @DeleteMapping("/playlist/{playId}")
    void deletePlaylist(@PathVariable Long playId) {
        playlistService.deletePlaylist(playId);
    }

}
