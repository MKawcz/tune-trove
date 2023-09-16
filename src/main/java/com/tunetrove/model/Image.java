package com.tunetrove.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;
    @Column(columnDefinition = "text")
    private String url;
    private Integer width;
    private Integer height;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;
}
