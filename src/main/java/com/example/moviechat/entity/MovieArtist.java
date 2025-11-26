package com.example.moviechat.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "movie_artists")
public class MovieArtist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private String role; // e.g., Actor, Producer
}