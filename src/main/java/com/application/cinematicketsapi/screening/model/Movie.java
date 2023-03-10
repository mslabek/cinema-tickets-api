package com.application.cinematicketsapi.screening.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a movie.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Screening> screenings = new ArrayList<>();

    public void addScreening(Screening screening) {
        screenings.add(screening);
        screening.setMovie(this);
    }

    public void removeScreening(Screening screening) {
        screenings.remove(screening);
        screening.setMovie(null);
    }

}
