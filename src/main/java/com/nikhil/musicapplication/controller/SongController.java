package com.nikhil.musicapplication.controller;

import com.nikhil.musicapplication.dto.SongDto;
import com.nikhil.musicapplication.model.Song;
import com.nikhil.musicapplication.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class SongController {
    private final SongService songService;

    @PostMapping( "/song")
    @ResponseStatus(HttpStatus.CREATED)
    void createSong(@RequestBody SongDto songDto) {
        songService.addSong(songDto);
    }

}
