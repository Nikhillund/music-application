package com.nikhil.musicapplication.service;

import com.nikhil.musicapplication.dto.ArtistDto;
import com.nikhil.musicapplication.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    void createArtist(ArtistDto artistDto) {

    }
}
