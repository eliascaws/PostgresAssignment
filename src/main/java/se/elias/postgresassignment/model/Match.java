package se.elias.postgresassignment.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Match")
public class Match {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Team team1;

    @ManyToOne
    private Team team2;

    private LocalDateTime scheduledTime;

    @Enumerated(EnumType.STRING)
    private Region region;

}
