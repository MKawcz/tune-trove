package com.tunetrove.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String spotifyId;
    private String username;
    private String emailAddress;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Rating> ratings;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image profileImage;

    public boolean hasSameProfileImage(Image profileImage) {
        return this.getProfileImage().equals(profileImage);
    }
}
