package com.tunetrove.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Song extends Reviewable {
    private String spotifyId;
    private String name;
    private Duration duration;
    @ManyToMany
    @JoinTable(
            name = "song_artist",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_artist")
    )
    private List<Artist> artists;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_album")
    private Album album;
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
