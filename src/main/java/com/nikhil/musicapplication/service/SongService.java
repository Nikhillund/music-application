package com.nikhil.musicapplication.service;

import com.nikhil.musicapplication.dto.SongDto;
import com.nikhil.musicapplication.model.Song;
import com.nikhil.musicapplication.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class SongService {
    private final SongRepository songRepository;

    public void addSong(SongDto songDto) {
        Song song = getSong(songDto);
        songRepository.save(song);
    }

    public Song getSong(SongDto songDto) {
        return Song.builder()
                .name(songDto.getName())
                .artists(songDto.getArtists())
                .releaseYear(songDto.getYear())
                .genre(songDto.getGenre())
                .build();
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
