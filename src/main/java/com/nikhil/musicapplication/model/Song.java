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
@ToString(exclude = "dependent_set")
@EqualsAndHashCode(exclude = "dependent_set")
public class Song extends BaseModel{

    private String name;
    @ManyToMany(mappedBy = "songs")
    private Set<Artist> artists;
    private String releaseYear;
    private Genre genre;
    @ManyToMany(mappedBy = "likedSongs", cascade = CascadeType.ALL)
    private Set<User> likeByUsers;
    @ManyToMany(mappedBy = "songs", cascade = CascadeType.ALL)
    private Set<Playlist> playlists;
}
