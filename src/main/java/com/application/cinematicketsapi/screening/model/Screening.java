package com.application.cinematicketsapi.screening.model;

import com.application.cinematicketsapi.cinema.model.Room;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a screening of a {@link Movie}.
 * <p>
 * Screening in this context means displaying a specified {@code Movie} in a specified {@link Room} taking place at a
 * specified time.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime beginning;

    private LocalDateTime ending;

}
