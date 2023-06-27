package com.nikhil.musicapplication.service;

import com.nikhil.musicapplication.dto.AlbumDto;
import com.nikhil.musicapplication.dto.SongDto;
import com.nikhil.musicapplication.model.Album;
import com.nikhil.musicapplication.model.Song;
import com.nikhil.musicapplication.repository.AlbumRepository;
import com.nikhil.musicapplication.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final SongService songService;

    public void addAlbum(AlbumDto albumDto) {
        Set<Song> songs = getSongs(albumDto);
        Album album = Album.builder()
                .artist(albumDto.getArtist())
                .name(albumDto.getName())
                .songs(songs)
                .genre(albumDto.getGenre())
                .build();
        songRepository.saveAll(songs);
        albumRepository.save(album);
    }

    private Set<Song> getSongs(AlbumDto albumDto) {
        Set<Song> songs = new HashSet<>();
        for(SongDto songDto : albumDto.getSongs()) {
            Song song = songService.getSong(songDto);
            songs.add(song);
        }
        return songs;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
}
