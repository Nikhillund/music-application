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
public class Album extends BaseModel {
    @OneToOne
    private Artist artist;
    private String name;
    @EqualsAndHashCode.Exclude
    @OneToMany
    private Set<Song> songs;
    public Genre genre;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "albums", cascade = CascadeType.ALL)
    private Set<Playlist> playlists;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "likedAlbums", cascade = CascadeType.ALL)
    private Set<User> likeByUsers;
}
