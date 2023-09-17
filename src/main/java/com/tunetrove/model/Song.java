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
public class Song extends Reviewable implements EntityWithImage{
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
    @OneToOne(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;
}
