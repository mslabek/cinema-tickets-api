package com.application.cinematicketsapi.cinema.model;

import com.application.cinematicketsapi.screening.model.Screening;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a cinema room.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Row> rows = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Screening> screenings = new ArrayList<>();

    public void addRow(Row row) {
        rows.add(row);
        row.setRoom(this);
    }

    public void removeRow(Row row) {
        rows.remove(row);
        row.setRoom(null);
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
