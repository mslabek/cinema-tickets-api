package com.application.cinematicketsapi.cinema.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity representing a row of {@link Seat seats} in a cinema {@link Room room}.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cinema_row")
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "row", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("column ASC")
    private Set<Seat> seats = new LinkedHashSet<>();

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setRow(this);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setRow(null);
    }

}
