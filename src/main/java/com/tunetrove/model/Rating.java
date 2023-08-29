package com.tunetrove.model;

import com.tunetrove.annotation.HalfNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;
    @Min(1)
    @Max(5)
    @HalfNumber
    private Double value;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reviewable reviewable;
}
