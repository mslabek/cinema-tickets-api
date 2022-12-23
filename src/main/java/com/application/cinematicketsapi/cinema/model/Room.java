package com.application.cinematicketsapi.cinema.model;

import com.application.cinematicketsapi.screening.model.Screening;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a cinema room.
 */
@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Screening> screenings = new ArrayList<>();

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setRoom(this);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setRoom(null);
    }

    public void addScreening(Screening screening) {
        screenings.add(screening);
        screening.setRoom(this);
    }

    public void removeScreening(Screening screening) {
        screenings.remove(screening);
        screening.setRoom(null);
    }

}
