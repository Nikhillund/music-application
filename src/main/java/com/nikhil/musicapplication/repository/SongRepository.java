package com.nikhil.musicapplication.repository;

import com.nikhil.musicapplication.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
//    Song getBySongNameAndArtist(Id id);
}
