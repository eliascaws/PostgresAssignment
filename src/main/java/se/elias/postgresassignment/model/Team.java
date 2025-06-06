package se.elias.postgresassignment.model;


import jakarta.persistence.*;

@Entity
@Table(name="Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String logoUrl;

    @Enumerated(EnumType.STRING)
    private Region region;

}
