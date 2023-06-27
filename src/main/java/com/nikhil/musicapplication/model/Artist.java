package com.nikhil.musicapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Data
@ToString(exclude = "dependent_set")
@EqualsAndHashCode(exclude = "dependent_set")
@NoArgsConstructor
@AllArgsConstructor
public class Artist extends BaseModel{

    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Song> songs;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Album> albums;
    @Column(name = "artist_rank")
    private BigInteger rank;
    private BigInteger likes = BigInteger.valueOf(0);

}
