package com.nikhil.musicapplication.repository;

import com.nikhil.musicapplication.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album,Long> {
}
