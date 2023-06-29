package com.nikhil.musicapplication.model;

import com.nikhil.musicapplication.constant.Visibility;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist extends BaseModel{
    private String name;
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "playlist_songs",
            joinColumns = @JoinColumn(name = "playlist_id",columnDefinition = "bigint default 0"),
            inverseJoinColumns = @JoinColumn(name = "songs_id")
    )
    private Set<Song> songs;
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "playlist_albums",
            joinColumns = @JoinColumn(name = "playlist_id", columnDefinition = "bigint default 0"),
            inverseJoinColumns = @JoinColumn(name = "albums_id")
    )
    private Set<Album> albums;
    private BigInteger likes = BigInteger.valueOf(0);
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private User owner;
    private Visibility visibility;

}
