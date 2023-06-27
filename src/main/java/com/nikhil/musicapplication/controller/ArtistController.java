package com.nikhil.musicapplication.controller;

import com.nikhil.musicapplication.dto.ArtistDto;
import com.nikhil.musicapplication.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{id}")
public class ArtistController {
    private final ArtistService artistService;
    @PostMapping( "/artist")
    @ResponseStatus(HttpStatus.CREATED)
    void createArtist(@RequestBody ArtistDto artistDto) {

    }
}
