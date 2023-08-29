package com.tunetrove.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reviewable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewable", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewable", cascade = CascadeType.ALL)
    private List<Rating> ratings;
}
