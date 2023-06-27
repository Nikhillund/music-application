package com.nikhil.musicapplication.dto;

import com.nikhil.musicapplication.constant.Genre;
import com.nikhil.musicapplication.model.Artist;
import lombok.Data;

import java.math.BigInteger;
import java.util.Set;

@Data
public class SongDto {

    private String name;
    private Set<Artist> artists;
    private String year;
    private Genre genre;
}
