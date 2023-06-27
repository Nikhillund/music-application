package com.nikhil.musicapplication.service;

import com.nikhil.musicapplication.dto.PlayListEditDto;
import com.nikhil.musicapplication.dto.PlaylistDto;
import com.nikhil.musicapplication.model.Album;
import com.nikhil.musicapplication.model.Playlist;
import com.nikhil.musicapplication.model.Song;
import com.nikhil.musicapplication.repository.AlbumRepository;
import com.nikhil.musicapplication.repository.PlaylistRepository;
import com.nikhil.musicapplication.repository.SongRepository;
import com.nikhil.musicapplication.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class PlaylistService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    public void createNewPlaylist(PlaylistDto playlistDto) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistDto.getName());
        playlist.setOwner(userRepository.findById(playlistDto.getOwnerId()).get());
        playlist.setVisibility(playlistDto.getVisibility());

        Set<Song> songSet = new HashSet<>();
        for(Long i : playlistDto.getSongIds()) {
            Optional<Song> optional = songRepository.findById(i);
            if(optional.isPresent()) {
                Song song = optional.get();
                song.getPlaylists().add(playlist);
                songSet.add(song);
            }

        }

        Set<Album> albumSet = new HashSet<>();
        for(Long i : playlistDto.getAlbumIds()) {
            Optional<Album> optional = albumRepository.findById(i);
            if(optional.isPresent()) {
                Album album = optional.get();
                album.getPlaylists().add(playlist);
                albumSet.add(album);
            }

        }
        playlist.setSongs(songSet);
        playlist.setAlbums(albumSet);

        playlistRepository.save(playlist);

    }

    public Playlist getPlaylist(Long play_id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(play_id);
        if(optionalPlaylist.isPresent()) {
            return optionalPlaylist.get();
        }
        else {
            throw new IllegalArgumentException("No Playlist present with given Id");
        }

    }

    public void editPlaylist(Long playId, PlayListEditDto playListEditDto) {
        Playlist playlist = getPlaylist(playId);

        Set<Song> songsToRemove = getSongsToRemove(playListEditDto);
        Set<Album> albumsToRemove = getAlbumsToRemove(playListEditDto);

        removeSongs(playlist, songsToRemove);
        removeAlbums(playlist, albumsToRemove);

        Set<Song> songsToAdd = getSongsToAdd(playListEditDto);
        Set<Album> albumsToAdd = getAlbumsToAdd(playListEditDto);

        addSongs(playlist, songsToAdd);
        addAlbums(playlist, albumsToAdd);

        playlistRepository.save(playlist);

    }

    private void addAlbums(Playlist playlist, Set<Album> albumsToAdd) {
        playlist.getAlbums().addAll(albumsToAdd);
    }

    private void addSongs(Playlist playlist, Set<Song> songsToAdd) {
        playlist.getSongs().addAll(songsToAdd);
    }

    private void removeAlbums(Playlist playlist, Set<Album> albumsToRemove) {
        Set<Album> currentAlbums = playlist.getAlbums();
        currentAlbums.removeIf(albumsToRemove::contains);
    }

    private void removeSongs(Playlist playlist, Set<Song> songsToRemove) {
        Set<Song> currentSongs = playlist.getSongs();
        currentSongs.removeIf(songsToRemove::contains);
    }

    private Set<Album> getAlbumsToRemove(PlayListEditDto playListEditDto) {
        Set<Album> albums = new HashSet<>();
        if(playListEditDto.getRemoveAlbumIds() != null) {
            for (Long albumId : playListEditDto.getRemoveAlbumIds()) {
                Optional<Album> album = albumRepository.findById(albumId);
                album.ifPresent(albums::add);
            }
        }
        return albums;
    }

    private Set<Album> getAlbumsToAdd(PlayListEditDto playListEditDto) {
        Set<Album> albums = new HashSet<>();
        if(playListEditDto.getAddAlbumIds() != null) {
            for (Long albumId : playListEditDto.getAddAlbumIds()) {
                Optional<Album> album = albumRepository.findById(albumId);
                album.ifPresent(albums::add);
            }
        }
        return albums;
    }

    private Set<Song> getSongsToRemove(PlayListEditDto playListEditDto) {
        Set<Song> songs = new HashSet<>();
        if(playListEditDto.getRemoveSongIds() != null) {
            for (Long songId : playListEditDto.getRemoveSongIds()) {
                Optional<Song> song = songRepository.findById(songId);
                song.ifPresent(songs::add);
            }
        }
        return songs;
    }

    private Set<Song> getSongsToAdd(PlayListEditDto playListEditDto) {
        Set<Song> songs = new HashSet<>();
        if(playListEditDto.getAddSongIds() != null) {
            for (Long songId : playListEditDto.getAddSongIds()) {
                Optional<Song> song = songRepository.findById(songId);
                song.ifPresent(songs::add);
            }
        }
        return songs;
    }

    public void deletePlaylist(Long playId) {
        playlistRepository.deleteById(playId);
    }
}
