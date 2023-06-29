package com.nikhil.musicapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist extends BaseModel{

    private String name;
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Song> songs;
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Album> albums;
    @Column(name = "artist_rank")
    private BigInteger rank;
    private BigInteger likes = BigInteger.valueOf(0);

}
