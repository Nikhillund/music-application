package com.nikhil.musicapplication.dto;

import com.nikhil.musicapplication.constant.Visibility;
import lombok.Data;

import java.util.Set;

@Data
public class PlayListEditDto {
    private String name;
    Set<Long> addSongIds;
    Set<Long> removeSongIds;
    Set<Long> addAlbumIds;
    Set<Long> removeAlbumIds;
    private Visibility visibility;
}
