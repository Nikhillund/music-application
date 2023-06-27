package com.nikhil.musicapplication.model;

import com.nikhil.musicapplication.constant.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "dependent_set")
@EqualsAndHashCode(exclude = "dependent_set")
public class Album extends BaseModel{
    @OneToOne
    private Artist artist;
    private String name;
    @OneToMany
    private Set<Song> songs;
    public Genre genre;
    @ManyToMany(mappedBy = "albums", cascade = CascadeType.ALL)
    private Set<Playlist> playlists;
    @ManyToMany(mappedBy = "likedAlbums", cascade = CascadeType.ALL)
    private Set<User> likeByUsers;
}
