package com.nikhil.musicapplication.model;

import com.nikhil.musicapplication.constant.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "dependent_set")
@EqualsAndHashCode(exclude = "dependent_set")
public class User extends BaseModel{

    private String name;
    private String email;
    private String phNumber;
    private int age;
    private Gender gender;
    private String username;
    private String password;
    @ManyToMany
    private Set<User> followers;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "song_like_by_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> likedSongs;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "album_like_by_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> likedAlbums;
}
