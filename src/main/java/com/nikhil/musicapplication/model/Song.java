package com.nikhil.musicapplication.model;

import com.nikhil.musicapplication.constant.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Song extends BaseModel{

    private String name;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "songs")
    private Set<Artist> artists;
    private String releaseYear;
    private Genre genre;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "likedSongs", cascade = CascadeType.ALL)
    private Set<User> likeByUsers;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "songs", cascade = CascadeType.ALL)
    private Set<Playlist> playlists;
}
