package com.movieflix.movieAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    @Column(nullable = false, length = 200)
    @NotBlank(message = "tittle name  must be enter: ")
    private String tittle;
    @Column(nullable = false, length = 200)
    @NotBlank(message = "studio  must be enter: ")
    private String studio;
    @Column(nullable = false, length = 200)
    @NotBlank(message = "director name  must be enter: ")
    private String director;
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    @Column(nullable = false, length = 200)
    @NotBlank(message = "date must be enter: ")
    private Integer releaseYear;
    @Column(nullable = false)
    @NotBlank(message = "tittle must be enter: ")

    private String poster;

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", tittle='" + tittle + '\'' +
                ", studio='" + studio + '\'' +
                ", director='" + director + '\'' +
                ", movieCast=" + movieCast +
                ", releaseYear=" + releaseYear +
                ", poster='" + poster + '\'' +
                '}';
    }

}
