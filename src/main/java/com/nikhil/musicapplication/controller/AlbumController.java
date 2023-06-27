package com.nikhil.musicapplication.controller;

import com.nikhil.musicapplication.dto.AlbumDto;
import com.nikhil.musicapplication.model.Album;
import com.nikhil.musicapplication.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping( "/album")
    @ResponseStatus(HttpStatus.CREATED)
    void createSong(@RequestBody AlbumDto albumDto) {
        albumService.addAlbum(albumDto);
    }

//    @GetMapping("/albums")
//    public List<Album> getAllAlbums() {
//        return albumService.getAllAlbums();
//    }
}
