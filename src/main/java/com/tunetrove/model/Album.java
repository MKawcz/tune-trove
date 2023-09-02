package com.tunetrove.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Album extends Reviewable {
    private String spotifyId;
    private String name;
    private LocalDate releaseDate;
    @ManyToMany
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_artist")
    )
    private List<Artist> artists;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    private List<Song> songs;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
