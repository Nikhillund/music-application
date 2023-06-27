package com.nikhil.musicapplication.dto;

import com.nikhil.musicapplication.constant.Visibility;
import com.nikhil.musicapplication.model.Album;
import com.nikhil.musicapplication.model.Song;
import lombok.Data;

import java.math.BigInteger;
import java.util.Set;

@Data
public class PlaylistDto {
    private String name;
    private Set<Long> songIds;
    private Set<Long> albumIds;
    private Long ownerId;
    private Visibility visibility;
}
