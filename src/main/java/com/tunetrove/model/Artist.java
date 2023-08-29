package com.tunetrove.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtist;
    private String spotifyId;
    private String name;
    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    private List<Album> albums;
    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    private List<Song> songs;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
